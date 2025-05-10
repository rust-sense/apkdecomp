package io.sentry.android.core.internal.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Choreographer;
import android.view.Display;
import android.view.FrameMetrics;
import android.view.Window;
import android.view.Window$OnFrameMetricsAvailableListener;
import io.sentry.ILogger;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SentryWrapper$$ExternalSyntheticApiModelOutline0;
import io.sentry.android.core.BuildInfoProvider;
import io.sentry.util.Objects;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class SentryFrameMetricsCollector implements Application.ActivityLifecycleCallbacks {
    private final BuildInfoProvider buildInfoProvider;
    private Choreographer choreographer;
    private Field choreographerLastFrameTimeField;
    private WeakReference<Window> currentWindow;
    private Window$OnFrameMetricsAvailableListener frameMetricsAvailableListener;
    private Handler handler;
    private boolean isAvailable;
    private long lastFrameEndNanos;
    private long lastFrameStartNanos;
    private final Map<String, FrameMetricsCollectorListener> listenerMap;
    private final ILogger logger;
    private final Set<Window> trackedWindows;
    private final WindowFrameMetricsManager windowFrameMetricsManager;
    private static final long oneSecondInNanos = TimeUnit.SECONDS.toNanos(1);
    private static final long frozenFrameThresholdNanos = TimeUnit.MILLISECONDS.toNanos(700);

    public interface FrameMetricsCollectorListener {
        void onFrameMetricCollected(long j, long j2, long j3, long j4, boolean z, boolean z2, float f);
    }

    public static boolean isFrozen(long j) {
        return j > frozenFrameThresholdNanos;
    }

    public static boolean isSlow(long j, long j2) {
        return j > j2;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public SentryFrameMetricsCollector(Context context, SentryOptions sentryOptions, BuildInfoProvider buildInfoProvider) {
        this(context, sentryOptions, buildInfoProvider, new WindowFrameMetricsManager() { // from class: io.sentry.android.core.internal.util.SentryFrameMetricsCollector.1
            @Override // io.sentry.android.core.internal.util.SentryFrameMetricsCollector.WindowFrameMetricsManager
            public /* synthetic */ void addOnFrameMetricsAvailableListener(Window window, Window$OnFrameMetricsAvailableListener window$OnFrameMetricsAvailableListener, Handler handler) {
                SentryWrapper$$ExternalSyntheticApiModelOutline0.m(window, window$OnFrameMetricsAvailableListener, handler);
            }

            @Override // io.sentry.android.core.internal.util.SentryFrameMetricsCollector.WindowFrameMetricsManager
            public /* synthetic */ void removeOnFrameMetricsAvailableListener(Window window, Window$OnFrameMetricsAvailableListener window$OnFrameMetricsAvailableListener) {
                SentryWrapper$$ExternalSyntheticApiModelOutline0.m(window, window$OnFrameMetricsAvailableListener);
            }
        });
    }

    public SentryFrameMetricsCollector(Context context, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        this(context, iLogger, buildInfoProvider, new WindowFrameMetricsManager() { // from class: io.sentry.android.core.internal.util.SentryFrameMetricsCollector.2
            @Override // io.sentry.android.core.internal.util.SentryFrameMetricsCollector.WindowFrameMetricsManager
            public /* synthetic */ void addOnFrameMetricsAvailableListener(Window window, Window$OnFrameMetricsAvailableListener window$OnFrameMetricsAvailableListener, Handler handler) {
                SentryWrapper$$ExternalSyntheticApiModelOutline0.m(window, window$OnFrameMetricsAvailableListener, handler);
            }

            @Override // io.sentry.android.core.internal.util.SentryFrameMetricsCollector.WindowFrameMetricsManager
            public /* synthetic */ void removeOnFrameMetricsAvailableListener(Window window, Window$OnFrameMetricsAvailableListener window$OnFrameMetricsAvailableListener) {
                SentryWrapper$$ExternalSyntheticApiModelOutline0.m(window, window$OnFrameMetricsAvailableListener);
            }
        });
    }

    public SentryFrameMetricsCollector(Context context, SentryOptions sentryOptions, BuildInfoProvider buildInfoProvider, WindowFrameMetricsManager windowFrameMetricsManager) {
        this(context, sentryOptions.getLogger(), buildInfoProvider, windowFrameMetricsManager);
    }

    public SentryFrameMetricsCollector(Context context, final ILogger iLogger, final BuildInfoProvider buildInfoProvider, WindowFrameMetricsManager windowFrameMetricsManager) {
        this.trackedWindows = new CopyOnWriteArraySet();
        this.listenerMap = new ConcurrentHashMap();
        this.isAvailable = false;
        this.lastFrameStartNanos = 0L;
        this.lastFrameEndNanos = 0L;
        Objects.requireNonNull(context, "The context is required");
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "Logger is required");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "BuildInfoProvider is required");
        this.windowFrameMetricsManager = (WindowFrameMetricsManager) Objects.requireNonNull(windowFrameMetricsManager, "WindowFrameMetricsManager is required");
        if ((context instanceof Application) && buildInfoProvider.getSdkInfoVersion() >= 24) {
            this.isAvailable = true;
            HandlerThread handlerThread = new HandlerThread("io.sentry.android.core.internal.util.SentryFrameMetricsCollector");
            handlerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: io.sentry.android.core.internal.util.SentryFrameMetricsCollector$$ExternalSyntheticLambda2
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread, Throwable th) {
                    ILogger.this.log(SentryLevel.ERROR, "Error during frames measurements.", th);
                }
            });
            handlerThread.start();
            this.handler = new Handler(handlerThread.getLooper());
            ((Application) context).registerActivityLifecycleCallbacks(this);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.sentry.android.core.internal.util.SentryFrameMetricsCollector$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    SentryFrameMetricsCollector.this.m834x2ab0da68(iLogger);
                }
            });
            try {
                Field declaredField = Choreographer.class.getDeclaredField("mLastFrameTimeNanos");
                this.choreographerLastFrameTimeField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                iLogger.log(SentryLevel.ERROR, "Unable to get the frame timestamp from the choreographer: ", e);
            }
            this.frameMetricsAvailableListener = new Window$OnFrameMetricsAvailableListener() { // from class: io.sentry.android.core.internal.util.SentryFrameMetricsCollector$$ExternalSyntheticLambda4
                public final void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
                    SentryFrameMetricsCollector.this.m835x94e06287(buildInfoProvider, window, frameMetrics, i);
                }
            };
        }
    }

    /* renamed from: lambda$new$1$io-sentry-android-core-internal-util-SentryFrameMetricsCollector, reason: not valid java name */
    /* synthetic */ void m834x2ab0da68(ILogger iLogger) {
        try {
            this.choreographer = Choreographer.getInstance();
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Error retrieving Choreographer instance. Slow and frozen frames will not be reported.", th);
        }
    }

    /* renamed from: lambda$new$2$io-sentry-android-core-internal-util-SentryFrameMetricsCollector, reason: not valid java name */
    /* synthetic */ void m835x94e06287(BuildInfoProvider buildInfoProvider, Window window, FrameMetrics frameMetrics, int i) {
        float refreshRate;
        Display display;
        long nanoTime = System.nanoTime();
        if (buildInfoProvider.getSdkInfoVersion() >= 30) {
            display = window.getContext().getDisplay();
            refreshRate = display.getRefreshRate();
        } else {
            refreshRate = window.getWindowManager().getDefaultDisplay().getRefreshRate();
        }
        long j = oneSecondInNanos;
        long frameCpuDuration = getFrameCpuDuration(frameMetrics);
        long max = Math.max(0L, frameCpuDuration - ((long) (j / refreshRate)));
        long frameStartTimestamp = getFrameStartTimestamp(frameMetrics);
        if (frameStartTimestamp < 0) {
            frameStartTimestamp = nanoTime - frameCpuDuration;
        }
        long max2 = Math.max(frameStartTimestamp, this.lastFrameEndNanos);
        if (max2 == this.lastFrameStartNanos) {
            return;
        }
        this.lastFrameStartNanos = max2;
        this.lastFrameEndNanos = max2 + frameCpuDuration;
        boolean isSlow = isSlow(frameCpuDuration, (long) (j / (refreshRate - 1.0f)));
        boolean z = isSlow && isFrozen(frameCpuDuration);
        Iterator<FrameMetricsCollectorListener> it = this.listenerMap.values().iterator();
        while (it.hasNext()) {
            it.next().onFrameMetricCollected(max2, this.lastFrameEndNanos, frameCpuDuration, max, isSlow, z, refreshRate);
            frameCpuDuration = frameCpuDuration;
        }
    }

    private long getFrameStartTimestamp(FrameMetrics frameMetrics) {
        long metric;
        if (this.buildInfoProvider.getSdkInfoVersion() >= 26) {
            metric = frameMetrics.getMetric(10);
            return metric;
        }
        return getLastKnownFrameStartTimeNanos();
    }

    private long getFrameCpuDuration(FrameMetrics frameMetrics) {
        long metric;
        long metric2;
        long metric3;
        long metric4;
        long metric5;
        long metric6;
        metric = frameMetrics.getMetric(0);
        metric2 = frameMetrics.getMetric(1);
        long j = metric + metric2;
        metric3 = frameMetrics.getMetric(2);
        long j2 = j + metric3;
        metric4 = frameMetrics.getMetric(3);
        long j3 = j2 + metric4;
        metric5 = frameMetrics.getMetric(4);
        long j4 = j3 + metric5;
        metric6 = frameMetrics.getMetric(5);
        return j4 + metric6;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        setCurrentWindow(activity.getWindow());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        stopTrackingWindow(activity.getWindow());
        WeakReference<Window> weakReference = this.currentWindow;
        if (weakReference == null || weakReference.get() != activity.getWindow()) {
            return;
        }
        this.currentWindow = null;
    }

    public String startCollection(FrameMetricsCollectorListener frameMetricsCollectorListener) {
        if (!this.isAvailable) {
            return null;
        }
        String uuid = UUID.randomUUID().toString();
        this.listenerMap.put(uuid, frameMetricsCollectorListener);
        trackCurrentWindow();
        return uuid;
    }

    public void stopCollection(String str) {
        if (this.isAvailable) {
            if (str != null) {
                this.listenerMap.remove(str);
            }
            WeakReference<Window> weakReference = this.currentWindow;
            Window window = weakReference != null ? weakReference.get() : null;
            if (window == null || !this.listenerMap.isEmpty()) {
                return;
            }
            stopTrackingWindow(window);
        }
    }

    private void stopTrackingWindow(Window window) {
        if (this.trackedWindows.contains(window)) {
            if (this.buildInfoProvider.getSdkInfoVersion() >= 24) {
                try {
                    this.windowFrameMetricsManager.removeOnFrameMetricsAvailableListener(window, this.frameMetricsAvailableListener);
                } catch (Exception e) {
                    this.logger.log(SentryLevel.ERROR, "Failed to remove frameMetricsAvailableListener", e);
                }
            }
            this.trackedWindows.remove(window);
        }
    }

    private void setCurrentWindow(Window window) {
        WeakReference<Window> weakReference = this.currentWindow;
        if (weakReference == null || weakReference.get() != window) {
            this.currentWindow = new WeakReference<>(window);
            trackCurrentWindow();
        }
    }

    private void trackCurrentWindow() {
        WeakReference<Window> weakReference = this.currentWindow;
        Window window = weakReference != null ? weakReference.get() : null;
        if (window == null || !this.isAvailable || this.trackedWindows.contains(window) || this.listenerMap.isEmpty() || this.buildInfoProvider.getSdkInfoVersion() < 24 || this.handler == null) {
            return;
        }
        this.trackedWindows.add(window);
        this.windowFrameMetricsManager.addOnFrameMetricsAvailableListener(window, this.frameMetricsAvailableListener, this.handler);
    }

    public long getLastKnownFrameStartTimeNanos() {
        Field field;
        Choreographer choreographer = this.choreographer;
        if (choreographer == null || (field = this.choreographerLastFrameTimeField) == null) {
            return -1L;
        }
        try {
            Long l = (Long) field.get(choreographer);
            if (l != null) {
                return l.longValue();
            }
            return -1L;
        } catch (IllegalAccessException unused) {
            return -1L;
        }
    }

    public interface WindowFrameMetricsManager {
        void addOnFrameMetricsAvailableListener(Window window, Window$OnFrameMetricsAvailableListener window$OnFrameMetricsAvailableListener, Handler handler);

        void removeOnFrameMetricsAvailableListener(Window window, Window$OnFrameMetricsAvailableListener window$OnFrameMetricsAvailableListener);

        /* renamed from: io.sentry.android.core.internal.util.SentryFrameMetricsCollector$WindowFrameMetricsManager$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
        }
    }
}

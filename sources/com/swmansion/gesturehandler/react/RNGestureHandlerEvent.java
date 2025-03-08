package com.swmansion.gesturehandler.react;

import android.view.View;
import androidx.core.util.Pools;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.react.eventbuilders.GestureHandlerEventDataBuilder;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNGestureHandlerEvent.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0016J;\u0010\u000f\u001a\u00020\u0010\"\u000e\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u00122\u0006\u0010\u0013\u001a\u0002H\u00112\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00062\u0006\u0010\u0014\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerEvent;", "Lcom/facebook/react/uimanager/events/Event;", "()V", "coalescingKey", "", "dataBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "useTopPrefixedName", "", "canCoalesce", "getCoalescingKey", "getEventData", "Lcom/facebook/react/bridge/WritableMap;", "getEventName", "", Session.JsonKeys.INIT, "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/swmansion/gesturehandler/core/GestureHandler;", "handler", "useNativeAnimatedName", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;Z)V", "onDispose", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RNGestureHandlerEvent extends Event<RNGestureHandlerEvent> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Pools.SynchronizedPool<RNGestureHandlerEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(7);
    public static final String EVENT_NAME = "onGestureHandlerEvent";
    public static final String NATIVE_ANIMATED_EVENT_NAME = "topGestureHandlerEvent";
    private static final int TOUCH_EVENTS_POOL_SIZE = 7;
    private short coalescingKey;
    private GestureHandlerEventDataBuilder<?> dataBuilder;
    private boolean useTopPrefixedName;

    public /* synthetic */ RNGestureHandlerEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return true;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return this.coalescingKey;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return this.useTopPrefixedName ? NATIVE_ANIMATED_EVENT_NAME : "onGestureHandlerEvent";
    }

    private RNGestureHandlerEvent() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends GestureHandler<T>> void init(T handler, GestureHandlerEventDataBuilder<T> dataBuilder, boolean useNativeAnimatedName) {
        View view = handler.getView();
        Intrinsics.checkNotNull(view);
        super.init(view.getId());
        this.dataBuilder = dataBuilder;
        this.useTopPrefixedName = useNativeAnimatedName;
        this.coalescingKey = handler.getEventCoalescingKey();
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        this.dataBuilder = null;
        EVENTS_POOL.release(this);
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        Companion companion = INSTANCE;
        GestureHandlerEventDataBuilder<?> gestureHandlerEventDataBuilder = this.dataBuilder;
        Intrinsics.checkNotNull(gestureHandlerEventDataBuilder);
        return companion.createEventData(gestureHandlerEventDataBuilder);
    }

    /* compiled from: RNGestureHandlerEvent.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eJ;\u0010\u000f\u001a\u00020\u0005\"\u000e\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00112\u0006\u0010\u0012\u001a\u0002H\u00102\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerEvent$Companion;", "", "()V", "EVENTS_POOL", "Landroidx/core/util/Pools$SynchronizedPool;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerEvent;", "EVENT_NAME", "", "NATIVE_ANIMATED_EVENT_NAME", "TOUCH_EVENTS_POOL_SIZE", "", "createEventData", "Lcom/facebook/react/bridge/WritableMap;", "dataBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "obtain", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/swmansion/gesturehandler/core/GestureHandler;", "handler", "useTopPrefixedName", "", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;Z)Lcom/swmansion/gesturehandler/react/RNGestureHandlerEvent;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ RNGestureHandlerEvent obtain$default(Companion companion, GestureHandler gestureHandler, GestureHandlerEventDataBuilder gestureHandlerEventDataBuilder, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.obtain(gestureHandler, gestureHandlerEventDataBuilder, z);
        }

        public final <T extends GestureHandler<T>> RNGestureHandlerEvent obtain(T handler, GestureHandlerEventDataBuilder<T> dataBuilder, boolean useTopPrefixedName) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(dataBuilder, "dataBuilder");
            RNGestureHandlerEvent rNGestureHandlerEvent = (RNGestureHandlerEvent) RNGestureHandlerEvent.EVENTS_POOL.acquire();
            if (rNGestureHandlerEvent == null) {
                rNGestureHandlerEvent = new RNGestureHandlerEvent(null);
            }
            rNGestureHandlerEvent.init((RNGestureHandlerEvent) handler, (GestureHandlerEventDataBuilder<RNGestureHandlerEvent>) dataBuilder, useTopPrefixedName);
            return rNGestureHandlerEvent;
        }

        public final WritableMap createEventData(GestureHandlerEventDataBuilder<?> dataBuilder) {
            Intrinsics.checkNotNullParameter(dataBuilder, "dataBuilder");
            WritableMap createMap = Arguments.createMap();
            Intrinsics.checkNotNull(createMap);
            dataBuilder.buildEventData(createMap);
            Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
            return createMap;
        }
    }
}

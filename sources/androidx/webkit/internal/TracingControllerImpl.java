package androidx.webkit.internal;

import android.webkit.TracingConfig;
import androidx.webkit.TracingConfig;
import androidx.webkit.TracingController;
import com.facebook.hermes.intl.Intl$$ExternalSyntheticApiModelOutline0;
import java.io.OutputStream;
import java.util.Collection;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.TracingControllerBoundaryInterface;

/* loaded from: classes.dex */
public class TracingControllerImpl extends TracingController {
    private TracingControllerBoundaryInterface mBoundaryInterface;
    private android.webkit.TracingController mFrameworksImpl;

    public TracingControllerImpl() {
        android.webkit.TracingController tracingController;
        WebViewFeatureInternal webViewFeatureInternal = WebViewFeatureInternal.TRACING_CONTROLLER_BASIC_USAGE;
        if (webViewFeatureInternal.isSupportedByFramework()) {
            tracingController = android.webkit.TracingController.getInstance();
            this.mFrameworksImpl = tracingController;
            this.mBoundaryInterface = null;
        } else {
            if (webViewFeatureInternal.isSupportedByWebView()) {
                this.mFrameworksImpl = null;
                this.mBoundaryInterface = WebViewGlueCommunicator.getFactory().getTracingController();
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    private android.webkit.TracingController getFrameworksImpl() {
        android.webkit.TracingController tracingController;
        if (this.mFrameworksImpl == null) {
            tracingController = android.webkit.TracingController.getInstance();
            this.mFrameworksImpl = tracingController;
        }
        return this.mFrameworksImpl;
    }

    private TracingControllerBoundaryInterface getBoundaryInterface() {
        if (this.mBoundaryInterface == null) {
            this.mBoundaryInterface = WebViewGlueCommunicator.getFactory().getTracingController();
        }
        return this.mBoundaryInterface;
    }

    @Override // androidx.webkit.TracingController
    public boolean isTracing() {
        boolean isTracing;
        WebViewFeatureInternal webViewFeatureInternal = WebViewFeatureInternal.TRACING_CONTROLLER_BASIC_USAGE;
        if (webViewFeatureInternal.isSupportedByFramework()) {
            isTracing = getFrameworksImpl().isTracing();
            return isTracing;
        }
        if (webViewFeatureInternal.isSupportedByWebView()) {
            return getBoundaryInterface().isTracing();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    @Override // androidx.webkit.TracingController
    public void start(TracingConfig tracingConfig) {
        TracingConfig.Builder addCategories;
        TracingConfig.Builder addCategories2;
        TracingConfig.Builder tracingMode;
        android.webkit.TracingConfig build;
        if (tracingConfig == null) {
            throw new IllegalArgumentException("Tracing config must be non null");
        }
        WebViewFeatureInternal webViewFeatureInternal = WebViewFeatureInternal.TRACING_CONTROLLER_BASIC_USAGE;
        if (webViewFeatureInternal.isSupportedByFramework()) {
            addCategories = Intl$$ExternalSyntheticApiModelOutline0.m216m().addCategories(tracingConfig.getPredefinedCategories());
            addCategories2 = addCategories.addCategories((Collection<String>) tracingConfig.getCustomIncludedCategories());
            tracingMode = addCategories2.setTracingMode(tracingConfig.getTracingMode());
            build = tracingMode.build();
            getFrameworksImpl().start(build);
            return;
        }
        if (webViewFeatureInternal.isSupportedByWebView()) {
            getBoundaryInterface().start(tracingConfig.getPredefinedCategories(), tracingConfig.getCustomIncludedCategories(), tracingConfig.getTracingMode());
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    @Override // androidx.webkit.TracingController
    public boolean stop(OutputStream outputStream, Executor executor) {
        boolean stop;
        WebViewFeatureInternal webViewFeatureInternal = WebViewFeatureInternal.TRACING_CONTROLLER_BASIC_USAGE;
        if (webViewFeatureInternal.isSupportedByFramework()) {
            stop = getFrameworksImpl().stop(outputStream, executor);
            return stop;
        }
        if (webViewFeatureInternal.isSupportedByWebView()) {
            return getBoundaryInterface().stop(outputStream, executor);
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }
}

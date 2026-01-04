package androidx.webkit.internal;

import android.webkit.ServiceWorkerController;
import android.webkit.ServiceWorkerWebSettings;
import androidx.webkit.ServiceWorkerClientCompat;
import androidx.webkit.ServiceWorkerControllerCompat;
import androidx.webkit.ServiceWorkerWebSettingsCompat;
import org.chromium.support_lib_boundary.ServiceWorkerControllerBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

/* loaded from: classes.dex */
public class ServiceWorkerControllerImpl extends ServiceWorkerControllerCompat {
    private ServiceWorkerControllerBoundaryInterface mBoundaryInterface;
    private ServiceWorkerController mFrameworksImpl;
    private final ServiceWorkerWebSettingsCompat mWebSettings;

    @Override // androidx.webkit.ServiceWorkerControllerCompat
    public ServiceWorkerWebSettingsCompat getServiceWorkerWebSettings() {
        return this.mWebSettings;
    }

    public ServiceWorkerControllerImpl() {
        ServiceWorkerController serviceWorkerController;
        ServiceWorkerWebSettings serviceWorkerWebSettings;
        WebViewFeatureInternal webViewFeatureInternal = WebViewFeatureInternal.SERVICE_WORKER_BASIC_USAGE;
        if (webViewFeatureInternal.isSupportedByFramework()) {
            serviceWorkerController = ServiceWorkerController.getInstance();
            this.mFrameworksImpl = serviceWorkerController;
            this.mBoundaryInterface = null;
            serviceWorkerWebSettings = serviceWorkerController.getServiceWorkerWebSettings();
            this.mWebSettings = new ServiceWorkerWebSettingsImpl(serviceWorkerWebSettings);
            return;
        }
        if (webViewFeatureInternal.isSupportedByWebView()) {
            this.mFrameworksImpl = null;
            ServiceWorkerControllerBoundaryInterface serviceWorkerController2 = WebViewGlueCommunicator.getFactory().getServiceWorkerController();
            this.mBoundaryInterface = serviceWorkerController2;
            this.mWebSettings = new ServiceWorkerWebSettingsImpl(serviceWorkerController2.getServiceWorkerWebSettings());
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    private ServiceWorkerController getFrameworksImpl() {
        ServiceWorkerController serviceWorkerController;
        if (this.mFrameworksImpl == null) {
            serviceWorkerController = ServiceWorkerController.getInstance();
            this.mFrameworksImpl = serviceWorkerController;
        }
        return this.mFrameworksImpl;
    }

    private ServiceWorkerControllerBoundaryInterface getBoundaryInterface() {
        if (this.mBoundaryInterface == null) {
            this.mBoundaryInterface = WebViewGlueCommunicator.getFactory().getServiceWorkerController();
        }
        return this.mBoundaryInterface;
    }

    @Override // androidx.webkit.ServiceWorkerControllerCompat
    public void setServiceWorkerClient(ServiceWorkerClientCompat serviceWorkerClientCompat) {
        WebViewFeatureInternal webViewFeatureInternal = WebViewFeatureInternal.SERVICE_WORKER_BASIC_USAGE;
        if (webViewFeatureInternal.isSupportedByFramework()) {
            getFrameworksImpl().setServiceWorkerClient(new FrameworkServiceWorkerClient(serviceWorkerClientCompat));
        } else {
            if (webViewFeatureInternal.isSupportedByWebView()) {
                getBoundaryInterface().setServiceWorkerClient(BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new ServiceWorkerClientAdapter(serviceWorkerClientCompat)));
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }
}

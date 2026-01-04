package expo.modules.gl;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.os.AsyncTask;
import android.util.Log;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.JavaScriptContextProvider;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.gl.cpp.EXGL;
import expo.modules.kotlin.Promise;
import io.sentry.protocol.ViewHierarchyNode;
import java.lang.ref.WeakReference;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* loaded from: classes2.dex */
public class GLContext {
    private EGL10 mEGL;
    private EGLConfig mEGLConfig;
    private EGLContext mEGLContext;
    private EGLDisplay mEGLDisplay;
    private EGLSurface mEGLSurface;
    private int mEXGLCtxId = -1;
    private BlockingQueue<Runnable> mEventQueue = new LinkedBlockingQueue();
    private GLThread mGLThread;
    private final GLObjectManagerModule mManager;

    public int getContextId() {
        return this.mEXGLCtxId;
    }

    public EGLConfig getEGLConfig() {
        return this.mEGLConfig;
    }

    public GLContext(GLObjectManagerModule gLObjectManagerModule) {
        this.mManager = gLObjectManagerModule;
    }

    public boolean isHeadless() {
        GLThread gLThread = this.mGLThread;
        return gLThread == null || gLThread.mSurfaceTexture == null;
    }

    public void runAsync(Runnable runnable) {
        this.mEventQueue.add(runnable);
    }

    public void initialize(SurfaceTexture surfaceTexture, final Boolean bool, final Runnable runnable) {
        if (this.mGLThread != null) {
            return;
        }
        GLThread gLThread = new GLThread(surfaceTexture);
        this.mGLThread = gLThread;
        gLThread.start();
        this.mEXGLCtxId = EXGL.EXGLContextCreate();
        ModuleRegistry legacyModuleRegistry = this.mManager.getAppContext().getLegacyModuleRegistry();
        final UIManager uIManager = (UIManager) legacyModuleRegistry.getModule(UIManager.class);
        final JavaScriptContextProvider javaScriptContextProvider = (JavaScriptContextProvider) legacyModuleRegistry.getModule(JavaScriptContextProvider.class);
        uIManager.runOnClientCodeQueueThread(new Runnable() { // from class: expo.modules.gl.GLContext.1
            @Override // java.lang.Runnable
            public void run() {
                long javaScriptContextRef = javaScriptContextProvider.getJavaScriptContextRef();
                synchronized (uIManager) {
                    if (javaScriptContextRef != 0) {
                        EXGL.EXGLContextPrepare(javaScriptContextRef, GLContext.this.mEXGLCtxId, this);
                    }
                }
                if (bool.booleanValue()) {
                    uIManager.runOnUiQueueThread(new Runnable() { // from class: expo.modules.gl.GLContext.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            EXGL.EXGLContextPrepareWorklet(GLContext.this.mEXGLCtxId);
                            GLContext.this.mManager.saveContext(this);
                            runnable.run();
                        }
                    });
                } else {
                    GLContext.this.mManager.saveContext(this);
                    runnable.run();
                }
            }
        });
    }

    public void flush() {
        runAsync(new Runnable() { // from class: expo.modules.gl.GLContext.2
            @Override // java.lang.Runnable
            public void run() {
                if (GLContext.this.mEXGLCtxId > 0) {
                    EXGL.EXGLContextFlush(GLContext.this.mEXGLCtxId);
                    if (GLContext.this.isHeadless() || !EXGL.EXGLContextNeedsRedraw(GLContext.this.mEXGLCtxId)) {
                        return;
                    }
                    GLContext gLContext = GLContext.this;
                    if (!gLContext.swapBuffers(gLContext.mEGLSurface)) {
                        Log.e("EXGL", "Cannot swap buffers!");
                    }
                    EXGL.EXGLContextDrawEnded(GLContext.this.mEXGLCtxId);
                }
            }
        });
    }

    public boolean swapBuffers(EGLSurface eGLSurface) {
        return this.mEGL.eglSwapBuffers(this.mEGLDisplay, eGLSurface);
    }

    public boolean makeCurrent(EGLSurface eGLSurface) {
        return this.mEGL.eglMakeCurrent(this.mEGLDisplay, eGLSurface, eGLSurface, this.mEGLContext);
    }

    public EGLSurface createSurface(EGLConfig eGLConfig, Object obj) {
        if (obj == null) {
            return this.mEGL.eglCreatePbufferSurface(this.mEGLDisplay, eGLConfig, new int[]{12375, 1, 12374, 1, 12344});
        }
        return this.mEGL.eglCreateWindowSurface(this.mEGLDisplay, eGLConfig, obj, null);
    }

    public boolean destroySurface(EGLSurface eGLSurface) {
        return this.mEGL.eglDestroySurface(this.mEGLDisplay, eGLSurface);
    }

    public void destroy() {
        if (this.mGLThread != null) {
            this.mManager.deleteContextWithId(this.mEXGLCtxId);
            EXGL.EXGLContextDestroy(this.mEXGLCtxId);
            try {
                this.mGLThread.interrupt();
                this.mGLThread.join();
            } catch (InterruptedException e) {
                Log.e("EXGL", "Can't interrupt GL thread.", e);
            }
            this.mGLThread = null;
        }
    }

    public Map<String, Object> getViewportRect() {
        int[] iArr = new int[4];
        GLES30.glGetIntegerv(2978, iArr, 0);
        HashMap hashMap = new HashMap();
        hashMap.put(ViewHierarchyNode.JsonKeys.X, Integer.valueOf(iArr[0]));
        hashMap.put(ViewHierarchyNode.JsonKeys.Y, Integer.valueOf(iArr[1]));
        hashMap.put("width", Integer.valueOf(iArr[2]));
        hashMap.put("height", Integer.valueOf(iArr[3]));
        return hashMap;
    }

    public void takeSnapshot(final Map<String, Object> map, final Context context, final Promise promise) {
        flush();
        runAsync(new Runnable() { // from class: expo.modules.gl.GLContext.3
            @Override // java.lang.Runnable
            public void run() {
                Map<String, Object> viewportRect = map.containsKey("rect") ? (Map) map.get("rect") : GLContext.this.getViewportRect();
                Boolean valueOf = Boolean.valueOf(map.containsKey("flip") && ((Boolean) map.get("flip")).booleanValue());
                String str = map.containsKey("format") ? (String) map.get("format") : null;
                int doubleValue = map.containsKey("compress") ? (int) (((Double) map.get("compress")).doubleValue() * 100.0d) : 100;
                int castNumberToInt = GLContext.this.castNumberToInt(viewportRect.get(ViewHierarchyNode.JsonKeys.X));
                int castNumberToInt2 = GLContext.this.castNumberToInt(viewportRect.get(ViewHierarchyNode.JsonKeys.Y));
                int castNumberToInt3 = GLContext.this.castNumberToInt(viewportRect.get("width"));
                int castNumberToInt4 = GLContext.this.castNumberToInt(viewportRect.get("height"));
                int[] iArr = new int[1];
                GLES30.glGetIntegerv(36006, iArr, 0);
                int i = GLContext.this.isHeadless() ? iArr[0] : 0;
                Map map2 = map.containsKey("framebuffer") ? (Map) map.get("framebuffer") : null;
                if (map2 != null && map2.containsKey("id")) {
                    i = EXGL.EXGLContextGetObject(GLContext.this.mEXGLCtxId, Integer.valueOf(GLContext.this.castNumberToInt(map2.get("id"))).intValue());
                }
                GLES30.glBindFramebuffer(36160, i);
                int[] iArr2 = new int[castNumberToInt3 * castNumberToInt4];
                IntBuffer wrap = IntBuffer.wrap(iArr2);
                wrap.position(0);
                GLES30.glReadPixels(castNumberToInt, castNumberToInt2, castNumberToInt3, castNumberToInt4, 6408, 5121, wrap);
                GLES30.glBindFramebuffer(36160, iArr[0]);
                new TakeSnapshot(context, castNumberToInt3, castNumberToInt4, valueOf.booleanValue(), str, doubleValue, iArr2, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        });
    }

    private static class TakeSnapshot extends AsyncTask<Void, Void, Void> {
        private final int mCompress;
        private final WeakReference<Context> mContext;
        private final int[] mDataArray;
        private final boolean mFlip;
        private final String mFormat;
        private final int mHeight;
        private final Promise mPromise;
        private final int mWidth;

        TakeSnapshot(Context context, int i, int i2, boolean z, String str, int i3, int[] iArr, Promise promise) {
            this.mContext = new WeakReference<>(context);
            this.mWidth = i;
            this.mHeight = i2;
            this.mFlip = z;
            this.mFormat = str;
            this.mCompress = i3;
            this.mDataArray = iArr;
            this.mPromise = promise;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0086  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00c4  */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Void doInBackground(java.lang.Void... r11) {
            /*
                Method dump skipped, instructions count: 244
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.gl.GLContext.TakeSnapshot.doInBackground(java.lang.Void[]):java.lang.Void");
        }
    }

    private class GLThread extends Thread {
        private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
        private SurfaceTexture mSurfaceTexture;

        GLThread(SurfaceTexture surfaceTexture) {
            this.mSurfaceTexture = surfaceTexture;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            initEGL();
            while (true) {
                try {
                    makeEGLContextCurrent();
                    ((Runnable) GLContext.this.mEventQueue.take()).run();
                    checkEGLError();
                } catch (InterruptedException unused) {
                    deinitEGL();
                    return;
                }
            }
        }

        private EGLContext createGLContext(int i, EGLConfig eGLConfig) {
            return GLContext.this.mEGL.eglCreateContext(GLContext.this.mEGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{EGL_CONTEXT_CLIENT_VERSION, i, 12344});
        }

        private void initEGL() {
            GLContext.this.mEGL = (EGL10) EGLContext.getEGL();
            GLContext gLContext = GLContext.this;
            gLContext.mEGLDisplay = gLContext.mEGL.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (GLContext.this.mEGLDisplay == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed " + GLUtils.getEGLErrorString(GLContext.this.mEGL.eglGetError()));
            }
            if (!GLContext.this.mEGL.eglInitialize(GLContext.this.mEGLDisplay, new int[2])) {
                throw new RuntimeException("eglInitialize failed " + GLUtils.getEGLErrorString(GLContext.this.mEGL.eglGetError()));
            }
            int[] iArr = new int[1];
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            if (!GLContext.this.mEGL.eglChooseConfig(GLContext.this.mEGLDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12326, 8, 12344}, eGLConfigArr, 1, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig failed " + GLUtils.getEGLErrorString(GLContext.this.mEGL.eglGetError()));
            }
            if (iArr[0] > 0) {
                GLContext.this.mEGLConfig = eGLConfigArr[0];
            }
            if (GLContext.this.mEGLConfig == null) {
                throw new RuntimeException("eglConfig not initialized");
            }
            GLContext gLContext2 = GLContext.this;
            gLContext2.mEGLContext = createGLContext(3, gLContext2.mEGLConfig);
            if (GLContext.this.mEGLContext == null || GLContext.this.mEGLContext == EGL10.EGL_NO_CONTEXT) {
                GLContext gLContext3 = GLContext.this;
                gLContext3.mEGLContext = createGLContext(2, gLContext3.mEGLConfig);
            }
            checkEGLError();
            GLContext gLContext4 = GLContext.this;
            gLContext4.mEGLSurface = gLContext4.createSurface(gLContext4.mEGLConfig, this.mSurfaceTexture);
            checkEGLError();
            if (GLContext.this.mEGLSurface == null || GLContext.this.mEGLSurface == EGL10.EGL_NO_SURFACE) {
                throw new RuntimeException("eglCreateWindowSurface failed " + GLUtils.getEGLErrorString(GLContext.this.mEGL.eglGetError()));
            }
            makeEGLContextCurrent();
            checkEGLError();
            EGL14.eglSurfaceAttrib(EGL14.eglGetCurrentDisplay(), EGL14.eglGetCurrentSurface(12377), 12435, 12436);
            checkEGLError();
        }

        private void deinitEGL() {
            makeEGLContextCurrent();
            GLContext gLContext = GLContext.this;
            gLContext.destroySurface(gLContext.mEGLSurface);
            checkEGLError();
            GLContext.this.mEGL.eglDestroyContext(GLContext.this.mEGLDisplay, GLContext.this.mEGLContext);
            checkEGLError();
            GLContext.this.mEGL.eglTerminate(GLContext.this.mEGLDisplay);
            checkEGLError();
        }

        private void makeEGLContextCurrent() {
            if (GLContext.this.mEGLContext.equals(GLContext.this.mEGL.eglGetCurrentContext()) && GLContext.this.mEGLSurface.equals(GLContext.this.mEGL.eglGetCurrentSurface(12377))) {
                return;
            }
            checkEGLError();
            GLContext gLContext = GLContext.this;
            if (!gLContext.makeCurrent(gLContext.mEGLSurface)) {
                throw new RuntimeException("eglMakeCurrent failed " + GLUtils.getEGLErrorString(GLContext.this.mEGL.eglGetError()));
            }
            checkEGLError();
        }

        private void checkEGLError() {
            int eglGetError = GLContext.this.mEGL.eglGetError();
            if (eglGetError != 12288) {
                Log.e("EXGL", "EGL error = 0x" + Integer.toHexString(eglGetError));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int castNumberToInt(Object obj) {
        if (obj instanceof Double) {
            return ((Double) obj).intValue();
        }
        return ((Integer) obj).intValue();
    }
}

package com.shopify.reactnative.skia;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Choreographer;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReactContext;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/* loaded from: classes2.dex */
public class PlatformContext {
    private final ReactContext mContext;
    private final HybridData mHybridData;
    private boolean _drawLoopActive = false;
    private boolean _isPaused = false;
    private final String TAG = "PlatformContext";
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private native HybridData initHybrid(float f);

    /* JADX INFO: Access modifiers changed from: private */
    public native void notifyDrawLoop();

    /* JADX INFO: Access modifiers changed from: private */
    public native void notifyTaskReady();

    public void endDrawLoop() {
        if (this._drawLoopActive) {
            this._drawLoopActive = false;
        }
    }

    public PlatformContext(ReactContext reactContext) {
        this.mContext = reactContext;
        this.mHybridData = initHybrid(reactContext.getResources().getDisplayMetrics().density);
    }

    private byte[] getStreamAsBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr, 0, 4096);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postFrameLoop() {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: com.shopify.reactnative.skia.PlatformContext.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                if (PlatformContext.this._drawLoopActive) {
                    Choreographer.getInstance().postFrameCallback(this);
                }
                if (PlatformContext.this._isPaused) {
                    return;
                }
                PlatformContext.this.notifyDrawLoop();
            }
        });
    }

    public void notifyTaskReadyOnMainThread() {
        this.mainHandler.post(new Runnable() { // from class: com.shopify.reactnative.skia.PlatformContext.2
            @Override // java.lang.Runnable
            public void run() {
                PlatformContext.this.notifyTaskReady();
            }
        });
    }

    Object takeScreenshotFromViewTag(int i) {
        return ViewScreenshotService.makeViewScreenshotFromTag(this.mContext, i);
    }

    public void raise(final String str) {
        this.mainHandler.post(new Runnable() { // from class: com.shopify.reactnative.skia.PlatformContext.3
            @Override // java.lang.Runnable
            public void run() {
                PlatformContext.this.mContext.handleException(new Exception(str));
            }
        });
    }

    public void beginDrawLoop() {
        if (this._drawLoopActive) {
            return;
        }
        this._drawLoopActive = true;
        this.mainHandler.post(new Runnable() { // from class: com.shopify.reactnative.skia.PlatformContext.4
            @Override // java.lang.Runnable
            public void run() {
                PlatformContext.this.postFrameLoop();
            }
        });
    }

    public byte[] getJniStreamFromSource(String str) throws IOException {
        int identifier = this.mContext.getResources().getIdentifier(str, "drawable", this.mContext.getPackageName());
        if (identifier == 0) {
            identifier = this.mContext.getResources().getIdentifier(str, "raw", this.mContext.getPackageName());
        }
        if (identifier != 0) {
            return getStreamAsBytes(this.mContext.getResources().openRawResource(identifier));
        }
        try {
            URI uri = new URI(str);
            if (uri.getScheme() == null) {
                throw new Exception("Invalid URI scheme");
            }
            URL url = uri.toURL();
            url.openConnection().connect();
            return getStreamAsBytes(new BufferedInputStream(url.openStream(), 8192));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (URISyntaxException e3) {
            e3.printStackTrace();
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    void onPause() {
        Log.i("PlatformContext", "Paused");
        this._isPaused = true;
    }

    void onResume() {
        this._isPaused = false;
        Log.i("PlatformContext", "Resume");
        if (this._drawLoopActive) {
            this.mainHandler.post(new Runnable() { // from class: com.shopify.reactnative.skia.PlatformContext.5
                @Override // java.lang.Runnable
                public void run() {
                    PlatformContext.this.postFrameLoop();
                }
            });
        }
    }

    protected void finalize() throws Throwable {
        this.mHybridData.resetNative();
        super.finalize();
    }
}

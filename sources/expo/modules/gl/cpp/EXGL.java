package expo.modules.gl.cpp;

import com.facebook.soloader.SoLoader;

/* loaded from: classes2.dex */
public class EXGL {
    public static native int EXGLContextCreate();

    public static native int EXGLContextCreateObject(int i);

    public static native void EXGLContextDestroy(int i);

    public static native void EXGLContextDestroyObject(int i, int i2);

    public static native void EXGLContextDrawEnded(int i);

    public static native void EXGLContextFlush(int i);

    public static native int EXGLContextGetObject(int i, int i2);

    public static native void EXGLContextMapObject(int i, int i2, int i3);

    public static native boolean EXGLContextNeedsRedraw(int i);

    public static native void EXGLContextPrepare(long j, int i, Object obj);

    public static native void EXGLContextPrepareWorklet(int i);

    static {
        SoLoader.loadLibrary("expo-gl");
    }
}

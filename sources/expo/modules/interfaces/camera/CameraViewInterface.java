package expo.modules.interfaces.camera;

import android.graphics.SurfaceTexture;

/* loaded from: classes2.dex */
public interface CameraViewInterface {
    int[] getPreviewSizeAsArray();

    void setPreviewTexture(SurfaceTexture surfaceTexture);
}

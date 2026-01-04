package expo.modules.gl;

import android.graphics.SurfaceTexture;
import android.opengl.GLES30;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.reanimated.layoutReanimation.Snapshot;
import expo.modules.gl.cpp.EXGL;
import expo.modules.interfaces.camera.CameraViewInterface;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes2.dex */
public class GLCameraObject extends GLObject implements SurfaceTexture.OnFrameAvailableListener {
    private static String fragmentShaderSource = "#extension GL_OES_EGL_image_external : require\nprecision highp float;uniform samplerExternalOES cameraTexture;varying vec2 coords;void main() {  gl_FragColor = texture2D(cameraTexture, coords);}";
    private static String vertexShaderSource = "precision highp float;attribute vec4 position;uniform mat4 transformMatrix;varying vec2 coords;void main() {  vec2 clipSpace = (1.0 - 2.0 * position.xy);  coords = (transformMatrix * position).xy;  gl_Position = vec4(clipSpace, 0.0, 1.0);}";
    private SurfaceTexture mCameraSurfaceTexture;
    private CameraViewInterface mCameraView;
    private int mDestTexture;
    private int mExtTexture;
    private int mFramebuffer;
    private GLContext mGLContext;
    private int mProgram;
    private int mTextureHeight;
    private int mTextureWidth;
    private int mVertexArray;
    private int mVertexBuffer;
    private float[] textureCoords;

    GLCameraObject(GLContext gLContext, CameraViewInterface cameraViewInterface) {
        super(gLContext.getContextId());
        this.mTextureWidth = -1;
        this.mTextureHeight = -1;
        this.textureCoords = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f};
        this.mGLContext = gLContext;
        this.mCameraView = cameraViewInterface;
        int[] iArr = new int[2];
        int[] iArr2 = new int[1];
        int[] iArr3 = new int[1];
        int[] iArr4 = new int[1];
        int glCreateProgram = GLES30.glCreateProgram();
        int glCreateShader = GLES30.glCreateShader(35633);
        int glCreateShader2 = GLES30.glCreateShader(35632);
        GLES30.glShaderSource(glCreateShader, vertexShaderSource);
        GLES30.glShaderSource(glCreateShader2, fragmentShaderSource);
        GLES30.glCompileShader(glCreateShader);
        GLES30.glCompileShader(glCreateShader2);
        GLES30.glAttachShader(glCreateProgram, glCreateShader);
        GLES30.glAttachShader(glCreateProgram, glCreateShader2);
        GLES30.glLinkProgram(glCreateProgram);
        GLES30.glGenTextures(2, iArr, 0);
        GLES30.glGenFramebuffers(1, iArr2, 0);
        GLES30.glGenBuffers(1, iArr3, 0);
        GLES30.glGenVertexArrays(1, iArr4, 0);
        this.mProgram = glCreateProgram;
        this.mExtTexture = iArr[0];
        this.mDestTexture = iArr[1];
        this.mFramebuffer = iArr2[0];
        this.mVertexBuffer = iArr3[0];
        this.mVertexArray = iArr4[0];
        EXGL.EXGLContextMapObject(this.exglCtxId, this.exglObjId, this.mDestTexture);
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mExtTexture);
        this.mCameraSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        this.mCameraView.setPreviewTexture(this.mCameraSurfaceTexture);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FloatBuffer setupVertexBuffer() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.textureCoords.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(this.textureCoords);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        int[] previewSizeAsArray = this.mCameraView.getPreviewSizeAsArray();
        final int i = previewSizeAsArray[0];
        final int i2 = previewSizeAsArray[1];
        this.mGLContext.runAsync(new Runnable() { // from class: expo.modules.gl.GLCameraObject.1
            @Override // java.lang.Runnable
            public void run() {
                if (GLCameraObject.this.mCameraSurfaceTexture == null) {
                    return;
                }
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                int[] iArr3 = new int[1];
                int[] iArr4 = new int[1];
                int[] iArr5 = new int[1];
                int[] iArr6 = new int[4];
                float[] fArr = new float[16];
                GLES30.glGetIntegerv(36006, iArr, 0);
                GLES30.glGetIntegerv(35725, iArr2, 0);
                GLES30.glGetIntegerv(34016, iArr3, 0);
                GLES30.glGetIntegerv(32873, iArr4, 0);
                GLES30.glGetIntegerv(34229, iArr5, 0);
                GLES30.glGetIntegerv(2978, iArr6, 0);
                GLES30.glUseProgram(GLCameraObject.this.mProgram);
                GLES30.glBindVertexArray(GLCameraObject.this.mVertexArray);
                GLES30.glBindFramebuffer(36009, GLCameraObject.this.mFramebuffer);
                int glGetAttribLocation = GLES30.glGetAttribLocation(GLCameraObject.this.mProgram, ViewProps.POSITION);
                int glGetUniformLocation = GLES30.glGetUniformLocation(GLCameraObject.this.mProgram, Snapshot.TRANSFORM_MATRIX);
                int glGetUniformLocation2 = GLES30.glGetUniformLocation(GLCameraObject.this.mProgram, "cameraTexture");
                if (GLCameraObject.this.mTextureWidth == -1) {
                    GLES30.glBindTexture(36197, GLCameraObject.this.mExtTexture);
                    GLES30.glTexParameteri(36197, 10242, 33071);
                    GLES30.glTexParameteri(36197, 10243, 33071);
                    GLES30.glTexParameteri(36197, 10240, 9729);
                    GLES30.glTexParameteri(36197, 10241, 9729);
                    GLES30.glBindTexture(3553, GLCameraObject.this.mDestTexture);
                    GLES30.glTexParameteri(3553, 10242, 33071);
                    GLES30.glTexParameteri(3553, 10243, 33071);
                    GLES30.glTexParameteri(3553, 10240, 9729);
                    GLES30.glTexParameteri(3553, 10241, 9729);
                    GLES30.glFramebufferTexture2D(36009, 36064, 3553, GLCameraObject.this.mDestTexture, 0);
                    FloatBuffer floatBuffer = GLCameraObject.this.setupVertexBuffer();
                    GLES30.glBindBuffer(34962, GLCameraObject.this.mVertexBuffer);
                    GLES30.glBufferData(34962, GLCameraObject.this.textureCoords.length * 4, floatBuffer, 35044);
                    GLES30.glEnableVertexAttribArray(glGetAttribLocation);
                    GLES30.glVertexAttribPointer(glGetAttribLocation, 2, 5126, false, 8, 0);
                }
                if (GLCameraObject.this.mTextureWidth != i || GLCameraObject.this.mTextureHeight != i2) {
                    GLCameraObject.this.mTextureWidth = i;
                    GLCameraObject.this.mTextureHeight = i2;
                    GLES30.glBindTexture(3553, GLCameraObject.this.mDestTexture);
                    GLES30.glTexImage2D(3553, 0, 6408, GLCameraObject.this.mTextureWidth, GLCameraObject.this.mTextureHeight, 0, 6408, 5121, null);
                    GLCameraObject.this.mCameraSurfaceTexture.setDefaultBufferSize(i, i2);
                }
                try {
                    GLCameraObject.this.mCameraSurfaceTexture.updateTexImage();
                    GLCameraObject.this.mCameraSurfaceTexture.getTransformMatrix(fArr);
                    GLES30.glBindTexture(36197, GLCameraObject.this.mExtTexture);
                    GLES30.glUniform1i(glGetUniformLocation2, iArr3[0] - 33984);
                    GLES30.glUniformMatrix4fv(glGetUniformLocation, 1, false, fArr, 0);
                    GLES30.glViewport(0, 0, GLCameraObject.this.mTextureWidth, GLCameraObject.this.mTextureHeight);
                    GLES30.glDrawArrays(4, 0, GLCameraObject.this.textureCoords.length / 2);
                    GLES30.glViewport(iArr6[0], iArr6[1], iArr6[2], iArr6[3]);
                    GLES30.glBindTexture(3553, iArr4[0]);
                    GLES30.glBindFramebuffer(36009, iArr[0]);
                    GLES30.glBindVertexArray(iArr5[0]);
                    GLES30.glUseProgram(iArr2[0]);
                } catch (IllegalStateException unused) {
                }
            }
        });
    }

    @Override // expo.modules.gl.GLObject
    void destroy() {
        CameraViewInterface cameraViewInterface = this.mCameraView;
        if (cameraViewInterface != null) {
            cameraViewInterface.setPreviewTexture(null);
            this.mCameraView = null;
        }
        SurfaceTexture surfaceTexture = this.mCameraSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mCameraSurfaceTexture = null;
        }
        super.destroy();
    }
}

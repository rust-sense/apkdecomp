package expo.modules.gl;

import expo.modules.gl.cpp.EXGL;

/* loaded from: classes2.dex */
public class GLObject {
    protected int exglCtxId;
    protected int exglObjId;

    int getEXGLObjId() {
        return this.exglObjId;
    }

    GLObject(int i) {
        this.exglCtxId = i;
        this.exglObjId = EXGL.EXGLContextCreateObject(i);
    }

    void destroy() {
        EXGL.EXGLContextDestroyObject(this.exglCtxId, this.exglObjId);
    }
}

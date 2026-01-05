package expo.modules.gl;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

/* compiled from: GLObjectManagerModule.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/gl/InvalidGLContextException;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-gl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class InvalidGLContextException extends CodedException {
    public InvalidGLContextException() {
        super("GLContext not found for given context id", null, 2, null);
    }
}

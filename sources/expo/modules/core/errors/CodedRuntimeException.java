package expo.modules.core.errors;

import expo.modules.core.interfaces.CodedThrowable;

/* loaded from: classes2.dex */
public abstract class CodedRuntimeException extends RuntimeException implements CodedThrowable {
    @Override // expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "ERR_UNSPECIFIED_ANDROID_EXCEPTION";
    }

    public CodedRuntimeException(String str) {
        super(str);
    }

    public CodedRuntimeException(Throwable th) {
        super(th);
    }

    public CodedRuntimeException(String str, Throwable th) {
        super(str, th);
    }
}

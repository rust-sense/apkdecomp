package org.apache.commons.lang3;

/* loaded from: classes3.dex */
public class NotImplementedException extends UnsupportedOperationException {
    private static final long serialVersionUID = 20131021;
    private final String code;

    public String getCode() {
        return this.code;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NotImplementedException(String str) {
        this(str, (String) null);
    }

    public NotImplementedException(Throwable th) {
        this(th, (String) null);
    }

    public NotImplementedException(String str, Throwable th) {
        this(str, th, null);
    }

    public NotImplementedException(String str, String str2) {
        super(str);
        this.code = str2;
    }

    public NotImplementedException(Throwable th, String str) {
        super(th);
        this.code = str;
    }

    public NotImplementedException(String str, Throwable th, String str2) {
        super(str, th);
        this.code = str2;
    }
}

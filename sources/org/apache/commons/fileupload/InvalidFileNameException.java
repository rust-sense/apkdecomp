package org.apache.commons.fileupload;

/* loaded from: classes3.dex */
public class InvalidFileNameException extends RuntimeException {
    private static final long serialVersionUID = 7922042602454350470L;
    private final String name;

    public String getName() {
        return this.name;
    }

    public InvalidFileNameException(String str, String str2) {
        super(str2);
        this.name = str;
    }
}

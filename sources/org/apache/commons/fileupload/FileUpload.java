package org.apache.commons.fileupload;

/* loaded from: classes3.dex */
public class FileUpload extends FileUploadBase {
    private FileItemFactory fileItemFactory;

    @Override // org.apache.commons.fileupload.FileUploadBase
    public FileItemFactory getFileItemFactory() {
        return this.fileItemFactory;
    }

    @Override // org.apache.commons.fileupload.FileUploadBase
    public void setFileItemFactory(FileItemFactory fileItemFactory) {
        this.fileItemFactory = fileItemFactory;
    }

    public FileUpload() {
    }

    public FileUpload(FileItemFactory fileItemFactory) {
        this.fileItemFactory = fileItemFactory;
    }
}

package androidx.webkit;

/* loaded from: classes.dex */
public class WebMessageCompat {
    private String mData;
    private WebMessagePortCompat[] mPorts;

    public String getData() {
        return this.mData;
    }

    public WebMessagePortCompat[] getPorts() {
        return this.mPorts;
    }

    public WebMessageCompat(String str) {
        this.mData = str;
    }

    public WebMessageCompat(String str, WebMessagePortCompat[] webMessagePortCompatArr) {
        this.mData = str;
        this.mPorts = webMessagePortCompatArr;
    }
}

package javax.servlet.http;

/* loaded from: classes2.dex */
public interface HttpUpgradeHandler {
    void destroy();

    void init(WebConnection webConnection);
}

package io.sentry;

import com.facebook.hermes.intl.Constants;
import com.google.android.gms.common.Scopes;
import io.sentry.cache.EnvelopeCache;
import org.apache.commons.fileupload.FileUploadBase;

/* loaded from: classes2.dex */
public enum DataCategory {
    All("__all__"),
    Default(Constants.COLLATION_DEFAULT),
    Error(com.google.firebase.messaging.Constants.IPC_BUNDLE_KEY_SEND_ERROR),
    Session(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE),
    Attachment(FileUploadBase.ATTACHMENT),
    Monitor("monitor"),
    Profile(Scopes.PROFILE),
    MetricBucket("metric_bucket"),
    Transaction("transaction"),
    Security("security"),
    UserReport("user_report"),
    Unknown("unknown");

    private final String category;

    public String getCategory() {
        return this.category;
    }

    DataCategory(String str) {
        this.category = str;
    }
}

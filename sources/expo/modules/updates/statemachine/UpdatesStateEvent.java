package expo.modules.updates.statemachine;

import com.google.firebase.messaging.Constants;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: UpdatesStateEvent.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000b\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u000b\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c¨\u0006\u001d"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "", "type", "Lexpo/modules/updates/statemachine/UpdatesStateEventType;", "(Lexpo/modules/updates/statemachine/UpdatesStateEventType;)V", "getType", "()Lexpo/modules/updates/statemachine/UpdatesStateEventType;", "Check", "CheckCompleteUnavailable", "CheckCompleteWithRollback", "CheckCompleteWithUpdate", "CheckError", "Download", "DownloadComplete", "DownloadCompleteWithRollback", "DownloadCompleteWithUpdate", "DownloadError", "Restart", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$Check;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$CheckCompleteUnavailable;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$CheckCompleteWithRollback;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$CheckCompleteWithUpdate;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$CheckError;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$Download;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$DownloadComplete;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$DownloadCompleteWithRollback;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$DownloadCompleteWithUpdate;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$DownloadError;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent$Restart;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class UpdatesStateEvent {
    private final UpdatesStateEventType type;

    public /* synthetic */ UpdatesStateEvent(UpdatesStateEventType updatesStateEventType, DefaultConstructorMarker defaultConstructorMarker) {
        this(updatesStateEventType);
    }

    public final UpdatesStateEventType getType() {
        return this.type;
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$Check;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "()V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Check extends UpdatesStateEvent {
        public Check() {
            super(UpdatesStateEventType.Check, null);
        }
    }

    private UpdatesStateEvent(UpdatesStateEventType updatesStateEventType) {
        this.type = updatesStateEventType;
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$Download;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "()V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Download extends UpdatesStateEvent {
        public Download() {
            super(UpdatesStateEventType.Download, null);
        }
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$CheckError;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "errorMessage", "", "(Ljava/lang/String;)V", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Lexpo/modules/updates/statemachine/UpdatesStateError;", "getError", "()Lexpo/modules/updates/statemachine/UpdatesStateError;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CheckError extends UpdatesStateEvent {
        private final String errorMessage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CheckError(String errorMessage) {
            super(UpdatesStateEventType.CheckError, null);
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            this.errorMessage = errorMessage;
        }

        public final UpdatesStateError getError() {
            return new UpdatesStateError(this.errorMessage);
        }
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$DownloadError;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "errorMessage", "", "(Ljava/lang/String;)V", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Lexpo/modules/updates/statemachine/UpdatesStateError;", "getError", "()Lexpo/modules/updates/statemachine/UpdatesStateError;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DownloadError extends UpdatesStateEvent {
        private final String errorMessage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadError(String errorMessage) {
            super(UpdatesStateEventType.DownloadError, null);
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            this.errorMessage = errorMessage;
        }

        public final UpdatesStateError getError() {
            return new UpdatesStateError(this.errorMessage);
        }
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$CheckCompleteUnavailable;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "()V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CheckCompleteUnavailable extends UpdatesStateEvent {
        public CheckCompleteUnavailable() {
            super(UpdatesStateEventType.CheckCompleteUnavailable, null);
        }
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$CheckCompleteWithUpdate;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "manifest", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getManifest", "()Lorg/json/JSONObject;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CheckCompleteWithUpdate extends UpdatesStateEvent {
        private final JSONObject manifest;

        public final JSONObject getManifest() {
            return this.manifest;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CheckCompleteWithUpdate(JSONObject manifest) {
            super(UpdatesStateEventType.CheckCompleteAvailable, null);
            Intrinsics.checkNotNullParameter(manifest, "manifest");
            this.manifest = manifest;
        }
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$CheckCompleteWithRollback;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "commitTime", "Ljava/util/Date;", "(Ljava/util/Date;)V", "getCommitTime", "()Ljava/util/Date;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CheckCompleteWithRollback extends UpdatesStateEvent {
        private final Date commitTime;

        public final Date getCommitTime() {
            return this.commitTime;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CheckCompleteWithRollback(Date commitTime) {
            super(UpdatesStateEventType.CheckCompleteAvailable, null);
            Intrinsics.checkNotNullParameter(commitTime, "commitTime");
            this.commitTime = commitTime;
        }
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$DownloadComplete;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "()V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DownloadComplete extends UpdatesStateEvent {
        public DownloadComplete() {
            super(UpdatesStateEventType.DownloadComplete, null);
        }
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$DownloadCompleteWithUpdate;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "manifest", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getManifest", "()Lorg/json/JSONObject;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DownloadCompleteWithUpdate extends UpdatesStateEvent {
        private final JSONObject manifest;

        public final JSONObject getManifest() {
            return this.manifest;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadCompleteWithUpdate(JSONObject manifest) {
            super(UpdatesStateEventType.DownloadComplete, null);
            Intrinsics.checkNotNullParameter(manifest, "manifest");
            this.manifest = manifest;
        }
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$DownloadCompleteWithRollback;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "()V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DownloadCompleteWithRollback extends UpdatesStateEvent {
        public DownloadCompleteWithRollback() {
            super(UpdatesStateEventType.DownloadComplete, null);
        }
    }

    /* compiled from: UpdatesStateEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEvent$Restart;", "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "()V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Restart extends UpdatesStateEvent {
        public Restart() {
            super(UpdatesStateEventType.Restart, null);
        }
    }
}

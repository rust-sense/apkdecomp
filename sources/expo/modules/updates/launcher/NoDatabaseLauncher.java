package expo.modules.updates.launcher;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.loader.EmbeddedLoader;
import java.io.File;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.FileUtils;

/* compiled from: NoDatabaseLauncher.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u001c\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0002R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012¨\u0006\u001a"}, d2 = {"Lexpo/modules/updates/launcher/NoDatabaseLauncher;", "Lexpo/modules/updates/launcher/Launcher;", "context", "Landroid/content/Context;", "fatalException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Landroid/content/Context;Ljava/lang/Exception;)V", "bundleAssetName", "", "getBundleAssetName", "()Ljava/lang/String;", "isUsingEmbeddedAssets", "", "()Z", "launchAssetFile", "", "getLaunchAssetFile", "()Ljava/lang/Void;", "launchedUpdate", "getLaunchedUpdate", "localAssetFiles", "getLocalAssetFiles", "writeErrorToLog", "", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NoDatabaseLauncher implements Launcher {
    private static final String ERROR_LOG_FILENAME = "expo-error.log";
    private final String bundleAssetName;
    private final boolean isUsingEmbeddedAssets;
    private final Void launchAssetFile;
    private final Void launchedUpdate;
    private final Void localAssetFiles;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "NoDatabaseLauncher";

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public NoDatabaseLauncher(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // expo.modules.updates.launcher.Launcher
    public String getBundleAssetName() {
        return this.bundleAssetName;
    }

    public Void getLaunchAssetFile() {
        return this.launchAssetFile;
    }

    public Void getLaunchedUpdate() {
        return this.launchedUpdate;
    }

    public Void getLocalAssetFiles() {
        return this.localAssetFiles;
    }

    @Override // expo.modules.updates.launcher.Launcher
    /* renamed from: isUsingEmbeddedAssets, reason: from getter */
    public boolean getIsUsingEmbeddedAssets() {
        return this.isUsingEmbeddedAssets;
    }

    public NoDatabaseLauncher(final Context context, final Exception exc) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.bundleAssetName = EmbeddedLoader.BARE_BUNDLE_FILENAME;
        this.isUsingEmbeddedAssets = true;
        if (exc != null) {
            AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.launcher.NoDatabaseLauncher$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    NoDatabaseLauncher._init_$lambda$0(NoDatabaseLauncher.this, context, exc);
                }
            });
        }
    }

    public /* synthetic */ NoDatabaseLauncher(Context context, Exception exc, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : exc);
    }

    @Override // expo.modules.updates.launcher.Launcher
    public /* bridge */ /* synthetic */ String getLaunchAssetFile() {
        return (String) getLaunchAssetFile();
    }

    @Override // expo.modules.updates.launcher.Launcher
    public /* bridge */ /* synthetic */ UpdateEntity getLaunchedUpdate() {
        return (UpdateEntity) getLaunchedUpdate();
    }

    @Override // expo.modules.updates.launcher.Launcher
    /* renamed from: getLocalAssetFiles, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Map mo753getLocalAssetFiles() {
        return (Map) getLocalAssetFiles();
    }

    private final void writeErrorToLog(Context context, Exception fatalException) {
        try {
            FileUtils.writeStringToFile(new File(context.getFilesDir(), ERROR_LOG_FILENAME), fatalException.toString(), "UTF-8", true);
        } catch (Exception e) {
            Log.e(TAG, "Failed to write fatal error to log", e);
        }
    }

    /* compiled from: NoDatabaseLauncher.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lexpo/modules/updates/launcher/NoDatabaseLauncher$Companion;", "", "()V", "ERROR_LOG_FILENAME", "", "TAG", "kotlin.jvm.PlatformType", "consumeErrorLog", "context", "Landroid/content/Context;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String consumeErrorLog(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                File file = new File(context.getFilesDir(), NoDatabaseLauncher.ERROR_LOG_FILENAME);
                if (!file.exists()) {
                    return null;
                }
                String readFileToString = FileUtils.readFileToString(file, "UTF-8");
                file.delete();
                return readFileToString;
            } catch (Exception e) {
                Log.e(NoDatabaseLauncher.TAG, "Failed to read error log", e);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(NoDatabaseLauncher this$0, Context context, Exception exc) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        this$0.writeErrorToLog(context, exc);
    }
}

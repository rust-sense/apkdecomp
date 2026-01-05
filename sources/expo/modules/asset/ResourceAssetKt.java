package expo.modules.asset;

import android.content.Context;
import android.content.res.Resources;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ResourceAsset.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u0006\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\t"}, d2 = {"findResourceId", "", "context", "Landroid/content/Context;", "assetName", "", "(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/Integer;", "openAssetResourceStream", "Ljava/io/InputStream;", "expo-asset_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ResourceAssetKt {
    public static final InputStream openAssetResourceStream(Context context, String assetName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetName, "assetName");
        Resources resources = context.getResources();
        Integer findResourceId = findResourceId(context, assetName);
        if (findResourceId == null) {
            throw new Resources.NotFoundException(assetName);
        }
        InputStream openRawResource = resources.openRawResource(findResourceId.intValue());
        Intrinsics.checkNotNullExpressionValue(openRawResource, "openRawResource(...)");
        return openRawResource;
    }

    private static final Integer findResourceId(Context context, String str) {
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        Integer valueOf = Integer.valueOf(resources.getIdentifier(str, "raw", packageName));
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        if (valueOf != null) {
            return valueOf;
        }
        Integer valueOf2 = Integer.valueOf(resources.getIdentifier(str, "drawable", packageName));
        if (valueOf2.intValue() != 0) {
            return valueOf2;
        }
        return null;
    }
}

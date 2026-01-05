package expo.modules.webbrowser;

import android.content.Intent;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsService;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomTabsActivitiesHelper.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\b\u0010\u0004\u001a\u00020\u0003H\u0002\u001a9\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u0007*\b\u0012\u0004\u0012\u0002H\b0\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00070\u000bH\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"DUMMY_URL", "", "createDefaultCustomTabsIntent", "Landroid/content/Intent;", "createDefaultCustomTabsServiceIntent", "mapToDistinctArrayList", "Ljava/util/ArrayList;", "R", ExifInterface.GPS_DIRECTION_TRUE, "", "mapper", "Lkotlin/Function1;", "expo-web-browser_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CustomTabsActivitiesHelperKt {
    private static final String DUMMY_URL = "https://expo.dev";

    private static final <T, R> ArrayList<R> mapToDistinctArrayList(Collection<? extends T> collection, Function1<? super T, ? extends R> function1) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(function1.invoke(it.next()));
        }
        return new ArrayList<>(linkedHashSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Intent createDefaultCustomTabsIntent() {
        CustomTabsIntent build = new CustomTabsIntent.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        Intent intent = build.intent;
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        intent.setData(Uri.parse(DUMMY_URL));
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Intent createDefaultCustomTabsServiceIntent() {
        Intent intent = new Intent();
        intent.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
        return intent;
    }
}

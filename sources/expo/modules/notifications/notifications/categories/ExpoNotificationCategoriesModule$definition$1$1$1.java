package expo.modules.notifications.notifications.categories;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.service.NotificationsService;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: ExpoNotificationCategoriesModule.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class ExpoNotificationCategoriesModule$definition$1$1$1 extends Lambda implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ ExpoNotificationCategoriesModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ExpoNotificationCategoriesModule$definition$1$1$1(Promise promise, ExpoNotificationCategoriesModule expoNotificationCategoriesModule) {
        super(2);
        this.$promise = promise;
        this.this$0 = expoNotificationCategoriesModule;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Bundle bundle) {
        invoke(num.intValue(), bundle);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        ArrayList parcelableArrayList = bundle != null ? bundle.getParcelableArrayList(NotificationsService.NOTIFICATION_CATEGORIES_KEY) : null;
        if (i == 0 && parcelableArrayList != null) {
            this.$promise.resolve(this.this$0.serializeCategories(parcelableArrayList));
        } else {
            this.$promise.reject("ERR_CATEGORIES_FETCH_FAILED", "A list of notification categories could not be fetched.", null);
        }
    }
}

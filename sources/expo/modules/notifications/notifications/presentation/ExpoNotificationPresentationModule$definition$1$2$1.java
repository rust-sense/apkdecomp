package expo.modules.notifications.notifications.presentation;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.service.NotificationsService;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: ExpoNotificationPresentationModule.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class ExpoNotificationPresentationModule$definition$1$2$1 extends Lambda implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ ExpoNotificationPresentationModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ExpoNotificationPresentationModule$definition$1$2$1(Promise promise, ExpoNotificationPresentationModule expoNotificationPresentationModule) {
        super(2);
        this.$promise = promise;
        this.this$0 = expoNotificationPresentationModule;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Bundle bundle) {
        invoke(num.intValue(), bundle);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        ArrayList parcelableArrayList = bundle != null ? bundle.getParcelableArrayList(NotificationsService.NOTIFICATIONS_KEY) : null;
        if (i == 0 && parcelableArrayList != null) {
            this.$promise.resolve(this.this$0.serializeNotifications(parcelableArrayList));
        } else {
            Serializable serializable = bundle != null ? bundle.getSerializable("exception") : null;
            this.$promise.reject("ERR_NOTIFICATIONS_FETCH_FAILED", "A list of displayed notifications could not be fetched.", serializable instanceof Exception ? (Exception) serializable : null);
        }
    }
}

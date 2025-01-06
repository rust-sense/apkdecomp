package expo.modules.notifications.service.delegates;

import android.content.Context;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.service.interfaces.CategoriesDelegate;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoCategoriesDelegate.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoCategoriesDelegate;", "Lexpo/modules/notifications/service/interfaces/CategoriesDelegate;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mStore", "Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationCategoriesStore;", "deleteCategory", "", "identifier", "", "getCategories", "", "Lexpo/modules/notifications/notifications/model/NotificationCategory;", "setCategory", "category", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoCategoriesDelegate implements CategoriesDelegate {
    private final Context context;
    private final SharedPreferencesNotificationCategoriesStore mStore;

    protected final Context getContext() {
        return this.context;
    }

    public ExpoCategoriesDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mStore = new SharedPreferencesNotificationCategoriesStore(context);
    }

    @Override // expo.modules.notifications.service.interfaces.CategoriesDelegate
    public Collection<NotificationCategory> getCategories() {
        return this.mStore.getAllNotificationCategories();
    }

    @Override // expo.modules.notifications.service.interfaces.CategoriesDelegate
    public NotificationCategory setCategory(NotificationCategory category) {
        Intrinsics.checkNotNullParameter(category, "category");
        return this.mStore.saveNotificationCategory(category);
    }

    @Override // expo.modules.notifications.service.interfaces.CategoriesDelegate
    public boolean deleteCategory(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        return this.mStore.removeNotificationCategory(identifier);
    }
}

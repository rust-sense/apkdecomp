package expo.modules.notifications.notifications.presentation.builders;

import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import expo.modules.notifications.notifications.model.NotificationAction;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.notifications.model.TextInputNotificationAction;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.delegates.SharedPreferencesNotificationCategoriesStore;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class CategoryAwareNotificationBuilder extends ExpoNotificationBuilder {
    protected SharedPreferencesNotificationCategoriesStore mStore;

    public CategoryAwareNotificationBuilder(Context context, SharedPreferencesNotificationCategoriesStore sharedPreferencesNotificationCategoriesStore) {
        super(context);
        this.mStore = sharedPreferencesNotificationCategoriesStore;
    }

    @Override // expo.modules.notifications.notifications.presentation.builders.ExpoNotificationBuilder, expo.modules.notifications.notifications.presentation.builders.ChannelAwareNotificationBuilder
    protected NotificationCompat.Builder createBuilder() {
        NotificationCompat.Builder createBuilder = super.createBuilder();
        NotificationContent notificationContent = getNotificationContent();
        String categoryId = notificationContent.getCategoryId();
        if (categoryId != null) {
            addActionsToBuilder(createBuilder, categoryId);
        }
        if (notificationContent.getBadgeCount() != null) {
            createBuilder.setNumber(notificationContent.getBadgeCount().intValue());
        }
        return createBuilder;
    }

    protected void addActionsToBuilder(NotificationCompat.Builder builder, String str) {
        List<NotificationAction> emptyList = Collections.emptyList();
        try {
            NotificationCategory notificationCategory = this.mStore.getNotificationCategory(str);
            if (notificationCategory != null) {
                emptyList = notificationCategory.getActions();
            }
        } catch (IOException | ClassNotFoundException e) {
            Log.e("expo-notifications", String.format("Could not read category with identifier: %s. %s", str, e.getMessage()));
            e.printStackTrace();
        }
        for (NotificationAction notificationAction : emptyList) {
            if (notificationAction instanceof TextInputNotificationAction) {
                builder.addAction(buildTextInputAction((TextInputNotificationAction) notificationAction));
            } else {
                builder.addAction(buildButtonAction(notificationAction));
            }
        }
    }

    protected NotificationCompat.Action buildButtonAction(NotificationAction notificationAction) {
        return new NotificationCompat.Action.Builder(super.getIcon(), notificationAction.getTitle(), NotificationsService.INSTANCE.createNotificationResponseIntent(getContext(), getNotification(), notificationAction)).build();
    }

    protected NotificationCompat.Action buildTextInputAction(TextInputNotificationAction textInputNotificationAction) {
        PendingIntent createNotificationResponseIntent = NotificationsService.INSTANCE.createNotificationResponseIntent(getContext(), getNotification(), textInputNotificationAction);
        return new NotificationCompat.Action.Builder(super.getIcon(), textInputNotificationAction.getTitle(), createNotificationResponseIntent).addRemoteInput(new RemoteInput.Builder(NotificationsService.USER_TEXT_RESPONSE_KEY).setLabel(textInputNotificationAction.getPlaceholder()).build()).build();
    }
}

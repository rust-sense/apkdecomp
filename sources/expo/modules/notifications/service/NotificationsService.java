package expo.modules.notifications.service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.core.app.RemoteInput;
import androidx.exifinterface.media.ExifInterface;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationAction;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.notifications.model.TextInputNotificationAction;
import expo.modules.notifications.notifications.model.TextInputNotificationResponse;
import expo.modules.notifications.service.delegates.ExpoCategoriesDelegate;
import expo.modules.notifications.service.delegates.ExpoHandlingDelegate;
import expo.modules.notifications.service.delegates.ExpoPresentationDelegate;
import expo.modules.notifications.service.delegates.ExpoSchedulingDelegate;
import expo.modules.notifications.service.interfaces.CategoriesDelegate;
import expo.modules.notifications.service.interfaces.HandlingDelegate;
import expo.modules.notifications.service.interfaces.PresentationDelegate;
import expo.modules.notifications.service.interfaces.SchedulingDelegate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationsService.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0016\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010 \u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010!\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\"\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010#\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006%"}, d2 = {"Lexpo/modules/notifications/service/NotificationsService;", "Landroid/content/BroadcastReceiver;", "()V", "getCategoriesDelegate", "Lexpo/modules/notifications/service/interfaces/CategoriesDelegate;", "context", "Landroid/content/Context;", "getHandlingDelegate", "Lexpo/modules/notifications/service/interfaces/HandlingDelegate;", "getPresentationDelegate", "Lexpo/modules/notifications/service/interfaces/PresentationDelegate;", "getSchedulingDelegate", "Lexpo/modules/notifications/service/interfaces/SchedulingDelegate;", "handleIntent", "", "intent", "Landroid/content/Intent;", "onDeleteCategory", "Landroid/os/Bundle;", "onDismissAllNotifications", "onDismissNotifications", "onGetAllPresentedNotifications", "onGetAllScheduledNotifications", "onGetCategories", "onGetScheduledNotification", "onNotificationTriggered", "onNotificationsDropped", "onPresentNotification", "onReceive", "onReceiveNotification", "onReceiveNotificationResponse", "onRemoveAllScheduledNotifications", "onRemoveScheduledNotifications", "onScheduleNotification", "onSetCategory", "onSetupScheduledNotifications", "Companion", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class NotificationsService extends BroadcastReceiver {
    private static final String DELETE_CATEGORY_TYPE = "deleteCategory";
    private static final String DISMISS_ALL_TYPE = "dismissAll";
    private static final String DISMISS_SELECTED_TYPE = "dismissSelected";
    private static final String DROPPED_TYPE = "dropped";
    public static final int ERROR_CODE = 1;
    public static final String EVENT_TYPE_KEY = "type";
    public static final String EXCEPTION_KEY = "exception";
    private static final String GET_ALL_DISPLAYED_TYPE = "getAllDisplayed";
    private static final String GET_ALL_SCHEDULED_TYPE = "getAllScheduled";
    private static final String GET_CATEGORIES_TYPE = "getCategories";
    private static final String GET_SCHEDULED_TYPE = "getScheduled";
    public static final String IDENTIFIERS_KEY = "identifiers";
    public static final String IDENTIFIER_KEY = "identifier";
    public static final String NOTIFICATIONS_KEY = "notifications";
    public static final String NOTIFICATION_ACTION_KEY = "notificationAction";
    public static final String NOTIFICATION_BEHAVIOR_KEY = "notificationBehavior";
    public static final String NOTIFICATION_CATEGORIES_KEY = "notificationCategories";
    public static final String NOTIFICATION_CATEGORY_KEY = "notificationCategory";
    public static final String NOTIFICATION_EVENT_ACTION = "expo.modules.notifications.NOTIFICATION_EVENT";
    public static final String NOTIFICATION_KEY = "notification";
    public static final String NOTIFICATION_REQUESTS_KEY = "notificationRequests";
    public static final String NOTIFICATION_REQUEST_KEY = "notificationRequest";
    public static final String NOTIFICATION_RESPONSE_KEY = "notificationResponse";
    private static final String PRESENT_TYPE = "present";
    public static final String RECEIVER_KEY = "receiver";
    private static final String RECEIVE_RESPONSE_TYPE = "receiveResponse";
    private static final String RECEIVE_TYPE = "receive";
    private static final String REMOVE_ALL_TYPE = "removeAll";
    private static final String REMOVE_SELECTED_TYPE = "removeSelected";
    private static final String SCHEDULE_TYPE = "schedule";
    private static final String SET_CATEGORY_TYPE = "setCategory";
    public static final String SUCCEEDED_KEY = "succeeded";
    public static final int SUCCESS_CODE = 0;
    public static final String TEXT_INPUT_NOTIFICATION_RESPONSE_KEY = "textInputNotificationResponse";
    private static final String TRIGGER_TYPE = "trigger";
    public static final String USER_TEXT_RESPONSE_KEY = "userTextResponse";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<String> SETUP_ACTIONS = CollectionsKt.listOf((Object[]) new String[]{"android.intent.action.BOOT_COMPLETED", "android.intent.action.REBOOT", "android.intent.action.MY_PACKAGE_REPLACED", "android.intent.action.QUICKBOOT_POWERON", "com.htc.intent.action.QUICKBOOT_POWERON"});

    /* compiled from: NotificationsService.kt */
    @Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102J\u001e\u00103\u001a\u0002042\u0006\u0010/\u001a\u0002002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208J\u0016\u00109\u001a\u0002042\u0006\u0010/\u001a\u0002002\u0006\u0010:\u001a\u00020\u0004J\"\u0010;\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010:\u001a\u00020\u00042\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J-\u0010?\u001a\u00020<2\u0006\u0010/\u001a\u0002002\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00040A2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>¢\u0006\u0002\u0010BJ\u001a\u0010C\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u0016\u0010D\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010E\u001a\u00020.J\u0018\u0010F\u001a\u0004\u0018\u00010G2\u0006\u0010/\u001a\u0002002\u0006\u0010E\u001a\u00020.J\u001a\u0010H\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u001a\u0010I\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\u001a\u0010K\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u000e\u0010L\u001a\u00020M2\u0006\u0010E\u001a\u00020.J\u0010\u0010N\u001a\u0004\u0018\u00010M2\u0006\u0010E\u001a\u00020.J\"\u0010O\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010:\u001a\u00020\u00042\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\b\u0010P\u001a\u00020QH\u0004J\u0010\u0010R\u001a\u00020Q2\u0006\u0010:\u001a\u00020\u0004H\u0004J\u000e\u0010S\u001a\u00020<2\u0006\u0010/\u001a\u000200J\u0012\u0010T\u001a\u0004\u0018\u00010U2\u0006\u0010V\u001a\u00020WH\u0002J.\u0010X\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u00105\u001a\u0002062\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010Z2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\"\u0010[\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u00105\u001a\u0002062\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u001a\u0010\\\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\"\u0010]\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010:\u001a\u00020\u00042\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J(\u0010^\u001a\u00020<2\u0006\u0010/\u001a\u0002002\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00040_2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\"\u0010`\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010a\u001a\u00020b2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\"\u0010c\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010d\u001a\u00020e2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u0016\u0010f\u001a\u00020<2\u0006\u0010E\u001a\u00020.2\u0006\u0010g\u001a\u00020MJ-\u0010h\u001a\u0004\u0018\u0001Hi\"\u0004\b\u0000\u0010i2\f\u0010j\u001a\b\u0012\u0004\u0012\u0002Hi0k2\b\u0010l\u001a\u0004\u0018\u00010UH\u0002¢\u0006\u0002\u0010mR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040$¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006n"}, d2 = {"Lexpo/modules/notifications/service/NotificationsService$Companion;", "", "()V", "DELETE_CATEGORY_TYPE", "", "DISMISS_ALL_TYPE", "DISMISS_SELECTED_TYPE", "DROPPED_TYPE", "ERROR_CODE", "", "EVENT_TYPE_KEY", "EXCEPTION_KEY", "GET_ALL_DISPLAYED_TYPE", "GET_ALL_SCHEDULED_TYPE", "GET_CATEGORIES_TYPE", "GET_SCHEDULED_TYPE", "IDENTIFIERS_KEY", "IDENTIFIER_KEY", "NOTIFICATIONS_KEY", "NOTIFICATION_ACTION_KEY", "NOTIFICATION_BEHAVIOR_KEY", "NOTIFICATION_CATEGORIES_KEY", "NOTIFICATION_CATEGORY_KEY", "NOTIFICATION_EVENT_ACTION", "NOTIFICATION_KEY", "NOTIFICATION_REQUESTS_KEY", "NOTIFICATION_REQUEST_KEY", "NOTIFICATION_RESPONSE_KEY", "PRESENT_TYPE", "RECEIVER_KEY", "RECEIVE_RESPONSE_TYPE", "RECEIVE_TYPE", "REMOVE_ALL_TYPE", "REMOVE_SELECTED_TYPE", "SCHEDULE_TYPE", "SETUP_ACTIONS", "", "getSETUP_ACTIONS", "()Ljava/util/List;", "SET_CATEGORY_TYPE", "SUCCEEDED_KEY", "SUCCESS_CODE", "TEXT_INPUT_NOTIFICATION_RESPONSE_KEY", "TRIGGER_TYPE", "USER_TEXT_RESPONSE_KEY", "createNotificationResponseBroadcastIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "extras", "Landroid/os/Bundle;", "createNotificationResponseIntent", "Landroid/app/PendingIntent;", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "action", "Lexpo/modules/notifications/notifications/model/NotificationAction;", "createNotificationTrigger", "identifier", NotificationsService.DELETE_CATEGORY_TYPE, "", NotificationsService.RECEIVER_KEY, "Landroid/os/ResultReceiver;", "dismiss", NotificationsService.IDENTIFIERS_KEY, "", "(Landroid/content/Context;[Ljava/lang/String;Landroid/os/ResultReceiver;)V", NotificationsService.DISMISS_ALL_TYPE, "doWork", "intent", "findDesignatedBroadcastReceiver", "Landroid/content/pm/ActivityInfo;", "getAllPresented", "getAllScheduledNotifications", "resultReceiver", NotificationsService.GET_CATEGORIES_TYPE, "getNotificationResponseFromBroadcastIntent", "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "getNotificationResponseFromOpenIntent", "getScheduledNotification", "getUriBuilder", "Landroid/net/Uri$Builder;", "getUriBuilderForIdentifier", "handleDropped", "marshalObject", "", "objectToMarshal", "Landroid/os/Parcelable;", NotificationsService.PRESENT_TYPE, "behavior", "Lexpo/modules/notifications/notifications/model/NotificationBehavior;", NotificationsService.RECEIVE_TYPE, "removeAllScheduledNotifications", "removeScheduledNotification", "removeScheduledNotifications", "", "schedule", NotificationsService.NOTIFICATION_REQUEST_KEY, "Lexpo/modules/notifications/notifications/model/NotificationRequest;", NotificationsService.SET_CATEGORY_TYPE, "category", "Lexpo/modules/notifications/notifications/model/NotificationCategory;", "setNotificationResponseToIntent", NotificationsService.NOTIFICATION_RESPONSE_KEY, "unmarshalObject", ExifInterface.GPS_DIRECTION_TRUE, "creator", "Landroid/os/Parcelable$Creator;", "byteArray", "(Landroid/os/Parcelable$Creator;[B)Ljava/lang/Object;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getSETUP_ACTIONS() {
            return NotificationsService.SETUP_ACTIONS;
        }

        public static /* synthetic */ void getAllPresented$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.getAllPresented(context, resultReceiver);
        }

        public final void getAllPresented(Context context, ResultReceiver receiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().build());
            intent.putExtra("type", NotificationsService.GET_ALL_DISPLAYED_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, receiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void present$default(Companion companion, Context context, Notification notification, NotificationBehavior notificationBehavior, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                notificationBehavior = null;
            }
            if ((i & 8) != 0) {
                resultReceiver = null;
            }
            companion.present(context, notification, notificationBehavior, resultReceiver);
        }

        public final void present(Context context, Notification notification, NotificationBehavior behavior, ResultReceiver receiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notification, "notification");
            String identifier = notification.getNotificationRequest().getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilderForIdentifier(identifier).appendPath(NotificationsService.PRESENT_TYPE).build());
            intent.putExtra("type", NotificationsService.PRESENT_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent.putExtra(NotificationsService.NOTIFICATION_BEHAVIOR_KEY, behavior);
            intent.putExtra(NotificationsService.RECEIVER_KEY, receiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void receive$default(Companion companion, Context context, Notification notification, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.receive(context, notification, resultReceiver);
        }

        public final void receive(Context context, Notification notification, ResultReceiver receiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notification, "notification");
            String identifier = notification.getNotificationRequest().getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilderForIdentifier(identifier).appendPath(NotificationsService.RECEIVE_TYPE).build());
            intent.putExtra("type", NotificationsService.RECEIVE_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent.putExtra(NotificationsService.RECEIVER_KEY, receiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void dismiss$default(Companion companion, Context context, String[] strArr, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.dismiss(context, strArr, resultReceiver);
        }

        public final void dismiss(Context context, String[] identifiers, ResultReceiver receiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(identifiers, "identifiers");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("dismiss").build());
            intent.putExtra("type", NotificationsService.DISMISS_SELECTED_TYPE);
            intent.putExtra(NotificationsService.IDENTIFIERS_KEY, identifiers);
            intent.putExtra(NotificationsService.RECEIVER_KEY, receiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void dismissAll$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.dismissAll(context, resultReceiver);
        }

        public final void dismissAll(Context context, ResultReceiver receiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("dismiss").build());
            intent.putExtra("type", NotificationsService.DISMISS_ALL_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, receiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public final void handleDropped(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION);
            intent.putExtra("type", NotificationsService.DROPPED_TYPE);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void getCategories$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.getCategories(context, resultReceiver);
        }

        public final void getCategories(Context context, ResultReceiver receiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("categories").build());
            intent.putExtra("type", NotificationsService.GET_CATEGORIES_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, receiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void setCategory$default(Companion companion, Context context, NotificationCategory notificationCategory, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.setCategory(context, notificationCategory, resultReceiver);
        }

        public final void setCategory(Context context, NotificationCategory category, ResultReceiver receiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(category, "category");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("categories").appendPath(category.getIdentifier()).build());
            intent.putExtra("type", NotificationsService.SET_CATEGORY_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_CATEGORY_KEY, (Parcelable) category);
            intent.putExtra(NotificationsService.RECEIVER_KEY, receiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void deleteCategory$default(Companion companion, Context context, String str, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.deleteCategory(context, str, resultReceiver);
        }

        public final void deleteCategory(Context context, String identifier, ResultReceiver receiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("categories").appendPath(identifier).build());
            intent.putExtra("type", NotificationsService.DELETE_CATEGORY_TYPE);
            intent.putExtra("identifier", identifier);
            intent.putExtra(NotificationsService.RECEIVER_KEY, receiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void getAllScheduledNotifications$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.getAllScheduledNotifications(context, resultReceiver);
        }

        public final void getAllScheduledNotifications(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION);
            intent.putExtra("type", NotificationsService.GET_ALL_SCHEDULED_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void getScheduledNotification$default(Companion companion, Context context, String str, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.getScheduledNotification(context, str, resultReceiver);
        }

        public final void getScheduledNotification(Context context, String identifier, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").appendPath(identifier).build());
            intent.putExtra("type", NotificationsService.GET_SCHEDULED_TYPE);
            intent.putExtra("identifier", identifier);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void schedule$default(Companion companion, Context context, NotificationRequest notificationRequest, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.schedule(context, notificationRequest, resultReceiver);
        }

        public final void schedule(Context context, NotificationRequest notificationRequest, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notificationRequest, "notificationRequest");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").appendPath(notificationRequest.getIdentifier()).build());
            intent.putExtra("type", "schedule");
            intent.putExtra(NotificationsService.NOTIFICATION_REQUEST_KEY, (Parcelable) notificationRequest);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void removeScheduledNotification$default(Companion companion, Context context, String str, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.removeScheduledNotification(context, str, resultReceiver);
        }

        public final void removeScheduledNotification(Context context, String identifier, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            removeScheduledNotifications(context, CollectionsKt.listOf(identifier), resultReceiver);
        }

        public static /* synthetic */ void removeScheduledNotifications$default(Companion companion, Context context, Collection collection, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.removeScheduledNotifications(context, collection, resultReceiver);
        }

        public final void removeScheduledNotifications(Context context, Collection<String> identifiers, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(identifiers, "identifiers");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").build());
            intent.putExtra("type", NotificationsService.REMOVE_SELECTED_TYPE);
            intent.putExtra(NotificationsService.IDENTIFIERS_KEY, (String[]) identifiers.toArray(new String[0]));
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void removeAllScheduledNotifications$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.removeAllScheduledNotifications(context, resultReceiver);
        }

        public final void removeAllScheduledNotifications(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION);
            intent.putExtra("type", NotificationsService.REMOVE_ALL_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public final void doWork(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            ActivityInfo findDesignatedBroadcastReceiver = findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
                context.sendBroadcast(intent);
                return;
            }
            Log.e("expo-notifications", "No service capable of handling notifications found (intent = " + intent.getAction() + "). Ensure that you have configured your AndroidManifest.xml properly.");
        }

        protected final Uri.Builder getUriBuilder() {
            Uri.Builder buildUpon = Uri.parse("expo-notifications://notifications/").buildUpon();
            Intrinsics.checkNotNullExpressionValue(buildUpon, "buildUpon(...)");
            return buildUpon;
        }

        protected final Uri.Builder getUriBuilderForIdentifier(String identifier) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            Uri.Builder appendPath = getUriBuilder().appendPath(identifier);
            Intrinsics.checkNotNullExpressionValue(appendPath, "appendPath(...)");
            return appendPath;
        }

        public final ActivityInfo findDesignatedBroadcastReceiver(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intent intent2 = new Intent(intent.getAction()).setPackage(context.getPackageName());
            Intrinsics.checkNotNullExpressionValue(intent2, "setPackage(...)");
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent2, 0);
            Intrinsics.checkNotNullExpressionValue(queryBroadcastReceivers, "queryBroadcastReceivers(...)");
            ResolveInfo resolveInfo = (ResolveInfo) CollectionsKt.firstOrNull((List) queryBroadcastReceivers);
            if (resolveInfo != null) {
                return resolveInfo.activityInfo;
            }
            return null;
        }

        public final PendingIntent createNotificationTrigger(Context context, String identifier) {
            String className;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").appendPath(identifier).appendPath(NotificationsService.TRIGGER_TYPE).build());
            ActivityInfo findDesignatedBroadcastReceiver = NotificationsService.INSTANCE.findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
            }
            intent.putExtra("type", NotificationsService.TRIGGER_TYPE);
            intent.putExtra("identifier", identifier);
            int i = Build.VERSION.SDK_INT >= 31 ? 33554432 : 0;
            ComponentName component = intent.getComponent();
            PendingIntent broadcast = PendingIntent.getBroadcast(context, (component == null || (className = component.getClassName()) == null) ? NotificationsService.class.hashCode() : className.hashCode(), intent, i | 134217728);
            Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
            return broadcast;
        }

        public final PendingIntent createNotificationResponseIntent(Context context, Notification notification, NotificationAction action) {
            String className;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notification, "notification");
            Intrinsics.checkNotNullParameter(action, "action");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath(notification.getNotificationRequest().getIdentifier()).appendPath("actions").appendPath(action.getIdentifier()).build());
            ActivityInfo findDesignatedBroadcastReceiver = NotificationsService.INSTANCE.findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
            }
            intent.putExtra("type", NotificationsService.RECEIVE_RESPONSE_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent.putExtra(NotificationsService.NOTIFICATION_ACTION_KEY, (Parcelable) action);
            if (action.opensAppToForeground()) {
                return ExpoHandlingDelegate.INSTANCE.createPendingIntentForOpeningApp(context, intent, getNotificationResponseFromBroadcastIntent(intent));
            }
            int i = Build.VERSION.SDK_INT >= 31 ? 33554432 : 0;
            ComponentName component = intent.getComponent();
            PendingIntent broadcast = PendingIntent.getBroadcast(context, (component == null || (className = component.getClassName()) == null) ? NotificationsService.class.hashCode() : className.hashCode(), intent, i | 134217728);
            Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
            return broadcast;
        }

        public final Intent createNotificationResponseBroadcastIntent(Context context, Bundle extras) {
            Intrinsics.checkNotNullParameter(context, "context");
            Notification notification = extras != null ? (Notification) extras.getParcelable(NotificationsService.NOTIFICATION_KEY) : null;
            NotificationAction notificationAction = extras != null ? (NotificationAction) extras.getParcelable(NotificationsService.NOTIFICATION_ACTION_KEY) : null;
            if (notification == null || notificationAction == null) {
                throw new IllegalArgumentException("notification and action should not be null");
            }
            NotificationAction notificationAction2 = new NotificationAction(notificationAction.getIdentifier(), notificationAction.getTitle(), false);
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath(notification.getNotificationRequest().getIdentifier()).appendPath("actions").appendPath(notificationAction2.getIdentifier()).build());
            ActivityInfo findDesignatedBroadcastReceiver = NotificationsService.INSTANCE.findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
            }
            intent.putExtra("type", NotificationsService.RECEIVE_RESPONSE_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent.putExtra(NotificationsService.NOTIFICATION_ACTION_KEY, (Parcelable) notificationAction2);
            return intent;
        }

        public final NotificationResponse getNotificationResponseFromBroadcastIntent(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Notification notification = (Notification) intent.getParcelableExtra(NotificationsService.NOTIFICATION_KEY);
            if (notification == null) {
                throw new IllegalArgumentException("notification not found in the intent extras.");
            }
            NotificationAction notificationAction = (NotificationAction) intent.getParcelableExtra(NotificationsService.NOTIFICATION_ACTION_KEY);
            if (notificationAction == null) {
                throw new IllegalArgumentException("notificationAction not found in the intent extras.");
            }
            if (notificationAction instanceof TextInputNotificationAction) {
                String placeholder = ((TextInputNotificationAction) notificationAction).getPlaceholder();
                if (placeholder == null) {
                    Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
                    placeholder = resultsFromIntent != null ? resultsFromIntent.getString(NotificationsService.USER_TEXT_RESPONSE_KEY) : null;
                    if (placeholder == null) {
                        placeholder = "";
                    }
                }
                return new TextInputNotificationResponse(notificationAction, notification, placeholder);
            }
            return new NotificationResponse(notificationAction, notification);
        }

        public final NotificationResponse getNotificationResponseFromOpenIntent(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            byte[] byteArrayExtra = intent.getByteArrayExtra(NotificationsService.NOTIFICATION_RESPONSE_KEY);
            if (byteArrayExtra != null) {
                Companion companion = NotificationsService.INSTANCE;
                Parcelable.Creator<NotificationResponse> CREATOR = NotificationResponse.CREATOR;
                Intrinsics.checkNotNullExpressionValue(CREATOR, "CREATOR");
                return (NotificationResponse) companion.unmarshalObject(CREATOR, byteArrayExtra);
            }
            byte[] byteArrayExtra2 = intent.getByteArrayExtra(NotificationsService.TEXT_INPUT_NOTIFICATION_RESPONSE_KEY);
            if (byteArrayExtra2 == null) {
                return null;
            }
            Companion companion2 = NotificationsService.INSTANCE;
            Parcelable.Creator<TextInputNotificationResponse> CREATOR2 = TextInputNotificationResponse.CREATOR;
            Intrinsics.checkNotNullExpressionValue(CREATOR2, "CREATOR");
            return (NotificationResponse) companion2.unmarshalObject(CREATOR2, byteArrayExtra2);
        }

        public final void setNotificationResponseToIntent(Intent intent, NotificationResponse notificationResponse) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            String str = NotificationsService.NOTIFICATION_RESPONSE_KEY;
            Intrinsics.checkNotNullParameter(notificationResponse, "notificationResponse");
            try {
                if (notificationResponse instanceof TextInputNotificationResponse) {
                    str = NotificationsService.TEXT_INPUT_NOTIFICATION_RESPONSE_KEY;
                }
                intent.putExtra(str, marshalObject(notificationResponse));
            } catch (Exception e) {
                Log.e("expo-notifications", "Could not marshal notification response: " + notificationResponse.getActionIdentifier() + ".");
                e.printStackTrace();
            }
        }

        private final byte[] marshalObject(Parcelable objectToMarshal) {
            Parcel obtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain(...)");
            objectToMarshal.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            Intrinsics.checkNotNullExpressionValue(marshall, "marshall(...)");
            obtain.recycle();
            return marshall;
        }

        private final <T> T unmarshalObject(Parcelable.Creator<T> creator, byte[] byteArray) {
            if (byteArray == null) {
                return null;
            }
            try {
                Parcel obtain = Parcel.obtain();
                Intrinsics.checkNotNullExpressionValue(obtain, "obtain(...)");
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                T createFromParcel = creator.createFromParcel(obtain);
                obtain.recycle();
                return createFromParcel;
            } catch (Exception e) {
                Log.e("expo-notifications", "Could not unmarshall NotificationResponse from Intent.extra.", e);
                return null;
            }
        }
    }

    protected PresentationDelegate getPresentationDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoPresentationDelegate(context);
    }

    protected HandlingDelegate getHandlingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoHandlingDelegate(context);
    }

    protected CategoriesDelegate getCategoriesDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoCategoriesDelegate(context);
    }

    protected SchedulingDelegate getSchedulingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoSchedulingDelegate(context);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        final BroadcastReceiver.PendingResult goAsync = goAsync();
        ThreadsKt.thread$default(false, false, null, null, 0, new Function0<Unit>() { // from class: expo.modules.notifications.service.NotificationsService$onReceive$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                try {
                    NotificationsService.this.handleIntent(context, intent);
                } finally {
                    goAsync.finish();
                }
            }
        }, 31, null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0120 A[Catch: Exception -> 0x013a, TryCatch #0 {Exception -> 0x013a, blocks: (B:20:0x0041, B:22:0x0049, B:23:0x004d, B:25:0x0052, B:27:0x005a, B:29:0x0120, B:32:0x0060, B:34:0x0068, B:35:0x006d, B:37:0x0075, B:38:0x007a, B:40:0x0082, B:41:0x0087, B:43:0x008f, B:44:0x0095, B:46:0x009d, B:47:0x00a2, B:49:0x00aa, B:50:0x00b0, B:52:0x00b8, B:53:0x00bc, B:55:0x00c4, B:56:0x00c8, B:58:0x00d0, B:59:0x00d4, B:61:0x00dc, B:62:0x00e0, B:64:0x00e8, B:65:0x00ec, B:67:0x00f4, B:68:0x00f9, B:70:0x0101, B:71:0x0106, B:73:0x010e, B:74:0x0113, B:76:0x011b, B:77:0x0125, B:78:0x0139), top: B:19:0x0041 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleIntent(android.content.Context r7, android.content.Intent r8) {
        /*
            Method dump skipped, instructions count: 470
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.NotificationsService.handleIntent(android.content.Context, android.content.Intent):void");
    }

    public void onPresentNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        PresentationDelegate presentationDelegate = getPresentationDelegate(context);
        Bundle extras = intent.getExtras();
        Notification notification = extras != null ? (Notification) extras.getParcelable(NOTIFICATION_KEY) : null;
        Intrinsics.checkNotNull(notification);
        Bundle extras2 = intent.getExtras();
        presentationDelegate.presentNotification(notification, extras2 != null ? (NotificationBehavior) extras2.getParcelable(NOTIFICATION_BEHAVIOR_KEY) : null);
    }

    public Bundle onGetAllPresentedNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NOTIFICATIONS_KEY, new ArrayList<>(getPresentationDelegate(context).getAllPresentedNotifications()));
        return bundle;
    }

    public void onDismissNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        PresentationDelegate presentationDelegate = getPresentationDelegate(context);
        Bundle extras = intent.getExtras();
        String[] stringArray = extras != null ? extras.getStringArray(IDENTIFIERS_KEY) : null;
        Intrinsics.checkNotNull(stringArray);
        presentationDelegate.dismissNotifications(ArraysKt.asList(stringArray));
    }

    public void onDismissAllNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getPresentationDelegate(context).dismissAllNotifications();
    }

    public void onReceiveNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        HandlingDelegate handlingDelegate = getHandlingDelegate(context);
        Parcelable parcelableExtra = intent.getParcelableExtra(NOTIFICATION_KEY);
        Intrinsics.checkNotNull(parcelableExtra);
        handlingDelegate.handleNotification((Notification) parcelableExtra);
    }

    public void onReceiveNotificationResponse(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getHandlingDelegate(context).handleNotificationResponse(INSTANCE.getNotificationResponseFromBroadcastIntent(intent));
    }

    public void onNotificationsDropped(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getHandlingDelegate(context).handleNotificationsDropped();
    }

    public Bundle onGetCategories(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NOTIFICATION_CATEGORIES_KEY, new ArrayList<>(getCategoriesDelegate(context).getCategories()));
        return bundle;
    }

    public Bundle onSetCategory(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        CategoriesDelegate categoriesDelegate = getCategoriesDelegate(context);
        Parcelable parcelableExtra = intent.getParcelableExtra(NOTIFICATION_CATEGORY_KEY);
        Intrinsics.checkNotNull(parcelableExtra);
        bundle.putParcelable(NOTIFICATION_CATEGORY_KEY, categoriesDelegate.setCategory((NotificationCategory) parcelableExtra));
        return bundle;
    }

    public Bundle onDeleteCategory(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        CategoriesDelegate categoriesDelegate = getCategoriesDelegate(context);
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString("identifier") : null;
        Intrinsics.checkNotNull(string);
        bundle.putBoolean(SUCCEEDED_KEY, categoriesDelegate.deleteCategory(string));
        return bundle;
    }

    public Bundle onGetAllScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NOTIFICATION_REQUESTS_KEY, new ArrayList<>(getSchedulingDelegate(context).getAllScheduledNotifications()));
        return bundle;
    }

    public Bundle onGetScheduledNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString("identifier") : null;
        Intrinsics.checkNotNull(string);
        bundle.putParcelable(NOTIFICATION_REQUEST_KEY, schedulingDelegate.getScheduledNotification(string));
        return bundle;
    }

    public void onScheduleNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        NotificationRequest notificationRequest = extras != null ? (NotificationRequest) extras.getParcelable(NOTIFICATION_REQUEST_KEY) : null;
        Intrinsics.checkNotNull(notificationRequest);
        schedulingDelegate.scheduleNotification(notificationRequest);
    }

    public void onNotificationTriggered(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString("identifier") : null;
        Intrinsics.checkNotNull(string);
        schedulingDelegate.triggerNotification(string);
    }

    public void onRemoveScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        String[] stringArray = extras != null ? extras.getStringArray(IDENTIFIERS_KEY) : null;
        Intrinsics.checkNotNull(stringArray);
        schedulingDelegate.removeScheduledNotifications(ArraysKt.asList(stringArray));
    }

    public void onRemoveAllScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getSchedulingDelegate(context).removeAllScheduledNotifications();
    }

    public void onSetupScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getSchedulingDelegate(context).setupScheduledNotifications();
    }
}

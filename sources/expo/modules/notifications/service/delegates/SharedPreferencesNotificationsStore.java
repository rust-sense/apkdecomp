package expo.modules.notifications.service.delegates;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.service.NotificationsService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SharedPreferencesNotificationsStore.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006J \u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u0011\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0007R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationsStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "allNotificationRequests", "", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "getAllNotificationRequests", "()Ljava/util/Collection;", "sharedPreferences", "Landroid/content/SharedPreferences;", "getNotificationRequest", "identifier", "", "preferencesNotificationRequestKey", "removeAllNotificationRequests", "removeNotificationRequest", "Landroid/content/SharedPreferences$Editor;", "kotlin.jvm.PlatformType", "editor", "", "saveNotificationRequest", NotificationsService.NOTIFICATION_REQUEST_KEY, "Companion", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SharedPreferencesNotificationsStore {
    private static final String NOTIFICATION_REQUEST_KEY_PREFIX = "notification_request-";
    private static final String SHARED_PREFERENCES_NAME = "expo.modules.notifications.SharedPreferencesNotificationsStore";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesNotificationsStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.sharedPreferences = sharedPreferences;
    }

    public final NotificationRequest getNotificationRequest(String identifier) throws IOException, ClassNotFoundException {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        String string = this.sharedPreferences.getString(preferencesNotificationRequestKey(identifier), null);
        if (string == null) {
            return null;
        }
        ObjectInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decode(string, 2));
        try {
            byteArrayInputStream = new ObjectInputStream(byteArrayInputStream);
            try {
                Object readObject = byteArrayInputStream.readObject();
                if (readObject instanceof NotificationRequest) {
                    CloseableKt.closeFinally(byteArrayInputStream, null);
                    CloseableKt.closeFinally(byteArrayInputStream, null);
                    return (NotificationRequest) readObject;
                }
                throw new InvalidClassException("Expected serialized object to be an instance of " + NotificationRequest.class + ". Found: " + readObject);
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } finally {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0057 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Collection<expo.modules.notifications.notifications.model.NotificationRequest> getAllNotificationRequests() {
        /*
            r11 = this;
            android.content.SharedPreferences r0 = r11.sharedPreferences
            java.util.Map r0 = r0.getAll()
            java.lang.String r1 = "getAll(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            r1.<init>()
            java.util.Map r1 = (java.util.Map) r1
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L1a:
            boolean r2 = r0.hasNext()
            r3 = 2
            r4 = 0
            if (r2 == 0) goto L48
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r5 = r2.getKey()
            java.lang.String r6 = "<get-key>(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r6 = "notification_request-"
            r7 = 0
            boolean r3 = kotlin.text.StringsKt.startsWith$default(r5, r6, r7, r3, r4)
            if (r3 == 0) goto L1a
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r2 = r2.getValue()
            r1.put(r3, r2)
            goto L1a
        L48:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L57:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto Lcc
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> Lc5
            if (r2 == 0) goto Lc5
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> Lc5
            byte[] r2 = android.util.Base64.decode(r2, r3)     // Catch: java.lang.Throwable -> Lc5
            r5.<init>(r2)     // Catch: java.lang.Throwable -> Lc5
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch: java.lang.Throwable -> Lc5
            r2 = r5
            java.io.ByteArrayInputStream r2 = (java.io.ByteArrayInputStream) r2     // Catch: java.lang.Throwable -> Lbe
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> Lbe
            java.io.InputStream r2 = (java.io.InputStream) r2     // Catch: java.lang.Throwable -> Lbe
            r6.<init>(r2)     // Catch: java.lang.Throwable -> Lbe
            java.io.Closeable r6 = (java.io.Closeable) r6     // Catch: java.lang.Throwable -> Lbe
            r2 = r6
            java.io.ObjectInputStream r2 = (java.io.ObjectInputStream) r2     // Catch: java.lang.Throwable -> Lb7
            java.lang.Object r2 = r2.readObject()     // Catch: java.lang.Throwable -> Lb7
            boolean r7 = r2 instanceof expo.modules.notifications.notifications.model.NotificationRequest     // Catch: java.lang.Throwable -> Lb7
            if (r7 == 0) goto L96
            kotlin.io.CloseableKt.closeFinally(r6, r4)     // Catch: java.lang.Throwable -> Lbe
            kotlin.io.CloseableKt.closeFinally(r5, r4)     // Catch: java.lang.Throwable -> Lc5 java.lang.Throwable -> Lc5
            expo.modules.notifications.notifications.model.NotificationRequest r2 = (expo.modules.notifications.notifications.model.NotificationRequest) r2     // Catch: java.lang.Throwable -> Lc5 java.lang.Throwable -> Lc5
            goto Lc6
        L96:
            java.io.InvalidClassException r7 = new java.io.InvalidClassException     // Catch: java.lang.Throwable -> Lb7
            java.lang.Class<expo.modules.notifications.notifications.model.NotificationRequest> r8 = expo.modules.notifications.notifications.model.NotificationRequest.class
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb7
            r9.<init>()     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r10 = "Expected serialized object to be an instance of "
            r9.append(r10)     // Catch: java.lang.Throwable -> Lb7
            r9.append(r8)     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r8 = ". Found: "
            r9.append(r8)     // Catch: java.lang.Throwable -> Lb7
            r9.append(r2)     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r2 = r9.toString()     // Catch: java.lang.Throwable -> Lb7
            r7.<init>(r2)     // Catch: java.lang.Throwable -> Lb7
            throw r7     // Catch: java.lang.Throwable -> Lb7
        Lb7:
            r2 = move-exception
            throw r2     // Catch: java.lang.Throwable -> Lb9
        Lb9:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r6, r2)     // Catch: java.lang.Throwable -> Lbe
            throw r7     // Catch: java.lang.Throwable -> Lbe
        Lbe:
            r2 = move-exception
            throw r2     // Catch: java.lang.Throwable -> Lc0
        Lc0:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r2)     // Catch: java.lang.Throwable -> Lc5 java.lang.Throwable -> Lc5
            throw r6     // Catch: java.lang.Throwable -> Lc5 java.lang.Throwable -> Lc5
        Lc5:
            r2 = r4
        Lc6:
            if (r2 == 0) goto L57
            r0.add(r2)
            goto L57
        Lcc:
            java.util.List r0 = (java.util.List) r0
            java.util.Collection r0 = (java.util.Collection) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.delegates.SharedPreferencesNotificationsStore.getAllNotificationRequests():java.util.Collection");
    }

    public final void saveNotificationRequest(NotificationRequest notificationRequest) throws IOException {
        Intrinsics.checkNotNullParameter(notificationRequest, "notificationRequest");
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        String identifier = notificationRequest.getIdentifier();
        Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
        edit.putString(preferencesNotificationRequestKey(identifier), Base64SerializationKt.encodedInBase64(notificationRequest)).apply();
    }

    public final void removeNotificationRequest(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
        removeNotificationRequest(edit, identifier).apply();
    }

    private final SharedPreferences.Editor removeNotificationRequest(SharedPreferences.Editor editor, String identifier) {
        return editor.remove(preferencesNotificationRequestKey(identifier));
    }

    public final Collection<String> removeAllNotificationRequests() {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        Collection<NotificationRequest> allNotificationRequests = getAllNotificationRequests();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(allNotificationRequests, 10));
        for (NotificationRequest notificationRequest : allNotificationRequests) {
            Intrinsics.checkNotNull(edit);
            String identifier = notificationRequest.getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            removeNotificationRequest(edit, identifier);
            arrayList.add(notificationRequest.getIdentifier());
        }
        edit.apply();
        return arrayList;
    }

    private final String preferencesNotificationRequestKey(String identifier) {
        return NOTIFICATION_REQUEST_KEY_PREFIX + identifier;
    }
}

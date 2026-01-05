package expo.modules.notifications.notifications.categories;

import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.records.Required;
import kotlin.Metadata;

/* compiled from: ExpoNotificationCategoriesModule.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0002\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lexpo/modules/notifications/notifications/categories/NotificationActionRecord;", "Lexpo/modules/kotlin/records/Record;", "()V", "buttonTitle", "", "getButtonTitle$annotations", "getButtonTitle", "()Ljava/lang/String;", "identifier", "getIdentifier$annotations", "getIdentifier", "options", "Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$Options;", "getOptions$annotations", "getOptions", "()Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$Options;", "textInput", "Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$TextInput;", "getTextInput$annotations", "getTextInput", "()Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$TextInput;", "Options", "TextInput", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NotificationActionRecord implements Record {
    private final TextInput textInput;
    private final String identifier = "";
    private final String buttonTitle = "";
    private final Options options = new Options();

    /* compiled from: ExpoNotificationCategoriesModule.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$Options;", "Lexpo/modules/kotlin/records/Record;", "()V", "opensAppToForeground", "", "getOpensAppToForeground$annotations", "getOpensAppToForeground", "()Z", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Options implements Record {
        private final boolean opensAppToForeground = true;

        @Field
        public static /* synthetic */ void getOpensAppToForeground$annotations() {
        }

        public final boolean getOpensAppToForeground() {
            return this.opensAppToForeground;
        }
    }

    /* compiled from: ExpoNotificationCategoriesModule.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/notifications/notifications/categories/NotificationActionRecord$TextInput;", "Lexpo/modules/kotlin/records/Record;", "()V", ReactTextInputShadowNode.PROP_PLACEHOLDER, "", "getPlaceholder$annotations", "getPlaceholder", "()Ljava/lang/String;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class TextInput implements Record {
        private final String placeholder = "";

        @Field
        @Required
        public static /* synthetic */ void getPlaceholder$annotations() {
        }

        public final String getPlaceholder() {
            return this.placeholder;
        }
    }

    @Field
    @Required
    public static /* synthetic */ void getButtonTitle$annotations() {
    }

    @Field
    @Required
    public static /* synthetic */ void getIdentifier$annotations() {
    }

    @Field
    public static /* synthetic */ void getOptions$annotations() {
    }

    @Field
    public static /* synthetic */ void getTextInput$annotations() {
    }

    public final String getButtonTitle() {
        return this.buttonTitle;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final Options getOptions() {
        return this.options;
    }

    public final TextInput getTextInput() {
        return this.textInput;
    }
}

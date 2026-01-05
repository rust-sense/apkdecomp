package expo.modules.application;

import android.content.pm.PackageManager;
import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApplicationModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/application/ApplicationPackageNameNotFoundException;", "Lexpo/modules/kotlin/exception/CodedException;", "cause", "Landroid/content/pm/PackageManager$NameNotFoundException;", "(Landroid/content/pm/PackageManager$NameNotFoundException;)V", "expo-application_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ApplicationPackageNameNotFoundException extends CodedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApplicationPackageNameNotFoundException(PackageManager.NameNotFoundException cause) {
        super("Unable to get install time of this application. Could not get package info or package name.", cause);
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}

package expo.modules.splashscreen.exceptions;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

/* compiled from: SplashScreenExceptions.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/splashscreen/exceptions/NoContentViewException;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NoContentViewException extends CodedException {
    public NoContentViewException() {
        super("ContentView is not yet available. Call 'SplashScreen.show(...)' once 'setContentView()' is called.", null, 2, null);
    }
}

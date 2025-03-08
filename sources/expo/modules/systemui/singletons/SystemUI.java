package expo.modules.systemui.singletons;

import android.util.Log;
import io.sentry.clientreport.DiscardedEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemUI.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007JC\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t2!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00060\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lexpo/modules/systemui/singletons/SystemUI;", "", "()V", "TAG", "", "setUserInterfaceStyle", "", "style", "successCallback", "Lkotlin/Function0;", "failureCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", DiscardedEvent.JsonKeys.REASON, "expo-system-ui_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SystemUI {
    public static final SystemUI INSTANCE = new SystemUI();
    private static final String TAG = "SystemUI";

    private SystemUI() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0030, code lost:
    
        if (r4.equals("light") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005d, code lost:
    
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0044, code lost:
    
        if (r4.equals("") == false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void setUserInterfaceStyle(java.lang.String r4, kotlin.jvm.functions.Function0<kotlin.Unit> r5, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r6) {
        /*
            r3 = this;
            r0 = -1
            if (r4 != 0) goto L4
            goto L5e
        L4:
            int r1 = r4.hashCode()
            if (r1 == 0) goto L3e
            r2 = 3075958(0x2eef76, float:4.310335E-39)
            if (r1 == r2) goto L33
            r2 = 102970646(0x6233516, float:3.0695894E-35)
            if (r1 == r2) goto L2a
            r2 = 1673671211(0x63c2322b, float:7.1645667E21)
            if (r1 == r2) goto L1a
            goto L46
        L1a:
            java.lang.String r1 = "automatic"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L46
            int r4 = android.os.Build.VERSION.SDK_INT
            r6 = 28
            if (r4 >= r6) goto L5e
            r0 = 3
            goto L5e
        L2a:
            java.lang.String r0 = "light"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L5d
            goto L46
        L33:
            java.lang.String r0 = "dark"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L3c
            goto L46
        L3c:
            r0 = 2
            goto L5e
        L3e:
            java.lang.String r0 = ""
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L5d
        L46:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "Invalid user interface style: \""
            r5.<init>(r0)
            r5.append(r4)
            java.lang.String r4 = "\""
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r6.invoke(r4)
            return
        L5d:
            r0 = 1
        L5e:
            androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(r0)
            r5.invoke()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.systemui.singletons.SystemUI.setUserInterfaceStyle(java.lang.String, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1):void");
    }

    @JvmStatic
    public static final void setUserInterfaceStyle(String style) {
        Intrinsics.checkNotNullParameter(style, "style");
        INSTANCE.setUserInterfaceStyle(style, new Function0<Unit>() { // from class: expo.modules.systemui.singletons.SystemUI$setUserInterfaceStyle$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        }, new Function1<String, Unit>() { // from class: expo.modules.systemui.singletons.SystemUI$setUserInterfaceStyle$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String m) {
                Intrinsics.checkNotNullParameter(m, "m");
                Log.e("SystemUI", m);
            }
        });
    }
}

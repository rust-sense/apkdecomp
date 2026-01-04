package expo.modules.haptics;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.graphics.SurfaceTexture;
import android.os.VibratorManager;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class HapticsModule$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ NotificationChannel m(Object obj) {
        return (NotificationChannel) obj;
    }

    public static /* synthetic */ NotificationChannel m(String str, CharSequence charSequence, int i) {
        return new NotificationChannel(str, charSequence, i);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NotificationChannelGroup m658m(Object obj) {
        return (NotificationChannelGroup) obj;
    }

    public static /* synthetic */ NotificationChannelGroup m(String str, CharSequence charSequence) {
        return new NotificationChannelGroup(str, charSequence);
    }

    public static /* synthetic */ SurfaceTexture m(boolean z) {
        return new SurfaceTexture(z);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ VibratorManager m662m(Object obj) {
        return (VibratorManager) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m665m() {
        return BasicFileAttributes.class;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m673m() {
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m677m(Object obj) {
        return obj instanceof NotificationChannelGroup;
    }

    /* renamed from: m$1, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m680m$1() {
        return Path.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof NotificationChannel;
    }

    /* renamed from: m$2, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m684m$2() {
        return LocalDate.class;
    }
}

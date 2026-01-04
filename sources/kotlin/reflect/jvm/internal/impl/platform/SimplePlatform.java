package kotlin.reflect.jvm.internal.impl.platform;

/* compiled from: TargetPlatform.kt */
/* loaded from: classes3.dex */
public abstract class SimplePlatform {
    private final String platformName;
    private final TargetPlatformVersion targetPlatformVersion;

    public TargetPlatformVersion getTargetPlatformVersion() {
        return this.targetPlatformVersion;
    }

    public String toString() {
        String targetName = getTargetName();
        if (targetName.length() <= 0) {
            return this.platformName;
        }
        return this.platformName + " (" + targetName + ')';
    }

    public String getTargetName() {
        return getTargetPlatformVersion().getDescription();
    }
}

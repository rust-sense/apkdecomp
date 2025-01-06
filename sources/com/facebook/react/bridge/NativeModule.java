package com.facebook.react.bridge;

import javax.annotation.Nonnull;

/* loaded from: classes.dex */
public interface NativeModule {

    /* renamed from: com.facebook.react.bridge.NativeModule$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$canOverrideExistingModule(NativeModule _this) {
            return false;
        }

        @Deprecated(forRemoval = true, since = "Use invalidate method instead")
        public static void $default$onCatalystInstanceDestroy(NativeModule _this) {
        }
    }

    boolean canOverrideExistingModule();

    @Nonnull
    String getName();

    void initialize();

    void invalidate();

    @Deprecated(forRemoval = true, since = "Use invalidate method instead")
    void onCatalystInstanceDestroy();
}

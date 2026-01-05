package com.facebook.react.module.model;

import com.facebook.react.turbomodule.core.interfaces.TurboModule;

/* loaded from: classes.dex */
public class ReactModuleInfo {
    private final boolean mCanOverrideExistingModule;
    private final String mClassName;
    private final boolean mIsCxxModule;
    private final boolean mIsTurboModule;
    private final String mName;
    private final boolean mNeedsEagerInit;

    public boolean canOverrideExistingModule() {
        return this.mCanOverrideExistingModule;
    }

    public String className() {
        return this.mClassName;
    }

    @Deprecated
    public boolean hasConstants() {
        return true;
    }

    public boolean isCxxModule() {
        return this.mIsCxxModule;
    }

    public boolean isTurboModule() {
        return this.mIsTurboModule;
    }

    public String name() {
        return this.mName;
    }

    public boolean needsEagerInit() {
        return this.mNeedsEagerInit;
    }

    public ReactModuleInfo(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.mName = str;
        this.mClassName = str2;
        this.mCanOverrideExistingModule = z;
        this.mNeedsEagerInit = z2;
        this.mIsCxxModule = z3;
        this.mIsTurboModule = z4;
    }

    @Deprecated
    public ReactModuleInfo(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this(str, str2, z, z2, z4, z5);
    }

    public static boolean classIsTurboModule(Class<?> cls) {
        return TurboModule.class.isAssignableFrom(cls);
    }
}

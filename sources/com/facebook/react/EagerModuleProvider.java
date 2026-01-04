package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import javax.inject.Provider;

/* loaded from: classes.dex */
class EagerModuleProvider implements Provider<NativeModule> {
    private final NativeModule mModule;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    public NativeModule get() {
        return this.mModule;
    }

    public EagerModuleProvider(NativeModule nativeModule) {
        this.mModule = nativeModule;
    }
}

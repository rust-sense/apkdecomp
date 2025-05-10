package com.facebook.react.runtime.cxxreactpackage;

import com.facebook.jni.HybridData;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: CxxReactPackage.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0011\b\u0014\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/facebook/react/runtime/cxxreactpackage/CxxReactPackage;", "", "hybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "mHybridData", "getMHybridData$annotations", "()V", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@UnstableReactNativeAPI
/* loaded from: classes.dex */
public abstract class CxxReactPackage {
    private static final Companion Companion = new Companion(null);
    private HybridData mHybridData;

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    protected CxxReactPackage(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    /* compiled from: CxxReactPackage.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/facebook/react/runtime/cxxreactpackage/CxxReactPackage$Companion;", "", "()V", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        SoLoader.loadLibrary("react_cxxreactpackage");
    }
}

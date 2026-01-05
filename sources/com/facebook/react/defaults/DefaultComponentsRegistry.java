package com.facebook.react.defaults;

import com.facebook.jni.HybridData;
import com.facebook.react.fabric.ComponentFactory;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultComponentsRegistry.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\b\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\t\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0083 R\u0016\u0010\u0005\u001a\u00020\u00068\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/defaults/DefaultComponentsRegistry;", "", "componentFactory", "Lcom/facebook/react/fabric/ComponentFactory;", "(Lcom/facebook/react/fabric/ComponentFactory;)V", "mHybridData", "Lcom/facebook/jni/HybridData;", "getMHybridData$annotations", "()V", "initHybrid", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DefaultComponentsRegistry {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final HybridData mHybridData;

    public /* synthetic */ DefaultComponentsRegistry(ComponentFactory componentFactory, DefaultConstructorMarker defaultConstructorMarker) {
        this(componentFactory);
    }

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    private final native HybridData initHybrid(ComponentFactory componentFactory);

    @JvmStatic
    public static final DefaultComponentsRegistry register(ComponentFactory componentFactory) {
        return INSTANCE.register(componentFactory);
    }

    private DefaultComponentsRegistry(ComponentFactory componentFactory) {
        this.mHybridData = initHybrid(componentFactory);
    }

    /* compiled from: DefaultComponentsRegistry.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/defaults/DefaultComponentsRegistry$Companion;", "", "()V", "register", "Lcom/facebook/react/defaults/DefaultComponentsRegistry;", "componentFactory", "Lcom/facebook/react/fabric/ComponentFactory;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DefaultComponentsRegistry register(ComponentFactory componentFactory) {
            Intrinsics.checkNotNullParameter(componentFactory, "componentFactory");
            return new DefaultComponentsRegistry(componentFactory, null);
        }
    }

    static {
        DefaultSoLoader.INSTANCE.maybeLoadSoLibrary();
    }
}

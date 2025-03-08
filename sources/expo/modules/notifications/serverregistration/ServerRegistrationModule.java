package expo.modules.notifications.serverregistration;

import android.content.Context;
import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: ServerRegistrationModule.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\t\u001a\u00020\u0014H\u0016R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lexpo/modules/notifications/serverregistration/ServerRegistrationModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "installationId", "Lexpo/modules/notifications/serverregistration/InstallationId;", "getInstallationId", "()Lexpo/modules/notifications/serverregistration/InstallationId;", "installationId$delegate", "Lkotlin/Lazy;", "mRegistrationInfo", "Lexpo/modules/notifications/serverregistration/RegistrationInfo;", "getMRegistrationInfo", "()Lexpo/modules/notifications/serverregistration/RegistrationInfo;", "mRegistrationInfo$delegate", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ServerRegistrationModule extends Module {

    /* renamed from: installationId$delegate, reason: from kotlin metadata */
    private final Lazy installationId = LazyKt.lazy(new Function0<InstallationId>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$installationId$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final InstallationId invoke() {
            return new InstallationId(ServerRegistrationModule.this.getContext());
        }
    });

    /* renamed from: mRegistrationInfo$delegate, reason: from kotlin metadata */
    private final Lazy mRegistrationInfo = LazyKt.lazy(new Function0<RegistrationInfo>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$mRegistrationInfo$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RegistrationInfo invoke() {
            return new RegistrationInfo(ServerRegistrationModule.this.getContext());
        }
    });

    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    protected final InstallationId getInstallationId() {
        return (InstallationId) this.installationId.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RegistrationInfo getMRegistrationInfo() {
        return (RegistrationInfo) this.mRegistrationInfo.getValue();
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        ServerRegistrationModule serverRegistrationModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (serverRegistrationModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(serverRegistrationModule);
            moduleDefinitionBuilder.Name("NotificationsServerRegistrationModule");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr = new AnyType[0];
            Function1<Object[], String> function1 = new Function1<Object[], String>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunction$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return ServerRegistrationModule.this.m739getInstallationId();
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent = new StringAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                            } else {
                                asyncFunctionComponent = new AsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new FloatAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new DoubleAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new BoolAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
                }
            } else {
                asyncFunctionComponent = new IntAsyncFunctionComponent("getInstallationIdAsync", anyTypeArr, function1);
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getInstallationIdAsync", asyncFunctionComponent);
            moduleDefinitionBuilder.getAsyncFunctions().put("getRegistrationInfoAsync", new AsyncFunctionComponent("getRegistrationInfoAsync", new AnyType[0], new Function1<Object[], String>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunction$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    RegistrationInfo mRegistrationInfo;
                    Intrinsics.checkNotNullParameter(it, "it");
                    mRegistrationInfo = ServerRegistrationModule.this.getMRegistrationInfo();
                    return mRegistrationInfo.get();
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionComponent2 = new AsyncFunctionWithPromiseComponent("setRegistrationInfoAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunction$3
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        RegistrationInfo mRegistrationInfo;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        mRegistrationInfo = ServerRegistrationModule.this.getMRegistrationInfo();
                        mRegistrationInfo.set((String) promise);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunction$4
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }))};
                Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.notifications.serverregistration.ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunction$5
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        RegistrationInfo mRegistrationInfo;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        String str = (String) objArr[0];
                        mRegistrationInfo = ServerRegistrationModule.this.getMRegistrationInfo();
                        mRegistrationInfo.set(str);
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent2 = new StringAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                                } else {
                                    asyncFunctionComponent2 = new AsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                                }
                            } else {
                                asyncFunctionComponent2 = new FloatAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                            }
                        } else {
                            asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                        }
                    } else {
                        asyncFunctionComponent2 = new BoolAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                    }
                } else {
                    asyncFunctionComponent2 = new IntAsyncFunctionComponent("setRegistrationInfoAsync", anyTypeArr2, function12);
                }
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("setRegistrationInfoAsync", asyncFunctionComponent2);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* renamed from: getInstallationId, reason: collision with other method in class */
    public String m739getInstallationId() {
        String orCreateUUID = getInstallationId().getOrCreateUUID();
        Intrinsics.checkNotNullExpressionValue(orCreateUUID, "getOrCreateUUID(...)");
        return orCreateUUID;
    }
}

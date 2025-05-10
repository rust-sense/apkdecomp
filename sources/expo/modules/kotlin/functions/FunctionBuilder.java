package expo.modules.kotlin.functions;

import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: FunctionBuilder.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J#\u0010\u0010\u001a\u00020\u00062\u0010\b\u0004\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012H\u0087\bø\u0001\u0000¢\u0006\u0002\b\u0013J,\u0010\u0010\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0004\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0012H\u0086\bø\u0001\u0000JI\u0010\u0010\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u0001\"\u0006\b\u0001\u0010\u0015\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\u00140\u0016H\u0086\bø\u0001\u0000Jf\u0010\u0010\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u0001\"\u0006\b\u0001\u0010\u0015\u0018\u0001\"\u0006\b\u0002\u0010\u0019\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u000328\b\u0004\u0010\u0011\u001a2\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u0011H\u0019¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u0002H\u00140\u001aH\u0086\bø\u0001\u0000J\u0083\u0001\u0010\u0010\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u0001\"\u0006\b\u0001\u0010\u0015\u0018\u0001\"\u0006\b\u0002\u0010\u0019\u0018\u0001\"\u0006\b\u0003\u0010\u001c\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032M\b\u0004\u0010\u0011\u001aG\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u0011H\u0019¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u0002H\u00140\u001dH\u0086\bø\u0001\u0000J \u0001\u0010\u0010\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u0001\"\u0006\b\u0001\u0010\u0015\u0018\u0001\"\u0006\b\u0002\u0010\u0019\u0018\u0001\"\u0006\b\u0003\u0010\u001c\u0018\u0001\"\u0006\b\u0004\u0010\u001f\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032b\b\u0004\u0010\u0011\u001a\\\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u0011H\u0019¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0004\u0012\u0002H\u00140 H\u0086\bø\u0001\u0000J½\u0001\u0010\u0010\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u0001\"\u0006\b\u0001\u0010\u0015\u0018\u0001\"\u0006\b\u0002\u0010\u0019\u0018\u0001\"\u0006\b\u0003\u0010\u001c\u0018\u0001\"\u0006\b\u0004\u0010\u001f\u0018\u0001\"\u0006\b\u0005\u0010\"\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032w\b\u0004\u0010\u0011\u001aq\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u0011H\u0019¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H\"¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b($\u0012\u0004\u0012\u0002H\u00140#H\u0086\bø\u0001\u0000JÜ\u0001\u0010\u0010\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u0001\"\u0006\b\u0001\u0010\u0015\u0018\u0001\"\u0006\b\u0002\u0010\u0019\u0018\u0001\"\u0006\b\u0003\u0010\u001c\u0018\u0001\"\u0006\b\u0004\u0010\u001f\u0018\u0001\"\u0006\b\u0005\u0010\"\u0018\u0001\"\u0006\b\u0006\u0010%\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u008d\u0001\b\u0004\u0010\u0011\u001a\u0086\u0001\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u0011H\u0019¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H\"¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b($\u0012\u0013\u0012\u0011H%¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b('\u0012\u0004\u0012\u0002H\u00140&H\u0086\bø\u0001\u0000Jù\u0001\u0010\u0010\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u0001\"\u0006\b\u0001\u0010\u0015\u0018\u0001\"\u0006\b\u0002\u0010\u0019\u0018\u0001\"\u0006\b\u0003\u0010\u001c\u0018\u0001\"\u0006\b\u0004\u0010\u001f\u0018\u0001\"\u0006\b\u0005\u0010\"\u0018\u0001\"\u0006\b\u0006\u0010%\u0018\u0001\"\u0006\b\u0007\u0010(\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032¢\u0001\b\u0004\u0010\u0011\u001a\u009b\u0001\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u0011H\u0019¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H\"¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b($\u0012\u0013\u0012\u0011H%¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b('\u0012\u0013\u0012\u0011H(¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(*\u0012\u0004\u0012\u0002H\u00140)H\u0086\bø\u0001\u0000J\u0096\u0002\u0010\u0010\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u0001\"\u0006\b\u0001\u0010\u0015\u0018\u0001\"\u0006\b\u0002\u0010\u0019\u0018\u0001\"\u0006\b\u0003\u0010\u001c\u0018\u0001\"\u0006\b\u0004\u0010\u001f\u0018\u0001\"\u0006\b\u0005\u0010\"\u0018\u0001\"\u0006\b\u0006\u0010%\u0018\u0001\"\u0006\b\u0007\u0010(\u0018\u0001\"\u0006\b\b\u0010+\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032·\u0001\b\u0004\u0010\u0011\u001a°\u0001\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u0011H\u0019¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u0011H\u001c¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H\"¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b($\u0012\u0013\u0012\u0011H%¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b('\u0012\u0013\u0012\u0011H(¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(*\u0012\u0013\u0012\u0011H+¢\u0006\f\b\u0017\u0012\b\b\u0002\u0012\u0004\b\b(-\u0012\u0004\u0012\u0002H\u00140,H\u0086\bø\u0001\u0000J\r\u0010.\u001a\u00020\u0006H\u0000¢\u0006\u0002\b/R&\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00060"}, d2 = {"Lexpo/modules/kotlin/functions/FunctionBuilder;", "", "name", "", "(Ljava/lang/String;)V", "functionComponent", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getFunctionComponent$annotations", "()V", "getFunctionComponent", "()Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "setFunctionComponent", "(Lexpo/modules/kotlin/functions/SyncFunctionComponent;)V", "getName$annotations", "getName", "()Ljava/lang/String;", "Body", "body", "Lkotlin/Function0;", "BodyWithoutArgs", "R", "P0", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "p0", "P1", "Lkotlin/Function2;", "p1", "P2", "Lkotlin/Function3;", "p2", "P3", "Lkotlin/Function4;", "p3", "P4", "Lkotlin/Function5;", "p4", "P5", "Lkotlin/Function6;", "p5", "P6", "Lkotlin/Function7;", "p6", "P7", "Lkotlin/Function8;", "p7", OperatingSystem.JsonKeys.BUILD, "build$expo_modules_core_release", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FunctionBuilder {
    private SyncFunctionComponent functionComponent;
    private final String name;

    public static /* synthetic */ void getFunctionComponent$annotations() {
    }

    public static /* synthetic */ void getName$annotations() {
    }

    public final SyncFunctionComponent getFunctionComponent() {
        return this.functionComponent;
    }

    public final String getName() {
        return this.name;
    }

    public final void setFunctionComponent(SyncFunctionComponent syncFunctionComponent) {
        this.functionComponent = syncFunctionComponent;
    }

    public FunctionBuilder(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    public final SyncFunctionComponent BodyWithoutArgs(final Function0<? extends Object> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(getName(), new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return body.invoke();
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R> SyncFunctionComponent Body(String name, final Function0<? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return body.invoke();
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0> SyncFunctionComponent Body(String name, final Function1<? super P0, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$1 functionBuilder$Body$$inlined$toArgsArray$default$1 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$1
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, functionBuilder$Body$$inlined$toArgsArray$default$1))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0]);
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> SyncFunctionComponent Body(String name, final Function2<? super P0, ? super P1, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$2 functionBuilder$Body$$inlined$toArgsArray$default$2 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$2
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$3 functionBuilder$Body$$inlined$toArgsArray$default$3 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$3
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, functionBuilder$Body$$inlined$toArgsArray$default$2)), new AnyType(new LazyKType(orCreateKotlinClass2, false, functionBuilder$Body$$inlined$toArgsArray$default$3))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1]);
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> SyncFunctionComponent Body(String name, final Function3<? super P0, ? super P1, ? super P2, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$4 functionBuilder$Body$$inlined$toArgsArray$default$4 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$4
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$5 functionBuilder$Body$$inlined$toArgsArray$default$5 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$5
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$6 functionBuilder$Body$$inlined$toArgsArray$default$6 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$6
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, functionBuilder$Body$$inlined$toArgsArray$default$4)), new AnyType(new LazyKType(orCreateKotlinClass2, false, functionBuilder$Body$$inlined$toArgsArray$default$5)), new AnyType(new LazyKType(orCreateKotlinClass3, false, functionBuilder$Body$$inlined$toArgsArray$default$6))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2]);
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> SyncFunctionComponent Body(String name, final Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$7 functionBuilder$Body$$inlined$toArgsArray$default$7 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$7
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$8 functionBuilder$Body$$inlined$toArgsArray$default$8 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$8
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$9 functionBuilder$Body$$inlined$toArgsArray$default$9 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$9
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$10 functionBuilder$Body$$inlined$toArgsArray$default$10 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$10
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, functionBuilder$Body$$inlined$toArgsArray$default$7)), new AnyType(new LazyKType(orCreateKotlinClass2, false, functionBuilder$Body$$inlined$toArgsArray$default$8)), new AnyType(new LazyKType(orCreateKotlinClass3, false, functionBuilder$Body$$inlined$toArgsArray$default$9)), new AnyType(new LazyKType(orCreateKotlinClass4, false, functionBuilder$Body$$inlined$toArgsArray$default$10))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> SyncFunctionComponent Body(String name, final Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$11 functionBuilder$Body$$inlined$toArgsArray$default$11 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$11
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$12 functionBuilder$Body$$inlined$toArgsArray$default$12 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$12
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$13 functionBuilder$Body$$inlined$toArgsArray$default$13 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$13
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$14 functionBuilder$Body$$inlined$toArgsArray$default$14 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$14
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$15 functionBuilder$Body$$inlined$toArgsArray$default$15 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$15
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, functionBuilder$Body$$inlined$toArgsArray$default$11)), new AnyType(new LazyKType(orCreateKotlinClass2, false, functionBuilder$Body$$inlined$toArgsArray$default$12)), new AnyType(new LazyKType(orCreateKotlinClass3, false, functionBuilder$Body$$inlined$toArgsArray$default$13)), new AnyType(new LazyKType(orCreateKotlinClass4, false, functionBuilder$Body$$inlined$toArgsArray$default$14)), new AnyType(new LazyKType(orCreateKotlinClass5, false, functionBuilder$Body$$inlined$toArgsArray$default$15))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SyncFunctionComponent Body(String name, final Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$16 functionBuilder$Body$$inlined$toArgsArray$default$16 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$16
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$17 functionBuilder$Body$$inlined$toArgsArray$default$17 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$17
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$18 functionBuilder$Body$$inlined$toArgsArray$default$18 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$18
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$19 functionBuilder$Body$$inlined$toArgsArray$default$19 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$19
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$20 functionBuilder$Body$$inlined$toArgsArray$default$20 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$20
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$21 functionBuilder$Body$$inlined$toArgsArray$default$21 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$21
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, functionBuilder$Body$$inlined$toArgsArray$default$16)), new AnyType(new LazyKType(orCreateKotlinClass2, false, functionBuilder$Body$$inlined$toArgsArray$default$17)), new AnyType(new LazyKType(orCreateKotlinClass3, false, functionBuilder$Body$$inlined$toArgsArray$default$18)), new AnyType(new LazyKType(orCreateKotlinClass4, false, functionBuilder$Body$$inlined$toArgsArray$default$19)), new AnyType(new LazyKType(orCreateKotlinClass5, false, functionBuilder$Body$$inlined$toArgsArray$default$20)), new AnyType(new LazyKType(orCreateKotlinClass6, false, functionBuilder$Body$$inlined$toArgsArray$default$21))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$15
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SyncFunctionComponent Body(String name, final Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$22 functionBuilder$Body$$inlined$toArgsArray$default$22 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$22
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$23 functionBuilder$Body$$inlined$toArgsArray$default$23 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$23
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$24 functionBuilder$Body$$inlined$toArgsArray$default$24 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$24
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$25 functionBuilder$Body$$inlined$toArgsArray$default$25 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$25
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$26 functionBuilder$Body$$inlined$toArgsArray$default$26 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$26
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$27 functionBuilder$Body$$inlined$toArgsArray$default$27 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$27
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$28 functionBuilder$Body$$inlined$toArgsArray$default$28 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$28
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, functionBuilder$Body$$inlined$toArgsArray$default$22)), new AnyType(new LazyKType(orCreateKotlinClass2, false, functionBuilder$Body$$inlined$toArgsArray$default$23)), new AnyType(new LazyKType(orCreateKotlinClass3, false, functionBuilder$Body$$inlined$toArgsArray$default$24)), new AnyType(new LazyKType(orCreateKotlinClass4, false, functionBuilder$Body$$inlined$toArgsArray$default$25)), new AnyType(new LazyKType(orCreateKotlinClass5, false, functionBuilder$Body$$inlined$toArgsArray$default$26)), new AnyType(new LazyKType(orCreateKotlinClass6, false, functionBuilder$Body$$inlined$toArgsArray$default$27)), new AnyType(new LazyKType(orCreateKotlinClass7, false, functionBuilder$Body$$inlined$toArgsArray$default$28))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$17
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SyncFunctionComponent Body(String name, final Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.reifiedOperationMarker(4, "P7");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$29 functionBuilder$Body$$inlined$toArgsArray$default$29 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$29
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$30 functionBuilder$Body$$inlined$toArgsArray$default$30 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$30
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$31 functionBuilder$Body$$inlined$toArgsArray$default$31 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$31
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$32 functionBuilder$Body$$inlined$toArgsArray$default$32 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$32
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$33 functionBuilder$Body$$inlined$toArgsArray$default$33 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$33
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$34 functionBuilder$Body$$inlined$toArgsArray$default$34 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$34
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$35 functionBuilder$Body$$inlined$toArgsArray$default$35 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$35
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        Intrinsics.needClassReification();
        FunctionBuilder$Body$$inlined$toArgsArray$default$36 functionBuilder$Body$$inlined$toArgsArray$default$36 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$$inlined$toArgsArray$default$36
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P7");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, functionBuilder$Body$$inlined$toArgsArray$default$29)), new AnyType(new LazyKType(orCreateKotlinClass2, false, functionBuilder$Body$$inlined$toArgsArray$default$30)), new AnyType(new LazyKType(orCreateKotlinClass3, false, functionBuilder$Body$$inlined$toArgsArray$default$31)), new AnyType(new LazyKType(orCreateKotlinClass4, false, functionBuilder$Body$$inlined$toArgsArray$default$32)), new AnyType(new LazyKType(orCreateKotlinClass5, false, functionBuilder$Body$$inlined$toArgsArray$default$33)), new AnyType(new LazyKType(orCreateKotlinClass6, false, functionBuilder$Body$$inlined$toArgsArray$default$34)), new AnyType(new LazyKType(orCreateKotlinClass7, false, functionBuilder$Body$$inlined$toArgsArray$default$35)), new AnyType(new LazyKType(orCreateKotlinClass8, false, functionBuilder$Body$$inlined$toArgsArray$default$36))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.functions.FunctionBuilder$Body$19
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] objArr) {
                Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                return body.invoke(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
        });
        setFunctionComponent(syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final SyncFunctionComponent build$expo_modules_core_release() {
        SyncFunctionComponent syncFunctionComponent = this.functionComponent;
        if (syncFunctionComponent != null) {
            return syncFunctionComponent;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }
}

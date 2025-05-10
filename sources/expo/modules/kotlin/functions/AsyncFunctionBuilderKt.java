package expo.modules.kotlin.functions;

import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a:\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u001e\b\u0004\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\u0086\f¢\u0006\u0002\u0010\b\u001aH\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001*\u00020\u00032$\b\u0004\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000bH\u0086\f¢\u0006\u0002\u0010\f\u001aV\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001*\u00020\u00032*\b\u0004\u0010\u0004\u001a$\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000eH\u0086\f¢\u0006\u0002\u0010\u000f\u001ad\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001*\u00020\u000320\b\u0004\u0010\u0004\u001a*\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0011H\u0086\f¢\u0006\u0002\u0010\u0012\u001ar\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001*\u00020\u000326\b\u0004\u0010\u0004\u001a0\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0014H\u0086\f¢\u0006\u0002\u0010\u0015\u001a\u0080\u0001\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001\"\u0006\b\u0005\u0010\u0016\u0018\u0001*\u00020\u00032<\b\u0004\u0010\u0004\u001a6\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0017H\u0086\f¢\u0006\u0002\u0010\u0018\u001a\u008e\u0001\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001\"\u0006\b\u0005\u0010\u0016\u0018\u0001\"\u0006\b\u0006\u0010\u0019\u0018\u0001*\u00020\u00032B\b\u0004\u0010\u0004\u001a<\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001aH\u0086\f¢\u0006\u0002\u0010\u001b\u001a\u009c\u0001\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001\"\u0006\b\u0005\u0010\u0016\u0018\u0001\"\u0006\b\u0006\u0010\u0019\u0018\u0001\"\u0006\b\u0007\u0010\u001c\u0018\u0001*\u00020\u00032H\b\u0004\u0010\u0004\u001aB\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001dH\u0086\f¢\u0006\u0002\u0010\u001e\u001aª\u0001\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001\"\u0006\b\u0005\u0010\u0016\u0018\u0001\"\u0006\b\u0006\u0010\u0019\u0018\u0001\"\u0006\b\u0007\u0010\u001c\u0018\u0001\"\u0006\b\b\u0010\u001f\u0018\u0001*\u00020\u00032N\b\u0004\u0010\u0004\u001aH\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070 H\u0086\f¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"Coroutine", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "R", "Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function1;)Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P0", "Lkotlin/Function2;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function2;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P1", "Lkotlin/Function3;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function3;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P2", "Lkotlin/Function4;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function4;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P3", "Lkotlin/Function5;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function5;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P4", "Lkotlin/Function6;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function6;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P5", "Lkotlin/Function7;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function7;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P6", "Lkotlin/Function8;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function8;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P7", "Lkotlin/Function9;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function9;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AsyncFunctionBuilderKt {
    public static final /* synthetic */ <R> BaseAsyncFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function1<? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(asyncFunctionBuilder.getName(), new AnyType[0], new AsyncFunctionBuilder$SuspendBody$1(block, null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function2<? super P0, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.needClassReification();
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$1 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$1 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$1
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P0");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$1))}, new AsyncFunctionBuilder$SuspendBody$3(block, null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function3<? super P0, ? super P1, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.needClassReification();
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$2 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$2 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$2
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P1");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$2)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3))}, new AsyncFunctionBuilder$SuspendBody$5(block, null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function4<? super P0, ? super P1, ? super P2, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.needClassReification();
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$4 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$4 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$4
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$6 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$6 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$6
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P2");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$4)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$6))}, new AsyncFunctionBuilder$SuspendBody$7(block, null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.needClassReification();
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$7 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$7 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$7
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$8 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$8 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$8
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$10 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$10 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$10
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P3");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$7)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$8)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$10))}, new AsyncFunctionBuilder$SuspendBody$9(block, null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.needClassReification();
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$11 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$11 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$11
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$12 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$12 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$12
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$13 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$13 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$13
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$14 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$14 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$14
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$15 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$15 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$15
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P4");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$11)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$12)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$13)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$14)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$15))}, new AsyncFunctionBuilder$SuspendBody$11(block, null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.needClassReification();
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$16 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$16 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$16
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$17 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$17 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$17
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$18 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$18 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$18
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$19 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$19 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$19
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$20 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$20 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$20
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$21 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$21 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$21
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P5");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$16)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$17)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$18)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$19)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$20)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$21))}, new AsyncFunctionBuilder$SuspendBody$13(block, null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.needClassReification();
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$22 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$22 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$22
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$23 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$23 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$23
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$24 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$24 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$24
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$25 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$25 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$25
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$26 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$26 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$26
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$27 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$27 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$27
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$28 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$28 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$28
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P6");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$22)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$23)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$24)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$25)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$26)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$27)), new AnyType(new LazyKType(orCreateKotlinClass7, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$28))}, new AsyncFunctionBuilder$SuspendBody$15(block, null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function9<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(4, "P0");
        Intrinsics.reifiedOperationMarker(4, "P1");
        Intrinsics.reifiedOperationMarker(4, "P2");
        Intrinsics.reifiedOperationMarker(4, "P3");
        Intrinsics.reifiedOperationMarker(4, "P4");
        Intrinsics.reifiedOperationMarker(4, "P5");
        Intrinsics.reifiedOperationMarker(4, "P6");
        Intrinsics.reifiedOperationMarker(4, "P7");
        Intrinsics.needClassReification();
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$29 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$29 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$29
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$30 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$30 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$30
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$31 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$31 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$31
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$32 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$32 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$32
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$33 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$33 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$33
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$34 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$34 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$34
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$35 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$35 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$35
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
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$36 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$36 = new Function0<KType>() { // from class: expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$36
            @Override // kotlin.jvm.functions.Function0
            public final KType invoke() {
                Intrinsics.reifiedOperationMarker(6, "P7");
                return null;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, new AnyType[]{new AnyType(new LazyKType(orCreateKotlinClass, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$29)), new AnyType(new LazyKType(orCreateKotlinClass2, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$30)), new AnyType(new LazyKType(orCreateKotlinClass3, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$31)), new AnyType(new LazyKType(orCreateKotlinClass4, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$32)), new AnyType(new LazyKType(orCreateKotlinClass5, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$33)), new AnyType(new LazyKType(orCreateKotlinClass6, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$34)), new AnyType(new LazyKType(orCreateKotlinClass7, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$35)), new AnyType(new LazyKType(orCreateKotlinClass8, false, asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$36))}, new AsyncFunctionBuilder$SuspendBody$17(block, null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }
}

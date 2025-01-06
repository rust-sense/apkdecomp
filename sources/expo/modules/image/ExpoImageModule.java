package expo.modules.image;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.FutureTarget;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import com.facebook.yoga.YogaConstants;
import expo.modules.image.enums.ContentFit;
import expo.modules.image.enums.Priority;
import expo.modules.image.records.CachePolicy;
import expo.modules.image.records.ContentPosition;
import expo.modules.image.records.DecodeFormat;
import expo.modules.image.records.ImageTransition;
import expo.modules.image.records.SourceMap;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.Queues;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.views.ConcreteViewProp;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import io.sentry.protocol.SentryThread;
import java.io.File;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: ExpoImageModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/image/ExpoImageModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoImageModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionComponent asyncFunctionComponent3;
        AsyncFunctionComponent asyncFunctionComponent4;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        AsyncFunctionComponent asyncFunctionComponent5;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        ExpoImageModule expoImageModule = this;
        androidx.tracing.Trace.beginSection("[ExpoModulesCore] " + (expoImageModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(expoImageModule);
            moduleDefinitionBuilder.Name("ExpoImage");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$OnCreate$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Context reactContext = ExpoImageModule.this.getAppContext().getReactContext();
                    if (reactContext != null) {
                        reactContext.registerComponentCallbacks(ExpoImageComponentCallbacks.INSTANCE);
                    }
                }
            }));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new Function0<Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$OnDestroy$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Context reactContext = ExpoImageModule.this.getAppContext().getReactContext();
                    if (reactContext != null) {
                        reactContext.unregisterComponentCallbacks(ExpoImageComponentCallbacks.INSTANCE);
                    }
                }
            }));
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("prefetch", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(List.class), false, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunctionWithPromise$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)));
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(CachePolicy.class), false, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunctionWithPromise$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(CachePolicy.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunctionWithPromise$3
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Map.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)));
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunctionWithPromise$4
                {
                    super(2);
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0074  */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke2(java.lang.Object[] r12, final expo.modules.kotlin.Promise r13) {
                    /*
                        r11 = this;
                        java.lang.String r0 = "<name for destructuring parameter 0>"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
                        java.lang.String r0 = "promise"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
                        r0 = 0
                        r1 = r12[r0]
                        r2 = 1
                        r3 = r12[r2]
                        r4 = 2
                        r12 = r12[r4]
                        java.util.Map r12 = (java.util.Map) r12
                        expo.modules.image.records.CachePolicy r3 = (expo.modules.image.records.CachePolicy) r3
                        java.util.List r1 = (java.util.List) r1
                        expo.modules.image.ExpoImageModule r4 = expo.modules.image.ExpoImageModule.this
                        expo.modules.kotlin.AppContext r4 = r4.getAppContext()
                        android.content.Context r4 = r4.getReactContext()
                        if (r4 != 0) goto L27
                        goto Lbc
                    L27:
                        kotlin.jvm.internal.Ref$IntRef r5 = new kotlin.jvm.internal.Ref$IntRef
                        r5.<init>()
                        kotlin.jvm.internal.Ref$BooleanRef r6 = new kotlin.jvm.internal.Ref$BooleanRef
                        r6.<init>()
                        if (r12 == 0) goto L65
                        com.bumptech.glide.load.model.LazyHeaders$Builder r7 = new com.bumptech.glide.load.model.LazyHeaders$Builder
                        r7.<init>()
                        java.util.Set r12 = r12.entrySet()
                        java.util.Iterator r12 = r12.iterator()
                    L40:
                        boolean r8 = r12.hasNext()
                        if (r8 == 0) goto L5c
                        java.lang.Object r8 = r12.next()
                        java.util.Map$Entry r8 = (java.util.Map.Entry) r8
                        java.lang.Object r9 = r8.getKey()
                        java.lang.String r9 = (java.lang.String) r9
                        java.lang.Object r8 = r8.getValue()
                        java.lang.String r8 = (java.lang.String) r8
                        r7.addHeader(r9, r8)
                        goto L40
                    L5c:
                        com.bumptech.glide.load.model.LazyHeaders r12 = r7.build()
                        if (r12 == 0) goto L65
                        com.bumptech.glide.load.model.Headers r12 = (com.bumptech.glide.load.model.Headers) r12
                        goto L67
                    L65:
                        com.bumptech.glide.load.model.Headers r12 = com.bumptech.glide.load.model.Headers.DEFAULT
                    L67:
                        r7 = r1
                        java.lang.Iterable r7 = (java.lang.Iterable) r7
                        java.util.Iterator r7 = r7.iterator()
                    L6e:
                        boolean r8 = r7.hasNext()
                        if (r8 == 0) goto Lbc
                        java.lang.Object r8 = r7.next()
                        java.lang.String r8 = (java.lang.String) r8
                        com.bumptech.glide.RequestManager r9 = com.bumptech.glide.Glide.with(r4)
                        com.bumptech.glide.load.model.GlideUrl r10 = new com.bumptech.glide.load.model.GlideUrl
                        r10.<init>(r8, r12)
                        com.bumptech.glide.RequestBuilder r8 = r9.load(r10)
                        r9 = 100
                        com.bumptech.glide.request.BaseRequestOptions r8 = r8.encodeQuality(r9)
                        com.bumptech.glide.RequestBuilder r8 = (com.bumptech.glide.RequestBuilder) r8
                        expo.modules.image.NoopDownsampleStrategy r9 = expo.modules.image.NoopDownsampleStrategy.INSTANCE
                        com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r9 = (com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r9
                        com.bumptech.glide.request.BaseRequestOptions r8 = r8.downsample(r9)
                        java.lang.String r9 = "downsample(...)"
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
                        com.bumptech.glide.RequestBuilder r8 = (com.bumptech.glide.RequestBuilder) r8
                        expo.modules.image.records.CachePolicy r9 = expo.modules.image.records.CachePolicy.MEMORY
                        if (r3 != r9) goto La4
                        r9 = r2
                        goto La5
                    La4:
                        r9 = r0
                    La5:
                        expo.modules.image.ExpoImageModule$definition$1$3$1$1 r10 = new kotlin.jvm.functions.Function1<com.bumptech.glide.RequestBuilder<android.graphics.drawable.Drawable>, com.bumptech.glide.RequestBuilder<android.graphics.drawable.Drawable>>() { // from class: expo.modules.image.ExpoImageModule$definition$1$3$1$1
                            static {
                                /*
                                    expo.modules.image.ExpoImageModule$definition$1$3$1$1 r0 = new expo.modules.image.ExpoImageModule$definition$1$3$1$1
                                    r0.<init>()
                                    
                                    // error: 0x0005: SPUT (r0 I:expo.modules.image.ExpoImageModule$definition$1$3$1$1) expo.modules.image.ExpoImageModule$definition$1$3$1$1.INSTANCE expo.modules.image.ExpoImageModule$definition$1$3$1$1
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: expo.modules.image.ExpoImageModule$definition$1$3$1$1.<clinit>():void");
                            }

                            {
                                /*
                                    r1 = this;
                                    r0 = 1
                                    r1.<init>(r0)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: expo.modules.image.ExpoImageModule$definition$1$3$1$1.<init>():void");
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ com.bumptech.glide.RequestBuilder<android.graphics.drawable.Drawable> invoke(com.bumptech.glide.RequestBuilder<android.graphics.drawable.Drawable> r1) {
                                /*
                                    r0 = this;
                                    com.bumptech.glide.RequestBuilder r1 = (com.bumptech.glide.RequestBuilder) r1
                                    com.bumptech.glide.RequestBuilder r1 = r0.invoke(r1)
                                    return r1
                                */
                                throw new UnsupportedOperationException("Method not decompiled: expo.modules.image.ExpoImageModule$definition$1$3$1$1.invoke(java.lang.Object):java.lang.Object");
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final com.bumptech.glide.RequestBuilder<android.graphics.drawable.Drawable> invoke(com.bumptech.glide.RequestBuilder<android.graphics.drawable.Drawable> r2) {
                                /*
                                    r1 = this;
                                    java.lang.String r0 = "$this$customize"
                                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                                    com.bumptech.glide.load.engine.DiskCacheStrategy r0 = com.bumptech.glide.load.engine.DiskCacheStrategy.NONE
                                    com.bumptech.glide.request.BaseRequestOptions r2 = r2.diskCacheStrategy(r0)
                                    java.lang.String r0 = "diskCacheStrategy(...)"
                                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
                                    com.bumptech.glide.RequestBuilder r2 = (com.bumptech.glide.RequestBuilder) r2
                                    return r2
                                */
                                throw new UnsupportedOperationException("Method not decompiled: expo.modules.image.ExpoImageModule$definition$1$3$1$1.invoke(com.bumptech.glide.RequestBuilder):com.bumptech.glide.RequestBuilder");
                            }
                        }
                        kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
                        com.bumptech.glide.RequestBuilder r8 = expo.modules.image.GlideExtensionsKt.customize(r8, r9, r10)
                        expo.modules.image.ExpoImageModule$definition$1$3$1$2 r9 = new expo.modules.image.ExpoImageModule$definition$1$3$1$2
                        r9.<init>()
                        com.bumptech.glide.request.RequestListener r9 = (com.bumptech.glide.request.RequestListener) r9
                        com.bumptech.glide.RequestBuilder r8 = r8.listener(r9)
                        r8.submit()
                        goto L6e
                    Lbc:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunctionWithPromise$4.invoke2(java.lang.Object[], expo.modules.kotlin.Promise):void");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("prefetch", asyncFunctionWithPromiseComponent3);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent4 = asyncFunctionWithPromiseComponent3;
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr = new AnyType[0];
            Function1<Object[], Boolean> function1 = new Function1<Object[], Boolean>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunction$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object[] it) {
                    boolean z;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Activity currentActivity = ExpoImageModule.this.getAppContext().getCurrentActivity();
                    if (currentActivity == null) {
                        z = false;
                    } else {
                        Glide.get(currentActivity).clearMemory();
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            };
            if (!Intrinsics.areEqual(Boolean.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Boolean.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Boolean.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Boolean.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Boolean.class, String.class)) {
                                asyncFunctionComponent = new StringAsyncFunctionComponent("clearMemoryCache", anyTypeArr, function1);
                            } else {
                                asyncFunctionComponent = new AsyncFunctionComponent("clearMemoryCache", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new FloatAsyncFunctionComponent("clearMemoryCache", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new DoubleAsyncFunctionComponent("clearMemoryCache", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new BoolAsyncFunctionComponent("clearMemoryCache", anyTypeArr, function1);
                }
            } else {
                asyncFunctionComponent = new IntAsyncFunctionComponent("clearMemoryCache", anyTypeArr, function1);
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("clearMemoryCache", asyncFunctionComponent);
            asyncFunctionComponent.runOnQueue(Queues.MAIN);
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr2 = new AnyType[0];
            Function1<Object[], Boolean> function12 = new Function1<Object[], Boolean>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunction$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object[] it) {
                    boolean z;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Activity currentActivity = ExpoImageModule.this.getAppContext().getCurrentActivity();
                    if (currentActivity == null) {
                        z = false;
                    } else {
                        Glide.get(currentActivity).clearDiskCache();
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            };
            if (!Intrinsics.areEqual(Boolean.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Boolean.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Boolean.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Boolean.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Boolean.class, String.class)) {
                                asyncFunctionComponent2 = new StringAsyncFunctionComponent("clearDiskCache", anyTypeArr2, function12);
                            } else {
                                asyncFunctionComponent2 = new AsyncFunctionComponent("clearDiskCache", anyTypeArr2, function12);
                            }
                        } else {
                            asyncFunctionComponent2 = new FloatAsyncFunctionComponent("clearDiskCache", anyTypeArr2, function12);
                        }
                    } else {
                        asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("clearDiskCache", anyTypeArr2, function12);
                    }
                } else {
                    asyncFunctionComponent2 = new BoolAsyncFunctionComponent("clearDiskCache", anyTypeArr2, function12);
                }
            } else {
                asyncFunctionComponent2 = new IntAsyncFunctionComponent("clearDiskCache", anyTypeArr2, function12);
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("clearDiskCache", asyncFunctionComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionComponent3 = new AsyncFunctionWithPromiseComponent("getCachePathAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunction$3
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        String str = (String) promise;
                        Context reactContext = ExpoImageModule.this.getAppContext().getReactContext();
                        if (reactContext == null) {
                            return;
                        }
                        FutureTarget submit = Glide.with(reactContext).asFile().load((Object) new GlideUrl(str)).onlyRetrieveFromCache(true).submit();
                        Intrinsics.checkNotNullExpressionValue(submit, "submit(...)");
                        try {
                            ((File) submit.get()).getAbsolutePath();
                        } catch (Exception unused) {
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                asyncFunctionComponent3 = new AsyncFunctionComponent("getCachePathAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunction$4
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))}, new Function1<Object[], String>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$AsyncFunction$5
                    {
                        super(1);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        String str = (String) objArr[0];
                        Context reactContext = ExpoImageModule.this.getAppContext().getReactContext();
                        if (reactContext == null) {
                            return null;
                        }
                        FutureTarget submit = Glide.with(reactContext).asFile().load((Object) new GlideUrl(str)).onlyRetrieveFromCache(true).submit();
                        Intrinsics.checkNotNullExpressionValue(submit, "submit(...)");
                        try {
                            return ((File) submit.get()).getAbsolutePath();
                        } catch (Exception unused) {
                            return null;
                        }
                    }
                });
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("getCachePathAsync", asyncFunctionComponent3);
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ExpoImageViewWrapper.class);
            if (moduleDefinitionBuilder.getViewManagerDefinition() != null) {
                throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
            }
            ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass, new LazyKType(Reflection.getOrCreateKotlinClass(ExpoImageViewWrapper.class), false, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$$inlined$View$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(ExpoImageViewWrapper.class);
                }
            }, 2, null));
            viewDefinitionBuilder.Events("onLoadStart", "onProgress", "onError", "onLoad");
            viewDefinitionBuilder.getProps().put("source", new ConcreteViewProp("source", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(List.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(SourceMap.class)));
                }
            })), new Function2<ExpoImageViewWrapper, List<? extends SourceMap>, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, List<? extends SourceMap> list) {
                    invoke2(expoImageViewWrapper, (List<SourceMap>) list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, List<SourceMap> list) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (list == null) {
                        list = CollectionsKt.emptyList();
                    }
                    view.setSources$expo_image_release(list);
                }
            }));
            viewDefinitionBuilder.getProps().put("contentFit", new ConcreteViewProp("contentFit", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ContentFit.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(ContentFit.class);
                }
            })), new Function2<ExpoImageViewWrapper, ContentFit, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, ContentFit contentFit) {
                    invoke2(expoImageViewWrapper, contentFit);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, ContentFit contentFit) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (contentFit == null) {
                        contentFit = ContentFit.Cover;
                    }
                    view.setContentFit$expo_image_release(contentFit);
                }
            }));
            viewDefinitionBuilder.getProps().put("placeholderContentFit", new ConcreteViewProp("placeholderContentFit", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ContentFit.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$3
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(ContentFit.class);
                }
            })), new Function2<ExpoImageViewWrapper, ContentFit, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, ContentFit contentFit) {
                    invoke2(expoImageViewWrapper, contentFit);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, ContentFit contentFit) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (contentFit == null) {
                        contentFit = ContentFit.ScaleDown;
                    }
                    view.setPlaceholderContentFit$expo_image_release(contentFit);
                }
            }));
            viewDefinitionBuilder.getProps().put("contentPosition", new ConcreteViewProp("contentPosition", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ContentPosition.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$4
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(ContentPosition.class);
                }
            })), new Function2<ExpoImageViewWrapper, ContentPosition, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, ContentPosition contentPosition) {
                    invoke2(expoImageViewWrapper, contentPosition);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, ContentPosition contentPosition) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (contentPosition == null) {
                        contentPosition = ContentPosition.INSTANCE.getCenter();
                    }
                    view.setContentPosition$expo_image_release(contentPosition);
                }
            }));
            viewDefinitionBuilder.getProps().put("blurRadius", new ConcreteViewProp("blurRadius", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$5
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Integer.class);
                }
            })), new Function2<ExpoImageViewWrapper, Integer, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$5
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Integer num) {
                    invoke2(expoImageViewWrapper, num);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, Integer num) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (num == null || num.intValue() <= 0) {
                        num = null;
                    }
                    view.setBlurRadius$expo_image_release(num);
                }
            }));
            viewDefinitionBuilder.getProps().put("transition", new ConcreteViewProp("transition", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ImageTransition.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$6
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(ImageTransition.class);
                }
            })), new Function2<ExpoImageViewWrapper, ImageTransition, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$6
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, ImageTransition imageTransition) {
                    invoke2(expoImageViewWrapper, imageTransition);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, ImageTransition imageTransition) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setTransition$expo_image_release(imageTransition);
                }
            }));
            int i = 9;
            int i2 = 7;
            Pair[] pairArr = {TuplesKt.to("borderRadius", 0), TuplesKt.to(ViewProps.BORDER_TOP_LEFT_RADIUS, 1), TuplesKt.to(ViewProps.BORDER_TOP_RIGHT_RADIUS, 2), TuplesKt.to(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, 3), TuplesKt.to(ViewProps.BORDER_BOTTOM_LEFT_RADIUS, 4), TuplesKt.to(ViewProps.BORDER_TOP_START_RADIUS, 5), TuplesKt.to(ViewProps.BORDER_TOP_END_RADIUS, 6), TuplesKt.to(ViewProps.BORDER_BOTTOM_START_RADIUS, 7), TuplesKt.to(ViewProps.BORDER_BOTTOM_END_RADIUS, 8)};
            final ExpoImageModule$definition$1$7$7 expoImageModule$definition$1$7$7 = new Function3<ExpoImageViewWrapper, Integer, Float, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$7
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Integer num, Float f) {
                    invoke(expoImageViewWrapper, num.intValue(), f);
                    return Unit.INSTANCE;
                }

                public final void invoke(ExpoImageViewWrapper view, int i3, Float f) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setBorderRadius(i3, YogaUtilsKt.makeYogaUndefinedIfNegative(f != null ? f.floatValue() : Float.NaN));
                }
            };
            int i3 = 0;
            while (i3 < i) {
                Pair pair = pairArr[i3];
                String str = (String) pair.component1();
                final Object component2 = pair.component2();
                viewDefinitionBuilder.getProps().put(str, new ConcreteViewProp(str, new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$PropGroup$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(Float.class);
                    }
                })), new Function2<ExpoImageViewWrapper, Float, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$PropGroup$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Float f) {
                        invoke(expoImageViewWrapper, f);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(ExpoImageViewWrapper view, Float f) {
                        Intrinsics.checkNotNullParameter(view, "view");
                        Function3.this.invoke(view, component2, f);
                    }
                }));
                i3++;
                i = 9;
                i2 = 7;
            }
            Pair[] pairArr2 = new Pair[i2];
            pairArr2[0] = TuplesKt.to(ViewProps.BORDER_WIDTH, 8);
            pairArr2[1] = TuplesKt.to(ViewProps.BORDER_LEFT_WIDTH, 0);
            pairArr2[2] = TuplesKt.to(ViewProps.BORDER_RIGHT_WIDTH, 2);
            pairArr2[3] = TuplesKt.to(ViewProps.BORDER_TOP_WIDTH, 1);
            pairArr2[4] = TuplesKt.to(ViewProps.BORDER_BOTTOM_WIDTH, 3);
            pairArr2[5] = TuplesKt.to(ViewProps.BORDER_START_WIDTH, 4);
            pairArr2[6] = TuplesKt.to(ViewProps.BORDER_END_WIDTH, 5);
            final ExpoImageModule$definition$1$7$8 expoImageModule$definition$1$7$8 = new Function3<ExpoImageViewWrapper, Integer, Float, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$8
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Integer num, Float f) {
                    invoke(expoImageViewWrapper, num.intValue(), f);
                    return Unit.INSTANCE;
                }

                public final void invoke(ExpoImageViewWrapper view, int i4, Float f) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    float makeYogaUndefinedIfNegative = YogaUtilsKt.makeYogaUndefinedIfNegative(f != null ? f.floatValue() : Float.NaN);
                    if (!YogaConstants.isUndefined(makeYogaUndefinedIfNegative)) {
                        makeYogaUndefinedIfNegative = PixelUtil.toPixelFromDIP(makeYogaUndefinedIfNegative);
                    }
                    view.setBorderWidth(i4, makeYogaUndefinedIfNegative);
                }
            };
            for (int i4 = 0; i4 < 7; i4++) {
                Pair pair2 = pairArr2[i4];
                String str2 = (String) pair2.component1();
                final Object component22 = pair2.component2();
                viewDefinitionBuilder.getProps().put(str2, new ConcreteViewProp(str2, new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$PropGroup$4
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(Float.class);
                    }
                })), new Function2<ExpoImageViewWrapper, Float, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$PropGroup$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Float f) {
                        invoke(expoImageViewWrapper, f);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(ExpoImageViewWrapper view, Float f) {
                        Intrinsics.checkNotNullParameter(view, "view");
                        Function3.this.invoke(view, component22, f);
                    }
                }));
            }
            Pair[] pairArr3 = {TuplesKt.to(ViewProps.BORDER_COLOR, 8), TuplesKt.to(ViewProps.BORDER_LEFT_COLOR, 0), TuplesKt.to(ViewProps.BORDER_RIGHT_COLOR, 2), TuplesKt.to(ViewProps.BORDER_TOP_COLOR, 1), TuplesKt.to(ViewProps.BORDER_BOTTOM_COLOR, 3), TuplesKt.to(ViewProps.BORDER_START_COLOR, 4), TuplesKt.to(ViewProps.BORDER_END_COLOR, 5)};
            final ExpoImageModule$definition$1$7$9 expoImageModule$definition$1$7$9 = new Function3<ExpoImageViewWrapper, Integer, Integer, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$9
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Integer num, Integer num2) {
                    invoke(expoImageViewWrapper, num.intValue(), num2);
                    return Unit.INSTANCE;
                }

                public final void invoke(ExpoImageViewWrapper view, int i5, Integer num) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setBorderColor(i5, num == null ? Float.NaN : num.intValue() & ViewCompat.MEASURED_SIZE_MASK, num != null ? num.intValue() >>> 24 : Float.NaN);
                }
            };
            for (int i5 = 0; i5 < 7; i5++) {
                Pair pair3 = pairArr3[i5];
                String str3 = (String) pair3.component1();
                final Object component23 = pair3.component2();
                viewDefinitionBuilder.getProps().put(str3, new ConcreteViewProp(str3, new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$PropGroup$6
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(Integer.class);
                    }
                })), new Function2<ExpoImageViewWrapper, Integer, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$PropGroup$5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Integer num) {
                        invoke(expoImageViewWrapper, num);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(ExpoImageViewWrapper view, Integer num) {
                        Intrinsics.checkNotNullParameter(view, "view");
                        Function3.this.invoke(view, component23, num);
                    }
                }));
            }
            viewDefinitionBuilder.getProps().put("borderStyle", new ConcreteViewProp("borderStyle", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$7
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            })), new Function2<ExpoImageViewWrapper, String, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$10
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, String str4) {
                    invoke2(expoImageViewWrapper, str4);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, String str4) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setBorderStyle$expo_image_release(str4);
                }
            }));
            viewDefinitionBuilder.getProps().put(ViewProps.BACKGROUND_COLOR, new ConcreteViewProp(ViewProps.BACKGROUND_COLOR, new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$8
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Integer.class);
                }
            })), new Function2<ExpoImageViewWrapper, Integer, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$11
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Integer num) {
                    invoke2(expoImageViewWrapper, num);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, Integer num) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setBackgroundColor$expo_image_release(num);
                }
            }));
            viewDefinitionBuilder.getProps().put("tintColor", new ConcreteViewProp("tintColor", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$9
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Integer.class);
                }
            })), new Function2<ExpoImageViewWrapper, Integer, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$12
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Integer num) {
                    invoke2(expoImageViewWrapper, num);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, Integer num) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setTintColor$expo_image_release(num);
                }
            }));
            viewDefinitionBuilder.getProps().put(ReactTextInputShadowNode.PROP_PLACEHOLDER, new ConcreteViewProp(ReactTextInputShadowNode.PROP_PLACEHOLDER, new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(List.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$10
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(SourceMap.class)));
                }
            })), new Function2<ExpoImageViewWrapper, List<? extends SourceMap>, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$13
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, List<? extends SourceMap> list) {
                    invoke2(expoImageViewWrapper, (List<SourceMap>) list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, List<SourceMap> list) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (list == null) {
                        list = CollectionsKt.emptyList();
                    }
                    view.setPlaceholders$expo_image_release(list);
                }
            }));
            viewDefinitionBuilder.getProps().put("accessible", new ConcreteViewProp("accessible", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$11
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Boolean.class);
                }
            })), new Function2<ExpoImageViewWrapper, Boolean, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$14
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Boolean bool) {
                    invoke2(expoImageViewWrapper, bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, Boolean bool) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setAccessible$expo_image_release(bool != null ? bool.booleanValue() : false);
                }
            }));
            viewDefinitionBuilder.getProps().put(ViewProps.ACCESSIBILITY_LABEL, new ConcreteViewProp(ViewProps.ACCESSIBILITY_LABEL, new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$12
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            })), new Function2<ExpoImageViewWrapper, String, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$15
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, String str4) {
                    invoke2(expoImageViewWrapper, str4);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, String str4) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setAccessibilityLabel$expo_image_release(str4);
                }
            }));
            viewDefinitionBuilder.getProps().put("focusable", new ConcreteViewProp("focusable", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$13
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Boolean.class);
                }
            })), new Function2<ExpoImageViewWrapper, Boolean, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$16
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Boolean bool) {
                    invoke2(expoImageViewWrapper, bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, Boolean bool) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setFocusableProp$expo_image_release(bool != null ? bool.booleanValue() : false);
                }
            }));
            viewDefinitionBuilder.getProps().put(SentryThread.JsonKeys.PRIORITY, new ConcreteViewProp(SentryThread.JsonKeys.PRIORITY, new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Priority.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$14
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Priority.class);
                }
            })), new Function2<ExpoImageViewWrapper, Priority, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$17
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Priority priority) {
                    invoke2(expoImageViewWrapper, priority);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, Priority priority) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (priority == null) {
                        priority = Priority.NORMAL;
                    }
                    view.setPriority$expo_image_release(priority);
                }
            }));
            viewDefinitionBuilder.getProps().put("cachePolicy", new ConcreteViewProp("cachePolicy", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(CachePolicy.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$15
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(CachePolicy.class);
                }
            })), new Function2<ExpoImageViewWrapper, CachePolicy, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$18
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, CachePolicy cachePolicy) {
                    invoke2(expoImageViewWrapper, cachePolicy);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, CachePolicy cachePolicy) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (cachePolicy == null) {
                        cachePolicy = CachePolicy.DISK;
                    }
                    view.setCachePolicy$expo_image_release(cachePolicy);
                }
            }));
            viewDefinitionBuilder.getProps().put("recyclingKey", new ConcreteViewProp("recyclingKey", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$16
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            })), new Function2<ExpoImageViewWrapper, String, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$19
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, String str4) {
                    invoke2(expoImageViewWrapper, str4);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, String str4) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setRecyclingKey(str4);
                }
            }));
            viewDefinitionBuilder.getProps().put("allowDownscaling", new ConcreteViewProp("allowDownscaling", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$17
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Boolean.class);
                }
            })), new Function2<ExpoImageViewWrapper, Boolean, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$20
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Boolean bool) {
                    invoke2(expoImageViewWrapper, bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, Boolean bool) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setAllowDownscaling$expo_image_release(bool != null ? bool.booleanValue() : true);
                }
            }));
            viewDefinitionBuilder.getProps().put("autoplay", new ConcreteViewProp("autoplay", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Boolean.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$18
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(Boolean.class);
                }
            })), new Function2<ExpoImageViewWrapper, Boolean, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$21
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, Boolean bool) {
                    invoke2(expoImageViewWrapper, bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, Boolean bool) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.setAutoplay$expo_image_release(bool != null ? bool.booleanValue() : true);
                }
            }));
            viewDefinitionBuilder.getProps().put("decodeFormat", new ConcreteViewProp("decodeFormat", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DecodeFormat.class), true, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$Prop$19
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(DecodeFormat.class);
                }
            })), new Function2<ExpoImageViewWrapper, DecodeFormat, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$1$7$22
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ExpoImageViewWrapper expoImageViewWrapper, DecodeFormat decodeFormat) {
                    invoke2(expoImageViewWrapper, decodeFormat);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ExpoImageViewWrapper view, DecodeFormat decodeFormat) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (decodeFormat == null) {
                        decodeFormat = DecodeFormat.ARGB_8888;
                    }
                    view.setDecodeFormat$expo_image_release(decodeFormat);
                }
            }));
            if (ExpoImageViewWrapper.class == Promise.class) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("startAnimating", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$AsyncFunction$1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        ((ExpoImageViewWrapper) promise).setIsAnimating(true);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr3 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ExpoImageViewWrapper.class), false, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(ExpoImageViewWrapper.class);
                    }
                }))};
                Function1<Object[], Unit> function13 = new Function1<Object[], Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$AsyncFunction$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        ((ExpoImageViewWrapper) objArr[0]).setIsAnimating(true);
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent4 = new StringAsyncFunctionComponent("startAnimating", anyTypeArr3, function13);
                                } else {
                                    asyncFunctionComponent4 = new AsyncFunctionComponent("startAnimating", anyTypeArr3, function13);
                                }
                            } else {
                                asyncFunctionComponent4 = new FloatAsyncFunctionComponent("startAnimating", anyTypeArr3, function13);
                            }
                        } else {
                            asyncFunctionComponent4 = new DoubleAsyncFunctionComponent("startAnimating", anyTypeArr3, function13);
                        }
                    } else {
                        asyncFunctionComponent4 = new BoolAsyncFunctionComponent("startAnimating", anyTypeArr3, function13);
                    }
                } else {
                    asyncFunctionComponent4 = new IntAsyncFunctionComponent("startAnimating", anyTypeArr3, function13);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent4;
            }
            viewDefinitionBuilder.getAsyncFunctions().put("startAnimating", asyncFunctionWithPromiseComponent);
            if (ExpoImageViewWrapper.class == Promise.class) {
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("stopAnimating", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$AsyncFunction$4
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        ((ExpoImageViewWrapper) promise).setIsAnimating(false);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr4 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ExpoImageViewWrapper.class), false, new Function0<KType>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$AsyncFunction$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(ExpoImageViewWrapper.class);
                    }
                }))};
                Function1<Object[], Unit> function14 = new Function1<Object[], Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$AsyncFunction$6
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        ((ExpoImageViewWrapper) objArr[0]).setIsAnimating(false);
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent5 = new StringAsyncFunctionComponent("stopAnimating", anyTypeArr4, function14);
                                } else {
                                    asyncFunctionComponent5 = new AsyncFunctionComponent("stopAnimating", anyTypeArr4, function14);
                                }
                            } else {
                                asyncFunctionComponent5 = new FloatAsyncFunctionComponent("stopAnimating", anyTypeArr4, function14);
                            }
                        } else {
                            asyncFunctionComponent5 = new DoubleAsyncFunctionComponent("stopAnimating", anyTypeArr4, function14);
                        }
                    } else {
                        asyncFunctionComponent5 = new BoolAsyncFunctionComponent("stopAnimating", anyTypeArr4, function14);
                    }
                } else {
                    asyncFunctionComponent5 = new IntAsyncFunctionComponent("stopAnimating", anyTypeArr4, function14);
                }
                asyncFunctionWithPromiseComponent2 = asyncFunctionComponent5;
            }
            viewDefinitionBuilder.getAsyncFunctions().put("stopAnimating", asyncFunctionWithPromiseComponent2);
            viewDefinitionBuilder.setOnViewDidUpdateProps(new Function1<View, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$OnViewDidUpdateProps$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ExpoImageViewWrapper.rerenderIfNeeded$expo_image_release$default((ExpoImageViewWrapper) it, false, 1, null);
                }
            });
            viewDefinitionBuilder.setOnViewDestroys(new Function1<View, Unit>() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$$inlined$OnViewDestroys$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    final ExpoImageViewWrapper expoImageViewWrapper = (ExpoImageViewWrapper) it;
                    final ExpoImageViewWrapper expoImageViewWrapper2 = expoImageViewWrapper;
                    if (!ViewCompat.isAttachedToWindow(expoImageViewWrapper2)) {
                        expoImageViewWrapper.onViewDestroys();
                    } else {
                        expoImageViewWrapper2.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: expo.modules.image.ExpoImageModule$definition$lambda$17$lambda$16$lambda$15$$inlined$doOnDetach$1
                            @Override // android.view.View.OnAttachStateChangeListener
                            public void onViewAttachedToWindow(View view) {
                            }

                            @Override // android.view.View.OnAttachStateChangeListener
                            public void onViewDetachedFromWindow(View view) {
                                expoImageViewWrapper2.removeOnAttachStateChangeListener(this);
                                expoImageViewWrapper.onViewDestroys();
                            }
                        });
                    }
                }
            });
            moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
            return moduleDefinitionBuilder.buildModule();
        } finally {
            androidx.tracing.Trace.endSection();
        }
    }
}

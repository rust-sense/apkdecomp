package expo.modules.gl;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import androidx.tracing.Trace;
import expo.modules.interfaces.camera.CameraViewInterface;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
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
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: GLObjectManagerModule.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lexpo/modules/gl/GLObjectManagerModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "mGLContextMap", "Landroid/util/SparseArray;", "Lexpo/modules/gl/GLContext;", "mGLObjects", "Lexpo/modules/gl/GLObject;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "deleteContextWithId", "", "exglCtxId", "", "getContextWithId", "saveContext", "glContext", "expo-gl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GLObjectManagerModule extends Module {
    private final SparseArray<GLObject> mGLObjects = new SparseArray<>();
    private final SparseArray<GLContext> mGLContextMap = new SparseArray<>();

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        AsyncFunctionComponent asyncFunctionComponent3;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3;
        GLObjectManagerModule gLObjectManagerModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (gLObjectManagerModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(gLObjectManagerModule);
            moduleDefinitionBuilder.Name("ExponentGLObjectManager");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Integer.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("destroyObjectAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunction$1
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        SparseArray sparseArray;
                        SparseArray sparseArray2;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        int intValue = ((Integer) promise).intValue();
                        sparseArray = GLObjectManagerModule.this.mGLObjects;
                        GLObject gLObject = (GLObject) sparseArray.get(intValue);
                        if (gLObject == null) {
                            return;
                        }
                        sparseArray2 = GLObjectManagerModule.this.mGLObjects;
                        sparseArray2.remove(intValue);
                        gLObject.destroy();
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, new Function0<KType>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Integer.TYPE);
                    }
                }))};
                Function1<Object[], Boolean> function1 = new Function1<Object[], Boolean>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunction$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(Object[] objArr) {
                        SparseArray sparseArray;
                        SparseArray sparseArray2;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        boolean z = false;
                        int intValue = ((Number) objArr[0]).intValue();
                        sparseArray = GLObjectManagerModule.this.mGLObjects;
                        GLObject gLObject = (GLObject) sparseArray.get(intValue);
                        if (gLObject != null) {
                            sparseArray2 = GLObjectManagerModule.this.mGLObjects;
                            sparseArray2.remove(intValue);
                            gLObject.destroy();
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
                                    asyncFunctionComponent = new StringAsyncFunctionComponent("destroyObjectAsync", anyTypeArr, function1);
                                } else {
                                    asyncFunctionComponent = new AsyncFunctionComponent("destroyObjectAsync", anyTypeArr, function1);
                                }
                            } else {
                                asyncFunctionComponent = new FloatAsyncFunctionComponent("destroyObjectAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new DoubleAsyncFunctionComponent("destroyObjectAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new BoolAsyncFunctionComponent("destroyObjectAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new IntAsyncFunctionComponent("destroyObjectAsync", anyTypeArr, function1);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent;
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("destroyObjectAsync", asyncFunctionWithPromiseComponent);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent4 = new AsyncFunctionWithPromiseComponent("createCameraTextureAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, new Function0<KType>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithPromise$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(Integer.TYPE);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, new Function0<KType>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithPromise$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(Integer.TYPE);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithPromise$3
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    final GLContext contextWithId;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    int intValue = ((Number) objArr[1]).intValue();
                    int intValue2 = ((Number) obj).intValue();
                    KeyEvent.Callback findView = GLObjectManagerModule.this.getAppContext().findView(intValue);
                    final CameraViewInterface cameraViewInterface = findView instanceof CameraViewInterface ? (CameraViewInterface) findView : null;
                    if (cameraViewInterface != null) {
                        contextWithId = GLObjectManagerModule.this.getContextWithId(intValue2);
                        if (contextWithId == null) {
                            throw new InvalidGLContextException();
                        }
                        final GLObjectManagerModule gLObjectManagerModule2 = GLObjectManagerModule.this;
                        contextWithId.runAsync(new Runnable() { // from class: expo.modules.gl.GLObjectManagerModule$definition$1$2$1
                            @Override // java.lang.Runnable
                            public final void run() {
                                SparseArray sparseArray;
                                GLCameraObject gLCameraObject = new GLCameraObject(GLContext.this, cameraViewInterface);
                                int eXGLObjId = gLCameraObject.getEXGLObjId();
                                sparseArray = gLObjectManagerModule2.mGLObjects;
                                sparseArray.put(eXGLObjId, gLCameraObject);
                                Bundle bundle = new Bundle();
                                bundle.putInt("exglObjId", eXGLObjId);
                                promise.resolve(bundle);
                            }
                        });
                        return;
                    }
                    throw new InvalidCameraViewException();
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("createCameraTextureAsync", asyncFunctionWithPromiseComponent4);
            asyncFunctionWithPromiseComponent4.runOnQueue(Queues.MAIN);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent5 = new AsyncFunctionWithPromiseComponent("takeSnapshotAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, new Function0<KType>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithPromise$4
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(Integer.TYPE);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), false, new Function0<KType>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithPromise$5
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(Map.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.nullableTypeOf(Object.class)));
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithPromise$6
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    GLContext contextWithId;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Map<String, Object> map = (Map) objArr[1];
                    int intValue = ((Number) obj).intValue();
                    Context reactContext = GLObjectManagerModule.this.getAppContext().getReactContext();
                    if (reactContext != null) {
                        contextWithId = GLObjectManagerModule.this.getContextWithId(intValue);
                        if (contextWithId == null) {
                            throw new InvalidGLContextException();
                        }
                        contextWithId.takeSnapshot(map, reactContext, promise);
                        return;
                    }
                    throw new Exceptions.ReactContextLost();
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("takeSnapshotAsync", asyncFunctionWithPromiseComponent5);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent6 = asyncFunctionWithPromiseComponent5;
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("createContextAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunction$4
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        GLContext gLContext = new GLContext(GLObjectManagerModule.this);
                        gLContext.initialize(null, false, new GLObjectManagerModule$definition$1$4$1(gLContext, promise));
                    }
                });
            } else {
                AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunction$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Promise.class);
                    }
                }))};
                Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunction$6
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        Promise promise = (Promise) objArr[0];
                        GLContext gLContext = new GLContext(GLObjectManagerModule.this);
                        gLContext.initialize(null, false, new GLObjectManagerModule$definition$1$4$1(gLContext, promise));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent2 = new StringAsyncFunctionComponent("createContextAsync", anyTypeArr2, function12);
                                } else {
                                    asyncFunctionComponent2 = new AsyncFunctionComponent("createContextAsync", anyTypeArr2, function12);
                                }
                            } else {
                                asyncFunctionComponent2 = new FloatAsyncFunctionComponent("createContextAsync", anyTypeArr2, function12);
                            }
                        } else {
                            asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("createContextAsync", anyTypeArr2, function12);
                        }
                    } else {
                        asyncFunctionComponent2 = new BoolAsyncFunctionComponent("createContextAsync", anyTypeArr2, function12);
                    }
                } else {
                    asyncFunctionComponent2 = new IntAsyncFunctionComponent("createContextAsync", anyTypeArr2, function12);
                }
                asyncFunctionWithPromiseComponent2 = asyncFunctionComponent2;
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("createContextAsync", asyncFunctionWithPromiseComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Integer.class, Promise.class)) {
                asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("destroyContextAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunction$7
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        GLContext contextWithId;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        contextWithId = GLObjectManagerModule.this.getContextWithId(((Integer) promise).intValue());
                        if (contextWithId == null) {
                            return;
                        }
                        contextWithId.destroy();
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr3 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, new Function0<KType>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunction$8
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Integer.TYPE);
                    }
                }))};
                Function1<Object[], Boolean> function13 = new Function1<Object[], Boolean>() { // from class: expo.modules.gl.GLObjectManagerModule$definition$lambda$5$$inlined$AsyncFunction$9
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(Object[] objArr) {
                        GLContext contextWithId;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        boolean z = false;
                        contextWithId = GLObjectManagerModule.this.getContextWithId(((Number) objArr[0]).intValue());
                        if (contextWithId != null) {
                            contextWithId.destroy();
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
                                    asyncFunctionComponent3 = new StringAsyncFunctionComponent("destroyContextAsync", anyTypeArr3, function13);
                                } else {
                                    asyncFunctionComponent3 = new AsyncFunctionComponent("destroyContextAsync", anyTypeArr3, function13);
                                }
                            } else {
                                asyncFunctionComponent3 = new FloatAsyncFunctionComponent("destroyContextAsync", anyTypeArr3, function13);
                            }
                        } else {
                            asyncFunctionComponent3 = new DoubleAsyncFunctionComponent("destroyContextAsync", anyTypeArr3, function13);
                        }
                    } else {
                        asyncFunctionComponent3 = new BoolAsyncFunctionComponent("destroyContextAsync", anyTypeArr3, function13);
                    }
                } else {
                    asyncFunctionComponent3 = new IntAsyncFunctionComponent("destroyContextAsync", anyTypeArr3, function13);
                }
                asyncFunctionWithPromiseComponent3 = asyncFunctionComponent3;
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("destroyContextAsync", asyncFunctionWithPromiseComponent3);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GLContext getContextWithId(int exglCtxId) {
        return this.mGLContextMap.get(exglCtxId);
    }

    public final void saveContext(GLContext glContext) {
        Intrinsics.checkNotNullParameter(glContext, "glContext");
        this.mGLContextMap.put(glContext.getContextId(), glContext);
    }

    public final void deleteContextWithId(int exglCtxId) {
        this.mGLContextMap.delete(exglCtxId);
    }
}

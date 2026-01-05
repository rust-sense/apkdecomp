package expo.modules.kotlin;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.util.JSStackTrace;
import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventListener;
import expo.modules.kotlin.events.EventListenerWithPayload;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.MethodNotFoundException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: ModuleHolder.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!J!\u0010\u001f\u001a\u00020\u0018\"\u0004\b\u0001\u0010\"2\u0006\u0010 \u001a\u00020!2\u0006\u0010#\u001a\u0002H\"¢\u0006\u0002\u0010$J/\u0010\u001f\u001a\u00020\u0018\"\u0004\b\u0001\u0010%\"\u0004\b\u0002\u0010\"2\u0006\u0010 \u001a\u00020!2\u0006\u0010&\u001a\u0002H%2\u0006\u0010#\u001a\u0002H\"¢\u0006\u0002\u0010'J\u0006\u0010(\u001a\u00020\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006)"}, d2 = {"Lexpo/modules/kotlin/ModuleHolder;", ExifInterface.GPS_DIRECTION_TRUE, "Lexpo/modules/kotlin/modules/Module;", "", "module", "(Lexpo/modules/kotlin/modules/Module;)V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getDefinition", "()Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "getJsObject", "()Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "jsObject$delegate", "Lkotlin/Lazy;", "getModule", "()Lexpo/modules/kotlin/modules/Module;", "Lexpo/modules/kotlin/modules/Module;", "name", "", "getName", "()Ljava/lang/String;", NotificationCompat.CATEGORY_CALL, "", JSStackTrace.METHOD_NAME_KEY, "args", "Lcom/facebook/react/bridge/ReadableArray;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "callSync", "post", "eventName", "Lexpo/modules/kotlin/events/EventName;", "Payload", "payload", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;)V", "Sender", "sender", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;Ljava/lang/Object;)V", "registerContracts", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ModuleHolder<T extends Module> {
    private final ModuleDefinitionData definition;

    /* renamed from: jsObject$delegate, reason: from kotlin metadata */
    private final Lazy jsObject;
    private final T module;

    public final ModuleDefinitionData getDefinition() {
        return this.definition;
    }

    public final T getModule() {
        return this.module;
    }

    public ModuleHolder(T module) {
        Intrinsics.checkNotNullParameter(module, "module");
        this.module = module;
        this.definition = module.definition();
        this.jsObject = LazyKt.lazy(new Function0<JavaScriptModuleObject>(this) { // from class: expo.modules.kotlin.ModuleHolder$jsObject$2
            final /* synthetic */ ModuleHolder<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Code restructure failed: missing block: B:58:0x0197, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:60:0x019b, code lost:
            
                throw r0;
             */
            @Override // kotlin.jvm.functions.Function0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final expo.modules.kotlin.jni.JavaScriptModuleObject invoke() {
                /*
                    Method dump skipped, instructions count: 412
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.ModuleHolder$jsObject$2.invoke():expo.modules.kotlin.jni.JavaScriptModuleObject");
            }
        });
    }

    public final String getName() {
        return this.definition.getName();
    }

    public final JavaScriptModuleObject getJsObject() {
        return (JavaScriptModuleObject) this.jsObject.getValue();
    }

    public final void call(String methodName, ReadableArray args, Promise promise) {
        UnexpectedException unexpectedException;
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            BaseAsyncFunctionComponent baseAsyncFunctionComponent = this.definition.getAsyncFunctions().get(methodName);
            if (baseAsyncFunctionComponent == null) {
                throw new MethodNotFoundException();
            }
            baseAsyncFunctionComponent.call(this, args, promise);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            if (th instanceof CodedException) {
                unexpectedException = (CodedException) th;
            } else if (th instanceof expo.modules.core.errors.CodedException) {
                String code = ((expo.modules.core.errors.CodedException) th).getCode();
                Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                unexpectedException = new CodedException(code, th.getMessage(), th.getCause());
            } else {
                unexpectedException = new UnexpectedException(th);
            }
            throw new FunctionCallException(methodName, this.definition.getName(), unexpectedException);
        }
    }

    public final Object callSync(String methodName, ReadableArray args) {
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(args, "args");
        SyncFunctionComponent syncFunctionComponent = this.definition.getSyncFunctions().get(methodName);
        if (syncFunctionComponent == null) {
            throw new MethodNotFoundException();
        }
        return syncFunctionComponent.call(args);
    }

    public final void post(EventName eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        EventListener eventListener = this.definition.getEventListeners().get(eventName);
        if (eventListener == null) {
            return;
        }
        BasicEventListener basicEventListener = eventListener instanceof BasicEventListener ? (BasicEventListener) eventListener : null;
        if (basicEventListener != null) {
            basicEventListener.call();
        }
    }

    public final <Payload> void post(EventName eventName, Payload payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        EventListener eventListener = this.definition.getEventListeners().get(eventName);
        if (eventListener == null) {
            return;
        }
        EventListenerWithPayload eventListenerWithPayload = eventListener instanceof EventListenerWithPayload ? (EventListenerWithPayload) eventListener : null;
        if (eventListenerWithPayload != null) {
            eventListenerWithPayload.call(payload);
        }
    }

    public final <Sender, Payload> void post(EventName eventName, Sender sender, Payload payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        EventListener eventListener = this.definition.getEventListeners().get(eventName);
        if (eventListener == null) {
            return;
        }
        EventListenerWithSenderAndPayload eventListenerWithSenderAndPayload = eventListener instanceof EventListenerWithSenderAndPayload ? (EventListenerWithSenderAndPayload) eventListener : null;
        if (eventListenerWithSenderAndPayload != null) {
            eventListenerWithSenderAndPayload.call(sender, payload);
        }
    }

    public final void registerContracts() {
        Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> registerContracts = this.definition.getRegisterContracts();
        if (registerContracts != null) {
            BuildersKt__Builders_commonKt.launch$default(this.module.getAppContext().getMainQueue(), null, null, new ModuleHolder$registerContracts$1$1(registerContracts, this, null), 3, null);
        }
    }
}

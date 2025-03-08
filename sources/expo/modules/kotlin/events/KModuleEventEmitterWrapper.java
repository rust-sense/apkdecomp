package expo.modules.kotlin.events;

import android.os.Bundle;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.types.JSTypeConverter;
import expo.modules.kotlin.types.JSTypeConverterHelperKt;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KModuleEventEmitterWrapper.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001a\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0012H\u0016J\"\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0010\u0010\u000f\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0015H\u0002R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lexpo/modules/kotlin/events/KModuleEventEmitterWrapper;", "Lexpo/modules/kotlin/events/KEventEmitterWrapper;", "moduleHolder", "Lexpo/modules/kotlin/ModuleHolder;", "legacyEventEmitter", "Lexpo/modules/core/interfaces/services/EventEmitter;", "reactContextHolder", "Ljava/lang/ref/WeakReference;", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lexpo/modules/kotlin/ModuleHolder;Lexpo/modules/core/interfaces/services/EventEmitter;Ljava/lang/ref/WeakReference;)V", "checkIfEventWasExported", "", "eventName", "", "emit", "eventBody", "Landroid/os/Bundle;", "Lcom/facebook/react/bridge/WritableMap;", "Lexpo/modules/kotlin/records/Record;", "", "emitNative", "Lcom/facebook/react/bridge/ReadableNativeMap;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class KModuleEventEmitterWrapper extends KEventEmitterWrapper {
    private final ModuleHolder<?> moduleHolder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KModuleEventEmitterWrapper(ModuleHolder<?> moduleHolder, expo.modules.core.interfaces.services.EventEmitter legacyEventEmitter, WeakReference<ReactApplicationContext> reactContextHolder) {
        super(legacyEventEmitter, reactContextHolder);
        Intrinsics.checkNotNullParameter(moduleHolder, "moduleHolder");
        Intrinsics.checkNotNullParameter(legacyEventEmitter, "legacyEventEmitter");
        Intrinsics.checkNotNullParameter(reactContextHolder, "reactContextHolder");
        this.moduleHolder = moduleHolder;
    }

    @Override // expo.modules.kotlin.events.KEventEmitterWrapper, expo.modules.core.interfaces.services.EventEmitter
    public void emit(String eventName, Bundle eventBody) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        checkIfEventWasExported(eventName);
        ReadableMap jSValue = eventBody != null ? JSTypeConverterHelperKt.toJSValue(eventBody, JSTypeConverter.DefaultContainerProvider.INSTANCE) : null;
        emitNative(eventName, jSValue instanceof ReadableNativeMap ? (ReadableNativeMap) jSValue : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // expo.modules.kotlin.events.KEventEmitterWrapper, expo.modules.kotlin.events.EventEmitter
    public void emit(String eventName, WritableMap eventBody) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        checkIfEventWasExported(eventName);
        emitNative(eventName, eventBody instanceof ReadableNativeMap ? (ReadableNativeMap) eventBody : null);
    }

    @Override // expo.modules.kotlin.events.KEventEmitterWrapper, expo.modules.kotlin.events.EventEmitter
    public void emit(String eventName, Record eventBody) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        checkIfEventWasExported(eventName);
        ReadableMap jSValue = eventBody != null ? JSTypeConverterHelperKt.toJSValue(eventBody, JSTypeConverter.DefaultContainerProvider.INSTANCE) : null;
        emitNative(eventName, jSValue instanceof ReadableNativeMap ? (ReadableNativeMap) jSValue : null);
    }

    @Override // expo.modules.kotlin.events.KEventEmitterWrapper, expo.modules.kotlin.events.EventEmitter
    public void emit(String eventName, Map<?, ?> eventBody) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        checkIfEventWasExported(eventName);
        ReadableMap jSValue = eventBody != null ? JSTypeConverterHelperKt.toJSValue(eventBody, JSTypeConverter.DefaultContainerProvider.INSTANCE) : null;
        emitNative(eventName, jSValue instanceof ReadableNativeMap ? (ReadableNativeMap) jSValue : null);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [expo.modules.kotlin.modules.Module] */
    private final void emitNative(String eventName, ReadableNativeMap eventBody) {
        this.moduleHolder.getJsObject().emitEvent(this.moduleHolder.getModule().getAppContext().getJsiInterop$expo_modules_core_release(), eventName, eventBody);
    }

    private final void checkIfEventWasExported(String eventName) {
        String[] names;
        EventsDefinition eventsDefinition = this.moduleHolder.getDefinition().getEventsDefinition();
        if (eventsDefinition == null || (names = eventsDefinition.getNames()) == null || !ArraysKt.contains(names, eventName)) {
            throw new IllegalArgumentException(("Unsupported event: " + eventName + ".").toString());
        }
    }
}

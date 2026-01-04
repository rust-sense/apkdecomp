package expo.modules.kotlin;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import androidx.tracing.Trace;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.providers.AppContextProvider;
import expo.modules.kotlin.views.ViewManagerDefinition;
import io.sentry.SentryEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: ModuleRegistry.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001:\u0001:B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J(\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u001aH\u0002J\u0018\u0010\u001c\u001a\u0004\u0018\u0001H\u001d\"\u0006\b\u0000\u0010\u001d\u0018\u0001H\u0086\b¢\u0006\u0002\u0010\u001eJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u000eJ%\u0010!\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u0002\"\b\b\u0000\u0010\u001d*\u00020\u001f2\u0006\u0010\"\u001a\u0002H\u001d¢\u0006\u0002\u0010#J$\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002\"\b\b\u0000\u0010\u001d*\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u001d0&J\u0014\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00022\u0006\u0010 \u001a\u00020\u000eJ\u000e\u0010'\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000eJ\u0013\u0010(\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020)H\u0096\u0002J\u000e\u0010*\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015J!\u0010*\u001a\u00020\u001a\"\u0004\b\u0000\u0010+2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u0002H+¢\u0006\u0002\u0010,J/\u0010*\u001a\u00020\u001a\"\u0004\b\u0000\u0010+\"\u0004\b\u0001\u0010-2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u0002H+2\u0006\u0010\u0018\u001a\u0002H-¢\u0006\u0002\u0010.J\u0006\u0010/\u001a\u00020\u001aJ\b\u00100\u001a\u00020\u001aH\u0002J\u001d\u00101\u001a\u00020\u001a\"\b\b\u0000\u0010\u001d*\u00020\u001f2\u0006\u0010\"\u001a\u0002H\u001d¢\u0006\u0002\u00102J\u000e\u00101\u001a\u00020\u00002\u0006\u00103\u001a\u000204J\u001f\u00101\u001a\u00020\u001a2\u0012\u00105\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f06\"\u00020\u001f¢\u0006\u0002\u00107J\r\u00108\u001a\u00020\u001aH\u0000¢\u0006\u0002\b9R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\r8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006;"}, d2 = {"Lexpo/modules/kotlin/ModuleRegistry;", "", "Lexpo/modules/kotlin/ModuleHolder;", "appContext", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "(Ljava/lang/ref/WeakReference;)V", "eventQueue", "", "Lexpo/modules/kotlin/ModuleRegistry$PostponedEvent;", "isReadyForPostingEvents", "", "registry", "", "", "getRegistry$annotations", "()V", "getRegistry", "()Ljava/util/Map;", "addToQueueIfNeeded", "eventName", "Lexpo/modules/kotlin/events/EventName;", "sender", "", "payload", "cleanUp", "", "flushTheEventQueue", "getModule", ExifInterface.GPS_DIRECTION_TRUE, "()Ljava/lang/Object;", "Lexpo/modules/kotlin/modules/Module;", "name", "getModuleHolder", "module", "(Lexpo/modules/kotlin/modules/Module;)Lexpo/modules/kotlin/ModuleHolder;", "Landroid/view/View;", "viewClass", "Ljava/lang/Class;", "hasModule", "iterator", "", "post", "Sender", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;)V", "Payload", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;Ljava/lang/Object;)V", "postOnCreate", "readyForPostingEvents", "register", "(Lexpo/modules/kotlin/modules/Module;)V", "provider", "Lexpo/modules/kotlin/ModulesProvider;", SentryEvent.JsonKeys.MODULES, "", "([Lexpo/modules/kotlin/modules/Module;)V", "registerActivityContracts", "registerActivityContracts$expo_modules_core_release", "PostponedEvent", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ModuleRegistry implements Iterable<ModuleHolder<?>>, KMappedMarker {
    private final WeakReference<AppContext> appContext;
    private final List<PostponedEvent> eventQueue;
    private boolean isReadyForPostingEvents;
    private final Map<String, ModuleHolder<?>> registry;

    public static /* synthetic */ void getRegistry$annotations() {
    }

    public final Map<String, ModuleHolder<?>> getRegistry() {
        return this.registry;
    }

    public ModuleRegistry(WeakReference<AppContext> appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        this.registry = new LinkedHashMap();
        this.eventQueue = new ArrayList();
    }

    public final <T extends Module> void register(T module) {
        Intrinsics.checkNotNullParameter(module, "module");
        Trace.beginSection("[ExpoModulesCore] " + ("ModuleRegistry.register(" + module.getClass() + ")"));
        try {
            Object obj = this.appContext.get();
            if (obj == null) {
                throw new IllegalArgumentException("Cannot create a module for invalid app context.".toString());
            }
            module.set_appContext$expo_modules_core_release((AppContext) obj);
            final ModuleHolder<?> moduleHolder = new ModuleHolder<>(module);
            module.setCoroutineScopeDelegate(LazyKt.lazy(new Function0<CoroutineScope>() { // from class: expo.modules.kotlin.ModuleRegistry$register$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final CoroutineScope invoke() {
                    return CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)).plus(new CoroutineName(moduleHolder.getDefinition().getName())));
                }
            }));
            getRegistry().put(moduleHolder.getName(), moduleHolder);
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    public final ModuleRegistry register(ModulesProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Iterator<T> it = provider.getModulesList().iterator();
        while (it.hasNext()) {
            register((ModuleRegistry) ((Class) it.next()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        }
        return this;
    }

    public final boolean hasModule(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.registry.containsKey(name);
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [expo.modules.kotlin.modules.Module] */
    public final Module getModule(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ModuleHolder<?> moduleHolder = this.registry.get(name);
        if (moduleHolder != null) {
            return moduleHolder.getModule();
        }
        return null;
    }

    public final /* synthetic */ <T> T getModule() {
        T t;
        Iterator<T> it = getRegistry().values().iterator();
        while (true) {
            if (!it.hasNext()) {
                t = (T) null;
                break;
            }
            t = it.next();
            Module module = ((ModuleHolder) t).getModule();
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (module instanceof Object) {
                break;
            }
        }
        ModuleHolder moduleHolder = t;
        AppContextProvider module2 = moduleHolder != null ? moduleHolder.getModule() : null;
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) module2;
    }

    public final ModuleHolder<?> getModuleHolder(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.registry.get(name);
    }

    public final <T extends Module> ModuleHolder<T> getModuleHolder(T module) {
        Object obj;
        Intrinsics.checkNotNullParameter(module, "module");
        Iterator<T> it = this.registry.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((ModuleHolder) obj).getModule() == module) {
                break;
            }
        }
        if (obj instanceof ModuleHolder) {
            return (ModuleHolder) obj;
        }
        return null;
    }

    public final <T extends View> ModuleHolder<?> getModuleHolder(Class<T> viewClass) {
        ModuleHolder<?> moduleHolder;
        Intrinsics.checkNotNullParameter(viewClass, "viewClass");
        Iterator<Map.Entry<String, ModuleHolder<?>>> it = this.registry.entrySet().iterator();
        do {
            moduleHolder = null;
            if (!it.hasNext()) {
                break;
            }
            ModuleHolder<?> value = it.next().getValue();
            ViewManagerDefinition viewManagerDefinition = value.getDefinition().getViewManagerDefinition();
            if (Intrinsics.areEqual(viewManagerDefinition != null ? viewManagerDefinition.getViewType$expo_modules_core_release() : null, viewClass)) {
                moduleHolder = value;
            }
        } while (moduleHolder == null);
        return moduleHolder;
    }

    public final void postOnCreate() {
        Iterator<ModuleHolder<?>> it = iterator();
        while (it.hasNext()) {
            it.next().post(EventName.MODULE_CREATE);
        }
        registerActivityContracts$expo_modules_core_release();
        readyForPostingEvents();
        flushTheEventQueue();
    }

    public final void post(EventName eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        if (addToQueueIfNeeded$default(this, eventName, null, null, 6, null)) {
            return;
        }
        Iterator<ModuleHolder<?>> it = iterator();
        while (it.hasNext()) {
            it.next().post(eventName);
        }
    }

    public final <Sender> void post(EventName eventName, Sender sender) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        if (addToQueueIfNeeded$default(this, eventName, sender, null, 4, null)) {
            return;
        }
        Iterator<ModuleHolder<?>> it = iterator();
        while (it.hasNext()) {
            it.next().post(eventName, sender);
        }
    }

    public final <Sender, Payload> void post(EventName eventName, Sender sender, Payload payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        if (addToQueueIfNeeded(eventName, sender, payload)) {
            return;
        }
        Iterator<ModuleHolder<?>> it = iterator();
        while (it.hasNext()) {
            it.next().post(eventName, sender, payload);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<ModuleHolder<?>> iterator() {
        return this.registry.values().iterator();
    }

    public final void cleanUp() {
        this.registry.clear();
        CoreLoggerKt.getLogger().info("✅ ModuleRegistry was destroyed");
    }

    public final void registerActivityContracts$expo_modules_core_release() {
        Iterator<ModuleHolder<?>> it = iterator();
        while (it.hasNext()) {
            it.next().registerContracts();
        }
    }

    private final void readyForPostingEvents() {
        synchronized (this) {
            this.isReadyForPostingEvents = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void flushTheEventQueue() {
        synchronized (this) {
            for (PostponedEvent postponedEvent : this.eventQueue) {
                Iterator<ModuleHolder<?>> it = iterator();
                while (it.hasNext()) {
                    postponedEvent.post(it.next());
                }
            }
            this.eventQueue.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    static /* synthetic */ boolean addToQueueIfNeeded$default(ModuleRegistry moduleRegistry, EventName eventName, Object obj, Object obj2, int i, Object obj3) {
        if ((i & 2) != 0) {
            obj = null;
        }
        if ((i & 4) != 0) {
            obj2 = null;
        }
        return moduleRegistry.addToQueueIfNeeded(eventName, obj, obj2);
    }

    private final boolean addToQueueIfNeeded(EventName eventName, Object sender, Object payload) {
        synchronized (this) {
            if (this.isReadyForPostingEvents) {
                return false;
            }
            this.eventQueue.add(new PostponedEvent(eventName, sender, payload));
            return true;
        }
    }

    /* compiled from: ModuleRegistry.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÆ\u0003J+\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0012\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u001b"}, d2 = {"Lexpo/modules/kotlin/ModuleRegistry$PostponedEvent;", "", "eventName", "Lexpo/modules/kotlin/events/EventName;", "sender", "payload", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;Ljava/lang/Object;)V", "getEventName", "()Lexpo/modules/kotlin/events/EventName;", "getPayload", "()Ljava/lang/Object;", "getSender", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "post", "", "moduleHolder", "Lexpo/modules/kotlin/ModuleHolder;", "toString", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class PostponedEvent {
        private final EventName eventName;
        private final Object payload;
        private final Object sender;

        public static /* synthetic */ PostponedEvent copy$default(PostponedEvent postponedEvent, EventName eventName, Object obj, Object obj2, int i, Object obj3) {
            if ((i & 1) != 0) {
                eventName = postponedEvent.eventName;
            }
            if ((i & 2) != 0) {
                obj = postponedEvent.sender;
            }
            if ((i & 4) != 0) {
                obj2 = postponedEvent.payload;
            }
            return postponedEvent.copy(eventName, obj, obj2);
        }

        /* renamed from: component1, reason: from getter */
        public final EventName getEventName() {
            return this.eventName;
        }

        /* renamed from: component2, reason: from getter */
        public final Object getSender() {
            return this.sender;
        }

        /* renamed from: component3, reason: from getter */
        public final Object getPayload() {
            return this.payload;
        }

        public final PostponedEvent copy(EventName eventName, Object sender, Object payload) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            return new PostponedEvent(eventName, sender, payload);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PostponedEvent)) {
                return false;
            }
            PostponedEvent postponedEvent = (PostponedEvent) other;
            return this.eventName == postponedEvent.eventName && Intrinsics.areEqual(this.sender, postponedEvent.sender) && Intrinsics.areEqual(this.payload, postponedEvent.payload);
        }

        public final EventName getEventName() {
            return this.eventName;
        }

        public final Object getPayload() {
            return this.payload;
        }

        public final Object getSender() {
            return this.sender;
        }

        public int hashCode() {
            int hashCode = this.eventName.hashCode() * 31;
            Object obj = this.sender;
            int hashCode2 = (hashCode + (obj == null ? 0 : obj.hashCode())) * 31;
            Object obj2 = this.payload;
            return hashCode2 + (obj2 != null ? obj2.hashCode() : 0);
        }

        public String toString() {
            return "PostponedEvent(eventName=" + this.eventName + ", sender=" + this.sender + ", payload=" + this.payload + ")";
        }

        public PostponedEvent(EventName eventName, Object obj, Object obj2) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            this.eventName = eventName;
            this.sender = obj;
            this.payload = obj2;
        }

        public /* synthetic */ PostponedEvent(EventName eventName, Object obj, Object obj2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(eventName, (i & 2) != 0 ? null : obj, (i & 4) != 0 ? null : obj2);
        }

        public final void post(ModuleHolder<?> moduleHolder) {
            Object obj;
            Intrinsics.checkNotNullParameter(moduleHolder, "moduleHolder");
            Object obj2 = this.sender;
            if (obj2 != null && (obj = this.payload) != null) {
                moduleHolder.post(this.eventName, obj2, obj);
            } else if (obj2 != null) {
                moduleHolder.post(this.eventName, obj2);
            } else {
                moduleHolder.post(this.eventName);
            }
        }
    }

    public final void register(Module... modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        for (Module module : modules) {
            register((ModuleRegistry) module);
        }
    }
}

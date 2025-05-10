package expo.modules.kotlin.sharedobjects;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.jni.JavaScriptObject;
import expo.modules.kotlin.jni.JavaScriptWeakObject;
import io.sentry.protocol.SentryStackFrame;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SharedObjectRegistry.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\tH\u0000ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010 \u001a\u00020\tH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0016\u001a\u00020\u000eH\u0000¢\u0006\u0002\b$J\u0017\u0010%\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b&J\u001c\u0010%\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001d\u001a\u00020\tH\u0000ø\u0001\u0000¢\u0006\u0004\b'\u0010(R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00030\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u00020\tX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\nR6\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rj\u0002`\u00100\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006)"}, d2 = {"Lexpo/modules/kotlin/sharedobjects/SharedObjectRegistry;", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "(Lexpo/modules/kotlin/AppContext;)V", "appContextHolder", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "currentId", "Lexpo/modules/kotlin/sharedobjects/SharedObjectId;", "I", "pairs", "", "Lkotlin/Pair;", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "Lexpo/modules/kotlin/jni/JavaScriptWeakObject;", "Lexpo/modules/kotlin/sharedobjects/SharedObjectPair;", "getPairs$expo_modules_core_release", "()Ljava/util/Map;", "setPairs$expo_modules_core_release", "(Ljava/util/Map;)V", "add", SentryStackFrame.JsonKeys.NATIVE, "js", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "add-5WKnsLU$expo_modules_core_release", "(Lexpo/modules/kotlin/sharedobjects/SharedObject;Lexpo/modules/kotlin/jni/JavaScriptObject;)I", "delete", "", "id", "delete-kyJHjyY$expo_modules_core_release", "(I)V", "pullNextId", "pullNextId-HSeVr_g", "()I", "toJavaScriptObject", "toJavaScriptObject$expo_modules_core_release", "toNativeObject", "toNativeObject$expo_modules_core_release", "toNativeObject-kyJHjyY$expo_modules_core_release", "(I)Lexpo/modules/kotlin/sharedobjects/SharedObject;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SharedObjectRegistry {
    private final WeakReference<AppContext> appContextHolder;
    private int currentId;
    private Map<SharedObjectId, Pair<SharedObject, JavaScriptWeakObject>> pairs;

    public final Map<SharedObjectId, Pair<SharedObject, JavaScriptWeakObject>> getPairs$expo_modules_core_release() {
        return this.pairs;
    }

    public final void setPairs$expo_modules_core_release(Map<SharedObjectId, Pair<SharedObject, JavaScriptWeakObject>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.pairs = map;
    }

    public SharedObjectRegistry(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContextHolder = new WeakReference<>(appContext);
        this.currentId = SharedObjectId.m695constructorimpl(1);
        this.pairs = new LinkedHashMap();
    }

    /* renamed from: pullNextId-HSeVr_g, reason: not valid java name */
    private final int m703pullNextIdHSeVr_g() {
        int i;
        synchronized (this) {
            i = this.currentId;
            this.currentId = SharedObjectId.m695constructorimpl(i + 1);
        }
        return i;
    }

    /* renamed from: add-5WKnsLU$expo_modules_core_release, reason: not valid java name */
    public final int m704add5WKnsLU$expo_modules_core_release(SharedObject r8, JavaScriptObject js) {
        Intrinsics.checkNotNullParameter(r8, "native");
        Intrinsics.checkNotNullParameter(js, "js");
        int m703pullNextIdHSeVr_g = m703pullNextIdHSeVr_g();
        r8.m693setSharedObjectIdkyJHjyY$expo_modules_core_release(m703pullNextIdHSeVr_g);
        JavaScriptObject.defineProperty$default(js, SharedObjectRegistryKt.sharedObjectIdPropertyName, m703pullNextIdHSeVr_g, (List) null, 4, (Object) null);
        AppContext appContext = this.appContextHolder.get();
        if (appContext == null) {
            throw new Exceptions.AppContextLost();
        }
        appContext.getJsiInterop$expo_modules_core_release().setNativeStateForSharedObject(m703pullNextIdHSeVr_g, js);
        JavaScriptWeakObject createWeak = js.createWeak();
        synchronized (this) {
            this.pairs.put(SharedObjectId.m694boximpl(m703pullNextIdHSeVr_g), TuplesKt.to(r8, createWeak));
            Unit unit = Unit.INSTANCE;
        }
        if (r8.getAppContextHolder$expo_modules_core_release().get() == null) {
            r8.setAppContextHolder$expo_modules_core_release(new WeakReference<>(appContext));
        }
        return m703pullNextIdHSeVr_g;
    }

    /* renamed from: delete-kyJHjyY$expo_modules_core_release, reason: not valid java name */
    public final void m705deletekyJHjyY$expo_modules_core_release(int id) {
        Pair<SharedObject, JavaScriptWeakObject> remove;
        synchronized (this) {
            remove = this.pairs.remove(SharedObjectId.m694boximpl(id));
        }
        if (remove != null) {
            SharedObject component1 = remove.component1();
            component1.m693setSharedObjectIdkyJHjyY$expo_modules_core_release(SharedObjectId.m695constructorimpl(0));
            component1.deallocate();
        }
    }

    /* renamed from: toNativeObject-kyJHjyY$expo_modules_core_release, reason: not valid java name */
    public final SharedObject m706toNativeObjectkyJHjyY$expo_modules_core_release(int id) {
        SharedObject first;
        synchronized (this) {
            Pair<SharedObject, JavaScriptWeakObject> pair = this.pairs.get(SharedObjectId.m694boximpl(id));
            first = pair != null ? pair.getFirst() : null;
        }
        return first;
    }

    public final SharedObject toNativeObject$expo_modules_core_release(JavaScriptObject js) {
        Intrinsics.checkNotNullParameter(js, "js");
        if (!js.hasProperty(SharedObjectRegistryKt.sharedObjectIdPropertyName)) {
            return null;
        }
        Pair<SharedObject, JavaScriptWeakObject> pair = this.pairs.get(SharedObjectId.m694boximpl(SharedObjectId.m695constructorimpl(js.getProperty(SharedObjectRegistryKt.sharedObjectIdPropertyName).getInt())));
        if (pair != null) {
            return pair.getFirst();
        }
        return null;
    }

    public final JavaScriptObject toJavaScriptObject$expo_modules_core_release(SharedObject r2) {
        JavaScriptObject lock;
        JavaScriptWeakObject second;
        Intrinsics.checkNotNullParameter(r2, "native");
        synchronized (this) {
            Pair<SharedObject, JavaScriptWeakObject> pair = this.pairs.get(SharedObjectId.m694boximpl(r2.getSharedObjectId()));
            lock = (pair == null || (second = pair.getSecond()) == null) ? null : second.lock();
        }
        return lock;
    }
}

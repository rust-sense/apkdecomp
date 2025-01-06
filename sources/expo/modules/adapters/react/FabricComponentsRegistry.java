package expo.modules.adapters.react;

import com.facebook.jni.HybridData;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FabricComponentsRegistry.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0004J\t\u0010\f\u001a\u00020\tH\u0082 J\u001c\u0010\r\u001a\u00020\u000b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0082 ¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lexpo/modules/adapters/react/FabricComponentsRegistry;", "", "viewManagerList", "", "Lcom/facebook/react/uimanager/ViewManager;", "(Ljava/util/List;)V", "componentNames", "", "mHybridData", "Lcom/facebook/jni/HybridData;", "finalize", "", "initHybrid", "registerComponentsRegistry", "", "([Ljava/lang/String;)V", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FabricComponentsRegistry {
    private final List<String> componentNames;
    private final HybridData mHybridData;

    private final native HybridData initHybrid();

    private final native void registerComponentsRegistry(String[] componentNames);

    public FabricComponentsRegistry(List<? extends ViewManager<?, ?>> viewManagerList) {
        Intrinsics.checkNotNullParameter(viewManagerList, "viewManagerList");
        List<? extends ViewManager<?, ?>> list = viewManagerList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ViewManager) it.next()).getName());
        }
        ArrayList arrayList2 = arrayList;
        this.componentNames = arrayList2;
        this.mHybridData = initHybrid();
        registerComponentsRegistry((String[]) arrayList2.toArray(new String[0]));
    }

    protected final void finalize() throws Throwable {
        this.mHybridData.resetNative();
    }

    static {
        SoLoader.loadLibrary("expo-modules-core");
    }
}

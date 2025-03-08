package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactStylesDiffMapHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"getBackingMap", "Lcom/facebook/react/bridge/ReadableMap;", "Lcom/facebook/react/uimanager/ReactStylesDiffMap;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ReactStylesDiffMapHelperKt {
    public static final ReadableMap getBackingMap(ReactStylesDiffMap reactStylesDiffMap) {
        Intrinsics.checkNotNullParameter(reactStylesDiffMap, "<this>");
        ReadableMap mBackingMap = reactStylesDiffMap.mBackingMap;
        Intrinsics.checkNotNullExpressionValue(mBackingMap, "mBackingMap");
        return mBackingMap;
    }
}

package com.facebook.react.fabric.interop;

import android.view.View;
import kotlin.Deprecated;
import kotlin.Metadata;

/* compiled from: UIBlockViewResolver.kt */
@Deprecated(message = "Use UIManagerListener or View Commands instead of addUIBlock and prependUIBlock.")
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/fabric/interop/UIBlockViewResolver;", "", "resolveView", "Landroid/view/View;", "reactTag", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public interface UIBlockViewResolver {
    View resolveView(int reactTag);
}
package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;

/* loaded from: classes.dex */
public interface IViewGroupManager<T extends View> extends IViewManagerWithChildren {
    void addView(T t, View view, int i);

    View getChildAt(T t, int i);

    int getChildCount(T t);

    void removeAllViews(T t);

    void removeViewAt(T t, int i);

    /* renamed from: com.facebook.react.uimanager.IViewGroupManager$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$removeAllViews(IViewGroupManager _this, View view) {
            UiThreadUtil.assertOnUiThread();
            for (int childCount = _this.getChildCount(view) - 1; childCount >= 0; childCount--) {
                _this.removeViewAt(view, childCount);
            }
        }
    }
}

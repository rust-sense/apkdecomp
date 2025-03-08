package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.SkiaPictureViewManagerInterface;

/* loaded from: classes.dex */
public class SkiaPictureViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & SkiaPictureViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SkiaPictureViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        if (str.equals("mode")) {
            ((SkiaPictureViewManagerInterface) this.mViewManager).setMode(t, obj == null ? null : (String) obj);
        } else if (str.equals("debug")) {
            ((SkiaPictureViewManagerInterface) this.mViewManager).setDebug(t, obj == null ? false : ((Boolean) obj).booleanValue());
        } else {
            super.setProperty(t, str, obj);
        }
    }
}

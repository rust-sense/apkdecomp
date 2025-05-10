package com.facebook.react.views.common;

import android.view.View;
import com.facebook.react.R;

/* loaded from: classes.dex */
public class ViewUtils {
    public static String getTestId(View view) {
        if (view == null) {
            return null;
        }
        Object tag = view.getTag(R.id.react_test_id);
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }
}

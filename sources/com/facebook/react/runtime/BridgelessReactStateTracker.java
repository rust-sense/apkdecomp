package com.facebook.react.runtime;

import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
class BridgelessReactStateTracker {
    private static final String TAG = "BridgelessReact";
    private final boolean mShouldTrackStates;
    private final List<String> mStates = Collections.synchronizedList(new ArrayList());

    BridgelessReactStateTracker(boolean z) {
        this.mShouldTrackStates = z;
    }

    void enterState(String str) {
        FLog.w(TAG, str);
        if (this.mShouldTrackStates) {
            this.mStates.add(str);
        }
    }
}

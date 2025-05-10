package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.uimanager.StateWrapper;

/* loaded from: classes.dex */
final class PreAllocateViewMountItem implements MountItem {
    private final String mComponent;
    private final EventEmitterWrapper mEventEmitterWrapper;
    private final boolean mIsLayoutable;
    private final ReadableMap mProps;
    private final int mReactTag;
    private final StateWrapper mStateWrapper;
    private final int mSurfaceId;

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    PreAllocateViewMountItem(int i, int i2, String str, ReadableMap readableMap, StateWrapper stateWrapper, EventEmitterWrapper eventEmitterWrapper, boolean z) {
        this.mComponent = FabricNameComponentMapping.getFabricComponentName(str);
        this.mSurfaceId = i;
        this.mProps = readableMap;
        this.mStateWrapper = stateWrapper;
        this.mEventEmitterWrapper = eventEmitterWrapper;
        this.mReactTag = i2;
        this.mIsLayoutable = z;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.mSurfaceId);
        if (surfaceManager == null) {
            FLog.e(FabricUIManager.TAG, "Skipping View PreAllocation; no SurfaceMountingManager found for [" + this.mSurfaceId + "]");
            return;
        }
        surfaceManager.preallocateView(this.mComponent, this.mReactTag, this.mProps, this.mStateWrapper, this.mEventEmitterWrapper, this.mIsLayoutable);
    }

    public String toString() {
        return "PreAllocateViewMountItem [" + this.mReactTag + "] - component: " + this.mComponent + " surfaceId: " + this.mSurfaceId + " isLayoutable: " + this.mIsLayoutable;
    }
}

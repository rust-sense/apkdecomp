package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.uimanager.StateWrapper;

/* loaded from: classes.dex */
public class MountItemFactory {
    public static DispatchCommandMountItem createDispatchCommandMountItem(int i, int i2, int i3, ReadableArray readableArray) {
        return new DispatchIntCommandMountItem(i, i2, i3, readableArray);
    }

    public static DispatchCommandMountItem createDispatchCommandMountItem(int i, int i2, String str, ReadableArray readableArray) {
        return new DispatchStringCommandMountItem(i, i2, str, readableArray);
    }

    public static MountItem createSendAccessibilityEventMountItem(int i, int i2, int i3) {
        return new SendAccessibilityEventMountItem(i, i2, i3);
    }

    public static MountItem createPreAllocateViewMountItem(int i, int i2, String str, ReadableMap readableMap, StateWrapper stateWrapper, EventEmitterWrapper eventEmitterWrapper, boolean z) {
        return new PreAllocateViewMountItem(i, i2, str, readableMap, stateWrapper, eventEmitterWrapper, z);
    }

    public static MountItem createIntBufferBatchMountItem(int i, int[] iArr, Object[] objArr, int i2) {
        return new IntBufferBatchMountItem(i, iArr, objArr, i2);
    }
}

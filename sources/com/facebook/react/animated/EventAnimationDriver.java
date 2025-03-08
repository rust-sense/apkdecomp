package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;
import com.facebook.react.uimanager.events.TouchEvent;
import java.util.List;

/* loaded from: classes.dex */
class EventAnimationDriver implements RCTModernEventEmitter {
    String mEventName;
    private List<String> mEventPath;
    ValueAnimatedNode mValueNode;
    int mViewTag;

    public EventAnimationDriver(String str, int i, List<String> list, ValueAnimatedNode valueAnimatedNode) {
        this.mEventName = str;
        this.mViewTag = i;
        this.mEventPath = list;
        this.mValueNode = valueAnimatedNode;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i, String str, WritableMap writableMap) {
        receiveEvent(-1, i, str, writableMap);
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int i, int i2, String str, WritableMap writableMap) {
        receiveEvent(i, i2, str, false, 0, writableMap, 2);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        throw new UnsupportedOperationException("receiveTouches is not support by native animated events");
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveTouches(TouchEvent touchEvent) {
        throw new UnsupportedOperationException("receiveTouches is not support by native animated events");
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int i, int i2, String str, boolean z, int i3, WritableMap writableMap, int i4) {
        ReadableArray array;
        ReadableMap readableMap;
        if (writableMap == null) {
            throw new IllegalArgumentException("Native animated events must have event data.");
        }
        ReadableArray readableArray = null;
        for (int i5 = 0; i5 < this.mEventPath.size() - 1; i5++) {
            if (writableMap != null) {
                String str2 = this.mEventPath.get(i5);
                ReadableType type = writableMap.getType(str2);
                if (type == ReadableType.Map) {
                    readableMap = writableMap.getMap(str2);
                    array = null;
                } else if (type == ReadableType.Array) {
                    array = writableMap.getArray(str2);
                    readableMap = null;
                } else {
                    throw new UnexpectedNativeTypeException("Unexpected type " + type + " for key '" + str2 + "'");
                }
                writableMap = readableMap;
                readableArray = array;
            } else {
                int parseInt = Integer.parseInt(this.mEventPath.get(i5));
                ReadableType type2 = readableArray.getType(parseInt);
                if (type2 == ReadableType.Map) {
                    writableMap = readableArray.getMap(parseInt);
                    readableArray = null;
                } else if (type2 == ReadableType.Array) {
                    readableArray = readableArray.getArray(parseInt);
                    writableMap = null;
                } else {
                    throw new UnexpectedNativeTypeException("Unexpected type " + type2 + " for index '" + parseInt + "'");
                }
            }
        }
        String str3 = this.mEventPath.get(r2.size() - 1);
        if (writableMap != null) {
            this.mValueNode.mValue = writableMap.getDouble(str3);
        } else {
            this.mValueNode.mValue = readableArray.getDouble(Integer.parseInt(str3));
        }
    }
}

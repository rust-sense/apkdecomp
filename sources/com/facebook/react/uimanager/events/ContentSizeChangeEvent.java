package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;

@Deprecated
/* loaded from: classes.dex */
public class ContentSizeChangeEvent extends Event<ContentSizeChangeEvent> {
    public static final String EVENT_NAME = "topContentSizeChange";
    private final int mHeight;
    private final int mWidth;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topContentSizeChange";
    }

    @Deprecated
    public ContentSizeChangeEvent(int i, int i2, int i3) {
        this(-1, i, i2, i3);
    }

    public ContentSizeChangeEvent(int i, int i2, int i3, int i4) {
        super(i, i2);
        this.mWidth = i3;
        this.mHeight = i4;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("width", PixelUtil.toDIPFromPixel(this.mWidth));
        createMap.putDouble("height", PixelUtil.toDIPFromPixel(this.mHeight));
        return createMap;
    }
}

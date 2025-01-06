package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.core.PanGestureHandler;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PanGestureHandlerEventDataBuilder.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/swmansion/gesturehandler/react/eventbuilders/PanGestureHandlerEventDataBuilder;", "Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "Lcom/swmansion/gesturehandler/core/PanGestureHandler;", "handler", "(Lcom/swmansion/gesturehandler/core/PanGestureHandler;)V", "absoluteX", "", "absoluteY", "translationX", "translationY", "velocityX", "velocityY", ViewHierarchyNode.JsonKeys.X, ViewHierarchyNode.JsonKeys.Y, "buildEventData", "", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PanGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder<PanGestureHandler> {
    private final float absoluteX;
    private final float absoluteY;
    private final float translationX;
    private final float translationY;
    private final float velocityX;
    private final float velocityY;
    private final float x;
    private final float y;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PanGestureHandlerEventDataBuilder(PanGestureHandler handler) {
        super(handler);
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.x = handler.getLastRelativePositionX();
        this.y = handler.getLastRelativePositionY();
        this.absoluteX = handler.getLastPositionInWindowX();
        this.absoluteY = handler.getLastPositionInWindowY();
        this.translationX = handler.getTranslationX();
        this.translationY = handler.getTranslationY();
        this.velocityX = handler.getVelocityX();
        this.velocityY = handler.getVelocityY();
    }

    @Override // com.swmansion.gesturehandler.react.eventbuilders.GestureHandlerEventDataBuilder
    public void buildEventData(WritableMap eventData) {
        Intrinsics.checkNotNullParameter(eventData, "eventData");
        super.buildEventData(eventData);
        eventData.putDouble(ViewHierarchyNode.JsonKeys.X, PixelUtil.toDIPFromPixel(this.x));
        eventData.putDouble(ViewHierarchyNode.JsonKeys.Y, PixelUtil.toDIPFromPixel(this.y));
        eventData.putDouble("absoluteX", PixelUtil.toDIPFromPixel(this.absoluteX));
        eventData.putDouble("absoluteY", PixelUtil.toDIPFromPixel(this.absoluteY));
        eventData.putDouble("translationX", PixelUtil.toDIPFromPixel(this.translationX));
        eventData.putDouble("translationY", PixelUtil.toDIPFromPixel(this.translationY));
        eventData.putDouble("velocityX", PixelUtil.toDIPFromPixel(this.velocityX));
        eventData.putDouble("velocityY", PixelUtil.toDIPFromPixel(this.velocityY));
    }
}
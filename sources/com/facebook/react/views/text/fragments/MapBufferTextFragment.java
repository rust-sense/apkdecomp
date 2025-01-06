package com.facebook.react.views.text.fragments;

import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.text.TextAttributeProps;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapBufferTextFragment.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001a\u001a\u00020\nH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/views/text/fragments/MapBufferTextFragment;", "Lcom/facebook/react/views/text/fragments/TextFragment;", Request.JsonKeys.FRAGMENT, "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "(Lcom/facebook/react/common/mapbuffer/MapBuffer;)V", "height", "", "getHeight", "()D", ViewProps.IS_ATTACHMENT, "", "()Z", "reactTag", "", "getReactTag", "()I", "string", "", "getString", "()Ljava/lang/String;", "textAttributeProps", "Lcom/facebook/react/views/text/TextAttributeProps;", "getTextAttributeProps", "()Lcom/facebook/react/views/text/TextAttributeProps;", "width", "getWidth", "hasIsAttachment", "hasReactTag", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MapBufferTextFragment implements TextFragment {
    private final MapBuffer fragment;

    public MapBufferTextFragment(MapBuffer fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.fragment = fragment;
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public TextAttributeProps getTextAttributeProps() {
        TextAttributeProps fromMapBuffer = TextAttributeProps.fromMapBuffer(this.fragment.getMapBuffer(5));
        Intrinsics.checkNotNullExpressionValue(fromMapBuffer, "fromMapBuffer(...)");
        return fromMapBuffer;
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public String getString() {
        return this.fragment.getString(0);
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public boolean hasReactTag() {
        return this.fragment.contains(1);
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public int getReactTag() {
        return this.fragment.getInt(1);
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public boolean hasIsAttachment() {
        return this.fragment.contains(2);
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public boolean isAttachment() {
        return this.fragment.getBoolean(2);
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public double getWidth() {
        return this.fragment.getDouble(3);
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public double getHeight() {
        return this.fragment.getDouble(4);
    }
}

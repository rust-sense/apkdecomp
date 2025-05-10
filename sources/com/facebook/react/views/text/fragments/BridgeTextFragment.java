package com.facebook.react.views.text.fragments;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.text.TextAttributeProps;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BridgeTextFragment.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001a\u001a\u00020\nH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/views/text/fragments/BridgeTextFragment;", "Lcom/facebook/react/views/text/fragments/TextFragment;", Request.JsonKeys.FRAGMENT, "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "height", "", "getHeight", "()D", ViewProps.IS_ATTACHMENT, "", "()Z", "reactTag", "", "getReactTag", "()I", "string", "", "getString", "()Ljava/lang/String;", "textAttributeProps", "Lcom/facebook/react/views/text/TextAttributeProps;", "getTextAttributeProps", "()Lcom/facebook/react/views/text/TextAttributeProps;", "width", "getWidth", "hasIsAttachment", "hasReactTag", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BridgeTextFragment implements TextFragment {
    private final ReadableMap fragment;

    public BridgeTextFragment(ReadableMap fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.fragment = fragment;
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public TextAttributeProps getTextAttributeProps() {
        TextAttributeProps fromReadableMap = TextAttributeProps.fromReadableMap(new ReactStylesDiffMap(this.fragment.getMap("textAttributes")));
        Intrinsics.checkNotNullExpressionValue(fromReadableMap, "fromReadableMap(...)");
        return fromReadableMap;
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public String getString() {
        return this.fragment.getString("string");
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public boolean hasReactTag() {
        return this.fragment.hasKey("reactTag");
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public int getReactTag() {
        return this.fragment.getInt("reactTag");
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public boolean hasIsAttachment() {
        return this.fragment.hasKey(ViewProps.IS_ATTACHMENT);
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public boolean isAttachment() {
        return this.fragment.getBoolean(ViewProps.IS_ATTACHMENT);
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public double getWidth() {
        return this.fragment.getDouble("width");
    }

    @Override // com.facebook.react.views.text.fragments.TextFragment
    public double getHeight() {
        return this.fragment.getDouble("height");
    }
}

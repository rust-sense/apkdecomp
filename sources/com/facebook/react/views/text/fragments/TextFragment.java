package com.facebook.react.views.text.fragments;

import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.text.TextAttributeProps;
import kotlin.Metadata;

/* compiled from: TextFragment.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J\b\u0010\u0017\u001a\u00020\u0007H&J\b\u0010\u0018\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u0004\u0018\u00010\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0012\u0010\u0015\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Lcom/facebook/react/views/text/fragments/TextFragment;", "", "height", "", "getHeight", "()D", ViewProps.IS_ATTACHMENT, "", "()Z", "reactTag", "", "getReactTag", "()I", "string", "", "getString", "()Ljava/lang/String;", "textAttributeProps", "Lcom/facebook/react/views/text/TextAttributeProps;", "getTextAttributeProps", "()Lcom/facebook/react/views/text/TextAttributeProps;", "width", "getWidth", "hasIsAttachment", "hasReactTag", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public interface TextFragment {
    double getHeight();

    int getReactTag();

    String getString();

    TextAttributeProps getTextAttributeProps();

    double getWidth();

    boolean hasIsAttachment();

    boolean hasReactTag();

    boolean isAttachment();
}

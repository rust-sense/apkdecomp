package com.facebook.react.views.text.fragments;

import com.facebook.react.common.mapbuffer.MapBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapBufferTextFragmentList.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/react/views/text/fragments/MapBufferTextFragmentList;", "Lcom/facebook/react/views/text/fragments/TextFragmentList;", "fragments", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "(Lcom/facebook/react/common/mapbuffer/MapBuffer;)V", "count", "", "getCount", "()I", "getFragment", "Lcom/facebook/react/views/text/fragments/TextFragment;", "index", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MapBufferTextFragmentList implements TextFragmentList {
    private final MapBuffer fragments;

    public MapBufferTextFragmentList(MapBuffer fragments) {
        Intrinsics.checkNotNullParameter(fragments, "fragments");
        this.fragments = fragments;
    }

    @Override // com.facebook.react.views.text.fragments.TextFragmentList
    public TextFragment getFragment(int index) {
        return new MapBufferTextFragment(this.fragments.getMapBuffer(index));
    }

    @Override // com.facebook.react.views.text.fragments.TextFragmentList
    public int getCount() {
        return this.fragments.getCount();
    }
}

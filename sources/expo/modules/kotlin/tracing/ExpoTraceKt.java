package expo.modules.kotlin.tracing;

import androidx.exifinterface.media.ExifInterface;
import androidx.tracing.Trace;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoTrace.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0019\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0086\b\u001a#\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\t\u0010\t\u001a\u00020\u0001H\u0086\b\u001a/\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\u0004\u001a\u00020\u00032\u000e\b\u0004\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0081\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a7\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u000e\b\u0004\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"beginAsyncTraceBlock", "", ViewHierarchyNode.JsonKeys.TAG, "", "blockName", "cookie", "", "beginTraceBlock", "endAsyncTraceBlock", "endTraceBlock", "trace", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoTraceKt {
    public static final <T> T trace(String tag, String blockName, Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(blockName, "blockName");
        Intrinsics.checkNotNullParameter(block, "block");
        Trace.beginSection("[" + tag + "] " + blockName);
        try {
            return block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            Trace.endSection();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void beginTraceBlock(String tag, String blockName) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(blockName, "blockName");
        Trace.beginSection("[" + tag + "] " + blockName);
    }

    public static final void endTraceBlock() {
        Trace.endSection();
    }

    public static /* synthetic */ void beginAsyncTraceBlock$default(String tag, String blockName, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(blockName, "blockName");
        Trace.beginAsyncSection("[" + tag + "] " + blockName, i);
    }

    public static final void beginAsyncTraceBlock(String tag, String blockName, int i) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(blockName, "blockName");
        Trace.beginAsyncSection("[" + tag + "] " + blockName, i);
    }

    public static /* synthetic */ void endAsyncTraceBlock$default(String tag, String blockName, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(blockName, "blockName");
        Trace.endAsyncSection("[" + tag + "] " + blockName, i);
    }

    public static final void endAsyncTraceBlock(String tag, String blockName, int i) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(blockName, "blockName");
        Trace.endAsyncSection("[" + tag + "] " + blockName, i);
    }

    public static final <T> T trace(String blockName, Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(blockName, "blockName");
        Intrinsics.checkNotNullParameter(block, "block");
        Trace.beginSection("[ExpoModulesCore] " + blockName);
        try {
            return block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            Trace.endSection();
            InlineMarker.finallyEnd(1);
        }
    }
}

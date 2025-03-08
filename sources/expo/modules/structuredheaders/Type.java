package expo.modules.structuredheaders;

import androidx.core.util.Supplier;

/* loaded from: classes2.dex */
public interface Type<T> extends Supplier<T> {
    String serialize();

    StringBuilder serializeTo(StringBuilder sb);
}

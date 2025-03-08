package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: JvmBuiltInsCustomizer.kt */
/* loaded from: classes3.dex */
public final class JvmBuiltInsCustomizerKt {
    private static final Name GET_FIRST_LIST_NAME;
    private static final Name GET_LAST_LIST_NAME;

    static {
        Name identifier = Name.identifier("getFirst");
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier(...)");
        GET_FIRST_LIST_NAME = identifier;
        Name identifier2 = Name.identifier("getLast");
        Intrinsics.checkNotNullExpressionValue(identifier2, "identifier(...)");
        GET_LAST_LIST_NAME = identifier2;
    }
}

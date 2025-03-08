package expo.modules.kotlin.jni;

import expo.modules.kotlin.jni.JavaScriptObject;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* compiled from: JavaScriptObject.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002¨\u0006\u0004"}, d2 = {"toCppOptions", "", "", "Lexpo/modules/kotlin/jni/JavaScriptObject$PropertyDescriptor;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JavaScriptObjectKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int toCppOptions(List<? extends JavaScriptObject.PropertyDescriptor> list) {
        Iterator<T> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i |= ((JavaScriptObject.PropertyDescriptor) it.next()).getValue();
        }
        return i;
    }
}

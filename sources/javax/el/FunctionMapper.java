package javax.el;

import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public abstract class FunctionMapper {
    public void mapFunction(String str, String str2, Method method) {
    }

    public abstract Method resolveFunction(String str, String str2);
}

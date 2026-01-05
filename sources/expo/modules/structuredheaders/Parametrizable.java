package expo.modules.structuredheaders;

/* loaded from: classes2.dex */
public interface Parametrizable<T> extends Type<T> {
    Parameters getParams();

    Parametrizable<T> withParams(Parameters parameters);
}

package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface InMemoryRepresentable {
    ASN1Primitive getLoadedObject() throws IOException;
}

package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface ASN1ExternalParser extends ASN1Encodable, InMemoryRepresentable {
    ASN1Encodable readObject() throws IOException;
}

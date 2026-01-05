package org.bouncycastle.pqc.crypto.sphincsplus;

/* loaded from: classes3.dex */
class NodeEntry {
    final int nodeHeight;
    final byte[] nodeValue;

    NodeEntry(byte[] bArr, int i) {
        this.nodeValue = bArr;
        this.nodeHeight = i;
    }
}

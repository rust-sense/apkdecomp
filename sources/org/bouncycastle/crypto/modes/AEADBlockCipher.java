package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;

/* loaded from: classes3.dex */
public interface AEADBlockCipher extends AEADCipher {
    BlockCipher getUnderlyingCipher();
}

package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {
    private byte[] IV;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    private byte[] counter;
    private byte[] counterOut;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.IV = new byte[blockSize];
        this.counter = new byte[blockSize];
        this.counterOut = new byte[blockSize];
        this.byteCount = 0;
    }

    private void adjustCounter(long j) {
        int i = 5;
        if (j >= 0) {
            long j2 = (this.byteCount + j) / this.blockSize;
            long j3 = j2;
            if (j2 > 255) {
                while (i >= 1) {
                    long j4 = 1 << (i * 8);
                    while (j3 >= j4) {
                        incrementCounterAt(i);
                        j3 -= j4;
                    }
                    i--;
                }
            }
            incrementCounter((int) j3);
            this.byteCount = (int) ((j + this.byteCount) - (this.blockSize * j2));
            return;
        }
        long j5 = ((-j) - this.byteCount) / this.blockSize;
        long j6 = j5;
        if (j5 > 255) {
            while (i >= 1) {
                long j7 = 1 << (i * 8);
                while (j6 > j7) {
                    decrementCounterAt(i);
                    j6 -= j7;
                }
                i--;
            }
        }
        for (long j8 = 0; j8 != j6; j8++) {
            decrementCounterAt(0);
        }
        int i2 = (int) (this.byteCount + j + (this.blockSize * j5));
        if (i2 >= 0) {
            this.byteCount = 0;
        } else {
            decrementCounterAt(0);
            this.byteCount = this.blockSize + i2;
        }
    }

    private void checkCounter() {
        if (this.IV.length >= this.blockSize) {
            return;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.IV;
            if (i == bArr.length) {
                return;
            }
            if (this.counter[i] != bArr[i]) {
                throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
            }
            i++;
        }
    }

    private void decrementCounterAt(int i) {
        byte b;
        int length = this.counter.length - i;
        do {
            length--;
            if (length < 0) {
                return;
            }
            b = (byte) (r1[length] - 1);
            this.counter[length] = b;
        } while (b == -1);
    }

    private void incrementCounter(int i) {
        byte[] bArr = this.counter;
        byte b = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + i);
        if (b == 0 || bArr[bArr.length - 1] >= b) {
            return;
        }
        incrementCounterAt(1);
    }

    private void incrementCounterAt(int i) {
        byte b;
        int length = this.counter.length - i;
        do {
            length--;
            if (length < 0) {
                return;
            }
            byte[] bArr = this.counter;
            b = (byte) (bArr[length] + 1);
            bArr[length] = b;
        } while (b == 0);
    }

    private void incrementCounterChecked() {
        byte b;
        int length = this.counter.length;
        do {
            length--;
            if (length < 0) {
                break;
            }
            byte[] bArr = this.counter;
            b = (byte) (bArr[length] + 1);
            bArr[length] = b;
        } while (b == 0);
        byte[] bArr2 = this.IV;
        if (length < bArr2.length && bArr2.length < this.blockSize) {
            throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
        }
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) throws DataLengthException, IllegalStateException {
        int i = this.byteCount;
        if (i == 0) {
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            byte[] bArr = this.counterOut;
            int i2 = this.byteCount;
            this.byteCount = i2 + 1;
            return (byte) (b ^ bArr[i2]);
        }
        byte[] bArr2 = this.counterOut;
        int i3 = i + 1;
        this.byteCount = i3;
        byte b2 = (byte) (b ^ bArr2[i]);
        if (i3 == this.counter.length) {
            this.byteCount = 0;
            incrementCounterChecked();
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        byte[] bArr = this.counter;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        int i = length - 1;
        while (i >= 1) {
            byte[] bArr3 = this.IV;
            int i2 = i < bArr3.length ? (bArr2[i] & 255) - (bArr3[i] & 255) : bArr2[i] & 255;
            if (i2 < 0) {
                int i3 = i - 1;
                bArr2[i3] = (byte) (bArr2[i3] - 1);
                i2 += 256;
            }
            bArr2[i] = (byte) i2;
            i--;
        }
        return (Pack.bigEndianToLong(bArr2, length - 8) * this.blockSize) + this.byteCount;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] clone = Arrays.clone(parametersWithIV.getIV());
        this.IV = clone;
        int i = this.blockSize;
        if (i < clone.length) {
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.blockSize + " bytes.");
        }
        int i2 = 8 > i / 2 ? i / 2 : 8;
        if (i - clone.length <= i2) {
            if (parametersWithIV.getParameters() != null) {
                this.cipher.init(true, parametersWithIV.getParameters());
            }
            reset();
        } else {
            throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.blockSize - i2) + " bytes.");
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.byteCount != 0) {
            processBytes(bArr, i, this.blockSize, bArr2, i2);
        } else {
            int i3 = this.blockSize;
            if (i + i3 > bArr.length) {
                throw new DataLengthException("input buffer too small");
            }
            if (i3 + i2 > bArr2.length) {
                throw new OutputLengthException("output buffer too short");
            }
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            for (int i4 = 0; i4 < this.blockSize; i4++) {
                bArr2[i2 + i4] = (byte) (bArr[i + i4] ^ this.counterOut[i4]);
            }
            incrementCounterChecked();
        }
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher, org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        byte b;
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too small");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = this.byteCount;
            if (i5 == 0) {
                this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
                byte b2 = bArr[i + i4];
                byte[] bArr3 = this.counterOut;
                int i6 = this.byteCount;
                this.byteCount = i6 + 1;
                b = (byte) (b2 ^ bArr3[i6]);
            } else {
                byte b3 = bArr[i + i4];
                byte[] bArr4 = this.counterOut;
                int i7 = i5 + 1;
                this.byteCount = i7;
                b = (byte) (bArr4[i5] ^ b3);
                if (i7 == this.counter.length) {
                    this.byteCount = 0;
                    incrementCounterChecked();
                }
            }
            bArr2[i3 + i4] = b;
        }
        return i2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.counter, (byte) 0);
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.counter, 0, bArr.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long skip(long j) {
        adjustCounter(j);
        checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return j;
    }
}

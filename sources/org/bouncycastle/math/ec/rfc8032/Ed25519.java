package org.bouncycastle.math.ec.rfc8032;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.math.ec.rfc7748.X25519;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public abstract class Ed25519 {
    private static final int COORD_INTS = 8;
    private static final int L0 = -50998291;
    private static final int L1 = 19280294;
    private static final int L2 = 127719000;
    private static final int L3 = -6428113;
    private static final int L4 = 5343;
    private static final long M08L = 255;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 32;
    private static final int PRECOMP_BLOCKS = 8;
    private static final int PRECOMP_MASK = 7;
    private static final int PRECOMP_POINTS = 8;
    private static final int PRECOMP_SPACING = 8;
    private static final int PRECOMP_TEETH = 4;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 32;
    private static final int SCALAR_BYTES = 32;
    private static final int SCALAR_INTS = 8;
    public static final int SECRET_KEY_SIZE = 32;
    public static final int SIGNATURE_SIZE = 64;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM2_PREFIX = {83, 105, 103, 69, 100, 50, 53, 53, 49, 57, 32, 110, 111, 32, 69, 100, 50, 53, 53, 49, 57, 32, 99, 111, 108, 108, 105, 115, 105, 111, 110, 115};
    private static final int[] P = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    private static final int[] L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    private static final int[] B_x = {52811034, 25909283, 8072341, 50637101, 13785486, 30858332, 20483199, 20966410, 43936626, 4379245};
    private static final int[] B_y = {40265304, 26843545, 6710886, 53687091, 13421772, 40265318, 26843545, 6710886, 53687091, 13421772};
    private static final int[] C_d = {56195235, 47411844, 25868126, 40503822, 57364, 58321048, 30416477, 31930572, 57760639, 10749657};
    private static final int[] C_d2 = {45281625, 27714825, 18181821, 13898781, 114729, 49533232, 60832955, 30306712, 48412415, 4722099};
    private static final int[] C_d4 = {23454386, 55429651, 2809210, 27797563, 229458, 31957600, 54557047, 27058993, 29715967, 9444199};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    public static final class Algorithm {
        public static final int Ed25519 = 0;
        public static final int Ed25519ctx = 1;
        public static final int Ed25519ph = 2;
    }

    private static class F extends X25519Field {
        private F() {
        }
    }

    private static class PointAccum {
        int[] u;
        int[] v;
        int[] x;
        int[] y;
        int[] z;

        private PointAccum() {
            this.x = F.create();
            this.y = F.create();
            this.z = F.create();
            this.u = F.create();
            this.v = F.create();
        }
    }

    private static class PointAffine {
        int[] x;
        int[] y;

        private PointAffine() {
            this.x = F.create();
            this.y = F.create();
        }
    }

    private static class PointExt {
        int[] t;
        int[] x;
        int[] y;
        int[] z;

        private PointExt() {
            this.x = F.create();
            this.y = F.create();
            this.z = F.create();
            this.t = F.create();
        }
    }

    private static class PointPrecomp {
        int[] xyd;
        int[] ymx_h;
        int[] ypx_h;

        private PointPrecomp() {
            this.ypx_h = F.create();
            this.ymx_h = F.create();
            this.xyd = F.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[16];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[8];
        decodeScalar(bArr3, 0, iArr3);
        Nat256.mulAddTo(iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[64];
        for (int i = 0; i < 16; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr, byte b) {
        return (bArr == null && b == 0) || (bArr != null && bArr.length < 256);
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        F.sqr(iArr, create2);
        F.sqr(iArr2, create3);
        F.mul(create2, create3, create);
        F.sub(create3, create2, create3);
        F.mul(create, C_d, create);
        F.addOne(create);
        F.sub(create, create3, create);
        F.normalize(create);
        return F.isZero(create);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        F.sqr(iArr, create2);
        F.sqr(iArr2, create3);
        F.sqr(iArr3, create4);
        F.mul(create2, create3, create);
        F.sub(create3, create2, create3);
        F.mul(create3, create4, create3);
        F.sqr(create4, create4);
        F.mul(create, C_d, create);
        F.add(create, create4, create);
        F.sub(create, create3, create);
        F.normalize(create);
        return F.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        int[] iArr = new int[8];
        decode32(bArr, 0, iArr, 0, 8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        return !Nat256.gte(iArr, P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] iArr) {
        decodeScalar(bArr, 0, iArr);
        return !Nat256.gte(iArr, L);
    }

    private static byte[] copy(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    private static Digest createDigest() {
        return new SHA512Digest();
    }

    public static Digest createPrehash() {
        return createDigest();
    }

    private static int decode24(byte[] bArr, int i) {
        return ((bArr[i + 2] & 255) << 16) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int i) {
        return (bArr[i + 3] << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private static void decode32(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = decode32(bArr, (i4 * 4) + i);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointAffine pointAffine) {
        byte[] copy = copy(bArr, i, 32);
        if (!checkPointVar(copy)) {
            return false;
        }
        byte b = copy[31];
        int i2 = (b & 128) >>> 7;
        copy[31] = (byte) (b & Byte.MAX_VALUE);
        F.decode(copy, 0, pointAffine.y);
        int[] create = F.create();
        int[] create2 = F.create();
        F.sqr(pointAffine.y, create);
        F.mul(C_d, create, create2);
        F.subOne(create);
        F.addOne(create2);
        if (!F.sqrtRatioVar(create, create2, pointAffine.x)) {
            return false;
        }
        F.normalize(pointAffine.x);
        if (i2 == 1 && F.isZeroVar(pointAffine.x)) {
            return false;
        }
        if (z ^ (i2 != (pointAffine.x[0] & 1))) {
            F.negate(pointAffine.x, pointAffine.x);
        }
        return true;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 8);
    }

    private static void dom2(Digest digest, byte b, byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = DOM2_PREFIX;
            int length = bArr2.length;
            int i = length + 2;
            int length2 = bArr.length + i;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bArr2, 0, bArr3, 0, length);
            bArr3[length] = b;
            bArr3[length + 1] = (byte) bArr.length;
            System.arraycopy(bArr, 0, bArr3, i, bArr.length);
            digest.update(bArr3, 0, length2);
        }
    }

    private static void encode24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    private static void encode56(long j, byte[] bArr, int i) {
        encode32((int) j, bArr, i);
        encode24((int) (j >>> 32), bArr, i + 4);
    }

    private static int encodePoint(PointAccum pointAccum, byte[] bArr, int i) {
        int[] create = F.create();
        int[] create2 = F.create();
        F.inv(pointAccum.z, create2);
        F.mul(pointAccum.x, create2, create);
        F.mul(pointAccum.y, create2, create2);
        F.normalize(create);
        F.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        F.encode(create2, bArr, i);
        int i2 = i + 31;
        bArr[i2] = (byte) (((create[0] & 1) << 7) | bArr[i2]);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Digest createDigest = createDigest();
        byte[] bArr3 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i, 32);
        createDigest.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[32];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] iArr, int i) {
        int[] iArr2 = new int[16];
        int i2 = 0;
        int i3 = 8;
        int i4 = 16;
        int i5 = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            int i6 = iArr[i3];
            iArr2[i4 - 1] = (i5 << 16) | (i6 >>> 16);
            i4 -= 2;
            iArr2[i4] = i6;
            i5 = i6;
        }
        byte[] bArr = new byte[253];
        int i7 = 32 - i;
        int i8 = 0;
        int i9 = 0;
        while (i2 < 16) {
            int i10 = iArr2[i2];
            while (i8 < 16) {
                int i11 = i10 >>> i8;
                if ((i11 & 1) == i9) {
                    i8++;
                } else {
                    int i12 = (i11 | 1) << i7;
                    bArr[(i2 << 4) + i8] = (byte) (i12 >> i7);
                    i8 += i;
                    i9 = i12 >>> 31;
                }
            }
            i2++;
            i8 -= 16;
        }
        return bArr;
    }

    private static void implSign(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom2(digest, b, bArr4);
        digest.update(bArr, 32, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom2(digest, b, bArr4);
        digest.update(bArr7, 0, 32);
        digest.update(bArr3, i, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 32);
        System.arraycopy(calculateS, 0, bArr6, i4 + 32, 32);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr5 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i, 32);
        createDigest.doFinal(bArr5, 0);
        byte[] bArr6 = new byte[32];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createDigest, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr6 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i, 32);
        createDigest.doFinal(bArr6, 0);
        byte[] bArr7 = new byte[32];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createDigest, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        byte[] copy = copy(bArr, i, 32);
        byte[] copy2 = copy(bArr, i + 32, 32);
        if (!checkPointVar(copy)) {
            return false;
        }
        int[] iArr = new int[8];
        if (!checkScalarVar(copy2, iArr)) {
            return false;
        }
        PointAffine pointAffine = new PointAffine();
        if (!decodePointVar(bArr2, i2, true, pointAffine)) {
            return false;
        }
        Digest createDigest = createDigest();
        byte[] bArr5 = new byte[createDigest.getDigestSize()];
        dom2(createDigest, b, bArr3);
        createDigest.update(copy, 0, 32);
        createDigest.update(bArr2, i2, 32);
        createDigest.update(bArr4, i3, i4);
        createDigest.doFinal(bArr5, 0);
        int[] iArr2 = new int[8];
        decodeScalar(reduceScalar(bArr5), 0, iArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultStrausVar(iArr, iArr2, pointAffine, pointAccum);
        byte[] bArr6 = new byte[32];
        return encodePoint(pointAccum, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copy);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2) {
        return F.isZeroVar(iArr) && F.isOneVar(iArr2);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2, int[] iArr3) {
        return F.isZeroVar(iArr) && F.areEqualVar(iArr2, iArr3);
    }

    private static void pointAdd(PointExt pointExt, PointAccum pointAccum) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        int[] iArr = pointAccum.u;
        int[] create5 = F.create();
        int[] create6 = F.create();
        int[] iArr2 = pointAccum.v;
        F.apm(pointAccum.y, pointAccum.x, create2, create);
        F.apm(pointExt.y, pointExt.x, create4, create3);
        F.mul(create, create3, create);
        F.mul(create2, create4, create2);
        F.mul(pointAccum.u, pointAccum.v, create3);
        F.mul(create3, pointExt.t, create3);
        F.mul(create3, C_d2, create3);
        F.mul(pointAccum.z, pointExt.z, create4);
        F.add(create4, create4, create4);
        F.apm(create2, create, iArr2, iArr);
        F.apm(create4, create3, create6, create5);
        F.carry(create6);
        F.mul(iArr, create5, pointAccum.x);
        F.mul(create6, iArr2, pointAccum.y);
        F.mul(create5, create6, pointAccum.z);
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        int[] create5 = F.create();
        int[] create6 = F.create();
        int[] create7 = F.create();
        int[] create8 = F.create();
        F.apm(pointExt.y, pointExt.x, create2, create);
        F.apm(pointExt2.y, pointExt2.x, create4, create3);
        F.mul(create, create3, create);
        F.mul(create2, create4, create2);
        F.mul(pointExt.t, pointExt2.t, create3);
        F.mul(create3, C_d2, create3);
        F.mul(pointExt.z, pointExt2.z, create4);
        F.add(create4, create4, create4);
        F.apm(create2, create, create8, create5);
        F.apm(create4, create3, create7, create6);
        F.carry(create7);
        F.mul(create5, create6, pointExt2.x);
        F.mul(create7, create8, pointExt2.y);
        F.mul(create6, create7, pointExt2.z);
        F.mul(create5, create8, pointExt2.t);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointAccum pointAccum) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] iArr = pointAccum.u;
        int[] create4 = F.create();
        int[] create5 = F.create();
        int[] iArr2 = pointAccum.v;
        F.apm(pointAccum.y, pointAccum.x, create2, create);
        F.mul(create, pointPrecomp.ymx_h, create);
        F.mul(create2, pointPrecomp.ypx_h, create2);
        F.mul(pointAccum.u, pointAccum.v, create3);
        F.mul(create3, pointPrecomp.xyd, create3);
        F.apm(create2, create, iArr2, iArr);
        F.apm(pointAccum.z, create3, create5, create4);
        F.carry(create5);
        F.mul(iArr, create4, pointAccum.x);
        F.mul(create5, iArr2, pointAccum.y);
        F.mul(create4, create5, pointAccum.z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointAccum pointAccum) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        int[] iArr5 = pointAccum.u;
        int[] create5 = F.create();
        int[] create6 = F.create();
        int[] iArr6 = pointAccum.v;
        if (z) {
            iArr2 = create3;
            iArr = create4;
            iArr4 = create5;
            iArr3 = create6;
        } else {
            iArr = create3;
            iArr2 = create4;
            iArr3 = create5;
            iArr4 = create6;
        }
        F.apm(pointAccum.y, pointAccum.x, create2, create);
        F.apm(pointExt.y, pointExt.x, iArr2, iArr);
        F.mul(create, create3, create);
        F.mul(create2, create4, create2);
        F.mul(pointAccum.u, pointAccum.v, create3);
        F.mul(create3, pointExt.t, create3);
        F.mul(create3, C_d2, create3);
        F.mul(pointAccum.z, pointExt.z, create4);
        F.add(create4, create4, create4);
        F.apm(create2, create, iArr6, iArr5);
        F.apm(create4, create3, iArr4, iArr3);
        F.carry(iArr4);
        F.mul(iArr5, create5, pointAccum.x);
        F.mul(create6, iArr6, pointAccum.y);
        F.mul(create5, create6, pointAccum.z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2, PointExt pointExt3) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        int[] create5 = F.create();
        int[] create6 = F.create();
        int[] create7 = F.create();
        int[] create8 = F.create();
        if (z) {
            iArr2 = create3;
            iArr = create4;
            iArr4 = create6;
            iArr3 = create7;
        } else {
            iArr = create3;
            iArr2 = create4;
            iArr3 = create6;
            iArr4 = create7;
        }
        F.apm(pointExt.y, pointExt.x, create2, create);
        F.apm(pointExt2.y, pointExt2.x, iArr2, iArr);
        F.mul(create, create3, create);
        F.mul(create2, create4, create2);
        F.mul(pointExt.t, pointExt2.t, create3);
        F.mul(create3, C_d2, create3);
        F.mul(pointExt.z, pointExt2.z, create4);
        F.add(create4, create4, create4);
        F.apm(create2, create, create8, create5);
        F.apm(create4, create3, iArr4, iArr3);
        F.carry(iArr4);
        F.mul(create5, create6, pointExt3.x);
        F.mul(create7, create8, pointExt3.y);
        F.mul(create6, create7, pointExt3.z);
        F.mul(create5, create8, pointExt3.t);
    }

    private static PointExt pointCopy(PointAccum pointAccum) {
        PointExt pointExt = new PointExt();
        F.copy(pointAccum.x, 0, pointExt.x, 0);
        F.copy(pointAccum.y, 0, pointExt.y, 0);
        F.copy(pointAccum.z, 0, pointExt.z, 0);
        F.mul(pointAccum.u, pointAccum.v, pointExt.t);
        return pointExt;
    }

    private static PointExt pointCopy(PointAffine pointAffine) {
        PointExt pointExt = new PointExt();
        F.copy(pointAffine.x, 0, pointExt.x, 0);
        F.copy(pointAffine.y, 0, pointExt.y, 0);
        pointExtendXY(pointExt);
        return pointExt;
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointAffine pointAffine, PointAccum pointAccum) {
        F.copy(pointAffine.x, 0, pointAccum.x, 0);
        F.copy(pointAffine.y, 0, pointAccum.y, 0);
        pointExtendXY(pointAccum);
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        F.copy(pointExt.x, 0, pointExt2.x, 0);
        F.copy(pointExt.y, 0, pointExt2.y, 0);
        F.copy(pointExt.z, 0, pointExt2.z, 0);
        F.copy(pointExt.t, 0, pointExt2.t, 0);
    }

    private static void pointDouble(PointAccum pointAccum) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] iArr = pointAccum.u;
        int[] create4 = F.create();
        int[] create5 = F.create();
        int[] iArr2 = pointAccum.v;
        F.sqr(pointAccum.x, create);
        F.sqr(pointAccum.y, create2);
        F.sqr(pointAccum.z, create3);
        F.add(create3, create3, create3);
        F.apm(create, create2, iArr2, create5);
        F.add(pointAccum.x, pointAccum.y, iArr);
        F.sqr(iArr, iArr);
        F.sub(iArr2, iArr, iArr);
        F.add(create3, create5, create4);
        F.carry(create4);
        F.mul(iArr, create4, pointAccum.x);
        F.mul(create5, iArr2, pointAccum.y);
        F.mul(create4, create5, pointAccum.z);
    }

    private static void pointExtendXY(PointAccum pointAccum) {
        F.one(pointAccum.z);
        F.copy(pointAccum.x, 0, pointAccum.u, 0);
        F.copy(pointAccum.y, 0, pointAccum.v, 0);
    }

    private static void pointExtendXY(PointExt pointExt) {
        F.one(pointExt.z);
        F.mul(pointExt.x, pointExt.y, pointExt.t);
    }

    private static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 240;
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            F.cmov(i5, precompBase, i3, pointPrecomp.ypx_h, 0);
            F.cmov(i5, precompBase, i3 + 10, pointPrecomp.ymx_h, 0);
            F.cmov(i5, precompBase, i3 + 20, pointPrecomp.xyd, 0);
            i3 += 30;
        }
    }

    private static void pointLookup(int[] iArr, int i, PointExt pointExt) {
        int i2 = i * 40;
        F.copy(iArr, i2, pointExt.x, 0);
        F.copy(iArr, i2 + 10, pointExt.y, 0);
        F.copy(iArr, i2 + 20, pointExt.z, 0);
        F.copy(iArr, i2 + 30, pointExt.t, 0);
    }

    private static void pointLookup(int[] iArr, int i, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            F.cmov(i6, iArr2, i4, pointExt.x, 0);
            F.cmov(i6, iArr2, i4 + 10, pointExt.y, 0);
            F.cmov(i6, iArr2, i4 + 20, pointExt.z, 0);
            F.cmov(i6, iArr2, i4 + 30, pointExt.t, 0);
            i4 += 40;
        }
        F.cnegate(i2, pointExt.x);
        F.cnegate(i2, pointExt.t);
    }

    private static int[] pointPrecompute(PointAffine pointAffine, int i) {
        PointExt pointCopy = pointCopy(pointAffine);
        PointExt pointCopy2 = pointCopy(pointCopy);
        pointAdd(pointCopy, pointCopy2);
        int[] createTable = F.createTable(i * 4);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            F.copy(pointCopy.x, 0, createTable, i2);
            F.copy(pointCopy.y, 0, createTable, i2 + 10);
            F.copy(pointCopy.z, 0, createTable, i2 + 20);
            F.copy(pointCopy.t, 0, createTable, i2 + 30);
            i2 += 40;
            i3++;
            if (i3 == i) {
                return createTable;
            }
            pointAdd(pointCopy2, pointCopy);
        }
    }

    private static PointExt[] pointPrecomputeVar(PointExt pointExt, int i) {
        PointExt pointExt2 = new PointExt();
        pointAddVar(false, pointExt, pointExt, pointExt2);
        PointExt[] pointExtArr = new PointExt[i];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i2 = 1; i2 < i; i2++) {
            PointExt pointExt3 = pointExtArr[i2 - 1];
            PointExt pointExt4 = new PointExt();
            pointExtArr[i2] = pointExt4;
            pointAddVar(false, pointExt3, pointExt2, pointExt4);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointAccum pointAccum) {
        F.zero(pointAccum.x);
        F.one(pointAccum.y);
        F.one(pointAccum.z);
        F.zero(pointAccum.u);
        F.one(pointAccum.v);
    }

    private static void pointSetNeutral(PointExt pointExt) {
        F.zero(pointExt.x);
        F.one(pointExt.y);
        F.one(pointExt.z);
        F.zero(pointExt.t);
    }

    public static void precompute() {
        int i;
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            int[] iArr = B_x;
            F.copy(iArr, 0, pointExt.x, 0);
            int[] iArr2 = B_y;
            F.copy(iArr2, 0, pointExt.y, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecomputeVar(pointExt, 32);
            PointAccum pointAccum = new PointAccum();
            F.copy(iArr, 0, pointAccum.x, 0);
            F.copy(iArr2, 0, pointAccum.y, 0);
            pointExtendXY(pointAccum);
            precompBase = F.createTable(192);
            int i2 = 0;
            for (int i3 = 0; i3 < 8; i3++) {
                PointExt[] pointExtArr = new PointExt[4];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int i4 = 0;
                while (true) {
                    i = 1;
                    if (i4 >= 4) {
                        break;
                    }
                    pointAddVar(true, pointExt2, pointCopy(pointAccum), pointExt2);
                    pointDouble(pointAccum);
                    pointExtArr[i4] = pointCopy(pointAccum);
                    if (i3 + i4 != 10) {
                        while (i < 8) {
                            pointDouble(pointAccum);
                            i++;
                        }
                    }
                    i4++;
                }
                PointExt[] pointExtArr2 = new PointExt[8];
                pointExtArr2[0] = pointExt2;
                int i5 = 0;
                int i6 = 1;
                while (i5 < 3) {
                    int i7 = i << i5;
                    int i8 = 0;
                    while (i8 < i7) {
                        PointExt pointExt3 = pointExtArr2[i6 - i7];
                        PointExt pointExt4 = pointExtArr[i5];
                        PointExt pointExt5 = new PointExt();
                        pointExtArr2[i6] = pointExt5;
                        pointAddVar(false, pointExt3, pointExt4, pointExt5);
                        i8++;
                        i6++;
                    }
                    i5++;
                    i = 1;
                }
                int[] createTable = F.createTable(8);
                int[] create = F.create();
                F.copy(pointExtArr2[0].z, 0, create, 0);
                F.copy(create, 0, createTable, 0);
                int i9 = 0;
                while (true) {
                    int i10 = i9 + 1;
                    if (i10 >= 8) {
                        break;
                    }
                    F.mul(create, pointExtArr2[i10].z, create);
                    F.copy(create, 0, createTable, i10 * 10);
                    i9 = i10;
                }
                F.add(create, create, create);
                F.invVar(create, create);
                int[] create2 = F.create();
                while (i9 > 0) {
                    int i11 = i9 - 1;
                    F.copy(createTable, i11 * 10, create2, 0);
                    F.mul(create2, create, create2);
                    F.copy(create2, 0, createTable, i9 * 10);
                    F.mul(create, pointExtArr2[i9].z, create);
                    i9 = i11;
                }
                F.copy(create, 0, createTable, 0);
                for (int i12 = 0; i12 < 8; i12++) {
                    PointExt pointExt6 = pointExtArr2[i12];
                    int[] create3 = F.create();
                    int[] create4 = F.create();
                    F.copy(createTable, i12 * 10, create4, 0);
                    F.mul(pointExt6.x, create4, create3);
                    F.mul(pointExt6.y, create4, create4);
                    PointPrecomp pointPrecomp = new PointPrecomp();
                    F.apm(create4, create3, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                    F.mul(create3, create4, pointPrecomp.xyd);
                    F.mul(pointPrecomp.xyd, C_d4, pointPrecomp.xyd);
                    F.normalize(pointPrecomp.ypx_h);
                    F.normalize(pointPrecomp.ymx_h);
                    F.copy(pointPrecomp.ypx_h, 0, precompBase, i2);
                    F.copy(pointPrecomp.ymx_h, 0, precompBase, i2 + 10);
                    F.copy(pointPrecomp.xyd, 0, precompBase, i2 + 20);
                    i2 += 30;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 32);
        bArr2[0] = (byte) (bArr2[0] & 248);
        byte b = (byte) (bArr2[31] & Byte.MAX_VALUE);
        bArr2[31] = b;
        bArr2[31] = (byte) (b | SignedBytes.MAX_POWER_OF_TWO);
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode32 = decode32(bArr, 49);
        long j = decode32 & 4294967295L;
        long decode322 = decode32(bArr, 56);
        long j2 = decode322 & 4294967295L;
        long j3 = bArr[63] & M08L;
        long decode24 = ((decode24(bArr, 60) << 4) & 4294967295L) + (j2 >> 28);
        long j4 = decode322 & M28L;
        long decode323 = (decode32(bArr, 28) & 4294967295L) - (decode24 * (-50998291));
        long decode242 = (((decode24(bArr, 32) << 4) & 4294967295L) - (j3 * (-50998291))) - (decode24 * 19280294);
        long decode324 = ((decode32(bArr, 42) & 4294967295L) - (j3 * (-6428113))) - (decode24 * 5343);
        long decode243 = ((((decode24(bArr, 39) << 4) & 4294967295L) - (j3 * 127719000)) - (decode24 * (-6428113))) - (j4 * 5343);
        long decode244 = ((decode24(bArr, 53) << 4) & 4294967295L) + (j >> 28);
        long j5 = decode32 & M28L;
        long decode325 = ((((decode32(bArr, 35) & 4294967295L) - (j3 * 19280294)) - (decode24 * 127719000)) - (j4 * (-6428113))) - (decode244 * 5343);
        long decode245 = ((((decode24(bArr, 25) << 4) & 4294967295L) - (j4 * (-50998291))) - (decode244 * 19280294)) - (j5 * 127719000);
        long j6 = ((decode242 - (j4 * 127719000)) - (decode244 * (-6428113))) - (j5 * 5343);
        long decode246 = (((decode24(bArr, 46) << 4) & 4294967295L) - (j3 * 5343)) + (decode324 >> 28);
        long j7 = (decode324 & M28L) + (decode243 >> 28);
        long decode247 = ((decode24(bArr, 11) << 4) & 4294967295L) - (j7 * (-50998291));
        long decode326 = ((decode32(bArr, 14) & 4294967295L) - (decode246 * (-50998291))) - (j7 * 19280294);
        long decode248 = ((((decode24(bArr, 18) << 4) & 4294967295L) - (j5 * (-50998291))) - (decode246 * 19280294)) - (j7 * 127719000);
        long decode327 = ((((decode32(bArr, 21) & 4294967295L) - (decode244 * (-50998291))) - (j5 * 19280294)) - (decode246 * 127719000)) - (j7 * (-6428113));
        long j8 = (decode245 - (decode246 * (-6428113))) - (j7 * 5343);
        long j9 = (decode243 & M28L) + (decode325 >> 28);
        long j10 = decode325 & M28L;
        long decode328 = (decode32(bArr, 7) & 4294967295L) - (j9 * (-50998291));
        long j11 = decode247 - (j9 * 19280294);
        long j12 = decode326 - (j9 * 127719000);
        long j13 = decode248 - (j9 * (-6428113));
        long j14 = decode327 - (j9 * 5343);
        long j15 = j10 + (j6 >> 28);
        long j16 = j6 & M28L;
        long decode249 = ((decode24(bArr, 4) << 4) & 4294967295L) - (j15 * (-50998291));
        long j17 = decode328 - (j15 * 19280294);
        long j18 = j11 - (j15 * 127719000);
        long j19 = j12 - (j15 * (-6428113));
        long j20 = j13 - (j15 * 5343);
        long j21 = ((((decode323 - (j4 * 19280294)) - (decode244 * 127719000)) - (j5 * (-6428113))) - (decode246 * 5343)) + (j8 >> 28);
        long j22 = j8 & M28L;
        long j23 = j21 & M28L;
        long j24 = j23 >>> 27;
        long j25 = j16 + (j21 >> 28) + j24;
        long decode329 = (decode32(bArr, 0) & 4294967295L) - (j25 * (-50998291));
        long j26 = (decode249 - (j25 * 19280294)) + (decode329 >> 28);
        long j27 = decode329 & M28L;
        long j28 = (j17 - (j25 * 127719000)) + (j26 >> 28);
        long j29 = j26 & M28L;
        long j30 = (j18 - (j25 * (-6428113))) + (j28 >> 28);
        long j31 = j28 & M28L;
        long j32 = (j19 - (j25 * 5343)) + (j30 >> 28);
        long j33 = j30 & M28L;
        long j34 = j20 + (j32 >> 28);
        long j35 = j32 & M28L;
        long j36 = j14 + (j34 >> 28);
        long j37 = j34 & M28L;
        long j38 = j22 + (j36 >> 28);
        long j39 = j36 & M28L;
        long j40 = j23 + (j38 >> 28);
        long j41 = j38 & M28L;
        long j42 = j40 >> 28;
        long j43 = j40 & M28L;
        long j44 = j42 - j24;
        long j45 = j27 + (j44 & (-50998291));
        long j46 = j29 + (j44 & 19280294) + (j45 >> 28);
        long j47 = j45 & M28L;
        long j48 = j31 + (j44 & 127719000) + (j46 >> 28);
        long j49 = j46 & M28L;
        long j50 = j33 + (j44 & (-6428113)) + (j48 >> 28);
        long j51 = j48 & M28L;
        long j52 = j35 + (j44 & 5343) + (j50 >> 28);
        long j53 = j50 & M28L;
        long j54 = j37 + (j52 >> 28);
        long j55 = j52 & M28L;
        long j56 = j39 + (j54 >> 28);
        long j57 = j54 & M28L;
        long j58 = j41 + (j56 >> 28);
        long j59 = j56 & M28L;
        long j60 = j43 + (j58 >> 28);
        long j61 = j58 & M28L;
        byte[] bArr2 = new byte[32];
        encode56(j47 | (j49 << 28), bArr2, 0);
        encode56((j53 << 28) | j51, bArr2, 7);
        encode56(j55 | (j57 << 28), bArr2, 14);
        encode56(j59 | (j61 << 28), bArr2, 21);
        encode32((int) j60, bArr2, 28);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointAffine pointAffine, PointAccum pointAccum) {
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBits(8, iArr, 3, 1);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, L, iArr);
        Nat.shiftDownBit(8, iArr, 0);
        int[] pointPrecompute = pointPrecompute(pointAffine, 8);
        PointExt pointExt = new PointExt();
        pointCopy(pointAffine, pointAccum);
        pointLookup(pointPrecompute, 7, pointExt);
        pointAdd(pointExt, pointAccum);
        int i = 62;
        while (true) {
            pointLookup(iArr, i, pointPrecompute, pointExt);
            pointAdd(pointExt, pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    private static void scalarMultBase(byte[] bArr, PointAccum pointAccum) {
        precompute();
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
        for (int i = 0; i < 8; i++) {
            iArr[i] = Interleave.shuffle2(iArr[i]);
        }
        PointPrecomp pointPrecomp = new PointPrecomp();
        pointSetNeutral(pointAccum);
        int i2 = 28;
        while (true) {
            for (int i3 = 0; i3 < 8; i3++) {
                int i4 = iArr[i3] >>> i2;
                int i5 = (i4 >>> 3) & 1;
                pointLookup(i3, (i4 ^ (-i5)) & 7, pointPrecomp);
                F.cswap(i5, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                F.cnegate(i5, pointPrecomp.xyd);
                pointAddPrecomp(pointPrecomp, pointAccum);
            }
            i2 -= 4;
            if (i2 < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr, pointAccum);
        if (encodePoint(pointAccum, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseYZ(X25519.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X25519");
        }
        byte[] bArr2 = new byte[32];
        pruneScalar(bArr, i, bArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr2, pointAccum);
        if (checkPoint(pointAccum.x, pointAccum.y, pointAccum.z) == 0) {
            throw new IllegalStateException();
        }
        F.copy(pointAccum.y, 0, iArr, 0);
        F.copy(pointAccum.z, 0, iArr2, 0);
    }

    private static void scalarMultOrderVar(PointAffine pointAffine, PointAccum pointAccum) {
        byte[] wnafVar = getWnafVar(L, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int i = 252;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> Ascii.US;
                pointAddVar(i2 != 0, pointPrecomputeVar[(b ^ i2) >>> 1], pointAccum);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointAffine pointAffine, PointAccum pointAccum) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int i = 252;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> Ascii.US;
                pointAddVar(i2 != 0, precompBaseTable[(b ^ i2) >>> 1], pointAccum);
            }
            byte b2 = wnafVar2[i];
            if (b2 != 0) {
                int i3 = b2 >> Ascii.US;
                pointAddVar(i3 != 0, pointPrecomputeVar[(b2 ^ i3) >>> 1], pointAccum);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        implSign(bArr, i, null, (byte) 0, bArr2, i2, i3, bArr3, i4);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        implSign(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4, bArr4, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != digest.doFinal(bArr5, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, 64, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Digest digest, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != digest.doFinal(bArr4, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, 64, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int i) {
        PointAffine pointAffine = new PointAffine();
        if (!decodePointVar(bArr, i, false, pointAffine)) {
            return false;
        }
        F.normalize(pointAffine.x);
        F.normalize(pointAffine.y);
        if (isNeutralElementVar(pointAffine.x, pointAffine.y)) {
            return false;
        }
        PointAccum pointAccum = new PointAccum();
        scalarMultOrderVar(pointAffine, pointAccum);
        F.normalize(pointAccum.x);
        F.normalize(pointAccum.y);
        F.normalize(pointAccum.z);
        return isNeutralElementVar(pointAccum.x, pointAccum.y, pointAccum.z);
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int i) {
        return decodePointVar(bArr, i, false, new PointAffine());
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest) {
        byte[] bArr4 = new byte[64];
        if (64 == digest.doFinal(bArr4, 0)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}

package org.bouncycastle.math.ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Random;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.endo.ECEndomorphism;
import org.bouncycastle.math.ec.endo.GLVEndomorphism;
import org.bouncycastle.math.field.FiniteField;
import org.bouncycastle.math.field.FiniteFields;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;

/* loaded from: classes3.dex */
public abstract class ECCurve {
    public static final int COORD_AFFINE = 0;
    public static final int COORD_HOMOGENEOUS = 1;
    public static final int COORD_JACOBIAN = 2;
    public static final int COORD_JACOBIAN_CHUDNOVSKY = 3;
    public static final int COORD_JACOBIAN_MODIFIED = 4;
    public static final int COORD_LAMBDA_AFFINE = 5;
    public static final int COORD_LAMBDA_PROJECTIVE = 6;
    public static final int COORD_SKEWED = 7;

    /* renamed from: a, reason: collision with root package name */
    protected ECFieldElement f27a;
    protected ECFieldElement b;
    protected BigInteger cofactor;
    protected FiniteField field;
    protected BigInteger order;
    protected int coord = 0;
    protected ECEndomorphism endomorphism = null;
    protected ECMultiplier multiplier = null;

    public static abstract class AbstractF2m extends ECCurve {
        private BigInteger[] si;

        protected AbstractF2m(int i, int i2, int i3, int i4) {
            super(buildField(i, i2, i3, i4));
            this.si = null;
        }

        private static FiniteField buildField(int i, int i2, int i3, int i4) {
            if (i2 == 0) {
                throw new IllegalArgumentException("k1 must be > 0");
            }
            if (i3 == 0) {
                if (i4 == 0) {
                    return FiniteFields.getBinaryExtensionField(new int[]{0, i2, i});
                }
                throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
            }
            if (i3 <= i2) {
                throw new IllegalArgumentException("k2 must be > k1");
            }
            if (i4 > i3) {
                return FiniteFields.getBinaryExtensionField(new int[]{0, i2, i3, i4, i});
            }
            throw new IllegalArgumentException("k3 must be > k2");
        }

        private static BigInteger implRandomFieldElementMult(SecureRandom secureRandom, int i) {
            BigInteger createRandomBigInteger;
            do {
                createRandomBigInteger = BigIntegers.createRandomBigInteger(i, secureRandom);
            } while (createRandomBigInteger.signum() <= 0);
            return createRandomBigInteger;
        }

        public static BigInteger inverse(int i, int[] iArr, BigInteger bigInteger) {
            return new LongArray(bigInteger).modInverse(i, iArr).toBigInteger();
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECPoint createPoint(BigInteger bigInteger, BigInteger bigInteger2) {
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            ECFieldElement fromBigInteger2 = fromBigInteger(bigInteger2);
            int coordinateSystem = getCoordinateSystem();
            if (coordinateSystem == 5 || coordinateSystem == 6) {
                if (!fromBigInteger.isZero()) {
                    fromBigInteger2 = fromBigInteger2.divide(fromBigInteger).add(fromBigInteger);
                } else if (!fromBigInteger2.square().equals(getB())) {
                    throw new IllegalArgumentException();
                }
            }
            return createRawPoint(fromBigInteger, fromBigInteger2);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint decompressPoint(int i, BigInteger bigInteger) {
            ECFieldElement eCFieldElement;
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            if (fromBigInteger.isZero()) {
                eCFieldElement = getB().sqrt();
            } else {
                ECFieldElement solveQuadraticEquation = solveQuadraticEquation(fromBigInteger.square().invert().multiply(getB()).add(getA()).add(fromBigInteger));
                if (solveQuadraticEquation != null) {
                    if (solveQuadraticEquation.testBitZero() != (i == 1)) {
                        solveQuadraticEquation = solveQuadraticEquation.addOne();
                    }
                    int coordinateSystem = getCoordinateSystem();
                    eCFieldElement = (coordinateSystem == 5 || coordinateSystem == 6) ? solveQuadraticEquation.add(fromBigInteger) : solveQuadraticEquation.multiply(fromBigInteger);
                } else {
                    eCFieldElement = null;
                }
            }
            if (eCFieldElement != null) {
                return createRawPoint(fromBigInteger, eCFieldElement);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }

        synchronized BigInteger[] getSi() {
            if (this.si == null) {
                this.si = Tnaf.getSi(this);
            }
            return this.si;
        }

        public boolean isKoblitz() {
            return this.order != null && this.cofactor != null && this.b.isOne() && (this.f27a.isZero() || this.f27a.isOne());
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public boolean isValidFieldElement(BigInteger bigInteger) {
            return bigInteger != null && bigInteger.signum() >= 0 && bigInteger.bitLength() <= getFieldSize();
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
            return fromBigInteger(BigIntegers.createRandomBigInteger(getFieldSize(), secureRandom));
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
            int fieldSize = getFieldSize();
            return fromBigInteger(implRandomFieldElementMult(secureRandom, fieldSize)).multiply(fromBigInteger(implRandomFieldElementMult(secureRandom, fieldSize)));
        }

        protected ECFieldElement solveQuadraticEquation(ECFieldElement eCFieldElement) {
            ECFieldElement eCFieldElement2;
            ECFieldElement.AbstractF2m abstractF2m = (ECFieldElement.AbstractF2m) eCFieldElement;
            boolean hasFastTrace = abstractF2m.hasFastTrace();
            if (hasFastTrace && abstractF2m.trace() != 0) {
                return null;
            }
            int fieldSize = getFieldSize();
            if ((fieldSize & 1) != 0) {
                ECFieldElement halfTrace = abstractF2m.halfTrace();
                if (hasFastTrace || halfTrace.square().add(halfTrace).add(eCFieldElement).isZero()) {
                    return halfTrace;
                }
                return null;
            }
            if (eCFieldElement.isZero()) {
                return eCFieldElement;
            }
            ECFieldElement fromBigInteger = fromBigInteger(ECConstants.ZERO);
            Random random = new Random();
            do {
                ECFieldElement fromBigInteger2 = fromBigInteger(new BigInteger(fieldSize, random));
                ECFieldElement eCFieldElement3 = eCFieldElement;
                eCFieldElement2 = fromBigInteger;
                for (int i = 1; i < fieldSize; i++) {
                    ECFieldElement square = eCFieldElement3.square();
                    eCFieldElement2 = eCFieldElement2.square().add(square.multiply(fromBigInteger2));
                    eCFieldElement3 = square.add(eCFieldElement);
                }
                if (!eCFieldElement3.isZero()) {
                    return null;
                }
            } while (eCFieldElement2.square().add(eCFieldElement2).isZero());
            return eCFieldElement2;
        }
    }

    public static abstract class AbstractFp extends ECCurve {
        protected AbstractFp(BigInteger bigInteger) {
            super(FiniteFields.getPrimeField(bigInteger));
        }

        private static BigInteger implRandomFieldElement(SecureRandom secureRandom, BigInteger bigInteger) {
            BigInteger createRandomBigInteger;
            do {
                createRandomBigInteger = BigIntegers.createRandomBigInteger(bigInteger.bitLength(), secureRandom);
            } while (createRandomBigInteger.compareTo(bigInteger) >= 0);
            return createRandomBigInteger;
        }

        private static BigInteger implRandomFieldElementMult(SecureRandom secureRandom, BigInteger bigInteger) {
            while (true) {
                BigInteger createRandomBigInteger = BigIntegers.createRandomBigInteger(bigInteger.bitLength(), secureRandom);
                if (createRandomBigInteger.signum() > 0 && createRandomBigInteger.compareTo(bigInteger) < 0) {
                    return createRandomBigInteger;
                }
            }
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint decompressPoint(int i, BigInteger bigInteger) {
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            ECFieldElement sqrt = fromBigInteger.square().add(this.f27a).multiply(fromBigInteger).add(this.b).sqrt();
            if (sqrt == null) {
                throw new IllegalArgumentException("Invalid point compression");
            }
            if (sqrt.testBitZero() != (i == 1)) {
                sqrt = sqrt.negate();
            }
            return createRawPoint(fromBigInteger, sqrt);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public boolean isValidFieldElement(BigInteger bigInteger) {
            return bigInteger != null && bigInteger.signum() >= 0 && bigInteger.compareTo(getField().getCharacteristic()) < 0;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
            BigInteger characteristic = getField().getCharacteristic();
            return fromBigInteger(implRandomFieldElement(secureRandom, characteristic)).multiply(fromBigInteger(implRandomFieldElement(secureRandom, characteristic)));
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
            BigInteger characteristic = getField().getCharacteristic();
            return fromBigInteger(implRandomFieldElementMult(secureRandom, characteristic)).multiply(fromBigInteger(implRandomFieldElementMult(secureRandom, characteristic)));
        }
    }

    public class Config {
        protected int coord;
        protected ECEndomorphism endomorphism;
        protected ECMultiplier multiplier;

        Config(int i, ECEndomorphism eCEndomorphism, ECMultiplier eCMultiplier) {
            this.coord = i;
            this.endomorphism = eCEndomorphism;
            this.multiplier = eCMultiplier;
        }

        public ECCurve create() {
            if (!ECCurve.this.supportsCoordinateSystem(this.coord)) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECCurve cloneCurve = ECCurve.this.cloneCurve();
            if (cloneCurve == ECCurve.this) {
                throw new IllegalStateException("implementation returned current curve");
            }
            synchronized (cloneCurve) {
                cloneCurve.coord = this.coord;
                cloneCurve.endomorphism = this.endomorphism;
                cloneCurve.multiplier = this.multiplier;
            }
            return cloneCurve;
        }

        public Config setCoordinateSystem(int i) {
            this.coord = i;
            return this;
        }

        public Config setEndomorphism(ECEndomorphism eCEndomorphism) {
            this.endomorphism = eCEndomorphism;
            return this;
        }

        public Config setMultiplier(ECMultiplier eCMultiplier) {
            this.multiplier = eCMultiplier;
            return this;
        }
    }

    public static class F2m extends AbstractF2m {
        private static final int F2M_DEFAULT_COORDS = 6;
        private ECPoint.F2m infinity;
        private int k1;
        private int k2;
        private int k3;
        private int m;

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i, i2, i3, i4, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(i, i2, i3, i4);
            this.m = i;
            this.k1 = i2;
            this.k2 = i3;
            this.k3 = i4;
            this.order = bigInteger3;
            this.cofactor = bigInteger4;
            this.infinity = new ECPoint.F2m(this, null, null);
            this.f27a = fromBigInteger(bigInteger);
            this.b = fromBigInteger(bigInteger2);
            this.coord = 6;
        }

        protected F2m(int i, int i2, int i3, int i4, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, BigInteger bigInteger, BigInteger bigInteger2) {
            super(i, i2, i3, i4);
            this.m = i;
            this.k1 = i2;
            this.k2 = i3;
            this.k3 = i4;
            this.order = bigInteger;
            this.cofactor = bigInteger2;
            this.infinity = new ECPoint.F2m(this, null, null);
            this.f27a = eCFieldElement;
            this.b = eCFieldElement2;
            this.coord = 6;
        }

        public F2m(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i, i2, 0, 0, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this(i, i2, 0, 0, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECCurve cloneCurve() {
            return new F2m(this.m, this.k1, this.k2, this.k3, this.f27a, this.b, this.order, this.cofactor);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
            final int i3 = (this.m + 63) >>> 6;
            final int[] iArr = isTrinomial() ? new int[]{this.k1} : new int[]{this.k1, this.k2, this.k3};
            final long[] jArr = new long[i2 * i3 * 2];
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                ECPoint eCPoint = eCPointArr[i + i5];
                ((ECFieldElement.F2m) eCPoint.getRawXCoord()).x.copyTo(jArr, i4);
                int i6 = i4 + i3;
                ((ECFieldElement.F2m) eCPoint.getRawYCoord()).x.copyTo(jArr, i6);
                i4 = i6 + i3;
            }
            return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.ECCurve.F2m.1
                private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                    return F2m.this.createRawPoint(new ECFieldElement.F2m(F2m.this.m, iArr, new LongArray(jArr2)), new ECFieldElement.F2m(F2m.this.m, iArr, new LongArray(jArr3)));
                }

                @Override // org.bouncycastle.math.ec.ECLookupTable
                public int getSize() {
                    return i2;
                }

                @Override // org.bouncycastle.math.ec.ECLookupTable
                public ECPoint lookup(int i7) {
                    int i8;
                    long[] create64 = Nat.create64(i3);
                    long[] create642 = Nat.create64(i3);
                    int i9 = 0;
                    for (int i10 = 0; i10 < i2; i10++) {
                        long j = ((i10 ^ i7) - 1) >> 31;
                        int i11 = 0;
                        while (true) {
                            i8 = i3;
                            if (i11 < i8) {
                                long j2 = create64[i11];
                                long[] jArr2 = jArr;
                                create64[i11] = j2 ^ (jArr2[i9 + i11] & j);
                                create642[i11] = create642[i11] ^ (jArr2[(i8 + i9) + i11] & j);
                                i11++;
                            }
                        }
                        i9 += i8 * 2;
                    }
                    return createPoint(create64, create642);
                }

                @Override // org.bouncycastle.math.ec.AbstractECLookupTable, org.bouncycastle.math.ec.ECLookupTable
                public ECPoint lookupVar(int i7) {
                    long[] create64 = Nat.create64(i3);
                    long[] create642 = Nat.create64(i3);
                    int i8 = i7 * i3 * 2;
                    int i9 = 0;
                    while (true) {
                        int i10 = i3;
                        if (i9 >= i10) {
                            return createPoint(create64, create642);
                        }
                        long[] jArr2 = jArr;
                        create64[i9] = jArr2[i8 + i9];
                        create642[i9] = jArr2[i10 + i8 + i9];
                        i9++;
                    }
                }
            };
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECMultiplier createDefaultMultiplier() {
            return isKoblitz() ? new WTauNafMultiplier() : super.createDefaultMultiplier();
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return new ECPoint.F2m(this, eCFieldElement, eCFieldElement2);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            return new ECPoint.F2m(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement fromBigInteger(BigInteger bigInteger) {
            return new ECFieldElement.F2m(this.m, this.k1, this.k2, this.k3, bigInteger);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public int getFieldSize() {
            return this.m;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECPoint getInfinity() {
            return this.infinity;
        }

        public int getK1() {
            return this.k1;
        }

        public int getK2() {
            return this.k2;
        }

        public int getK3() {
            return this.k3;
        }

        public int getM() {
            return this.m;
        }

        public boolean isTrinomial() {
            return this.k2 == 0 && this.k3 == 0;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public boolean supportsCoordinateSystem(int i) {
            return i == 0 || i == 1 || i == 6;
        }
    }

    public static class Fp extends AbstractFp {
        private static final int FP_DEFAULT_COORDS = 4;
        ECPoint.Fp infinity;
        BigInteger q;
        BigInteger r;

        public Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            this(bigInteger, bigInteger2, bigInteger3, null, null);
        }

        public Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
            super(bigInteger);
            this.q = bigInteger;
            this.r = ECFieldElement.Fp.calculateResidue(bigInteger);
            this.infinity = new ECPoint.Fp(this, null, null);
            this.f27a = fromBigInteger(bigInteger2);
            this.b = fromBigInteger(bigInteger3);
            this.order = bigInteger4;
            this.cofactor = bigInteger5;
            this.coord = 4;
        }

        protected Fp(BigInteger bigInteger, BigInteger bigInteger2, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(bigInteger);
            this.q = bigInteger;
            this.r = bigInteger2;
            this.infinity = new ECPoint.Fp(this, null, null);
            this.f27a = eCFieldElement;
            this.b = eCFieldElement2;
            this.order = bigInteger3;
            this.cofactor = bigInteger4;
            this.coord = 4;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECCurve cloneCurve() {
            return new Fp(this.q, this.r, this.f27a, this.b, this.order, this.cofactor);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return new ECPoint.Fp(this, eCFieldElement, eCFieldElement2);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            return new ECPoint.Fp(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement fromBigInteger(BigInteger bigInteger) {
            return new ECFieldElement.Fp(this.q, this.r, bigInteger);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public int getFieldSize() {
            return this.q.bitLength();
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECPoint getInfinity() {
            return this.infinity;
        }

        public BigInteger getQ() {
            return this.q;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECPoint importPoint(ECPoint eCPoint) {
            int coordinateSystem;
            return (this == eCPoint.getCurve() || getCoordinateSystem() != 2 || eCPoint.isInfinity() || !((coordinateSystem = eCPoint.getCurve().getCoordinateSystem()) == 2 || coordinateSystem == 3 || coordinateSystem == 4)) ? super.importPoint(eCPoint) : new ECPoint.Fp(this, fromBigInteger(eCPoint.x.toBigInteger()), fromBigInteger(eCPoint.y.toBigInteger()), new ECFieldElement[]{fromBigInteger(eCPoint.zs[0].toBigInteger())});
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public boolean supportsCoordinateSystem(int i) {
            return i == 0 || i == 1 || i == 2 || i == 4;
        }
    }

    protected ECCurve(FiniteField finiteField) {
        this.field = finiteField;
    }

    public static int[] getAllCoordinateSystems() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    protected void checkPoint(ECPoint eCPoint) {
        if (eCPoint == null || this != eCPoint.getCurve()) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
    }

    protected void checkPoints(ECPoint[] eCPointArr) {
        checkPoints(eCPointArr, 0, eCPointArr.length);
    }

    protected void checkPoints(ECPoint[] eCPointArr, int i, int i2) {
        if (eCPointArr == null) {
            throw new IllegalArgumentException("'points' cannot be null");
        }
        if (i < 0 || i2 < 0 || i > eCPointArr.length - i2) {
            throw new IllegalArgumentException("invalid range specified for 'points'");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            ECPoint eCPoint = eCPointArr[i + i3];
            if (eCPoint != null && this != eCPoint.getCurve()) {
                throw new IllegalArgumentException("'points' entries must be null or on this curve");
            }
        }
    }

    protected abstract ECCurve cloneCurve();

    public synchronized Config configure() {
        return new Config(this.coord, this.endomorphism, this.multiplier);
    }

    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final int fieldSize = (getFieldSize() + 7) >>> 3;
        final byte[] bArr = new byte[i2 * fieldSize * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            byte[] byteArray = eCPoint.getRawXCoord().toBigInteger().toByteArray();
            byte[] byteArray2 = eCPoint.getRawYCoord().toBigInteger().toByteArray();
            int i5 = 1;
            int i6 = byteArray.length > fieldSize ? 1 : 0;
            int length = byteArray.length - i6;
            if (byteArray2.length <= fieldSize) {
                i5 = 0;
            }
            int length2 = byteArray2.length - i5;
            int i7 = i3 + fieldSize;
            System.arraycopy(byteArray, i6, bArr, i7 - length, length);
            i3 = i7 + fieldSize;
            System.arraycopy(byteArray2, i5, bArr, i3 - length2, length2);
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.ECCurve.1
            private ECPoint createPoint(byte[] bArr2, byte[] bArr3) {
                ECCurve eCCurve = ECCurve.this;
                return eCCurve.createRawPoint(eCCurve.fromBigInteger(new BigInteger(1, bArr2)), ECCurve.this.fromBigInteger(new BigInteger(1, bArr3)));
            }

            @Override // org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return i2;
            }

            @Override // org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int i8) {
                int i9;
                int i10 = fieldSize;
                byte[] bArr2 = new byte[i10];
                byte[] bArr3 = new byte[i10];
                int i11 = 0;
                for (int i12 = 0; i12 < i2; i12++) {
                    int i13 = ((i12 ^ i8) - 1) >> 31;
                    int i14 = 0;
                    while (true) {
                        i9 = fieldSize;
                        if (i14 < i9) {
                            byte b = bArr2[i14];
                            byte[] bArr4 = bArr;
                            bArr2[i14] = (byte) (b ^ (bArr4[i11 + i14] & i13));
                            bArr3[i14] = (byte) ((bArr4[(i9 + i11) + i14] & i13) ^ bArr3[i14]);
                            i14++;
                        }
                    }
                    i11 += i9 * 2;
                }
                return createPoint(bArr2, bArr3);
            }

            @Override // org.bouncycastle.math.ec.AbstractECLookupTable, org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookupVar(int i8) {
                int i9 = fieldSize;
                byte[] bArr2 = new byte[i9];
                byte[] bArr3 = new byte[i9];
                int i10 = i8 * i9 * 2;
                int i11 = 0;
                while (true) {
                    int i12 = fieldSize;
                    if (i11 >= i12) {
                        return createPoint(bArr2, bArr3);
                    }
                    byte[] bArr4 = bArr;
                    bArr2[i11] = bArr4[i10 + i11];
                    bArr3[i11] = bArr4[i12 + i10 + i11];
                    i11++;
                }
            }
        };
    }

    protected ECMultiplier createDefaultMultiplier() {
        ECEndomorphism eCEndomorphism = this.endomorphism;
        return eCEndomorphism instanceof GLVEndomorphism ? new GLVMultiplier(this, (GLVEndomorphism) eCEndomorphism) : new WNafL2RMultiplier();
    }

    public ECPoint createPoint(BigInteger bigInteger, BigInteger bigInteger2) {
        return createRawPoint(fromBigInteger(bigInteger), fromBigInteger(bigInteger2));
    }

    protected abstract ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2);

    protected abstract ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr);

    public ECPoint decodePoint(byte[] bArr) {
        ECPoint infinity;
        int fieldSize = (getFieldSize() + 7) / 8;
        byte b = bArr[0];
        if (b != 0) {
            if (b == 2 || b == 3) {
                if (bArr.length != fieldSize + 1) {
                    throw new IllegalArgumentException("Incorrect length for compressed encoding");
                }
                infinity = decompressPoint(b & 1, BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize));
                if (!infinity.implIsValid(true, true)) {
                    throw new IllegalArgumentException("Invalid point");
                }
            } else if (b != 4) {
                if (b != 6 && b != 7) {
                    throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(b, 16));
                }
                if (bArr.length != (fieldSize * 2) + 1) {
                    throw new IllegalArgumentException("Incorrect length for hybrid encoding");
                }
                BigInteger fromUnsignedByteArray = BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize);
                BigInteger fromUnsignedByteArray2 = BigIntegers.fromUnsignedByteArray(bArr, fieldSize + 1, fieldSize);
                if (fromUnsignedByteArray2.testBit(0) != (b == 7)) {
                    throw new IllegalArgumentException("Inconsistent Y coordinate in hybrid encoding");
                }
                infinity = validatePoint(fromUnsignedByteArray, fromUnsignedByteArray2);
            } else {
                if (bArr.length != (fieldSize * 2) + 1) {
                    throw new IllegalArgumentException("Incorrect length for uncompressed encoding");
                }
                infinity = validatePoint(BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize), BigIntegers.fromUnsignedByteArray(bArr, fieldSize + 1, fieldSize));
            }
        } else {
            if (bArr.length != 1) {
                throw new IllegalArgumentException("Incorrect length for infinity encoding");
            }
            infinity = getInfinity();
        }
        if (b == 0 || !infinity.isInfinity()) {
            return infinity;
        }
        throw new IllegalArgumentException("Invalid infinity encoding");
    }

    protected abstract ECPoint decompressPoint(int i, BigInteger bigInteger);

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ECCurve) && equals((ECCurve) obj));
    }

    public boolean equals(ECCurve eCCurve) {
        return this == eCCurve || (eCCurve != null && getField().equals(eCCurve.getField()) && getA().toBigInteger().equals(eCCurve.getA().toBigInteger()) && getB().toBigInteger().equals(eCCurve.getB().toBigInteger()));
    }

    public abstract ECFieldElement fromBigInteger(BigInteger bigInteger);

    public ECFieldElement getA() {
        return this.f27a;
    }

    public ECFieldElement getB() {
        return this.b;
    }

    public BigInteger getCofactor() {
        return this.cofactor;
    }

    public int getCoordinateSystem() {
        return this.coord;
    }

    public ECEndomorphism getEndomorphism() {
        return this.endomorphism;
    }

    public FiniteField getField() {
        return this.field;
    }

    public abstract int getFieldSize();

    public abstract ECPoint getInfinity();

    public ECMultiplier getMultiplier() {
        if (this.multiplier == null) {
            this.multiplier = createDefaultMultiplier();
        }
        return this.multiplier;
    }

    public BigInteger getOrder() {
        return this.order;
    }

    public PreCompInfo getPreCompInfo(ECPoint eCPoint, String str) {
        Hashtable hashtable;
        PreCompInfo preCompInfo;
        checkPoint(eCPoint);
        synchronized (eCPoint) {
            hashtable = eCPoint.preCompTable;
        }
        if (hashtable == null) {
            return null;
        }
        synchronized (hashtable) {
            preCompInfo = (PreCompInfo) hashtable.get(str);
        }
        return preCompInfo;
    }

    public int hashCode() {
        return (getField().hashCode() ^ Integers.rotateLeft(getA().toBigInteger().hashCode(), 8)) ^ Integers.rotateLeft(getB().toBigInteger().hashCode(), 16);
    }

    public ECPoint importPoint(ECPoint eCPoint) {
        if (this == eCPoint.getCurve()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return getInfinity();
        }
        ECPoint normalize = eCPoint.normalize();
        return createPoint(normalize.getXCoord().toBigInteger(), normalize.getYCoord().toBigInteger());
    }

    public abstract boolean isValidFieldElement(BigInteger bigInteger);

    public void normalizeAll(ECPoint[] eCPointArr) {
        normalizeAll(eCPointArr, 0, eCPointArr.length, null);
    }

    public void normalizeAll(ECPoint[] eCPointArr, int i, int i2, ECFieldElement eCFieldElement) {
        checkPoints(eCPointArr, i, i2);
        int coordinateSystem = getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            if (eCFieldElement != null) {
                throw new IllegalArgumentException("'iso' not valid for affine coordinates");
            }
            return;
        }
        ECFieldElement[] eCFieldElementArr = new ECFieldElement[i2];
        int[] iArr = new int[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i + i4;
            ECPoint eCPoint = eCPointArr[i5];
            if (eCPoint != null && (eCFieldElement != null || !eCPoint.isNormalized())) {
                eCFieldElementArr[i3] = eCPoint.getZCoord(0);
                iArr[i3] = i5;
                i3++;
            }
        }
        if (i3 == 0) {
            return;
        }
        ECAlgorithms.montgomeryTrick(eCFieldElementArr, 0, i3, eCFieldElement);
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = iArr[i6];
            eCPointArr[i7] = eCPointArr[i7].normalize(eCFieldElementArr[i6]);
        }
    }

    public PreCompInfo precompute(ECPoint eCPoint, String str, PreCompCallback preCompCallback) {
        Hashtable hashtable;
        PreCompInfo precompute;
        checkPoint(eCPoint);
        synchronized (eCPoint) {
            hashtable = eCPoint.preCompTable;
            if (hashtable == null) {
                hashtable = new Hashtable(4);
                eCPoint.preCompTable = hashtable;
            }
        }
        synchronized (hashtable) {
            PreCompInfo preCompInfo = (PreCompInfo) hashtable.get(str);
            precompute = preCompCallback.precompute(preCompInfo);
            if (precompute != preCompInfo) {
                hashtable.put(str, precompute);
            }
        }
        return precompute;
    }

    public abstract ECFieldElement randomFieldElement(SecureRandom secureRandom);

    public abstract ECFieldElement randomFieldElementMult(SecureRandom secureRandom);

    public boolean supportsCoordinateSystem(int i) {
        return i == 0;
    }

    public ECPoint validatePoint(BigInteger bigInteger, BigInteger bigInteger2) {
        ECPoint createPoint = createPoint(bigInteger, bigInteger2);
        if (createPoint.isValid()) {
            return createPoint;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }
}

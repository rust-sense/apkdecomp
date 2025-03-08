package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* loaded from: classes3.dex */
public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private short[][] A1;
    private short[][] A1inv;
    private short[][] A2;
    private short[][] A2inv;
    private short[] b1;
    private short[] b2;
    private boolean initialized = false;
    private Layer[] layers;
    private int numOfLayers;
    private short[][] pub_quadratic;
    private short[] pub_scalar;
    private short[][] pub_singular;
    private RainbowKeyGenerationParameters rainbowParams;
    private SecureRandom sr;
    private int[] vi;

    private void compactPublicKey(short[][][] sArr) {
        int length = sArr.length;
        int length2 = sArr[0].length;
        this.pub_quadratic = (short[][]) Array.newInstance((Class<?>) Short.TYPE, length, ((length2 + 1) * length2) / 2);
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            for (int i3 = 0; i3 < length2; i3++) {
                for (int i4 = i3; i4 < length2; i4++) {
                    short[][] sArr2 = this.pub_quadratic;
                    if (i4 == i3) {
                        sArr2[i][i2] = sArr[i][i3][i4];
                    } else {
                        short[] sArr3 = sArr2[i];
                        short[][] sArr4 = sArr[i];
                        sArr3[i2] = GF2Field.addElem(sArr4[i3][i4], sArr4[i4][i3]);
                    }
                    i2++;
                }
            }
        }
    }

    private void computePublicKey() {
        ComputeInField computeInField = new ComputeInField();
        int[] iArr = this.vi;
        int i = 0;
        int i2 = iArr[iArr.length - 1] - iArr[0];
        int i3 = iArr[iArr.length - 1];
        short[][][] sArr = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, i2, i3, i3);
        this.pub_singular = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i2, i3);
        this.pub_scalar = new short[i2];
        short[] sArr2 = new short[i3];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            Layer[] layerArr = this.layers;
            if (i4 >= layerArr.length) {
                break;
            }
            short[][][] coeffAlpha = layerArr[i4].getCoeffAlpha();
            short[][][] coeffBeta = this.layers[i4].getCoeffBeta();
            short[][] coeffGamma = this.layers[i4].getCoeffGamma();
            short[] coeffEta = this.layers[i4].getCoeffEta();
            int length = coeffAlpha[i].length;
            int length2 = coeffBeta[i].length;
            int i6 = i;
            while (i6 < length) {
                int i7 = i;
                while (i7 < length) {
                    while (i < length2) {
                        int i8 = i3;
                        int i9 = i2;
                        int i10 = i7 + length2;
                        short[] multVect = computeInField.multVect(coeffAlpha[i6][i7][i], this.A2[i10]);
                        int i11 = i5 + i6;
                        int i12 = i4;
                        sArr[i11] = computeInField.addSquareMatrix(sArr[i11], computeInField.multVects(multVect, this.A2[i]));
                        short[] multVect2 = computeInField.multVect(this.b2[i], multVect);
                        short[][] sArr3 = this.pub_singular;
                        sArr3[i11] = computeInField.addVect(multVect2, sArr3[i11]);
                        short[] multVect3 = computeInField.multVect(this.b2[i10], computeInField.multVect(coeffAlpha[i6][i7][i], this.A2[i]));
                        short[][] sArr4 = this.pub_singular;
                        sArr4[i11] = computeInField.addVect(multVect3, sArr4[i11]);
                        short multElem = GF2Field.multElem(coeffAlpha[i6][i7][i], this.b2[i10]);
                        short[] sArr5 = this.pub_scalar;
                        sArr5[i11] = GF2Field.addElem(sArr5[i11], GF2Field.multElem(multElem, this.b2[i]));
                        i++;
                        i2 = i9;
                        i3 = i8;
                        coeffAlpha = coeffAlpha;
                        i4 = i12;
                        coeffEta = coeffEta;
                    }
                    i7++;
                    i = 0;
                }
                int i13 = i3;
                int i14 = i2;
                int i15 = i4;
                short[][][] sArr6 = coeffAlpha;
                short[] sArr7 = coeffEta;
                for (int i16 = 0; i16 < length2; i16++) {
                    for (int i17 = 0; i17 < length2; i17++) {
                        short[] multVect4 = computeInField.multVect(coeffBeta[i6][i16][i17], this.A2[i16]);
                        int i18 = i5 + i6;
                        sArr[i18] = computeInField.addSquareMatrix(sArr[i18], computeInField.multVects(multVect4, this.A2[i17]));
                        short[] multVect5 = computeInField.multVect(this.b2[i17], multVect4);
                        short[][] sArr8 = this.pub_singular;
                        sArr8[i18] = computeInField.addVect(multVect5, sArr8[i18]);
                        short[] multVect6 = computeInField.multVect(this.b2[i16], computeInField.multVect(coeffBeta[i6][i16][i17], this.A2[i17]));
                        short[][] sArr9 = this.pub_singular;
                        sArr9[i18] = computeInField.addVect(multVect6, sArr9[i18]);
                        short multElem2 = GF2Field.multElem(coeffBeta[i6][i16][i17], this.b2[i16]);
                        short[] sArr10 = this.pub_scalar;
                        sArr10[i18] = GF2Field.addElem(sArr10[i18], GF2Field.multElem(multElem2, this.b2[i17]));
                    }
                }
                for (int i19 = 0; i19 < length2 + length; i19++) {
                    short[] multVect7 = computeInField.multVect(coeffGamma[i6][i19], this.A2[i19]);
                    short[][] sArr11 = this.pub_singular;
                    int i20 = i5 + i6;
                    sArr11[i20] = computeInField.addVect(multVect7, sArr11[i20]);
                    short[] sArr12 = this.pub_scalar;
                    sArr12[i20] = GF2Field.addElem(sArr12[i20], GF2Field.multElem(coeffGamma[i6][i19], this.b2[i19]));
                }
                short[] sArr13 = this.pub_scalar;
                int i21 = i5 + i6;
                sArr13[i21] = GF2Field.addElem(sArr13[i21], sArr7[i6]);
                i6++;
                i2 = i14;
                i3 = i13;
                coeffAlpha = sArr6;
                i4 = i15;
                coeffEta = sArr7;
                i = 0;
            }
            i5 += length;
            i4++;
            i = 0;
        }
        short[][][] sArr14 = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, i2, i3, i3);
        short[][] sArr15 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i2, i3);
        short[] sArr16 = new short[i2];
        for (int i22 = 0; i22 < i2; i22++) {
            int i23 = 0;
            while (true) {
                short[][] sArr17 = this.A1;
                if (i23 < sArr17.length) {
                    sArr14[i22] = computeInField.addSquareMatrix(sArr14[i22], computeInField.multMatrix(sArr17[i22][i23], sArr[i23]));
                    sArr15[i22] = computeInField.addVect(sArr15[i22], computeInField.multVect(this.A1[i22][i23], this.pub_singular[i23]));
                    sArr16[i22] = GF2Field.addElem(sArr16[i22], GF2Field.multElem(this.A1[i22][i23], this.pub_scalar[i23]));
                    i23++;
                }
            }
            sArr16[i22] = GF2Field.addElem(sArr16[i22], this.b1[i22]);
        }
        this.pub_singular = sArr15;
        this.pub_scalar = sArr16;
        compactPublicKey(sArr14);
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        int i = 0;
        while (i < this.numOfLayers) {
            Layer[] layerArr = this.layers;
            int[] iArr = this.vi;
            int i2 = i + 1;
            layerArr[i] = new Layer(iArr[i], iArr[i2], this.sr);
            i = i2;
        }
    }

    private void generateL1() {
        int[] iArr = this.vi;
        int i = iArr[iArr.length - 1] - iArr[0];
        this.A1 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i, i);
        this.A1inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A1inv == null) {
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    this.A1[i2][i3] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A1inv = computeInField.inverse(this.A1);
        }
        this.b1 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.b1[i4] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void generateL2() {
        int i;
        int i2 = this.vi[r0.length - 1];
        this.A2 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i2, i2);
        this.A2inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (true) {
            if (this.A2inv != null) {
                break;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    this.A2[i3][i4] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A2inv = computeInField.inverse(this.A2);
        }
        this.b2 = new short[i2];
        for (i = 0; i < i2; i++) {
            this.b2[i] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void initializeDefault() {
        initialize(new RainbowKeyGenerationParameters(CryptoServicesRegistrar.getSecureRandom(), new RainbowParameters()));
    }

    private void keygen() {
        generateL1();
        generateL2();
        generateF();
        computePublicKey();
    }

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        keygen();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = new RainbowPrivateKeyParameters(this.A1inv, this.b1, this.A2inv, this.b2, this.vi, this.layers);
        int[] iArr = this.vi;
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RainbowPublicKeyParameters(iArr[iArr.length - 1] - iArr[0], this.pub_quadratic, this.pub_singular, this.pub_scalar), (AsymmetricKeyParameter) rainbowPrivateKeyParameters);
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        RainbowKeyGenerationParameters rainbowKeyGenerationParameters = (RainbowKeyGenerationParameters) keyGenerationParameters;
        this.rainbowParams = rainbowKeyGenerationParameters;
        this.sr = rainbowKeyGenerationParameters.getRandom();
        this.vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }
}

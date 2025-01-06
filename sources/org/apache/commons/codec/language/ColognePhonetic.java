package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes3.dex */
public class ColognePhonetic implements StringEncoder {
    private static final char[] AEIJOUY = {'A', 'E', 'I', 'J', 'O', Matrix.MATRIX_TYPE_RANDOM_UT, 'Y'};
    private static final char[] SCZ = {'S', 'C', Matrix.MATRIX_TYPE_ZERO};
    private static final char[] WFPV = {'W', 'F', 'P', 'V'};
    private static final char[] GKQ = {'G', 'K', 'Q'};
    private static final char[] CKQ = {'C', 'K', 'Q'};
    private static final char[] AHKLOQRUX = {'A', 'H', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'O', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, Matrix.MATRIX_TYPE_RANDOM_UT, 'X'};
    private static final char[] SZ = {'S', Matrix.MATRIX_TYPE_ZERO};
    private static final char[] AHOUKQX = {'A', 'H', 'O', Matrix.MATRIX_TYPE_RANDOM_UT, 'K', 'Q', 'X'};
    private static final char[] TDX = {'T', 'D', 'X'};
    private static final char[][] PREPROCESS_MAP = {new char[]{196, 'A'}, new char[]{220, Matrix.MATRIX_TYPE_RANDOM_UT}, new char[]{214, 'O'}, new char[]{223, 'S'}};

    private abstract class CologneBuffer {
        protected final char[] data;
        protected int length;

        protected abstract char[] copyData(int i, int i2);

        public int length() {
            return this.length;
        }

        public CologneBuffer(char[] cArr) {
            this.length = 0;
            this.data = cArr;
            this.length = cArr.length;
        }

        public CologneBuffer(int i) {
            this.length = 0;
            this.data = new char[i];
            this.length = 0;
        }

        public String toString() {
            return new String(copyData(0, this.length));
        }
    }

    private class CologneOutputBuffer extends CologneBuffer {
        public CologneOutputBuffer(int i) {
            super(i);
        }

        public void addRight(char c) {
            this.data[this.length] = c;
            this.length++;
        }

        @Override // org.apache.commons.codec.language.ColognePhonetic.CologneBuffer
        protected char[] copyData(int i, int i2) {
            char[] cArr = new char[i2];
            System.arraycopy(this.data, i, cArr, 0, i2);
            return cArr;
        }
    }

    private class CologneInputBuffer extends CologneBuffer {
        public CologneInputBuffer(char[] cArr) {
            super(cArr);
        }

        public void addLeft(char c) {
            this.length++;
            this.data[getNextPos()] = c;
        }

        @Override // org.apache.commons.codec.language.ColognePhonetic.CologneBuffer
        protected char[] copyData(int i, int i2) {
            char[] cArr = new char[i2];
            System.arraycopy(this.data, (this.data.length - this.length) + i, cArr, 0, i2);
            return cArr;
        }

        public char getNextChar() {
            return this.data[getNextPos()];
        }

        protected int getNextPos() {
            return this.data.length - this.length;
        }

        public char removeNext() {
            this.length--;
            return getNextChar();
        }
    }

    private static boolean arrayContains(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public String colognePhonetic(String str) {
        char c;
        if (str == null) {
            return null;
        }
        String preprocess = preprocess(str);
        CologneOutputBuffer cologneOutputBuffer = new CologneOutputBuffer(preprocess.length() * 2);
        CologneInputBuffer cologneInputBuffer = new CologneInputBuffer(preprocess.toCharArray());
        int length = cologneInputBuffer.length();
        char c2 = '/';
        char c3 = '-';
        while (length > 0) {
            char removeNext = cologneInputBuffer.removeNext();
            int length2 = cologneInputBuffer.length();
            char nextChar = length2 > 0 ? cologneInputBuffer.getNextChar() : '-';
            if (arrayContains(AEIJOUY, removeNext)) {
                c = '0';
            } else if (removeNext == 'H' || removeNext < 'A' || removeNext > 'Z') {
                if (c2 == '/') {
                    length = length2;
                } else {
                    c = '-';
                }
            } else if (removeNext == 'B' || (removeNext == 'P' && nextChar != 'H')) {
                c = '1';
            } else if ((removeNext == 'D' || removeNext == 'T') && !arrayContains(SCZ, nextChar)) {
                c = '2';
            } else if (arrayContains(WFPV, removeNext)) {
                c = '3';
            } else {
                if (!arrayContains(GKQ, removeNext)) {
                    if (removeNext != 'X' || arrayContains(CKQ, c3)) {
                        if (removeNext != 'S' && removeNext != 'Z') {
                            if (removeNext == 'C') {
                                if (c2 != '/') {
                                }
                            } else if (!arrayContains(TDX, removeNext)) {
                                c = removeNext == 'R' ? '7' : removeNext == 'L' ? '5' : (removeNext == 'M' || removeNext == 'N') ? '6' : removeNext;
                            }
                        }
                        c = '8';
                    } else {
                        cologneInputBuffer.addLeft('S');
                        length2++;
                    }
                }
                c = '4';
            }
            if (c != '-' && ((c2 != c && (c != '0' || c2 == '/')) || c < '0' || c > '8')) {
                cologneOutputBuffer.addRight(c);
            }
            c2 = c;
            c3 = removeNext;
            length = length2;
        }
        return cologneOutputBuffer.toString();
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("This method's parameter was expected to be of the type " + String.class.getName() + ". But actually it was of the type " + obj.getClass().getName() + ".");
        }
        return encode((String) obj);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return colognePhonetic(str);
    }

    public boolean isEncodeEqual(String str, String str2) {
        return colognePhonetic(str).equals(colognePhonetic(str2));
    }

    private String preprocess(String str) {
        char[] charArray = str.toUpperCase(Locale.GERMAN).toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] > 'Z') {
                char[][] cArr = PREPROCESS_MAP;
                int length = cArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        char[] cArr2 = cArr[i2];
                        if (charArray[i] == cArr2[0]) {
                            charArray[i] = cArr2[1];
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
        return new String(charArray);
    }
}

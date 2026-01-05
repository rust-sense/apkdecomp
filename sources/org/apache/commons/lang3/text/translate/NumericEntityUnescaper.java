package org.apache.commons.lang3.text.translate;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

@Deprecated
/* loaded from: classes3.dex */
public class NumericEntityUnescaper extends CharSequenceTranslator {
    private final EnumSet<OPTION> options;

    public enum OPTION {
        semiColonRequired,
        semiColonOptional,
        errorIfNoSemiColon
    }

    public NumericEntityUnescaper(OPTION... optionArr) {
        if (optionArr.length > 0) {
            this.options = EnumSet.copyOf((Collection) Arrays.asList(optionArr));
        } else {
            this.options = EnumSet.copyOf((Collection) Arrays.asList(OPTION.semiColonRequired));
        }
    }

    public boolean isSet(OPTION option) {
        EnumSet<OPTION> enumSet = this.options;
        return enumSet != null && enumSet.contains(option);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0071, code lost:
    
        if (r8.charAt(r2) != ';') goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0073, code lost:
    
        r0 = 1;
     */
    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int translate(java.lang.CharSequence r8, int r9, java.io.Writer r10) throws java.io.IOException {
        /*
            r7 = this;
            int r0 = r8.length()
            char r1 = r8.charAt(r9)
            r2 = 38
            r3 = 0
            if (r1 != r2) goto Lce
            int r1 = r0 + (-2)
            if (r9 >= r1) goto Lce
            int r1 = r9 + 1
            char r1 = r8.charAt(r1)
            r2 = 35
            if (r1 != r2) goto Lce
            int r1 = r9 + 2
            char r2 = r8.charAt(r1)
            r4 = 120(0x78, float:1.68E-43)
            r5 = 1
            if (r2 == r4) goto L2d
            r4 = 88
            if (r2 != r4) goto L2b
            goto L2d
        L2b:
            r9 = r3
            goto L33
        L2d:
            int r1 = r9 + 3
            if (r1 != r0) goto L32
            return r3
        L32:
            r9 = r5
        L33:
            r2 = r1
        L34:
            if (r2 >= r0) goto L69
            char r4 = r8.charAt(r2)
            r6 = 48
            if (r4 < r6) goto L46
            char r4 = r8.charAt(r2)
            r6 = 57
            if (r4 <= r6) goto L66
        L46:
            char r4 = r8.charAt(r2)
            r6 = 97
            if (r4 < r6) goto L56
            char r4 = r8.charAt(r2)
            r6 = 102(0x66, float:1.43E-43)
            if (r4 <= r6) goto L66
        L56:
            char r4 = r8.charAt(r2)
            r6 = 65
            if (r4 < r6) goto L69
            char r4 = r8.charAt(r2)
            r6 = 70
            if (r4 > r6) goto L69
        L66:
            int r2 = r2 + 1
            goto L34
        L69:
            if (r2 == r0) goto L75
            char r0 = r8.charAt(r2)
            r4 = 59
            if (r0 != r4) goto L75
            r0 = r5
            goto L76
        L75:
            r0 = r3
        L76:
            if (r0 != 0) goto L92
            org.apache.commons.lang3.text.translate.NumericEntityUnescaper$OPTION r4 = org.apache.commons.lang3.text.translate.NumericEntityUnescaper.OPTION.semiColonRequired
            boolean r4 = r7.isSet(r4)
            if (r4 == 0) goto L81
            return r3
        L81:
            org.apache.commons.lang3.text.translate.NumericEntityUnescaper$OPTION r4 = org.apache.commons.lang3.text.translate.NumericEntityUnescaper.OPTION.errorIfNoSemiColon
            boolean r4 = r7.isSet(r4)
            if (r4 != 0) goto L8a
            goto L92
        L8a:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Semi-colon required at end of numeric entity"
            r8.<init>(r9)
            throw r8
        L92:
            if (r9 == 0) goto La3
            java.lang.CharSequence r8 = r8.subSequence(r1, r2)     // Catch: java.lang.NumberFormatException -> Lce
            java.lang.String r8 = r8.toString()     // Catch: java.lang.NumberFormatException -> Lce
            r4 = 16
            int r8 = java.lang.Integer.parseInt(r8, r4)     // Catch: java.lang.NumberFormatException -> Lce
            goto Lb1
        La3:
            java.lang.CharSequence r8 = r8.subSequence(r1, r2)     // Catch: java.lang.NumberFormatException -> Lce
            java.lang.String r8 = r8.toString()     // Catch: java.lang.NumberFormatException -> Lce
            r4 = 10
            int r8 = java.lang.Integer.parseInt(r8, r4)     // Catch: java.lang.NumberFormatException -> Lce
        Lb1:
            r4 = 65535(0xffff, float:9.1834E-41)
            if (r8 <= r4) goto Lc5
            char[] r8 = java.lang.Character.toChars(r8)
            char r3 = r8[r3]
            r10.write(r3)
            char r8 = r8[r5]
            r10.write(r8)
            goto Lc8
        Lc5:
            r10.write(r8)
        Lc8:
            int r2 = r2 + 2
            int r2 = r2 - r1
            int r2 = r2 + r9
            int r2 = r2 + r0
            return r2
        Lce:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.text.translate.NumericEntityUnescaper.translate(java.lang.CharSequence, int, java.io.Writer):int");
    }
}

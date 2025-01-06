package com.caverock.androidsvg;

/* loaded from: classes.dex */
class NumberParser {
    private int pos;
    private static final float[] positivePowersOf10 = {1.0f, 10.0f, 100.0f, 1000.0f, 10000.0f, 100000.0f, 1000000.0f, 1.0E7f, 1.0E8f, 1.0E9f, 1.0E10f, 9.9999998E10f, 1.0E12f, 9.9999998E12f, 1.0E14f, 9.9999999E14f, 1.00000003E16f, 9.9999998E16f, 9.9999998E17f, 1.0E19f, 1.0E20f, 1.0E21f, 1.0E22f, 1.0E23f, 1.0E24f, 1.0E25f, 1.0E26f, 1.0E27f, 1.0E28f, 1.0E29f, 1.0E30f, 1.0E31f, 1.0E32f, 1.0E33f, 1.0E34f, 1.0E35f, 1.0E36f, 1.0E37f, 1.0E38f};
    private static final float[] negativePowersOf10 = {1.0f, 0.1f, 0.01f, 0.001f, 1.0E-4f, 1.0E-5f, 1.0E-6f, 1.0E-7f, 1.0E-8f, 1.0E-9f, 1.0E-10f, 1.0E-11f, 1.0E-12f, 1.0E-13f, 1.0E-14f, 1.0E-15f, 1.0E-16f, 1.0E-17f, 1.0E-18f, 1.0E-19f, 1.0E-20f, 1.0E-21f, 1.0E-22f, 1.0E-23f, 1.0E-24f, 1.0E-25f, 1.0E-26f, 1.0E-27f, 1.0E-28f, 1.0E-29f, 1.0E-30f, 1.0E-31f, 1.0E-32f, 1.0E-33f, 1.0E-34f, 1.0E-35f, 1.0E-36f, 1.0E-37f, 1.0E-38f};

    int getEndPos() {
        return this.pos;
    }

    NumberParser() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x0101, code lost:
    
        if (r22.pos != r8) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0103, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0104, code lost:
    
        if (r6 == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0106, code lost:
    
        r13 = r13 - r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0108, code lost:
    
        r13 = r13 + r9;
     */
    /* JADX WARN: Removed duplicated region for block: B:118:0x008a A[EDGE_INSN: B:118:0x008a->B:47:0x008a BREAK  A[LOOP:0: B:10:0x0032->B:17:0x0080], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    float parseNumber(java.lang.String r23, int r24, int r25) {
        /*
            Method dump skipped, instructions count: 344
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.NumberParser.parseNumber(java.lang.String, int, int):float");
    }
}

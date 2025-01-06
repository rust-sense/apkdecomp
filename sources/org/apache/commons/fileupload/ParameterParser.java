package org.apache.commons.fileupload;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class ParameterParser {
    private char[] chars = null;
    private int pos = 0;
    private int len = 0;
    private int i1 = 0;
    private int i2 = 0;
    private boolean lowerCaseNames = false;

    private boolean hasChar() {
        return this.pos < this.len;
    }

    public boolean isLowerCaseNames() {
        return this.lowerCaseNames;
    }

    public void setLowerCaseNames(boolean z) {
        this.lowerCaseNames = z;
    }

    private String getToken(boolean z) {
        while (true) {
            int i = this.i1;
            if (i >= this.i2 || !Character.isWhitespace(this.chars[i])) {
                break;
            }
            this.i1++;
        }
        while (true) {
            int i2 = this.i2;
            if (i2 <= this.i1 || !Character.isWhitespace(this.chars[i2 - 1])) {
                break;
            }
            this.i2--;
        }
        if (z) {
            int i3 = this.i2;
            int i4 = this.i1;
            if (i3 - i4 >= 2) {
                char[] cArr = this.chars;
                if (cArr[i4] == '\"' && cArr[i3 - 1] == '\"') {
                    this.i1 = i4 + 1;
                    this.i2 = i3 - 1;
                }
            }
        }
        int i5 = this.i2;
        int i6 = this.i1;
        if (i5 > i6) {
            return new String(this.chars, i6, i5 - i6);
        }
        return null;
    }

    private boolean isOneOf(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private String parseToken(char[] cArr) {
        int i = this.pos;
        this.i1 = i;
        this.i2 = i;
        while (hasChar() && !isOneOf(this.chars[this.pos], cArr)) {
            this.i2++;
            this.pos++;
        }
        return getToken(false);
    }

    private String parseQuotedToken(char[] cArr) {
        int i = this.pos;
        this.i1 = i;
        this.i2 = i;
        boolean z = false;
        boolean z2 = false;
        while (hasChar()) {
            char c = this.chars[this.pos];
            if (!z && isOneOf(c, cArr)) {
                break;
            }
            if (!z2 && c == '\"') {
                z = !z;
            }
            z2 = !z2 && c == '\\';
            this.i2++;
            this.pos++;
        }
        return getToken(true);
    }

    public Map<String, String> parse(String str, char[] cArr) {
        if (cArr == null || cArr.length == 0) {
            return new HashMap();
        }
        char c = cArr[0];
        if (str != null) {
            int length = str.length();
            for (char c2 : cArr) {
                int indexOf = str.indexOf(c2);
                if (indexOf != -1 && indexOf < length) {
                    c = c2;
                    length = indexOf;
                }
            }
        }
        return parse(str, c);
    }

    public Map<String, String> parse(String str, char c) {
        if (str == null) {
            return new HashMap();
        }
        return parse(str.toCharArray(), c);
    }

    public Map<String, String> parse(char[] cArr, char c) {
        if (cArr == null) {
            return new HashMap();
        }
        return parse(cArr, 0, cArr.length, c);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Map<java.lang.String, java.lang.String> parse(char[] r6, int r7, int r8, char r9) {
        /*
            r5 = this;
            if (r6 != 0) goto L8
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            return r6
        L8:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r5.chars = r6
            r5.pos = r7
            r5.len = r8
        L13:
            boolean r7 = r5.hasChar()
            if (r7 == 0) goto L6e
            r7 = 2
            char[] r7 = new char[r7]
            r8 = 0
            r1 = 61
            r7[r8] = r1
            r2 = 1
            r7[r2] = r9
            java.lang.String r7 = r5.parseToken(r7)
            boolean r3 = r5.hasChar()
            if (r3 == 0) goto L47
            int r3 = r5.pos
            char r4 = r6[r3]
            if (r4 != r1) goto L47
            int r3 = r3 + 1
            r5.pos = r3
            char[] r1 = new char[r2]
            r1[r8] = r9
            java.lang.String r8 = r5.parseQuotedToken(r1)
            if (r8 == 0) goto L48
            java.lang.String r8 = org.apache.commons.fileupload.util.mime.MimeUtility.decodeText(r8)     // Catch: java.io.UnsupportedEncodingException -> L48
            goto L48
        L47:
            r8 = 0
        L48:
            boolean r1 = r5.hasChar()
            if (r1 == 0) goto L58
            int r1 = r5.pos
            char r2 = r6[r1]
            if (r2 != r9) goto L58
            int r1 = r1 + 1
            r5.pos = r1
        L58:
            if (r7 == 0) goto L13
            int r1 = r7.length()
            if (r1 <= 0) goto L13
            boolean r1 = r5.lowerCaseNames
            if (r1 == 0) goto L6a
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r7 = r7.toLowerCase(r1)
        L6a:
            r0.put(r7, r8)
            goto L13
        L6e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.fileupload.ParameterParser.parse(char[], int, int, char):java.util.Map");
    }
}

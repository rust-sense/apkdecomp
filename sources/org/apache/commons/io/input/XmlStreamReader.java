package org.apache.commons.io.input;

import androidx.appcompat.app.AppCompatDelegate;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.ByteOrderMark;

/* loaded from: classes3.dex */
public class XmlStreamReader extends Reader {
    private static final int BUFFER_SIZE = 4096;
    private static final String HTTP_EX_1 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], BOM must be NULL";
    private static final String HTTP_EX_2 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], encoding mismatch";
    private static final String HTTP_EX_3 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], Invalid MIME";
    private static final String RAW_EX_1 = "Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch";
    private static final String RAW_EX_2 = "Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] unknown BOM";
    private static final String US_ASCII = "US-ASCII";
    private static final String UTF_16 = "UTF-16";
    private static final String UTF_16BE = "UTF-16BE";
    private static final String UTF_16LE = "UTF-16LE";
    private static final String UTF_32 = "UTF-32";
    private static final String UTF_8 = "UTF-8";
    private final String defaultEncoding;
    private final String encoding;
    private final Reader reader;
    private static final ByteOrderMark[] BOMS = {ByteOrderMark.UTF_8, ByteOrderMark.UTF_16BE, ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_32BE, ByteOrderMark.UTF_32LE};
    private static final String UTF_32BE = "UTF-32BE";
    private static final String UTF_32LE = "UTF-32LE";
    private static final String EBCDIC = "CP1047";
    private static final ByteOrderMark[] XML_GUESS_BYTES = {new ByteOrderMark("UTF-8", 60, 63, 120, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY), new ByteOrderMark("UTF-16BE", 0, 60, 0, 63), new ByteOrderMark("UTF-16LE", 60, 0, 63, 0), new ByteOrderMark(UTF_32BE, 0, 0, 0, 60, 0, 0, 0, 63, 0, 0, 0, 120, 0, 0, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY), new ByteOrderMark(UTF_32LE, 60, 0, 0, 0, 63, 0, 0, 0, 120, 0, 0, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 0, 0, 0), new ByteOrderMark(EBCDIC, 76, 111, 167, 148)};
    private static final Pattern CHARSET_PATTERN = Pattern.compile("charset=[\"']?([.[^; \"']]*)[\"']?");
    public static final Pattern ENCODING_PATTERN = Pattern.compile("<\\?xml.*encoding[\\s]*=[\\s]*((?:\".[^\"]*\")|(?:'.[^']*'))", 8);

    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public XmlStreamReader(File file) throws IOException {
        this(new FileInputStream(file));
    }

    public XmlStreamReader(InputStream inputStream) throws IOException {
        this(inputStream, true);
    }

    public XmlStreamReader(InputStream inputStream, boolean z) throws IOException {
        this(inputStream, z, (String) null);
    }

    public XmlStreamReader(InputStream inputStream, boolean z, String str) throws IOException {
        this.defaultEncoding = str;
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream(inputStream, 4096), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        String doRawStream = doRawStream(bOMInputStream, bOMInputStream2, z);
        this.encoding = doRawStream;
        this.reader = new InputStreamReader(bOMInputStream2, doRawStream);
    }

    public XmlStreamReader(URL url) throws IOException {
        this(url.openConnection(), (String) null);
    }

    public XmlStreamReader(URLConnection uRLConnection, String str) throws IOException {
        this.defaultEncoding = str;
        String contentType = uRLConnection.getContentType();
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream(uRLConnection.getInputStream(), 4096), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        if ((uRLConnection instanceof HttpURLConnection) || contentType != null) {
            this.encoding = doHttpStream(bOMInputStream, bOMInputStream2, contentType, true);
        } else {
            this.encoding = doRawStream(bOMInputStream, bOMInputStream2, true);
        }
        this.reader = new InputStreamReader(bOMInputStream2, this.encoding);
    }

    public XmlStreamReader(InputStream inputStream, String str) throws IOException {
        this(inputStream, str, true);
    }

    public XmlStreamReader(InputStream inputStream, String str, boolean z, String str2) throws IOException {
        this.defaultEncoding = str2;
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream(inputStream, 4096), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        String doHttpStream = doHttpStream(bOMInputStream, bOMInputStream2, str, z);
        this.encoding = doHttpStream;
        this.reader = new InputStreamReader(bOMInputStream2, doHttpStream);
    }

    public XmlStreamReader(InputStream inputStream, String str, boolean z) throws IOException {
        this(inputStream, str, z, null);
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        return this.reader.read(cArr, i, i2);
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    private String doRawStream(BOMInputStream bOMInputStream, BOMInputStream bOMInputStream2, boolean z) throws IOException {
        String bOMCharsetName = bOMInputStream.getBOMCharsetName();
        String bOMCharsetName2 = bOMInputStream2.getBOMCharsetName();
        try {
            return calculateRawEncoding(bOMCharsetName, bOMCharsetName2, getXmlProlog(bOMInputStream2, bOMCharsetName2));
        } catch (XmlStreamReaderException e) {
            if (z) {
                return doLenientDetection(null, e);
            }
            throw e;
        }
    }

    private String doHttpStream(BOMInputStream bOMInputStream, BOMInputStream bOMInputStream2, String str, boolean z) throws IOException {
        String bOMCharsetName = bOMInputStream.getBOMCharsetName();
        String bOMCharsetName2 = bOMInputStream2.getBOMCharsetName();
        try {
            return calculateHttpEncoding(str, bOMCharsetName, bOMCharsetName2, getXmlProlog(bOMInputStream2, bOMCharsetName2), z);
        } catch (XmlStreamReaderException e) {
            if (z) {
                return doLenientDetection(str, e);
            }
            throw e;
        }
    }

    private String doLenientDetection(String str, XmlStreamReaderException xmlStreamReaderException) throws IOException {
        if (str != null && str.startsWith("text/html")) {
            try {
                return calculateHttpEncoding("text/xml" + str.substring(9), xmlStreamReaderException.getBomEncoding(), xmlStreamReaderException.getXmlGuessEncoding(), xmlStreamReaderException.getXmlEncoding(), true);
            } catch (XmlStreamReaderException e) {
                xmlStreamReaderException = e;
            }
        }
        String xmlEncoding = xmlStreamReaderException.getXmlEncoding();
        if (xmlEncoding == null) {
            xmlEncoding = xmlStreamReaderException.getContentTypeEncoding();
        }
        if (xmlEncoding != null) {
            return xmlEncoding;
        }
        String str2 = this.defaultEncoding;
        return str2 == null ? "UTF-8" : str2;
    }

    String calculateRawEncoding(String str, String str2, String str3) throws IOException {
        if (str == null) {
            if (str2 != null && str3 != null) {
                return (str3.equals("UTF-16") && (str2.equals("UTF-16BE") || str2.equals("UTF-16LE"))) ? str2 : str3;
            }
            String str4 = this.defaultEncoding;
            return str4 == null ? "UTF-8" : str4;
        }
        if (str.equals("UTF-8")) {
            if (str2 != null && !str2.equals("UTF-8")) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            if (str3 == null || str3.equals("UTF-8")) {
                return str;
            }
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
        }
        if (str.equals("UTF-16BE") || str.equals("UTF-16LE")) {
            if (str2 != null && !str2.equals(str)) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            if (str3 == null || str3.equals("UTF-16") || str3.equals(str)) {
                return str;
            }
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
        }
        if (str.equals(UTF_32BE) || str.equals(UTF_32LE)) {
            if (str2 != null && !str2.equals(str)) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            if (str3 == null || str3.equals(UTF_32) || str3.equals(str)) {
                return str;
            }
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
        }
        throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_2, str, str2, str3), str, str2, str3);
    }

    String calculateHttpEncoding(String str, String str2, String str3, String str4, boolean z) throws IOException {
        if (z && str4 != null) {
            return str4;
        }
        String contentTypeMime = getContentTypeMime(str);
        String contentTypeEncoding = getContentTypeEncoding(str);
        boolean isAppXml = isAppXml(contentTypeMime);
        boolean isTextXml = isTextXml(contentTypeMime);
        if (!isAppXml && !isTextXml) {
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_3, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
        }
        if (contentTypeEncoding == null) {
            if (isAppXml) {
                return calculateRawEncoding(str2, str3, str4);
            }
            String str5 = this.defaultEncoding;
            return str5 == null ? "US-ASCII" : str5;
        }
        if (contentTypeEncoding.equals("UTF-16BE") || contentTypeEncoding.equals("UTF-16LE")) {
            if (str2 == null) {
                return contentTypeEncoding;
            }
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_1, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
        }
        if (contentTypeEncoding.equals("UTF-16")) {
            if (str2 == null || !str2.startsWith("UTF-16")) {
                throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_2, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
            }
            return str2;
        }
        if (contentTypeEncoding.equals(UTF_32BE) || contentTypeEncoding.equals(UTF_32LE)) {
            if (str2 == null) {
                return contentTypeEncoding;
            }
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_1, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
        }
        if (!contentTypeEncoding.equals(UTF_32)) {
            return contentTypeEncoding;
        }
        if (str2 == null || !str2.startsWith(UTF_32)) {
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_2, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
        }
        return str2;
    }

    static String getContentTypeMime(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(";");
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        return str.trim();
    }

    static String getContentTypeEncoding(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(";")) <= -1) {
            return null;
        }
        Matcher matcher = CHARSET_PATTERN.matcher(str.substring(indexOf + 1));
        String group = matcher.find() ? matcher.group(1) : null;
        if (group != null) {
            return group.toUpperCase(Locale.US);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0037, code lost:
    
        throw new java.io.IOException("Unexpected end of XML stream");
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0050, code lost:
    
        throw new java.io.IOException("XML prolog or ROOT element not found on first " + r7 + " bytes");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002e, code lost:
    
        if (r3 != (-1)) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getXmlProlog(java.io.InputStream r9, java.lang.String r10) throws java.io.IOException {
        /*
            if (r10 == 0) goto L97
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r0]
            r9.mark(r0)
            r2 = 0
            int r3 = r9.read(r1, r2, r0)
            r4 = -1
            java.lang.String r5 = ""
            r8 = r0
            r7 = r2
            r6 = r4
        L14:
            if (r3 == r4) goto L2c
            if (r6 != r4) goto L2c
            if (r7 >= r0) goto L2c
            int r7 = r7 + r3
            int r8 = r8 - r3
            int r3 = r9.read(r1, r7, r8)
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1, r2, r7, r10)
            r6 = 62
            int r6 = r5.indexOf(r6)
            goto L14
        L2c:
            if (r6 != r4) goto L51
            if (r3 != r4) goto L38
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Unexpected end of XML stream"
            r9.<init>(r10)
            throw r9
        L38:
            java.io.IOException r9 = new java.io.IOException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "XML prolog or ROOT element not found on first "
            r10.<init>(r0)
            r10.append(r7)
            java.lang.String r0 = " bytes"
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L51:
            if (r7 <= 0) goto L97
            r9.reset()
            java.io.BufferedReader r9 = new java.io.BufferedReader
            java.io.StringReader r10 = new java.io.StringReader
            r0 = 1
            int r6 = r6 + r0
            java.lang.String r1 = r5.substring(r2, r6)
            r10.<init>(r1)
            r9.<init>(r10)
            java.lang.StringBuffer r10 = new java.lang.StringBuffer
            r10.<init>()
            java.lang.String r1 = r9.readLine()
        L6f:
            if (r1 == 0) goto L79
            r10.append(r1)
            java.lang.String r1 = r9.readLine()
            goto L6f
        L79:
            java.util.regex.Pattern r9 = org.apache.commons.io.input.XmlStreamReader.ENCODING_PATTERN
            java.util.regex.Matcher r9 = r9.matcher(r10)
            boolean r10 = r9.find()
            if (r10 == 0) goto L97
            java.lang.String r9 = r9.group(r0)
            java.lang.String r9 = r9.toUpperCase()
            int r10 = r9.length()
            int r10 = r10 - r0
            java.lang.String r9 = r9.substring(r0, r10)
            goto L98
        L97:
            r9 = 0
        L98:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.XmlStreamReader.getXmlProlog(java.io.InputStream, java.lang.String):java.lang.String");
    }

    static boolean isAppXml(String str) {
        return str != null && (str.equals("application/xml") || str.equals("application/xml-dtd") || str.equals("application/xml-external-parsed-entity") || (str.startsWith("application/") && str.endsWith("+xml")));
    }

    static boolean isTextXml(String str) {
        return str != null && (str.equals("text/xml") || str.equals("text/xml-external-parsed-entity") || (str.startsWith("text/") && str.endsWith("+xml")));
    }
}

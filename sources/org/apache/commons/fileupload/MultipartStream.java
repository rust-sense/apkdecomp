package org.apache.commons.fileupload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.util.Closeable;
import org.apache.commons.fileupload.util.Streams;

/* loaded from: classes3.dex */
public class MultipartStream {
    public static final byte CR = 13;
    protected static final int DEFAULT_BUFSIZE = 4096;
    public static final int HEADER_PART_SIZE_MAX = 10240;
    public static final byte LF = 10;
    private final byte[] boundary;
    private int boundaryLength;
    private final int[] boundaryTable;
    private final int bufSize;
    private final byte[] buffer;
    private int head;
    private String headerEncoding;
    private final InputStream input;
    private final int keepRegion;
    private final ProgressNotifier notifier;
    private int tail;
    protected static final byte[] HEADER_SEPARATOR = {13, 10, 13, 10};
    protected static final byte[] FIELD_SEPARATOR = {13, 10};
    public static final byte DASH = 45;
    protected static final byte[] STREAM_TERMINATOR = {DASH, DASH};
    protected static final byte[] BOUNDARY_PREFIX = {13, 10, DASH, DASH};

    public String getHeaderEncoding() {
        return this.headerEncoding;
    }

    public void setHeaderEncoding(String str) {
        this.headerEncoding = str;
    }

    static /* synthetic */ int access$108(MultipartStream multipartStream) {
        int i = multipartStream.head;
        multipartStream.head = i + 1;
        return i;
    }

    public static class ProgressNotifier {
        private long bytesRead;
        private final long contentLength;
        private int items;
        private final ProgressListener listener;

        ProgressNotifier(ProgressListener progressListener, long j) {
            this.listener = progressListener;
            this.contentLength = j;
        }

        void noteBytesRead(int i) {
            this.bytesRead += i;
            notifyListener();
        }

        void noteItem() {
            this.items++;
            notifyListener();
        }

        private void notifyListener() {
            ProgressListener progressListener = this.listener;
            if (progressListener != null) {
                progressListener.update(this.bytesRead, this.contentLength, this.items);
            }
        }
    }

    @Deprecated
    public MultipartStream() {
        this((InputStream) null, (byte[]) null, (ProgressNotifier) null);
    }

    @Deprecated
    public MultipartStream(InputStream inputStream, byte[] bArr, int i) {
        this(inputStream, bArr, i, null);
    }

    public MultipartStream(InputStream inputStream, byte[] bArr, int i, ProgressNotifier progressNotifier) {
        if (bArr == null) {
            throw new IllegalArgumentException("boundary may not be null");
        }
        int length = bArr.length;
        byte[] bArr2 = BOUNDARY_PREFIX;
        int length2 = length + bArr2.length;
        this.boundaryLength = length2;
        if (i < length2 + 1) {
            throw new IllegalArgumentException("The buffer size specified for the MultipartStream is too small");
        }
        this.input = inputStream;
        int max = Math.max(i, length2 * 2);
        this.bufSize = max;
        this.buffer = new byte[max];
        this.notifier = progressNotifier;
        int i2 = this.boundaryLength;
        byte[] bArr3 = new byte[i2];
        this.boundary = bArr3;
        this.boundaryTable = new int[i2 + 1];
        this.keepRegion = bArr3.length;
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, 0, bArr3, bArr2.length, bArr.length);
        computeBoundaryTable();
        this.head = 0;
        this.tail = 0;
    }

    MultipartStream(InputStream inputStream, byte[] bArr, ProgressNotifier progressNotifier) {
        this(inputStream, bArr, 4096, progressNotifier);
    }

    @Deprecated
    public MultipartStream(InputStream inputStream, byte[] bArr) {
        this(inputStream, bArr, 4096, null);
    }

    public byte readByte() throws IOException {
        if (this.head == this.tail) {
            this.head = 0;
            int read = this.input.read(this.buffer, 0, this.bufSize);
            this.tail = read;
            if (read == -1) {
                throw new IOException("No more data is available");
            }
            ProgressNotifier progressNotifier = this.notifier;
            if (progressNotifier != null) {
                progressNotifier.noteBytesRead(read);
            }
        }
        byte[] bArr = this.buffer;
        int i = this.head;
        this.head = i + 1;
        return bArr[i];
    }

    public boolean readBoundary() throws FileUploadBase.FileUploadIOException, MalformedStreamException {
        byte[] bArr = new byte[2];
        this.head += this.boundaryLength;
        try {
            byte readByte = readByte();
            bArr[0] = readByte;
            if (readByte == 10) {
                return true;
            }
            bArr[1] = readByte();
            if (arrayequals(bArr, STREAM_TERMINATOR, 2)) {
                return false;
            }
            if (arrayequals(bArr, FIELD_SEPARATOR, 2)) {
                return true;
            }
            throw new MalformedStreamException("Unexpected characters follow a boundary");
        } catch (FileUploadBase.FileUploadIOException e) {
            throw e;
        } catch (IOException unused) {
            throw new MalformedStreamException("Stream ended unexpectedly");
        }
    }

    public void setBoundary(byte[] bArr) throws IllegalBoundaryException {
        int length = bArr.length;
        int i = this.boundaryLength;
        byte[] bArr2 = BOUNDARY_PREFIX;
        if (length != i - bArr2.length) {
            throw new IllegalBoundaryException("The length of a boundary token cannot be changed");
        }
        System.arraycopy(bArr, 0, this.boundary, bArr2.length, bArr.length);
        computeBoundaryTable();
    }

    private void computeBoundaryTable() {
        int[] iArr = this.boundaryTable;
        iArr[0] = -1;
        iArr[1] = 0;
        int i = 2;
        int i2 = 0;
        while (i <= this.boundaryLength) {
            byte[] bArr = this.boundary;
            if (bArr[i - 1] == bArr[i2]) {
                i2++;
                this.boundaryTable[i] = i2;
            } else if (i2 > 0) {
                i2 = this.boundaryTable[i2];
            } else {
                this.boundaryTable[i] = 0;
            }
            i++;
        }
    }

    public String readHeaders() throws FileUploadBase.FileUploadIOException, MalformedStreamException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = HEADER_SEPARATOR;
            if (i >= bArr.length) {
                String str = this.headerEncoding;
                if (str != null) {
                    try {
                        return byteArrayOutputStream.toString(str);
                    } catch (UnsupportedEncodingException unused) {
                        return byteArrayOutputStream.toString();
                    }
                }
                return byteArrayOutputStream.toString();
            }
            try {
                byte readByte = readByte();
                i2++;
                if (i2 > 10240) {
                    throw new MalformedStreamException(String.format("Header section has more than %s bytes (maybe it is not properly terminated)", 10240));
                }
                i = readByte == bArr[i] ? i + 1 : 0;
                byteArrayOutputStream.write(readByte);
            } catch (FileUploadBase.FileUploadIOException e) {
                throw e;
            } catch (IOException unused2) {
                throw new MalformedStreamException("Stream ended unexpectedly");
            }
        }
    }

    public int readBodyData(OutputStream outputStream) throws MalformedStreamException, IOException {
        return (int) Streams.copy(newInputStream(), outputStream, false);
    }

    ItemInputStream newInputStream() {
        return new ItemInputStream();
    }

    public int discardBodyData() throws MalformedStreamException, IOException {
        return readBodyData(null);
    }

    public boolean skipPreamble() throws IOException {
        byte[] bArr = this.boundary;
        System.arraycopy(bArr, 2, bArr, 0, bArr.length - 2);
        this.boundaryLength = this.boundary.length - 2;
        computeBoundaryTable();
        try {
            discardBodyData();
            return readBoundary();
        } catch (MalformedStreamException unused) {
            return false;
        } finally {
            byte[] bArr2 = this.boundary;
            System.arraycopy(bArr2, 0, bArr2, 2, bArr2.length - 2);
            byte[] bArr3 = this.boundary;
            this.boundaryLength = bArr3.length;
            bArr3[0] = 13;
            bArr3[1] = 10;
            computeBoundaryTable();
        }
    }

    public static boolean arrayequals(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    protected int findByte(byte b, int i) {
        while (i < this.tail) {
            if (this.buffer[i] == b) {
                return i;
            }
            i++;
        }
        return -1;
    }

    protected int findSeparator() {
        int i = this.head;
        int i2 = 0;
        while (i < this.tail) {
            while (i2 >= 0 && this.buffer[i] != this.boundary[i2]) {
                i2 = this.boundaryTable[i2];
            }
            i++;
            i2++;
            int i3 = this.boundaryLength;
            if (i2 == i3) {
                return i - i3;
            }
        }
        return -1;
    }

    public static class MalformedStreamException extends IOException {
        private static final long serialVersionUID = 6466926458059796677L;

        public MalformedStreamException() {
        }

        public MalformedStreamException(String str) {
            super(str);
        }
    }

    public static class IllegalBoundaryException extends IOException {
        private static final long serialVersionUID = -161533165102632918L;

        public IllegalBoundaryException() {
        }

        public IllegalBoundaryException(String str) {
            super(str);
        }
    }

    public class ItemInputStream extends InputStream implements Closeable {
        private static final int BYTE_POSITIVE_OFFSET = 256;
        private boolean closed;
        private int pad;
        private int pos;
        private long total;

        public long getBytesRead() {
            return this.total;
        }

        @Override // org.apache.commons.fileupload.util.Closeable
        public boolean isClosed() {
            return this.closed;
        }

        ItemInputStream() {
            findSeparator();
        }

        private void findSeparator() {
            int findSeparator = MultipartStream.this.findSeparator();
            this.pos = findSeparator;
            if (findSeparator == -1) {
                if (MultipartStream.this.tail - MultipartStream.this.head > MultipartStream.this.keepRegion) {
                    this.pad = MultipartStream.this.keepRegion;
                } else {
                    this.pad = MultipartStream.this.tail - MultipartStream.this.head;
                }
            }
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            int i;
            int i2 = this.pos;
            if (i2 == -1) {
                i2 = MultipartStream.this.tail - MultipartStream.this.head;
                i = this.pad;
            } else {
                i = MultipartStream.this.head;
            }
            return i2 - i;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.closed) {
                throw new FileItemStream.ItemSkippedException();
            }
            if (available() == 0 && makeAvailable() == 0) {
                return -1;
            }
            this.total++;
            byte b = MultipartStream.this.buffer[MultipartStream.access$108(MultipartStream.this)];
            return b >= 0 ? b : b + 256;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (this.closed) {
                throw new FileItemStream.ItemSkippedException();
            }
            if (i2 == 0) {
                return 0;
            }
            int available = available();
            if (available == 0 && (available = makeAvailable()) == 0) {
                return -1;
            }
            int min = Math.min(available, i2);
            System.arraycopy(MultipartStream.this.buffer, MultipartStream.this.head, bArr, i, min);
            MultipartStream.this.head += min;
            this.total += min;
            return min;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable, org.apache.commons.fileupload.util.Closeable
        public void close() throws IOException {
            close(false);
        }

        public void close(boolean z) throws IOException {
            if (this.closed) {
                return;
            }
            if (!z) {
                while (true) {
                    int available = available();
                    if (available == 0 && (available = makeAvailable()) == 0) {
                        break;
                    } else {
                        skip(available);
                    }
                }
            } else {
                this.closed = true;
                MultipartStream.this.input.close();
            }
            this.closed = true;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            if (this.closed) {
                throw new FileItemStream.ItemSkippedException();
            }
            int available = available();
            if (available == 0 && (available = makeAvailable()) == 0) {
                return 0L;
            }
            long min = Math.min(available, j);
            MultipartStream.this.head = (int) (r0.head + min);
            return min;
        }

        private int makeAvailable() throws IOException {
            int available;
            if (this.pos != -1) {
                return 0;
            }
            this.total += (MultipartStream.this.tail - MultipartStream.this.head) - this.pad;
            System.arraycopy(MultipartStream.this.buffer, MultipartStream.this.tail - this.pad, MultipartStream.this.buffer, 0, this.pad);
            MultipartStream.this.head = 0;
            MultipartStream.this.tail = this.pad;
            do {
                int read = MultipartStream.this.input.read(MultipartStream.this.buffer, MultipartStream.this.tail, MultipartStream.this.bufSize - MultipartStream.this.tail);
                if (read != -1) {
                    if (MultipartStream.this.notifier != null) {
                        MultipartStream.this.notifier.noteBytesRead(read);
                    }
                    MultipartStream.this.tail += read;
                    findSeparator();
                    available = available();
                    if (available > 0) {
                        break;
                    }
                } else {
                    throw new MalformedStreamException("Stream ended unexpectedly");
                }
            } while (this.pos == -1);
            return available;
        }
    }
}

package com.facebook.imagepipeline.image;

import android.graphics.ColorSpace;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.SharedReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.HeifExifUtil;
import com.facebook.imageutils.ImageMetaData;
import com.facebook.imageutils.JfifUtil;
import com.facebook.imageutils.WebpUtil;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
import kotlin.Pair;

/* loaded from: classes.dex */
public class EncodedImage implements Closeable {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    public static final int UNKNOWN_HEIGHT = -1;
    public static final int UNKNOWN_ROTATION_ANGLE = -1;
    public static final int UNKNOWN_STREAM_SIZE = -1;
    public static final int UNKNOWN_WIDTH = -1;
    private static boolean sUseCachedMetadata;

    @Nullable
    private BytesRange mBytesRange;

    @Nullable
    private ColorSpace mColorSpace;
    private int mExifOrientation;
    private boolean mHasParsedMetadata;
    private int mHeight;
    private ImageFormat mImageFormat;

    @Nullable
    private final Supplier<FileInputStream> mInputStreamSupplier;

    @Nullable
    private final CloseableReference<PooledByteBuffer> mPooledByteBufferRef;
    private int mRotationAngle;
    private int mSampleSize;

    @Nullable
    private String mSource;
    private int mStreamSize;
    private int mWidth;

    public static void setUseCachedMetadata(boolean z) {
        sUseCachedMetadata = z;
    }

    @Nullable
    public BytesRange getBytesRange() {
        return this.mBytesRange;
    }

    public int getSampleSize() {
        return this.mSampleSize;
    }

    @Nullable
    public String getSource() {
        return this.mSource;
    }

    protected boolean hasParsedMetaData() {
        return this.mHasParsedMetadata;
    }

    public void setBytesRange(@Nullable BytesRange bytesRange) {
        this.mBytesRange = bytesRange;
    }

    public void setExifOrientation(int i) {
        this.mExifOrientation = i;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public void setImageFormat(ImageFormat imageFormat) {
        this.mImageFormat = imageFormat;
    }

    public void setRotationAngle(int i) {
        this.mRotationAngle = i;
    }

    public void setSampleSize(int i) {
        this.mSampleSize = i;
    }

    public void setSource(@Nullable String str) {
        this.mSource = str;
    }

    public void setStreamSize(int i) {
        this.mStreamSize = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }

    public EncodedImage(CloseableReference<PooledByteBuffer> closeableReference) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mExifOrientation = 0;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        Preconditions.checkArgument(Boolean.valueOf(CloseableReference.isValid(closeableReference)));
        this.mPooledByteBufferRef = closeableReference.mo194clone();
        this.mInputStreamSupplier = null;
    }

    public EncodedImage(Supplier<FileInputStream> supplier) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mExifOrientation = 0;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        Preconditions.checkNotNull(supplier);
        this.mPooledByteBufferRef = null;
        this.mInputStreamSupplier = supplier;
    }

    public EncodedImage(Supplier<FileInputStream> supplier, int i) {
        this(supplier);
        this.mStreamSize = i;
    }

    @Nullable
    public static EncodedImage cloneOrNull(@Nullable EncodedImage encodedImage) {
        if (encodedImage != null) {
            return encodedImage.cloneOrNull();
        }
        return null;
    }

    @Nullable
    public EncodedImage cloneOrNull() {
        EncodedImage encodedImage;
        Supplier<FileInputStream> supplier = this.mInputStreamSupplier;
        if (supplier != null) {
            encodedImage = new EncodedImage(supplier, this.mStreamSize);
        } else {
            CloseableReference cloneOrNull = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
            if (cloneOrNull == null) {
                encodedImage = null;
            } else {
                try {
                    encodedImage = new EncodedImage((CloseableReference<PooledByteBuffer>) cloneOrNull);
                } finally {
                    CloseableReference.closeSafely((CloseableReference<?>) cloneOrNull);
                }
            }
        }
        if (encodedImage != null) {
            encodedImage.copyMetaDataFrom(this);
        }
        return encodedImage;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CloseableReference.closeSafely(this.mPooledByteBufferRef);
    }

    public synchronized boolean isValid() {
        boolean z;
        if (!CloseableReference.isValid(this.mPooledByteBufferRef)) {
            z = this.mInputStreamSupplier != null;
        }
        return z;
    }

    public CloseableReference<PooledByteBuffer> getByteBufferRef() {
        return CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
    }

    @Nullable
    public InputStream getInputStream() {
        Supplier<FileInputStream> supplier = this.mInputStreamSupplier;
        if (supplier != null) {
            return supplier.get();
        }
        CloseableReference cloneOrNull = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
        if (cloneOrNull == null) {
            return null;
        }
        try {
            return new PooledByteBufferInputStream((PooledByteBuffer) cloneOrNull.get());
        } finally {
            CloseableReference.closeSafely((CloseableReference<?>) cloneOrNull);
        }
    }

    public InputStream getInputStreamOrThrow() {
        return (InputStream) Preconditions.checkNotNull(getInputStream());
    }

    public ImageFormat getImageFormat() {
        parseMetadataIfNeeded();
        return this.mImageFormat;
    }

    public int getRotationAngle() {
        parseMetadataIfNeeded();
        return this.mRotationAngle;
    }

    public int getExifOrientation() {
        parseMetadataIfNeeded();
        return this.mExifOrientation;
    }

    public int getWidth() {
        parseMetadataIfNeeded();
        return this.mWidth;
    }

    public int getHeight() {
        parseMetadataIfNeeded();
        return this.mHeight;
    }

    @Nullable
    public ColorSpace getColorSpace() {
        parseMetadataIfNeeded();
        return this.mColorSpace;
    }

    public boolean isCompleteAt(int i) {
        if ((this.mImageFormat != DefaultImageFormats.JPEG && this.mImageFormat != DefaultImageFormats.DNG) || this.mInputStreamSupplier != null) {
            return true;
        }
        Preconditions.checkNotNull(this.mPooledByteBufferRef);
        PooledByteBuffer pooledByteBuffer = this.mPooledByteBufferRef.get();
        return pooledByteBuffer.read(i + (-2)) == -1 && pooledByteBuffer.read(i - 1) == -39;
    }

    public int getSize() {
        CloseableReference<PooledByteBuffer> closeableReference = this.mPooledByteBufferRef;
        return (closeableReference == null || closeableReference.get() == null) ? this.mStreamSize : this.mPooledByteBufferRef.get().size();
    }

    public String getFirstBytesAsHexString(int i) {
        CloseableReference<PooledByteBuffer> byteBufferRef = getByteBufferRef();
        if (byteBufferRef == null) {
            return "";
        }
        int min = Math.min(getSize(), i);
        byte[] bArr = new byte[min];
        try {
            PooledByteBuffer pooledByteBuffer = byteBufferRef.get();
            if (pooledByteBuffer == null) {
                return "";
            }
            pooledByteBuffer.read(0, bArr, 0, min);
            byteBufferRef.close();
            StringBuilder sb = new StringBuilder(min * 2);
            for (int i2 = 0; i2 < min; i2++) {
                sb.append(String.format("%02X", Byte.valueOf(bArr[i2])));
            }
            return sb.toString();
        } finally {
            byteBufferRef.close();
        }
    }

    private void parseMetadataIfNeeded() {
        if (this.mWidth < 0 || this.mHeight < 0) {
            parseMetaData();
        }
    }

    public void parseMetaData() {
        if (!sUseCachedMetadata) {
            internalParseMetaData();
        } else {
            if (this.mHasParsedMetadata) {
                return;
            }
            internalParseMetaData();
            this.mHasParsedMetadata = true;
        }
    }

    private void internalParseMetaData() {
        Pair<Integer, Integer> dimensions;
        ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(getInputStream());
        this.mImageFormat = imageFormat_WrapIOException;
        if (DefaultImageFormats.isWebpFormat(imageFormat_WrapIOException)) {
            dimensions = readWebPImageSize();
        } else {
            dimensions = readImageMetaData().getDimensions();
        }
        if (imageFormat_WrapIOException == DefaultImageFormats.JPEG && this.mRotationAngle == -1) {
            if (dimensions != null) {
                int orientation = JfifUtil.getOrientation(getInputStream());
                this.mExifOrientation = orientation;
                this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(orientation);
                return;
            }
            return;
        }
        if (imageFormat_WrapIOException == DefaultImageFormats.HEIF && this.mRotationAngle == -1) {
            int orientation2 = HeifExifUtil.getOrientation(getInputStream());
            this.mExifOrientation = orientation2;
            this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(orientation2);
        } else if (this.mRotationAngle == -1) {
            this.mRotationAngle = 0;
        }
    }

    @Nullable
    private Pair<Integer, Integer> readWebPImageSize() {
        InputStream inputStream = getInputStream();
        if (inputStream == null) {
            return null;
        }
        Pair<Integer, Integer> size = WebpUtil.getSize(inputStream);
        if (size != null) {
            this.mWidth = size.component1().intValue();
            this.mHeight = size.component2().intValue();
        }
        return size;
    }

    private ImageMetaData readImageMetaData() {
        InputStream inputStream;
        try {
            inputStream = getInputStream();
            try {
                ImageMetaData decodeDimensionsAndColorSpace = BitmapUtil.decodeDimensionsAndColorSpace(inputStream);
                this.mColorSpace = decodeDimensionsAndColorSpace.getColorSpace();
                Pair<Integer, Integer> dimensions = decodeDimensionsAndColorSpace.getDimensions();
                if (dimensions != null) {
                    this.mWidth = dimensions.component1().intValue();
                    this.mHeight = dimensions.component2().intValue();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                return decodeDimensionsAndColorSpace;
            } catch (Throwable th) {
                th = th;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    public void copyMetaDataFrom(EncodedImage encodedImage) {
        this.mImageFormat = encodedImage.getImageFormat();
        this.mWidth = encodedImage.getWidth();
        this.mHeight = encodedImage.getHeight();
        this.mRotationAngle = encodedImage.getRotationAngle();
        this.mExifOrientation = encodedImage.getExifOrientation();
        this.mSampleSize = encodedImage.getSampleSize();
        this.mStreamSize = encodedImage.getSize();
        this.mBytesRange = encodedImage.getBytesRange();
        this.mColorSpace = encodedImage.getColorSpace();
        this.mHasParsedMetadata = encodedImage.hasParsedMetaData();
    }

    public static boolean isMetaDataAvailable(EncodedImage encodedImage) {
        return encodedImage.mRotationAngle >= 0 && encodedImage.mWidth >= 0 && encodedImage.mHeight >= 0;
    }

    public static void closeSafely(@Nullable EncodedImage encodedImage) {
        if (encodedImage != null) {
            encodedImage.close();
        }
    }

    public static boolean isValid(@Nullable EncodedImage encodedImage) {
        return encodedImage != null && encodedImage.isValid();
    }

    @Nullable
    public synchronized SharedReference<PooledByteBuffer> getUnderlyingReferenceTestOnly() {
        CloseableReference<PooledByteBuffer> closeableReference;
        closeableReference = this.mPooledByteBufferRef;
        return closeableReference != null ? closeableReference.getUnderlyingReferenceTestOnly() : null;
    }
}

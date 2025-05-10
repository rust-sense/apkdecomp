package expo.modules.image.blurhash;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.google.firebase.messaging.Constants;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* compiled from: BlurhashDecoder.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u0006\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJC\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0002¢\u0006\u0002\u0010\u0019J6\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u0017\u001a\u00020\u0018J$\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001c2\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u0005H\u0002J\u0018\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u001eH\u0002J\u0010\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0005H\u0002J \u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0002J \u0010*\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u001eH\u0002J\u0010\u0010,\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001eH\u0002J\u0010\u0010-\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u0005H\u0002J4\u0010.\u001a\u00020/*\u00020\u00062\u0006\u0010)\u001a\u00020\u00182\u0006\u00100\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u0005H\u0002R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lexpo/modules/image/blurhash/BlurhashDecoder;", "", "()V", "cacheCosinesX", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "cacheCosinesY", "charMap", "", "", "clearCache", "", "composeBitmap", "Landroid/graphics/Bitmap;", "width", "height", "numCompX", "numCompY", "colors", "", "", "useCache", "", "(IIII[[FZ)Landroid/graphics/Bitmap;", "decode", "blurHash", "", "punch", "", "decode83", "str", Constants.MessagePayloadKeys.FROM, "to", "decodeAc", "value", "maxAc", "decodeDc", "colorEnc", "getArrayForCosinesX", "calculate", "getArrayForCosinesY", "linearToSrgb", "signedPow2", "srgbToLinear", "getCos", "", ViewHierarchyNode.JsonKeys.X, "numComp", ViewHierarchyNode.JsonKeys.Y, "size", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BlurhashDecoder {
    public static final BlurhashDecoder INSTANCE = new BlurhashDecoder();
    private static final HashMap<Integer, double[]> cacheCosinesX = new HashMap<>();
    private static final HashMap<Integer, double[]> cacheCosinesY = new HashMap<>();
    private static final Map<Character, Integer> charMap;

    private BlurhashDecoder() {
    }

    static {
        int i = 0;
        List listOf = CollectionsKt.listOf((Object[]) new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT), 'M', 'N', 'O', 'P', 'Q', Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR), 'S', 'T', Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT), 'V', 'W', 'X', 'Y', Character.valueOf(Matrix.MATRIX_TYPE_ZERO), 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '#', '$', '%', '*', '+', ',', '-', '.', ':', ';', '=', '?', '@', '[', ']', '^', '_', '{', '|', '}', '~'});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOf, 10));
        for (Object obj : listOf) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(TuplesKt.to(Character.valueOf(((Character) obj).charValue()), Integer.valueOf(i)));
            i = i2;
        }
        charMap = MapsKt.toMap(arrayList);
    }

    public final void clearCache() {
        cacheCosinesX.clear();
        cacheCosinesY.clear();
    }

    public static /* synthetic */ Bitmap decode$default(BlurhashDecoder blurhashDecoder, String str, int i, int i2, float f, boolean z, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            f = 1.0f;
        }
        float f2 = f;
        if ((i3 & 16) != 0) {
            z = true;
        }
        return blurhashDecoder.decode(str, i, i2, f2, z);
    }

    public final Bitmap decode(String blurHash, int width, int height, float punch, boolean useCache) {
        float[] decodeAc;
        if (blurHash == null || blurHash.length() < 6) {
            return null;
        }
        int decode83 = decode83(blurHash, 0, 1);
        int i = (decode83 % 9) + 1;
        int i2 = (decode83 / 9) + 1;
        if (blurHash.length() != (i * 2 * i2) + 4) {
            return null;
        }
        float decode832 = (decode83(blurHash, 1, 2) + 1) / 166.0f;
        int i3 = i * i2;
        float[][] fArr = new float[i3][];
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 == 0) {
                BlurhashDecoder blurhashDecoder = INSTANCE;
                decodeAc = blurhashDecoder.decodeDc(blurhashDecoder.decode83(blurHash, 2, 6));
            } else {
                int i5 = i4 * 2;
                BlurhashDecoder blurhashDecoder2 = INSTANCE;
                decodeAc = blurhashDecoder2.decodeAc(blurhashDecoder2.decode83(blurHash, i5 + 4, i5 + 6), decode832 * punch);
            }
            fArr[i4] = decodeAc;
        }
        return composeBitmap(width, height, i, i2, fArr, useCache);
    }

    static /* synthetic */ int decode83$default(BlurhashDecoder blurhashDecoder, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        return blurhashDecoder.decode83(str, i, i2);
    }

    private final int decode83(String str, int from, int to) {
        int i = 0;
        while (from < to) {
            Integer num = charMap.get(Character.valueOf(str.charAt(from)));
            int intValue = num != null ? num.intValue() : -1;
            if (intValue != -1) {
                i = (i * 83) + intValue;
            }
            from++;
        }
        return i;
    }

    private final float[] decodeDc(int colorEnc) {
        return new float[]{srgbToLinear(colorEnc >> 16), srgbToLinear((colorEnc >> 8) & 255), srgbToLinear(colorEnc & 255)};
    }

    private final float srgbToLinear(int colorEnc) {
        float f = colorEnc / 255.0f;
        return f <= 0.04045f ? f / 12.92f : (float) Math.pow((f + 0.055f) / 1.055f, 2.4f);
    }

    private final float[] decodeAc(int value, float maxAc) {
        return new float[]{signedPow2(((value / 361) - 9) / 9.0f) * maxAc, signedPow2((((value / 19) % 19) - 9) / 9.0f) * maxAc, signedPow2(((value % 19) - 9) / 9.0f) * maxAc};
    }

    private final float signedPow2(float value) {
        return Math.copySign((float) Math.pow(value, 2.0f), value);
    }

    private final Bitmap composeBitmap(int width, int height, int numCompX, int numCompY, float[][] colors, boolean useCache) {
        int[] iArr = new int[width * height];
        boolean z = (useCache && cacheCosinesX.containsKey(Integer.valueOf(width * numCompX))) ? false : true;
        double[] arrayForCosinesX = getArrayForCosinesX(z, width, numCompX);
        boolean z2 = (useCache && cacheCosinesY.containsKey(Integer.valueOf(height * numCompY))) ? false : true;
        double[] arrayForCosinesY = getArrayForCosinesY(z2, height, numCompY);
        int i = 0;
        while (i < height) {
            int i2 = 0;
            while (i2 < width) {
                float f = 0.0f;
                float f2 = 0.0f;
                float f3 = 0.0f;
                int i3 = 0;
                while (i3 < numCompY) {
                    float f4 = f;
                    float f5 = f2;
                    float f6 = f3;
                    int i4 = 0;
                    while (i4 < numCompX) {
                        int i5 = i4;
                        int i6 = i3;
                        int i7 = i2;
                        boolean z3 = z2;
                        i = i;
                        float cos = (float) (getCos(arrayForCosinesY, z3, i6, numCompY, i, height) * getCos(arrayForCosinesX, z, i5, numCompX, i7, width));
                        float[] fArr = colors[(i6 * numCompX) + i5];
                        f4 += fArr[0] * cos;
                        f5 += fArr[1] * cos;
                        f6 += fArr[2] * cos;
                        i4 = i5 + 1;
                        i3 = i6;
                        i2 = i7;
                        z2 = z3;
                    }
                    i3++;
                    f = f4;
                    f2 = f5;
                    f3 = f6;
                }
                int i8 = i2;
                iArr[i8 + (width * i)] = Color.rgb(linearToSrgb(f), linearToSrgb(f2), linearToSrgb(f3));
                i2 = i8 + 1;
            }
            i++;
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        return createBitmap;
    }

    private final double[] getArrayForCosinesY(boolean calculate, int height, int numCompY) {
        if (calculate) {
            int i = height * numCompY;
            double[] dArr = new double[i];
            cacheCosinesY.put(Integer.valueOf(i), dArr);
            return dArr;
        }
        double[] dArr2 = cacheCosinesY.get(Integer.valueOf(height * numCompY));
        Intrinsics.checkNotNull(dArr2);
        Intrinsics.checkNotNull(dArr2);
        return dArr2;
    }

    private final double[] getArrayForCosinesX(boolean calculate, int width, int numCompX) {
        if (calculate) {
            int i = width * numCompX;
            double[] dArr = new double[i];
            cacheCosinesX.put(Integer.valueOf(i), dArr);
            return dArr;
        }
        double[] dArr2 = cacheCosinesX.get(Integer.valueOf(width * numCompX));
        Intrinsics.checkNotNull(dArr2);
        return dArr2;
    }

    private final double getCos(double[] dArr, boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            dArr[(i2 * i3) + i] = Math.cos(((i3 * 3.141592653589793d) * i) / i4);
        }
        return dArr[i + (i2 * i3)];
    }

    private final int linearToSrgb(float value) {
        float pow;
        float f;
        float coerceIn = RangesKt.coerceIn(value, 0.0f, 1.0f);
        if (coerceIn <= 0.0031308f) {
            pow = coerceIn * 12.92f;
            f = 255.0f;
        } else {
            pow = (((float) Math.pow(coerceIn, 0.41666666f)) * 1.055f) - 0.055f;
            f = 255;
        }
        return (int) ((pow * f) + 0.5f);
    }
}

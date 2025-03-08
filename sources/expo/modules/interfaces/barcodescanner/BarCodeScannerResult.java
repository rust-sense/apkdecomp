package expo.modules.interfaces.barcodescanner;

import java.util.List;

/* loaded from: classes2.dex */
public class BarCodeScannerResult {
    private List<Integer> mCornerPoints;
    private String mRaw;
    private int mReferenceImageHeight;
    private int mReferenceImageWidth;
    private int mType;
    private String mValue;

    public List<Integer> getCornerPoints() {
        return this.mCornerPoints;
    }

    public String getRaw() {
        return this.mRaw;
    }

    public int getReferenceImageHeight() {
        return this.mReferenceImageHeight;
    }

    public int getReferenceImageWidth() {
        return this.mReferenceImageWidth;
    }

    public int getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }

    public void setCornerPoints(List<Integer> list) {
        this.mCornerPoints = list;
    }

    public void setReferenceImageHeight(int i) {
        this.mReferenceImageHeight = i;
    }

    public void setReferenceImageWidth(int i) {
        this.mReferenceImageWidth = i;
    }

    public static class BoundingBox {
        private final int height;
        private final int width;
        private final int x;
        private final int y;

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public BoundingBox(int i, int i2, int i3, int i4) {
            this.x = i;
            this.y = i2;
            this.width = i3;
            this.height = i4;
        }
    }

    public BarCodeScannerResult(int i, String str, String str2, List<Integer> list, int i2, int i3) {
        this.mType = i;
        this.mValue = str;
        this.mRaw = str2;
        this.mCornerPoints = list;
        this.mReferenceImageHeight = i2;
        this.mReferenceImageWidth = i3;
    }

    public BoundingBox getBoundingBox() {
        if (this.mCornerPoints.isEmpty()) {
            return new BoundingBox(0, 0, 0, 0);
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        for (int i5 = 0; i5 < this.mCornerPoints.size(); i5 += 2) {
            int intValue = this.mCornerPoints.get(i5).intValue();
            int intValue2 = this.mCornerPoints.get(i5 + 1).intValue();
            i2 = Math.min(i2, intValue);
            i3 = Math.min(i3, intValue2);
            i = Math.max(i, intValue);
            i4 = Math.max(i4, intValue2);
        }
        return new BoundingBox(i2, i3, i - i2, i4 - i3);
    }
}

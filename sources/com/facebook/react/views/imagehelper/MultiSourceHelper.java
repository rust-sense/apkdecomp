package com.facebook.react.views.imagehelper;

import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import java.util.List;

/* loaded from: classes.dex */
public class MultiSourceHelper {

    public static class MultiSourceResult {
        private final ImageSource bestResult;
        private final ImageSource bestResultInCache;

        public ImageSource getBestResult() {
            return this.bestResult;
        }

        public ImageSource getBestResultInCache() {
            return this.bestResultInCache;
        }

        private MultiSourceResult(ImageSource imageSource, ImageSource imageSource2) {
            this.bestResult = imageSource;
            this.bestResultInCache = imageSource2;
        }
    }

    public static MultiSourceResult getBestSourceForSize(int i, int i2, List<ImageSource> list) {
        return getBestSourceForSize(i, i2, list, 1.0d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static MultiSourceResult getBestSourceForSize(int i, int i2, List<ImageSource> list, double d) {
        ImageSource imageSource = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        Object[] objArr4 = 0;
        Object[] objArr5 = 0;
        Object[] objArr6 = 0;
        Object[] objArr7 = 0;
        Object[] objArr8 = 0;
        if (list.isEmpty()) {
            return new MultiSourceResult(imageSource, objArr8 == true ? 1 : 0);
        }
        if (list.size() == 1) {
            return new MultiSourceResult(list.get(0), objArr6 == true ? 1 : 0);
        }
        if (i <= 0 || i2 <= 0) {
            return new MultiSourceResult(objArr3 == true ? 1 : 0, objArr2 == true ? 1 : 0);
        }
        ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
        double d2 = i * i2 * d;
        double d3 = Double.MAX_VALUE;
        double d4 = Double.MAX_VALUE;
        ImageSource imageSource2 = null;
        ImageSource imageSource3 = null;
        for (ImageSource imageSource4 : list) {
            double abs = Math.abs(1.0d - (imageSource4.getSize() / d2));
            if (abs < d3) {
                imageSource3 = imageSource4;
                d3 = abs;
            }
            if (abs < d4 && (imagePipeline.isInBitmapMemoryCache(imageSource4.getUri()) || imagePipeline.isInDiskCacheSync(imageSource4.getUri()))) {
                imageSource2 = imageSource4;
                d4 = abs;
            }
        }
        if (imageSource2 != null && imageSource3 != null && imageSource2.getSource().equals(imageSource3.getSource())) {
            imageSource2 = null;
        }
        return new MultiSourceResult(imageSource3, imageSource2);
    }
}

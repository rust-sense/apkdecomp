package expo.modules.interfaces.barcodescanner;

import android.graphics.Bitmap;
import java.util.List;

/* loaded from: classes2.dex */
public interface BarCodeScannerInterface {
    BarCodeScannerResult scan(byte[] bArr, int i, int i2, int i3);

    List<BarCodeScannerResult> scanMultiple(Bitmap bitmap);

    void setSettings(BarCodeScannerSettings barCodeScannerSettings);
}

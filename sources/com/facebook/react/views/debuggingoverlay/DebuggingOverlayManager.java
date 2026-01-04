package com.facebook.react.views.debuggingoverlay;

import android.graphics.RectF;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;

@ReactModule(name = DebuggingOverlayManager.REACT_CLASS)
/* loaded from: classes.dex */
public class DebuggingOverlayManager extends SimpleViewManager<DebuggingOverlay> {
    public static final String REACT_CLASS = "DebuggingOverlay";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(DebuggingOverlay debuggingOverlay, String str, ReadableArray readableArray) {
        str.hashCode();
        int i = 0;
        char c = 65535;
        switch (str.hashCode()) {
            case -1942063165:
                if (str.equals("clearElementsHighlights")) {
                    c = 0;
                    break;
                }
                break;
            case 1326903961:
                if (str.equals("highlightTraceUpdates")) {
                    c = 1;
                    break;
                }
                break;
            case 1385348555:
                if (str.equals("highlightElements")) {
                    c = 2;
                    break;
                }
                break;
        }
        String str2 = "width";
        switch (c) {
            case 0:
                debuggingOverlay.clearElementsHighlights();
                break;
            case 1:
                if (readableArray != null) {
                    ReadableArray array = readableArray.getArray(0);
                    ArrayList arrayList = new ArrayList();
                    while (i < array.size()) {
                        ReadableMap map = array.getMap(i);
                        ReadableMap map2 = map.getMap("rectangle");
                        if (map2 == null) {
                            ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Unexpected payload for highlighting trace updates: rectangle field is null"));
                            break;
                        } else {
                            int i2 = map.getInt("id");
                            int i3 = map.getInt(ViewProps.COLOR);
                            try {
                                float f = (float) map2.getDouble(ViewHierarchyNode.JsonKeys.X);
                                float f2 = (float) map2.getDouble(ViewHierarchyNode.JsonKeys.Y);
                                ReadableArray readableArray2 = array;
                                String str3 = str2;
                                arrayList.add(new TraceUpdate(i2, new RectF(PixelUtil.toPixelFromDIP(f), PixelUtil.toPixelFromDIP(f2), PixelUtil.toPixelFromDIP((float) (f + map2.getDouble(str2))), PixelUtil.toPixelFromDIP((float) (f2 + map2.getDouble("height")))), i3));
                                i++;
                                str2 = str3;
                                array = readableArray2;
                            } catch (NoSuchKeyException | UnexpectedNativeTypeException unused) {
                                ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Unexpected payload for highlighting trace updates: rectangle field should have x, y, width, height fields"));
                                return;
                            }
                        }
                    }
                    debuggingOverlay.setTraceUpdates(arrayList);
                    break;
                }
                break;
            case 2:
                if (readableArray != null) {
                    ReadableArray array2 = readableArray.getArray(0);
                    ArrayList arrayList2 = new ArrayList();
                    while (i < array2.size()) {
                        ReadableMap map3 = array2.getMap(i);
                        try {
                            float f3 = (float) map3.getDouble(ViewHierarchyNode.JsonKeys.X);
                            float f4 = (float) map3.getDouble(ViewHierarchyNode.JsonKeys.Y);
                            arrayList2.add(new RectF(PixelUtil.toPixelFromDIP(f3), PixelUtil.toPixelFromDIP(f4), PixelUtil.toPixelFromDIP((float) (f3 + map3.getDouble("width"))), PixelUtil.toPixelFromDIP((float) (f4 + map3.getDouble("height")))));
                            i++;
                        } catch (NoSuchKeyException | UnexpectedNativeTypeException unused2) {
                            ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Unexpected payload for highlighting elements: every element should have x, y, width, height fields"));
                            return;
                        }
                    }
                    debuggingOverlay.setHighlightedElementsRectangles(arrayList2);
                    break;
                }
                break;
            default:
                ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Received unexpected command in DebuggingOverlayManager"));
                break;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public DebuggingOverlay createViewInstance(ThemedReactContext themedReactContext) {
        return new DebuggingOverlay(themedReactContext);
    }
}

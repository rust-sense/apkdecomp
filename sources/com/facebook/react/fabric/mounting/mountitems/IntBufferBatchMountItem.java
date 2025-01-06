package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.systrace.Systrace;

/* loaded from: classes.dex */
final class IntBufferBatchMountItem implements BatchMountItem {
    static final int INSTRUCTION_CREATE = 2;
    static final int INSTRUCTION_DELETE = 4;
    static final int INSTRUCTION_FLAG_MULTIPLE = 1;
    static final int INSTRUCTION_INSERT = 8;
    static final int INSTRUCTION_REMOVE = 16;
    static final int INSTRUCTION_REMOVE_DELETE_TREE = 2048;
    static final int INSTRUCTION_UPDATE_EVENT_EMITTER = 256;
    static final int INSTRUCTION_UPDATE_LAYOUT = 128;
    static final int INSTRUCTION_UPDATE_OVERFLOW_INSET = 1024;
    static final int INSTRUCTION_UPDATE_PADDING = 512;
    static final int INSTRUCTION_UPDATE_PROPS = 32;
    static final int INSTRUCTION_UPDATE_STATE = 64;
    static final String TAG = "IntBufferBatchMountItem";
    private final int mCommitNumber;
    private final int[] mIntBuffer;
    private final int mIntBufferLen;
    private final Object[] mObjBuffer;
    private final int mObjBufferLen;
    private final int mSurfaceId;

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.BatchMountItem
    public boolean isBatchEmpty() {
        return this.mIntBufferLen == 0;
    }

    IntBufferBatchMountItem(int i, int[] iArr, Object[] objArr, int i2) {
        this.mSurfaceId = i;
        this.mCommitNumber = i2;
        this.mIntBuffer = iArr;
        this.mObjBuffer = objArr;
        this.mIntBufferLen = iArr != null ? iArr.length : 0;
        this.mObjBufferLen = objArr != null ? objArr.length : 0;
    }

    private void beginMarkers(String str) {
        Systrace.beginSection(0L, "FabricUIManager::" + str);
        if (this.mCommitNumber > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_START, null, this.mCommitNumber);
        }
    }

    private void endMarkers() {
        if (this.mCommitNumber > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END, null, this.mCommitNumber);
        }
        Systrace.endSection(0L);
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.mSurfaceId);
        if (surfaceManager == null) {
            FLog.e(TAG, "Skipping batch of MountItems; no SurfaceMountingManager found for [%d].", Integer.valueOf(this.mSurfaceId));
            return;
        }
        if (surfaceManager.isStopped()) {
            FLog.e(TAG, "Skipping batch of MountItems; was stopped [%d].", Integer.valueOf(this.mSurfaceId));
            return;
        }
        if (FabricUIManager.ENABLE_FABRIC_LOGS) {
            FLog.d(TAG, "Executing IntBufferBatchMountItem on surface [%d]", Integer.valueOf(this.mSurfaceId));
        }
        beginMarkers("mountViews");
        int i6 = 0;
        int i7 = 0;
        while (i6 < this.mIntBufferLen) {
            int[] iArr = this.mIntBuffer;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            int i10 = i9 & (-2);
            if ((i9 & 1) != 0) {
                int i11 = iArr[i8];
                i8 = i6 + 2;
                i = i11;
            } else {
                i = 1;
            }
            int i12 = i7;
            i6 = i8;
            for (int i13 = 0; i13 < i; i13++) {
                if (i10 == 2) {
                    String fabricComponentName = FabricNameComponentMapping.getFabricComponentName((String) this.mObjBuffer[i12]);
                    int[] iArr2 = this.mIntBuffer;
                    int i14 = iArr2[i6];
                    Object[] objArr = this.mObjBuffer;
                    ReadableMap readableMap = (ReadableMap) objArr[i12 + 1];
                    int i15 = i12 + 3;
                    StateWrapper stateWrapper = (StateWrapper) objArr[i12 + 2];
                    i12 += 4;
                    i5 = i6 + 2;
                    surfaceManager.createView(fabricComponentName, i14, readableMap, stateWrapper, (EventEmitterWrapper) objArr[i15], iArr2[i6 + 1] == 1);
                } else {
                    if (i10 == 4) {
                        surfaceManager.deleteView(this.mIntBuffer[i6]);
                        i6++;
                    } else if (i10 == 8) {
                        int[] iArr3 = this.mIntBuffer;
                        int i16 = iArr3[i6];
                        int i17 = i6 + 2;
                        int i18 = iArr3[i6 + 1];
                        i6 += 3;
                        surfaceManager.addViewAt(i18, i16, iArr3[i17]);
                    } else if (i10 == 16) {
                        int[] iArr4 = this.mIntBuffer;
                        int i19 = iArr4[i6];
                        int i20 = i6 + 2;
                        int i21 = iArr4[i6 + 1];
                        i6 += 3;
                        surfaceManager.removeViewAt(i19, i21, iArr4[i20]);
                    } else if (i10 == 2048) {
                        int[] iArr5 = this.mIntBuffer;
                        int i22 = iArr5[i6];
                        int i23 = i6 + 2;
                        int i24 = iArr5[i6 + 1];
                        i6 += 3;
                        surfaceManager.removeDeleteTreeAt(i22, i24, iArr5[i23]);
                    } else {
                        if (i10 == 32) {
                            i2 = i6 + 1;
                            i3 = i12 + 1;
                            surfaceManager.updateProps(this.mIntBuffer[i6], (ReadableMap) this.mObjBuffer[i12]);
                        } else if (i10 == 64) {
                            i2 = i6 + 1;
                            i3 = i12 + 1;
                            surfaceManager.updateState(this.mIntBuffer[i6], (StateWrapper) this.mObjBuffer[i12]);
                        } else if (i10 == 128) {
                            int[] iArr6 = this.mIntBuffer;
                            i5 = i6 + 7;
                            surfaceManager.updateLayout(iArr6[i6], iArr6[i6 + 1], iArr6[i6 + 2], iArr6[i6 + 3], iArr6[i6 + 4], iArr6[i6 + 5], iArr6[i6 + 6]);
                        } else {
                            if (i10 == 512) {
                                int[] iArr7 = this.mIntBuffer;
                                i4 = i6 + 5;
                                surfaceManager.updatePadding(iArr7[i6], iArr7[i6 + 1], iArr7[i6 + 2], iArr7[i6 + 3], iArr7[i6 + 4]);
                            } else if (i10 == 1024) {
                                int[] iArr8 = this.mIntBuffer;
                                i4 = i6 + 5;
                                surfaceManager.updateOverflowInset(iArr8[i6], iArr8[i6 + 1], iArr8[i6 + 2], iArr8[i6 + 3], iArr8[i6 + 4]);
                            } else if (i10 == 256) {
                                i2 = i6 + 1;
                                i3 = i12 + 1;
                                surfaceManager.updateEventEmitter(this.mIntBuffer[i6], (EventEmitterWrapper) this.mObjBuffer[i12]);
                            } else {
                                throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i10 + " at index: " + i6);
                            }
                            i6 = i4;
                        }
                        i6 = i2;
                        i12 = i3;
                    }
                }
                i6 = i5;
            }
            i7 = i12;
        }
        endMarkers();
    }

    public String toString() {
        int i;
        int i2;
        int i3;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("IntBufferBatchMountItem [surface:%d]:\n", Integer.valueOf(this.mSurfaceId)));
            int i4 = 0;
            int i5 = 0;
            while (i4 < this.mIntBufferLen) {
                int[] iArr = this.mIntBuffer;
                int i6 = i4 + 1;
                int i7 = iArr[i4];
                int i8 = i7 & (-2);
                if ((i7 & 1) != 0) {
                    i = iArr[i6];
                    i6 = i4 + 2;
                } else {
                    i = 1;
                }
                i4 = i6;
                for (int i9 = 0; i9 < i; i9++) {
                    if (i8 == 2) {
                        String fabricComponentName = FabricNameComponentMapping.getFabricComponentName((String) this.mObjBuffer[i5]);
                        i5 += 4;
                        int i10 = i4 + 1;
                        i4 += 2;
                        sb.append(String.format("CREATE [%d] - layoutable:%d - %s\n", Integer.valueOf(this.mIntBuffer[i4]), Integer.valueOf(this.mIntBuffer[i10]), fabricComponentName));
                    } else {
                        if (i8 == 4) {
                            i2 = i4 + 1;
                            sb.append(String.format("DELETE [%d]\n", Integer.valueOf(this.mIntBuffer[i4])));
                        } else if (i8 == 8) {
                            int i11 = i4 + 2;
                            i4 += 3;
                            sb.append(String.format("INSERT [%d]->[%d] @%d\n", Integer.valueOf(this.mIntBuffer[i4]), Integer.valueOf(this.mIntBuffer[i4 + 1]), Integer.valueOf(this.mIntBuffer[i11])));
                        } else if (i8 == 16) {
                            int i12 = i4 + 2;
                            i4 += 3;
                            sb.append(String.format("REMOVE [%d]->[%d] @%d\n", Integer.valueOf(this.mIntBuffer[i4]), Integer.valueOf(this.mIntBuffer[i4 + 1]), Integer.valueOf(this.mIntBuffer[i12])));
                        } else if (i8 == 2048) {
                            int i13 = i4 + 2;
                            i4 += 3;
                            sb.append(String.format("REMOVE+DELETE TREE [%d]->[%d] @%d\n", Integer.valueOf(this.mIntBuffer[i4]), Integer.valueOf(this.mIntBuffer[i4 + 1]), Integer.valueOf(this.mIntBuffer[i13])));
                        } else {
                            if (i8 == 32) {
                                i3 = i5 + 1;
                                Object obj = this.mObjBuffer[i5];
                                i2 = i4 + 1;
                                sb.append(String.format("UPDATE PROPS [%d]: %s\n", Integer.valueOf(this.mIntBuffer[i4]), "<hidden>"));
                            } else if (i8 == 64) {
                                i3 = i5 + 1;
                                i2 = i4 + 1;
                                sb.append(String.format("UPDATE STATE [%d]: %s\n", Integer.valueOf(this.mIntBuffer[i4]), "<hidden>"));
                            } else if (i8 == 128) {
                                int[] iArr2 = this.mIntBuffer;
                                int i14 = i4 + 6;
                                i4 += 7;
                                sb.append(String.format("UPDATE LAYOUT [%d]->[%d]: x:%d y:%d w:%d h:%d displayType:%d\n", Integer.valueOf(iArr2[i4 + 1]), Integer.valueOf(iArr2[i4]), Integer.valueOf(this.mIntBuffer[i4 + 2]), Integer.valueOf(this.mIntBuffer[i4 + 3]), Integer.valueOf(this.mIntBuffer[i4 + 4]), Integer.valueOf(this.mIntBuffer[i4 + 5]), Integer.valueOf(this.mIntBuffer[i14])));
                            } else if (i8 == 512) {
                                int i15 = i4 + 4;
                                i4 += 5;
                                sb.append(String.format("UPDATE PADDING [%d]: top:%d right:%d bottom:%d left:%d\n", Integer.valueOf(this.mIntBuffer[i4]), Integer.valueOf(this.mIntBuffer[i4 + 1]), Integer.valueOf(this.mIntBuffer[i4 + 2]), Integer.valueOf(this.mIntBuffer[i4 + 3]), Integer.valueOf(this.mIntBuffer[i15])));
                            } else if (i8 == 1024) {
                                int i16 = i4 + 4;
                                i4 += 5;
                                sb.append(String.format("UPDATE OVERFLOWINSET [%d]: left:%d top:%d right:%d bottom:%d\n", Integer.valueOf(this.mIntBuffer[i4]), Integer.valueOf(this.mIntBuffer[i4 + 1]), Integer.valueOf(this.mIntBuffer[i4 + 2]), Integer.valueOf(this.mIntBuffer[i4 + 3]), Integer.valueOf(this.mIntBuffer[i16])));
                            } else {
                                if (i8 != 256) {
                                    FLog.e(TAG, "String so far: " + sb.toString());
                                    throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i8 + " at index: " + i4);
                                }
                                i5++;
                                i2 = i4 + 1;
                                sb.append(String.format("UPDATE EVENTEMITTER [%d]\n", Integer.valueOf(this.mIntBuffer[i4])));
                            }
                            i5 = i3;
                        }
                        i4 = i2;
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            FLog.e(TAG, "Caught exception trying to print", e);
            StringBuilder sb2 = new StringBuilder();
            for (int i17 = 0; i17 < this.mIntBufferLen; i17++) {
                sb2.append(this.mIntBuffer[i17]);
                sb2.append(", ");
            }
            FLog.e(TAG, sb2.toString());
            for (int i18 = 0; i18 < this.mObjBufferLen; i18++) {
                String str = TAG;
                Object obj2 = this.mObjBuffer[i18];
                FLog.e(str, obj2 != null ? obj2.toString() : "null");
            }
            return "";
        }
    }
}

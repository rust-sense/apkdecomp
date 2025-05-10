package org.bouncycastle.pqc.crypto.qtesla;

/* loaded from: classes3.dex */
final class IntSlicer {
    private int base;
    private final int[] values;

    IntSlicer(int[] iArr, int i) {
        this.values = iArr;
        this.base = i;
    }

    final int at(int i) {
        return this.values[this.base + i];
    }

    final int at(int i, int i2) {
        this.values[this.base + i] = i2;
        return i2;
    }

    final int at(int i, long j) {
        int[] iArr = this.values;
        int i2 = this.base + i;
        int i3 = (int) j;
        iArr[i2] = i3;
        return i3;
    }

    final IntSlicer copy() {
        return new IntSlicer(this.values, this.base);
    }

    final IntSlicer from(int i) {
        return new IntSlicer(this.values, this.base + i);
    }

    final void incBase(int i) {
        this.base += i;
    }
}

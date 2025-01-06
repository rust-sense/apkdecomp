package org.apache.commons.lang3;

/* loaded from: classes3.dex */
public class BitField {
    private final int _mask;
    private final int _shift_count;

    public int clear(int i) {
        return i & (~this._mask);
    }

    public int getRawValue(int i) {
        return i & this._mask;
    }

    public boolean isAllSet(int i) {
        int i2 = this._mask;
        return (i & i2) == i2;
    }

    public boolean isSet(int i) {
        return (i & this._mask) != 0;
    }

    public int set(int i) {
        return i | this._mask;
    }

    public int setValue(int i, int i2) {
        int i3 = this._mask;
        return (i & (~i3)) | ((i2 << this._shift_count) & i3);
    }

    public BitField(int i) {
        this._mask = i;
        this._shift_count = i == 0 ? 0 : Integer.numberOfTrailingZeros(i);
    }

    public int getValue(int i) {
        return getRawValue(i) >> this._shift_count;
    }

    public short getShortValue(short s) {
        return (short) getValue(s);
    }

    public short getShortRawValue(short s) {
        return (short) getRawValue(s);
    }

    public short setShortValue(short s, short s2) {
        return (short) setValue(s, s2);
    }

    public short clearShort(short s) {
        return (short) clear(s);
    }

    public byte clearByte(byte b) {
        return (byte) clear(b);
    }

    public short setShort(short s) {
        return (short) set(s);
    }

    public byte setByte(byte b) {
        return (byte) set(b);
    }

    public int setBoolean(int i, boolean z) {
        return z ? set(i) : clear(i);
    }

    public short setShortBoolean(short s, boolean z) {
        return z ? setShort(s) : clearShort(s);
    }

    public byte setByteBoolean(byte b, boolean z) {
        return z ? setByte(b) : clearByte(b);
    }
}
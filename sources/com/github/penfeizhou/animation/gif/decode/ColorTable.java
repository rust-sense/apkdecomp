package com.github.penfeizhou.animation.gif.decode;

import androidx.core.view.ViewCompat;
import com.github.penfeizhou.animation.gif.io.GifReader;
import java.io.IOException;

/* loaded from: classes.dex */
public class ColorTable implements Block {
    private int[] colorTable;

    public int[] getColorTable() {
        return this.colorTable;
    }

    public ColorTable(int i) {
        this.colorTable = new int[i];
    }

    @Override // com.github.penfeizhou.animation.gif.decode.Block
    public void receive(GifReader gifReader) throws IOException {
        for (int i = 0; i < this.colorTable.length; i++) {
            byte peek = gifReader.peek();
            byte peek2 = gifReader.peek();
            int i2 = (peek2 & 255) << 8;
            int i3 = peek & 255;
            this.colorTable[i] = i3 | i2 | ((gifReader.peek() & 255) << 16) | ViewCompat.MEASURED_STATE_MASK;
        }
    }

    @Override // com.github.penfeizhou.animation.gif.decode.Block
    public int size() {
        return this.colorTable.length * 3;
    }
}

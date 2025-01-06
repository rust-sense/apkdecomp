package com.github.penfeizhou.animation.gif.decode;

import com.github.penfeizhou.animation.gif.io.GifReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PlaintTextExtension extends ExtensionBlock {
    private List<DataSubBlock> plainTextData = new ArrayList();

    @Override // com.github.penfeizhou.animation.gif.decode.Block
    public int size() {
        return 0;
    }

    @Override // com.github.penfeizhou.animation.gif.decode.Block
    public void receive(GifReader gifReader) throws IOException {
        gifReader.peek();
        gifReader.readUInt16();
        gifReader.readUInt16();
        gifReader.readUInt16();
        gifReader.readUInt16();
        gifReader.peek();
        gifReader.peek();
        gifReader.peek();
        gifReader.peek();
        while (true) {
            DataSubBlock retrieve = DataSubBlock.retrieve(gifReader);
            if (retrieve.isTerminal()) {
                return;
            } else {
                this.plainTextData.add(retrieve);
            }
        }
    }
}

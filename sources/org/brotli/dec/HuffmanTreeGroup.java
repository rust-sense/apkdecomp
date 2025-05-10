package org.brotli.dec;

/* loaded from: classes3.dex */
final class HuffmanTreeGroup {
    private int alphabetSize;
    int[] codes;
    int[] trees;

    HuffmanTreeGroup() {
    }

    static void init(HuffmanTreeGroup huffmanTreeGroup, int i, int i2) {
        huffmanTreeGroup.alphabetSize = i;
        huffmanTreeGroup.codes = new int[i2 * 1080];
        huffmanTreeGroup.trees = new int[i2];
    }

    static void decode(HuffmanTreeGroup huffmanTreeGroup, BitReader bitReader) {
        int length = huffmanTreeGroup.trees.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            huffmanTreeGroup.trees[i2] = i;
            Decode.readHuffmanCode(huffmanTreeGroup.alphabetSize, huffmanTreeGroup.codes, i, bitReader);
            i += 1080;
        }
    }
}

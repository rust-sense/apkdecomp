package com.google.thirdparty.publicsuffix;

/* loaded from: classes2.dex */
public enum PublicSuffixType {
    PRIVATE(':', ','),
    REGISTRY('!', '?');

    private final char innerNodeCode;
    private final char leafNodeCode;

    static PublicSuffixType fromIsPrivate(boolean z) {
        return z ? PRIVATE : REGISTRY;
    }

    char getInnerNodeCode() {
        return this.innerNodeCode;
    }

    char getLeafNodeCode() {
        return this.leafNodeCode;
    }

    PublicSuffixType(char c, char c2) {
        this.innerNodeCode = c;
        this.leafNodeCode = c2;
    }

    static PublicSuffixType fromCode(char c) {
        for (PublicSuffixType publicSuffixType : values()) {
            if (publicSuffixType.getInnerNodeCode() == c || publicSuffixType.getLeafNodeCode() == c) {
                return publicSuffixType;
            }
        }
        throw new IllegalArgumentException("No enum corresponding to given code: " + c);
    }
}

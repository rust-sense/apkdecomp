package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.XMSSAddress;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
final class OTSHashAddress extends XMSSAddress {
    private static final int TYPE = 0;
    private final int chainAddress;
    private final int hashAddress;
    private final int otsAddress;

    protected static class Builder extends XMSSAddress.Builder<Builder> {
        private int chainAddress;
        private int hashAddress;
        private int otsAddress;

        protected Builder() {
            super(0);
            this.otsAddress = 0;
            this.chainAddress = 0;
            this.hashAddress = 0;
        }

        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress.Builder
        protected XMSSAddress build() {
            return new OTSHashAddress(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress.Builder
        public Builder getThis() {
            return this;
        }

        protected Builder withChainAddress(int i) {
            this.chainAddress = i;
            return this;
        }

        protected Builder withHashAddress(int i) {
            this.hashAddress = i;
            return this;
        }

        protected Builder withOTSAddress(int i) {
            this.otsAddress = i;
            return this;
        }
    }

    private OTSHashAddress(Builder builder) {
        super(builder);
        this.otsAddress = builder.otsAddress;
        this.chainAddress = builder.chainAddress;
        this.hashAddress = builder.hashAddress;
    }

    protected int getChainAddress() {
        return this.chainAddress;
    }

    protected int getHashAddress() {
        return this.hashAddress;
    }

    protected int getOTSAddress() {
        return this.otsAddress;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress
    protected byte[] toByteArray() {
        byte[] byteArray = super.toByteArray();
        Pack.intToBigEndian(this.otsAddress, byteArray, 16);
        Pack.intToBigEndian(this.chainAddress, byteArray, 20);
        Pack.intToBigEndian(this.hashAddress, byteArray, 24);
        return byteArray;
    }
}

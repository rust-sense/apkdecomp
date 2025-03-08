package org.bouncycastle.asn1.dvcs;

import java.util.Arrays;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.PolicyInformation;

/* loaded from: classes3.dex */
public class PathProcInput extends ASN1Object {
    private PolicyInformation[] acceptablePolicySet;
    private boolean explicitPolicyReqd;
    private boolean inhibitAnyPolicy;
    private boolean inhibitPolicyMapping;

    public PathProcInput(PolicyInformation[] policyInformationArr) {
        this.inhibitPolicyMapping = false;
        this.explicitPolicyReqd = false;
        this.inhibitAnyPolicy = false;
        this.acceptablePolicySet = copy(policyInformationArr);
    }

    public PathProcInput(PolicyInformation[] policyInformationArr, boolean z, boolean z2, boolean z3) {
        this.inhibitPolicyMapping = false;
        this.explicitPolicyReqd = false;
        this.inhibitAnyPolicy = false;
        this.acceptablePolicySet = copy(policyInformationArr);
        this.inhibitPolicyMapping = z;
        this.explicitPolicyReqd = z2;
        this.inhibitAnyPolicy = z3;
    }

    private PolicyInformation[] copy(PolicyInformation[] policyInformationArr) {
        int length = policyInformationArr.length;
        PolicyInformation[] policyInformationArr2 = new PolicyInformation[length];
        System.arraycopy(policyInformationArr, 0, policyInformationArr2, 0, length);
        return policyInformationArr2;
    }

    private static PolicyInformation[] fromSequence(ASN1Sequence aSN1Sequence) {
        int size = aSN1Sequence.size();
        PolicyInformation[] policyInformationArr = new PolicyInformation[size];
        for (int i = 0; i != size; i++) {
            policyInformationArr[i] = PolicyInformation.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return policyInformationArr;
    }

    public static PathProcInput getInstance(Object obj) {
        if (obj instanceof PathProcInput) {
            return (PathProcInput) obj;
        }
        if (obj == null) {
            return null;
        }
        ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(obj);
        PathProcInput pathProcInput = new PathProcInput(fromSequence(ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(0))));
        for (int i = 1; i < aSN1Sequence.size(); i++) {
            ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i);
            if (objectAt instanceof ASN1Boolean) {
                pathProcInput.setInhibitPolicyMapping(ASN1Boolean.getInstance(objectAt).isTrue());
            } else if (objectAt instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objectAt);
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    pathProcInput.setExplicitPolicyReqd(ASN1Boolean.getInstance(aSN1TaggedObject, false).isTrue());
                } else {
                    if (tagNo != 1) {
                        throw new IllegalArgumentException("Unknown tag encountered: " + aSN1TaggedObject.getTagNo());
                    }
                    pathProcInput.setInhibitAnyPolicy(ASN1Boolean.getInstance(aSN1TaggedObject, false).isTrue());
                }
            } else {
                continue;
            }
        }
        return pathProcInput;
    }

    public static PathProcInput getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    private void setExplicitPolicyReqd(boolean z) {
        this.explicitPolicyReqd = z;
    }

    private void setInhibitAnyPolicy(boolean z) {
        this.inhibitAnyPolicy = z;
    }

    private void setInhibitPolicyMapping(boolean z) {
        this.inhibitPolicyMapping = z;
    }

    public PolicyInformation[] getAcceptablePolicySet() {
        return copy(this.acceptablePolicySet);
    }

    public boolean isExplicitPolicyReqd() {
        return this.explicitPolicyReqd;
    }

    public boolean isInhibitAnyPolicy() {
        return this.inhibitAnyPolicy;
    }

    public boolean isInhibitPolicyMapping() {
        return this.inhibitPolicyMapping;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(this.acceptablePolicySet.length);
        int i = 0;
        while (true) {
            PolicyInformation[] policyInformationArr = this.acceptablePolicySet;
            if (i == policyInformationArr.length) {
                break;
            }
            aSN1EncodableVector2.add(policyInformationArr[i]);
            i++;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        boolean z = this.inhibitPolicyMapping;
        if (z) {
            aSN1EncodableVector.add(ASN1Boolean.getInstance(z));
        }
        if (this.explicitPolicyReqd) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) ASN1Boolean.getInstance(this.explicitPolicyReqd)));
        }
        if (this.inhibitAnyPolicy) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) ASN1Boolean.getInstance(this.inhibitAnyPolicy)));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "PathProcInput: {\nacceptablePolicySet: " + Arrays.asList(this.acceptablePolicySet) + "\ninhibitPolicyMapping: " + this.inhibitPolicyMapping + "\nexplicitPolicyReqd: " + this.explicitPolicyReqd + "\ninhibitAnyPolicy: " + this.inhibitAnyPolicy + "\n}\n";
    }
}

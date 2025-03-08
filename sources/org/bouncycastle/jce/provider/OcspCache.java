package org.bouncycastle.jce.provider;

import androidx.browser.trusted.sharing.ShareTarget;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Extension;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.Headers$$ExternalSyntheticApiModelOutline0;
import org.apache.commons.fileupload.FileUploadBase;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.asn1.ocsp.CertID;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.ocsp.OCSPRequest;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.asn1.ocsp.ResponseBytes;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.ocsp.TBSRequest;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.jcajce.PKIXCertRevocationCheckerParameters;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.io.Streams;

/* loaded from: classes3.dex */
class OcspCache {
    private static final int DEFAULT_MAX_RESPONSE_SIZE = 32768;
    private static final int DEFAULT_TIMEOUT = 15000;
    private static Map<URI, WeakReference<Map<CertID, OCSPResponse>>> cache = Collections.synchronizedMap(new WeakHashMap());

    OcspCache() {
    }

    static OCSPResponse getOcspResponse(CertID certID, PKIXCertRevocationCheckerParameters pKIXCertRevocationCheckerParameters, URI uri, X509Certificate x509Certificate, List<Extension> list, JcaJceHelper jcaJceHelper) throws CertPathValidatorException {
        OCSPResponse oCSPResponse;
        byte[] value;
        String id;
        String id2;
        boolean isCritical;
        OCSPResponse oCSPResponse2;
        ASN1GeneralizedTime nextUpdate;
        WeakReference<Map<CertID, OCSPResponse>> weakReference = cache.get(uri);
        Map<CertID, OCSPResponse> map = weakReference != null ? weakReference.get() : null;
        if (map != null && (oCSPResponse2 = map.get(certID)) != null) {
            ASN1Sequence responses = ResponseData.getInstance(BasicOCSPResponse.getInstance(ASN1OctetString.getInstance(oCSPResponse2.getResponseBytes().getResponse()).getOctets()).getTbsResponseData()).getResponses();
            for (int i = 0; i != responses.size(); i++) {
                SingleResponse singleResponse = SingleResponse.getInstance(responses.getObjectAt(i));
                if (certID.equals(singleResponse.getCertID()) && (nextUpdate = singleResponse.getNextUpdate()) != null) {
                    try {
                    } catch (ParseException unused) {
                        map.remove(certID);
                    }
                    if (pKIXCertRevocationCheckerParameters.getValidDate().after(nextUpdate.getDate())) {
                        map.remove(certID);
                        oCSPResponse2 = null;
                    }
                }
            }
            if (oCSPResponse2 != null) {
                return oCSPResponse2;
            }
        }
        try {
            URL url = uri.toURL();
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.add(new Request(certID, null));
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            byte[] bArr = null;
            for (int i2 = 0; i2 != list.size(); i2++) {
                Extension m = Headers$$ExternalSyntheticApiModelOutline0.m((Object) list.get(i2));
                value = m.getValue();
                String id3 = OCSPObjectIdentifiers.id_pkix_ocsp_nonce.getId();
                id = m.getId();
                if (id3.equals(id)) {
                    bArr = value;
                }
                id2 = m.getId();
                ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier(id2);
                isCritical = m.isCritical();
                aSN1EncodableVector2.add(new org.bouncycastle.asn1.x509.Extension(aSN1ObjectIdentifier, isCritical, value));
            }
            try {
                byte[] encoded = new OCSPRequest(new TBSRequest((GeneralName) null, new DERSequence(aSN1EncodableVector), Extensions.getInstance(new DERSequence(aSN1EncodableVector2))), null).getEncoded();
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(DEFAULT_TIMEOUT);
                httpURLConnection.setReadTimeout(DEFAULT_TIMEOUT);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod(ShareTarget.METHOD_POST);
                httpURLConnection.setRequestProperty(FileUploadBase.CONTENT_TYPE, "application/ocsp-request");
                httpURLConnection.setRequestProperty(FileUploadBase.CONTENT_LENGTH, String.valueOf(encoded.length));
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(encoded);
                outputStream.flush();
                InputStream inputStream = httpURLConnection.getInputStream();
                int contentLength = httpURLConnection.getContentLength();
                if (contentLength < 0) {
                    contentLength = 32768;
                }
                oCSPResponse = OCSPResponse.getInstance(Streams.readAllLimited(inputStream, contentLength));
            } catch (IOException e) {
                e = e;
            }
            try {
                if (oCSPResponse.getResponseStatus().getIntValue() != 0) {
                    throw new CertPathValidatorException("OCSP responder failed: " + oCSPResponse.getResponseStatus().getValue(), null, pKIXCertRevocationCheckerParameters.getCertPath(), pKIXCertRevocationCheckerParameters.getIndex());
                }
                ResponseBytes responseBytes = ResponseBytes.getInstance(oCSPResponse.getResponseBytes());
                if (responseBytes.getResponseType().equals((ASN1Primitive) OCSPObjectIdentifiers.id_pkix_ocsp_basic)) {
                    if (ProvOcspRevocationChecker.validatedOcspResponse(BasicOCSPResponse.getInstance(responseBytes.getResponse().getOctets()), pKIXCertRevocationCheckerParameters, bArr, x509Certificate, jcaJceHelper)) {
                        WeakReference<Map<CertID, OCSPResponse>> weakReference2 = cache.get(uri);
                        if (weakReference2 != null) {
                            weakReference2.get().put(certID, oCSPResponse);
                        } else {
                            HashMap hashMap = new HashMap();
                            hashMap.put(certID, oCSPResponse);
                            cache.put(uri, new WeakReference<>(hashMap));
                        }
                        return oCSPResponse;
                    }
                }
                throw new CertPathValidatorException("OCSP response failed to validate", null, pKIXCertRevocationCheckerParameters.getCertPath(), pKIXCertRevocationCheckerParameters.getIndex());
            } catch (IOException e2) {
                e = e2;
                throw new CertPathValidatorException("configuration error: " + e.getMessage(), e, pKIXCertRevocationCheckerParameters.getCertPath(), pKIXCertRevocationCheckerParameters.getIndex());
            }
        } catch (MalformedURLException e3) {
            throw new CertPathValidatorException("configuration error: " + e3.getMessage(), e3, pKIXCertRevocationCheckerParameters.getCertPath(), pKIXCertRevocationCheckerParameters.getIndex());
        }
    }
}

package com.cloudaware.cloudmine.amazon.iot;

import com.amazonaws.services.iot.model.CertificateDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class CertificateDetailsResponse extends AmazonResponse {

    private CertificateDescription certificate;

    public CertificateDescription getCertificate() {
        return certificate;
    }

    public void setCertificate(final CertificateDescription certificate) {
        this.certificate = certificate;
    }
}

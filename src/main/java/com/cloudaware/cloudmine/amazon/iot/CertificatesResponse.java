package com.cloudaware.cloudmine.amazon.iot;

import com.amazonaws.services.iot.model.Certificate;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class CertificatesResponse extends AmazonResponse {

    private List<Certificate> certificates;

    public void setCertificates(final List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }
}

package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.ServerCertificate;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 04:05
 */
public final class ServerCertificateResponse extends AmazonResponse {
    private ServerCertificate serverCertificate;

    public ServerCertificateResponse() {
    }

    public ServerCertificateResponse(final AmazonException exception) {
        super(exception);
    }

    public ServerCertificateResponse(final ServerCertificate serverCertificate) {
        this.serverCertificate = serverCertificate;
    }

    public ServerCertificate getServerCertificate() {
        return serverCertificate;
    }

    public void setServerCertificate(final ServerCertificate serverCertificate) {
        this.serverCertificate = serverCertificate;
    }
}

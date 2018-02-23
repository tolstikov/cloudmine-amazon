package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.ServerSideEncryptionConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class EncryptionConfigurationResponse extends AmazonResponse {

    private ServerSideEncryptionConfiguration serverSideEncryptionConfiguration;

    public EncryptionConfigurationResponse(final AmazonException exception) {
        super(exception);
    }

    public EncryptionConfigurationResponse(final ServerSideEncryptionConfiguration serverSideEncryptionConfiguration) {
        this.serverSideEncryptionConfiguration = serverSideEncryptionConfiguration;
    }

    public ServerSideEncryptionConfiguration getServerSideEncryptionConfiguration() {
        return serverSideEncryptionConfiguration;
    }

    public void setServerSideEncryptionConfiguration(final ServerSideEncryptionConfiguration serverSideEncryptionConfiguration) {
        this.serverSideEncryptionConfiguration = serverSideEncryptionConfiguration;
    }
}

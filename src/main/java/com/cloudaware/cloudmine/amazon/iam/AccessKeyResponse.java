package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.AccessKey;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 03:43
 */
public final class AccessKeyResponse extends AmazonResponse {
    private AccessKey accessKey;

    public AccessKeyResponse() {
    }

    public AccessKeyResponse(final AmazonException exception) {
        super(exception);
    }

    public AccessKeyResponse(final AccessKey accessKey) {
        this.accessKey = accessKey;
    }

    public AccessKey getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(final AccessKey accessKey) {
        this.accessKey = accessKey;
    }
}

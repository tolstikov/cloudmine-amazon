package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.AccessKeyMetadata;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 03:25
 */
public final class AccessKeysResponse extends AmazonResponse {
    private List<AccessKeyMetadata> accessKeys;

    public AccessKeysResponse() {
    }

    public AccessKeysResponse(final AmazonException exception) {
        super(exception);
    }

    public AccessKeysResponse(final List<AccessKeyMetadata> accessKeys, final String nextPage) {
        super(nextPage);
        this.accessKeys = accessKeys;
    }

    public List<AccessKeyMetadata> getAccessKeys() {

        return accessKeys;
    }

    public void setAccessKeys(final List<AccessKeyMetadata> accessKeys) {
        this.accessKeys = accessKeys;
    }
}

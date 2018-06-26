package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.SSHPublicKeyMetadata;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ListSshPublicKeysResponse extends AmazonResponse {

    private List<SSHPublicKeyMetadata> publicKeys;

    public List<SSHPublicKeyMetadata> getPublicKeys() {
        return publicKeys;
    }

    public void setPublicKeys(final List<SSHPublicKeyMetadata> publicKeys) {
        this.publicKeys = publicKeys;
    }
}

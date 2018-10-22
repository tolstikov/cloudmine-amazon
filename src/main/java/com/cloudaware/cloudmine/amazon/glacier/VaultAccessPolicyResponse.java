package com.cloudaware.cloudmine.amazon.glacier;

import com.amazonaws.services.glacier.model.VaultAccessPolicy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class VaultAccessPolicyResponse extends AmazonResponse {
    private VaultAccessPolicy policy;

    public VaultAccessPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(final VaultAccessPolicy policy) {
        this.policy = policy;
    }
}

package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class IdentitiesResponse extends AmazonResponse {

    private List<String> identities;

    public List<String> getIdentities() {
        return identities;
    }

    public void setIdentities(final List<String> identities) {
        this.identities = identities;
    }
}

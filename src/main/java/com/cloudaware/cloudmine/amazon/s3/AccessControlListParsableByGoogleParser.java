package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.Grant;
import com.amazonaws.services.s3.model.Owner;

import java.util.List;

public final class AccessControlListParsableByGoogleParser {

    private List<Grant> grants;
    private Owner owner;
    private boolean isRequesterCharged;

    public List<Grant> getGrants() {
        return grants;
    }

    public void setGrants(final List<Grant> grants) {
        this.grants = grants;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(final Owner owner) {
        this.owner = owner;
    }

    public boolean isRequesterCharged() {
        return isRequesterCharged;
    }

    public void setRequesterCharged(final boolean requesterCharged) {
        isRequesterCharged = requesterCharged;
    }
}

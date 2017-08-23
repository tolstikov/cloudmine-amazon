package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.Principal;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProtfolioPrincipalsResponse extends AmazonResponse {
    private List<Principal> principals;

    public List<Principal> getPrincipals() {
        return principals;
    }

    public void setPrincipals(final List<Principal> principals) {
        this.principals = principals;
    }
}

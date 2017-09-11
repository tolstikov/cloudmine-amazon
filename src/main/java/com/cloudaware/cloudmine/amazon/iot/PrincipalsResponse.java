package com.cloudaware.cloudmine.amazon.iot;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PrincipalsResponse extends AmazonResponse {

    private List<String> principals;

    public void setPrincipals(final List<String> principals) {
        this.principals = principals;
    }

    public List<String> getPrincipals() {
        return principals;
    }
}

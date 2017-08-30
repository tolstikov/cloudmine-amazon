package com.cloudaware.cloudmine.amazon.codedeploy;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class OnPremisesInstanceNamesResponse extends AmazonResponse {

    private List<String> instanceNames;

    public List<String> getInstanceNames() {
        return instanceNames;
    }

    public void setInstanceNames(final List<String> instanceNames) {
        this.instanceNames = instanceNames;
    }
}

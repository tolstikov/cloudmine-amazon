package com.cloudaware.cloudmine.amazon.codedeploy;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ApplicationNamesResponse extends AmazonResponse {

    private List<String> applicationNames;

    public List<String> getApplicationNames() {
        return applicationNames;
    }

    public void setApplicationNames(final List<String> applicationNames) {
        this.applicationNames = applicationNames;
    }
}

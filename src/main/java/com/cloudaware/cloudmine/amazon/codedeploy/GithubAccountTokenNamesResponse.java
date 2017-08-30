package com.cloudaware.cloudmine.amazon.codedeploy;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class GithubAccountTokenNamesResponse extends AmazonResponse {

    private List<String> tokenNames;

    public List<String> getTokenNames() {
        return tokenNames;
    }

    public void setTokenNames(final List<String> tokenNames) {
        this.tokenNames = tokenNames;
    }
}

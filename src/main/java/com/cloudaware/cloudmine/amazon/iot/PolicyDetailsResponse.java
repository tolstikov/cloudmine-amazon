package com.cloudaware.cloudmine.amazon.iot;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class PolicyDetailsResponse extends AmazonResponse {

    private String defaultVersionId;
    private String policyArn;
    private String policyDocument;
    private String policyName;

    public void setDefaultVersionId(final String defaultVersionId) {
        this.defaultVersionId = defaultVersionId;
    }

    public String getDefaultVersionId() {
        return defaultVersionId;
    }

    public void setPolicyArn(final String policyArn) {
        this.policyArn = policyArn;
    }

    public String getPolicyArn() {
        return policyArn;
    }

    public void setPolicyDocument(final String policyDocument) {
        this.policyDocument = policyDocument;
    }

    public String getPolicyDocument() {
        return policyDocument;
    }

    public void setPolicyName(final String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyName() {
        return policyName;
    }
}

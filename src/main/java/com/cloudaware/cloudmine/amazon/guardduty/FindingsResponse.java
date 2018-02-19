package com.cloudaware.cloudmine.amazon.guardduty;

import com.amazonaws.services.guardduty.model.Finding;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class FindingsResponse extends AmazonResponse {

    private List<Finding> findings;

    public List<Finding> getFindings() {
        return findings;
    }

    public void setFindings(final List<Finding> findings) {
        this.findings = findings;
    }
}

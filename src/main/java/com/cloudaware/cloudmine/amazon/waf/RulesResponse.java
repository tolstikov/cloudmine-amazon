package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.RuleSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RulesResponse extends AmazonResponse {
    private List<RuleSummary> ruleSummaries;

    public List<RuleSummary> getRuleSummaries() {
        return ruleSummaries;
    }

    public void setRuleSummaries(final List<RuleSummary> ruleSummaries) {
        this.ruleSummaries = ruleSummaries;
    }
}

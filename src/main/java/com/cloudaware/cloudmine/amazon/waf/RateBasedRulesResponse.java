package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.RuleSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RateBasedRulesResponse extends AmazonResponse {
    private List<RuleSummary> rateBasedRuleSummaries;

    public List<RuleSummary> getRateBasedRuleSummaries() {
        return rateBasedRuleSummaries;
    }

    public void setRateBasedRuleSummaries(final List<RuleSummary> rateBasedRuleSummaries) {
        this.rateBasedRuleSummaries = rateBasedRuleSummaries;
    }
}

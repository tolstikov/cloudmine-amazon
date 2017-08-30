package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.RateBasedRule;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class RateBasedRuleResponse extends AmazonResponse {
    private RateBasedRule rateBasedRule;

    public RateBasedRule getRateBasedRule() {
        return rateBasedRule;
    }

    public void setRateBasedRule(final RateBasedRule rateBasedRule) {
        this.rateBasedRule = rateBasedRule;
    }
}

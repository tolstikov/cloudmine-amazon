package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.SizeConstraintSetSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SizeConstraintSetsResponse extends AmazonResponse {
    private List<SizeConstraintSetSummary> sizeConstraintSetSummaries;

    public List<SizeConstraintSetSummary> getSizeConstraintSetSummaries() {
        return sizeConstraintSetSummaries;
    }

    public void setSizeConstraintSetSummaries(final List<SizeConstraintSetSummary> sizeConstraintSetSummaries) {
        this.sizeConstraintSetSummaries = sizeConstraintSetSummaries;
    }
}

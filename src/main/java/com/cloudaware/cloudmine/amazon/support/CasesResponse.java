package com.cloudaware.cloudmine.amazon.support;

import com.amazonaws.services.support.model.CaseDetails;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 03:08
 */
public final class CasesResponse extends AmazonResponse {
    private List<CaseDetails> cases;

    public CasesResponse() {
    }

    public CasesResponse(final AmazonException exception) {
        super(exception);
    }

    public CasesResponse(final List<CaseDetails> cases, final String nextPage) {
        super(nextPage);
        this.cases = cases;
    }

    public List<CaseDetails> getCases() {
        return cases;
    }

    public void setCases(final List<CaseDetails> cases) {
        this.cases = cases;
    }
}

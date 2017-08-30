package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.SqlInjectionMatchSetSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SqlInjectionMatchSetsResponse extends AmazonResponse {
    private List<SqlInjectionMatchSetSummary> sqlInjectionMatchSetSummaries;

    public List<SqlInjectionMatchSetSummary> getSqlInjectionMatchSetSummaries() {
        return sqlInjectionMatchSetSummaries;
    }

    public void setSqlInjectionMatchSetSummaries(final List<SqlInjectionMatchSetSummary> sqlInjectionMatchSetSummaries) {
        this.sqlInjectionMatchSetSummaries = sqlInjectionMatchSetSummaries;
    }
}

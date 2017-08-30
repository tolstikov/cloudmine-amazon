package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.SqlInjectionMatchSet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class SqlInjectionMatchSetResponse extends AmazonResponse {
    private SqlInjectionMatchSet sqlInjectionMatchSet;

    public SqlInjectionMatchSet getSqlInjectionMatchSet() {
        return sqlInjectionMatchSet;
    }

    public void setSqlInjectionMatchSet(final SqlInjectionMatchSet sqlInjectionMatchSet) {
        this.sqlInjectionMatchSet = sqlInjectionMatchSet;
    }
}

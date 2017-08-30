package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.XssMatchSet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class XssMatchSetResponse extends AmazonResponse {
    private XssMatchSet xssMatchSet;

    public XssMatchSet getXssMatchSet() {
        return xssMatchSet;
    }

    public void setXssMatchSet(final XssMatchSet xssMatchSet) {
        this.xssMatchSet = xssMatchSet;
    }
}

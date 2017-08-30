package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.IPSet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class IpSetResponse extends AmazonResponse {
    private IPSet ipSet;

    public IPSet getIpSet() {
        return ipSet;
    }

    public void setIpSet(final IPSet ipSet) {
        this.ipSet = ipSet;
    }
}

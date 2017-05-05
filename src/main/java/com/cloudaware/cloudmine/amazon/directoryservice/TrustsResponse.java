package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.Trust;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TrustsResponse extends AmazonResponse {
    private List<Trust> trusts;

    public TrustsResponse() {
    }

    public TrustsResponse(final AmazonException excepiton) {
        super(excepiton);
    }

    public TrustsResponse(final List<Trust> trusts, final String nextPage) {
        super(nextPage);
        this.trusts = trusts;
    }

    public List<Trust> getTrusts() {
        return trusts;
    }

    public void setTrusts(final List<Trust> trusts) {
        this.trusts = trusts;
    }
}

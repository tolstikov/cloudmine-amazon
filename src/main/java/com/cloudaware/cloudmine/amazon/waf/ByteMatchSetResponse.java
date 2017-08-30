package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.ByteMatchSet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class ByteMatchSetResponse extends AmazonResponse {
    private ByteMatchSet byteMatchSet;

    public ByteMatchSet getByteMatchSet() {
        return byteMatchSet;
    }

    public void setByteMatchSet(final ByteMatchSet byteMatchSet) {
        this.byteMatchSet = byteMatchSet;
    }
}

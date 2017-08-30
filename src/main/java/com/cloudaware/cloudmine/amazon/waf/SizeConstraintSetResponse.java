package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.SizeConstraintSet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class SizeConstraintSetResponse extends AmazonResponse {
    private SizeConstraintSet sizeConstraintSet;

    public SizeConstraintSet getSizeConstraintSet() {
        return sizeConstraintSet;
    }

    public void setSizeConstraintSet(final SizeConstraintSet sizeConstraintSet) {
        this.sizeConstraintSet = sizeConstraintSet;
    }
}

package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.ConstraintDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProtfolioConstraintsResponse extends AmazonResponse {
    private List<ConstraintDetail> constraintDetails;

    public List<ConstraintDetail> getConstraintDetails() {
        return constraintDetails;
    }

    public void setConstraintDetails(final List<ConstraintDetail> constraintDetails) {
        this.constraintDetails = constraintDetails;
    }
}

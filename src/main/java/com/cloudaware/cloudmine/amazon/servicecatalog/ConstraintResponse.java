package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.ConstraintDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class ConstraintResponse extends AmazonResponse {
    private ConstraintDetail constraintDetail;
    private String constraintParameters;
    private String status;

    public ConstraintDetail getConstraintDetail() {
        return constraintDetail;
    }

    public void setConstraintDetail(final ConstraintDetail constraintDetail) {
        this.constraintDetail = constraintDetail;
    }

    public String getConstraintParameters() {
        return constraintParameters;
    }

    public void setConstraintParameters(final String constraintParameters) {
        this.constraintParameters = constraintParameters;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}

package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.ExpressionStatus;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:50
 */
public final class ExpressionsResponse extends AmazonResponse {
    private List<ExpressionStatus> expressions;

    public ExpressionsResponse() {
    }

    public ExpressionsResponse(final AmazonException exception) {
        super(exception);
    }

    public ExpressionsResponse(final List<ExpressionStatus> expressions) {
        this.expressions = expressions;
    }

    public List<ExpressionStatus> getExpressions() {
        return expressions;
    }

    public void setExpressions(final List<ExpressionStatus> expressions) {
        this.expressions = expressions;
    }
}

package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.StepSummary;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:55
 */
public final class StepsResponse extends AmazonResponse {
    private List<StepSummary> steps;

    public StepsResponse() {
    }

    public StepsResponse(final AmazonException exception) {
        super(exception);
    }

    public StepsResponse(final List<StepSummary> steps, final String nextPage) {
        super(nextPage);
        this.steps = steps;
    }

    public List<StepSummary> getSteps() {
        return steps;
    }

    public void setSteps(final List<StepSummary> steps) {
        this.steps = steps;
    }
}

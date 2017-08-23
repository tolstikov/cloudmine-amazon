package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.services.config.model.EvaluationResult;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ComplianceDetailsResponse extends AmazonResponse {

    private List<EvaluationResult> evaluationResults;

    public List<EvaluationResult> getEvaluationResults() {
        return evaluationResults;
    }

    public void setEvaluationResults(final List<EvaluationResult> evaluationResults) {
        this.evaluationResults = evaluationResults;
    }
}

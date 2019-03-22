package com.cloudaware.cloudmine.amazon.costandusagereport;

import com.amazonaws.services.costandusagereport.model.ReportDefinition;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ReportDefinitionsResponse extends AmazonResponse {
    private List<ReportDefinition> reportDefinitions;

    public List<ReportDefinition> getReportDefinitions() {
        return reportDefinitions;
    }

    public void setReportDefinitions(final List<ReportDefinition> reportDefinitions) {
        this.reportDefinitions = reportDefinitions;
    }
}

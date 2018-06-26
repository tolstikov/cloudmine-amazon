package com.cloudaware.cloudmine.amazon.iam;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

public final class GetCredentialReportResponse extends AmazonResponse {

    private String content;
    private String reportFormat;
    private Date generatedTime;

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(final String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public Date getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(final Date generatedTime) {
        this.generatedTime = generatedTime;
    }
}

package com.cloudaware.cloudmine.amazon.route53domains;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

public final class OperationResponse extends AmazonResponse {

    private String domainName;
    private String message;
    private String operationId;
    private String status;
    private Date submittedDate;
    private String type;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(final String domainName) {
        this.domainName = domainName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(final String operationId) {
        this.operationId = operationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(final Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}

package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class CustomVerificationEmailTemplateResponse extends AmazonResponse {

    private String fromEmailAddress;
    private String failureRedirectionUrl;
    private String successRedirectionUrl;
    private String templateContent;
    private String templateName;
    private String templateSubject;

    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    public void setFromEmailAddress(final String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    public String getFailureRedirectionUrl() {
        return failureRedirectionUrl;
    }

    public void setFailureRedirectionUrl(final String failureRedirectionUrl) {
        this.failureRedirectionUrl = failureRedirectionUrl;
    }

    public String getSuccessRedirectionUrl() {
        return successRedirectionUrl;
    }

    public void setSuccessRedirectionUrl(final String successRedirectionUrl) {
        this.successRedirectionUrl = successRedirectionUrl;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(final String templateContent) {
        this.templateContent = templateContent;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateSubject() {
        return templateSubject;
    }

    public void setTemplateSubject(final String templateSubject) {
        this.templateSubject = templateSubject;
    }
}

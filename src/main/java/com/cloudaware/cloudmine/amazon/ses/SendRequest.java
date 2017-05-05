package com.cloudaware.cloudmine.amazon.ses;

import java.util.List;

public final class SendRequest {

    private String source;
    private String subject;
    private List<String> toAddresses;
    private List<String> ccAddresses;
    private String body;
    private String bodyMediaType;
    private String zipFileName;
    private List<EmailAttachment> attachments;

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getBodyMediaType() {
        return bodyMediaType;
    }

    public void setBodyMediaType(final String bodyMediaType) {
        this.bodyMediaType = bodyMediaType;
    }

    public List<EmailAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(final List<EmailAttachment> attachments) {
        this.attachments = attachments;
    }

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public List<String> getToAddresses() {
        return toAddresses;
    }

    public void setToAddresses(final List<String> toAddresses) {
        this.toAddresses = toAddresses;
    }

    public List<String> getCcAddresses() {
        return ccAddresses;
    }

    public void setCcAddresses(final List<String> ccAddresses) {
        this.ccAddresses = ccAddresses;
    }

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(final String zipFileName) {
        this.zipFileName = zipFileName;
    }
}

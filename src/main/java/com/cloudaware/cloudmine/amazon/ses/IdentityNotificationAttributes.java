package com.cloudaware.cloudmine.amazon.ses;

public final class IdentityNotificationAttributes {

    private String identityName;
    private String bounceTopic;
    private String complaintTopic;
    private String deliveryTopic;
    private Boolean forwardingEnabled;
    private Boolean headersInBounceNotificationsEnabled;
    private Boolean headersInComplaintNotificationsEnabled;
    private Boolean headersInDeliveryNotificationsEnabled;

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(final String identityName) {
        this.identityName = identityName;
    }

    public String getBounceTopic() {
        return bounceTopic;
    }

    public void setBounceTopic(final String bounceTopic) {
        this.bounceTopic = bounceTopic;
    }

    public String getComplaintTopic() {
        return complaintTopic;
    }

    public void setComplaintTopic(final String complaintTopic) {
        this.complaintTopic = complaintTopic;
    }

    public String getDeliveryTopic() {
        return deliveryTopic;
    }

    public void setDeliveryTopic(final String deliveryTopic) {
        this.deliveryTopic = deliveryTopic;
    }

    public Boolean getForwardingEnabled() {
        return forwardingEnabled;
    }

    public void setForwardingEnabled(final Boolean forwardingEnabled) {
        this.forwardingEnabled = forwardingEnabled;
    }

    public Boolean getHeadersInBounceNotificationsEnabled() {
        return headersInBounceNotificationsEnabled;
    }

    public void setHeadersInBounceNotificationsEnabled(final Boolean headersInBounceNotificationsEnabled) {
        this.headersInBounceNotificationsEnabled = headersInBounceNotificationsEnabled;
    }

    public Boolean getHeadersInComplaintNotificationsEnabled() {
        return headersInComplaintNotificationsEnabled;
    }

    public void setHeadersInComplaintNotificationsEnabled(final Boolean headersInComplaintNotificationsEnabled) {
        this.headersInComplaintNotificationsEnabled = headersInComplaintNotificationsEnabled;
    }

    public Boolean getHeadersInDeliveryNotificationsEnabled() {
        return headersInDeliveryNotificationsEnabled;
    }

    public void setHeadersInDeliveryNotificationsEnabled(final Boolean headersInDeliveryNotificationsEnabled) {
        this.headersInDeliveryNotificationsEnabled = headersInDeliveryNotificationsEnabled;
    }
}

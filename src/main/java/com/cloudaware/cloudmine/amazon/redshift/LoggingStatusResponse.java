package com.cloudaware.cloudmine.amazon.redshift;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

public final class LoggingStatusResponse extends AmazonResponse {

    private Boolean loggingEnabled;
    private String bucketName;
    private String s3KeyPrefix;
    private Date lastSuccessfulDeliveryTime;
    private Date lastFailureTime;
    private String lastFailureMessage;

    public Boolean getLoggingEnabled() {
        return loggingEnabled;
    }

    public void setLoggingEnabled(final Boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(final String bucketName) {
        this.bucketName = bucketName;
    }

    public String getS3KeyPrefix() {
        return s3KeyPrefix;
    }

    public void setS3KeyPrefix(final String s3KeyPrefix) {
        this.s3KeyPrefix = s3KeyPrefix;
    }

    public Date getLastSuccessfulDeliveryTime() {
        return lastSuccessfulDeliveryTime;
    }

    public void setLastSuccessfulDeliveryTime(final Date lastSuccessfulDeliveryTime) {
        this.lastSuccessfulDeliveryTime = lastSuccessfulDeliveryTime;
    }

    public Date getLastFailureTime() {
        return lastFailureTime;
    }

    public void setLastFailureTime(final Date lastFailureTime) {
        this.lastFailureTime = lastFailureTime;
    }

    public String getLastFailureMessage() {
        return lastFailureMessage;
    }

    public void setLastFailureMessage(final String lastFailureMessage) {
        this.lastFailureMessage = lastFailureMessage;
    }
}

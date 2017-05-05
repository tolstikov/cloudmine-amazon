package com.cloudaware.cloudmine.amazon.s3;

/**
 * User: urmuzov
 * Date: 11/28/13
 * Time: 7:04 PM
 */
public final class CopyObjectRequest {
    private String sourceBucketName;
    private String sourceKey;
    private String destinationBucketName;
    private String destinationKey;

    public CopyObjectRequest() {
    }

    public CopyObjectRequest(final String sourceBucketName, final String sourceKey, final String destinationBucketName, final String destinationKey) {
        this.destinationBucketName = destinationBucketName;
        this.destinationKey = destinationKey;
        this.sourceBucketName = sourceBucketName;
        this.sourceKey = sourceKey;
    }

    public String getDestinationBucketName() {
        return destinationBucketName;
    }

    public void setDestinationBucketName(final String destinationBucketName) {
        this.destinationBucketName = destinationBucketName;
    }

    public String getDestinationKey() {
        return destinationKey;
    }

    public void setDestinationKey(final String destinationKey) {
        this.destinationKey = destinationKey;
    }

    public String getSourceBucketName() {
        return sourceBucketName;
    }

    public void setSourceBucketName(final String sourceBucketName) {
        this.sourceBucketName = sourceBucketName;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(final String sourceKey) {
        this.sourceKey = sourceKey;
    }
}

package com.cloudaware.cloudmine.amazon.s3;

import java.util.List;

/**
 * User: urmuzov
 * Date: 11/28/13
 * Time: 9:34 PM
 */
public final class DeleteObjectsRequest {
    private String bucketName;
    private List<String> keys;

    public DeleteObjectsRequest() {
    }

    public DeleteObjectsRequest(final String bucketName, final List<String> keys) {
        this.bucketName = bucketName;
        this.keys = keys;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(final String bucketName) {
        this.bucketName = bucketName;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(final List<String> keys) {
        this.keys = keys;
    }
}

package com.cloudaware.cloudmine.amazon.sqs;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 01:42
 */
public final class QueuesResponse extends AmazonResponse {
    private List<String> queueUrls;

    public QueuesResponse() {
    }

    public QueuesResponse(final AmazonException exception) {
        super(exception);
    }

    public QueuesResponse(final List<String> queueUrls) {
        this.queueUrls = queueUrls;
    }

    public List<String> getQueueUrls() {
        return queueUrls;
    }

    public void setQueueUrls(final List<String> queueUrls) {
        this.queueUrls = queueUrls;
    }
}

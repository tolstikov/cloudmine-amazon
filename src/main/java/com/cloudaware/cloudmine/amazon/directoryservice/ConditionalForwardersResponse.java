package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.ConditionalForwarder;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ConditionalForwardersResponse extends AmazonResponse {
    private List<ConditionalForwarder> conditionalForwarders;

    public ConditionalForwardersResponse() {
    }

    public ConditionalForwardersResponse(final AmazonException excepiton) {
        super(excepiton);
    }

    public ConditionalForwardersResponse(final List<ConditionalForwarder> conditionalForwarders) {
        this.conditionalForwarders = conditionalForwarders;
    }

    public List<ConditionalForwarder> getConditionalForwarders() {
        return conditionalForwarders;
    }

    public void setConditionalForwarders(final List<ConditionalForwarder> conditionalForwarders) {
        this.conditionalForwarders = conditionalForwarders;
    }
}

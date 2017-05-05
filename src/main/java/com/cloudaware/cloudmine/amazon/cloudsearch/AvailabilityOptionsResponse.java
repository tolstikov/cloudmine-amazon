package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.AvailabilityOptionsStatus;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:55
 */
public final class AvailabilityOptionsResponse extends AmazonResponse {
    private AvailabilityOptionsStatus availabilityOptions;

    public AvailabilityOptionsResponse() {
    }

    public AvailabilityOptionsResponse(final AmazonException exception) {
        super(exception);
    }

    public AvailabilityOptionsResponse(final AvailabilityOptionsStatus availabilityOptions) {
        this.availabilityOptions = availabilityOptions;
    }

    public AvailabilityOptionsStatus getAvailabilityOptions() {
        return availabilityOptions;
    }

    public void setAvailabilityOptions(final AvailabilityOptionsStatus availabilityOptions) {
        this.availabilityOptions = availabilityOptions;
    }
}

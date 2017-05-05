package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.ReservedDBInstance;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:25
 */
public final class ReservedDbInstancesResponse extends AmazonResponse {
    private List<ReservedDBInstance> reservedDbInstances;

    public ReservedDbInstancesResponse() {
    }

    public ReservedDbInstancesResponse(final AmazonException exception) {
        super(exception);
    }

    public ReservedDbInstancesResponse(final List<ReservedDBInstance> reservedDbInstances, final String nextPage) {
        super(nextPage);
        this.reservedDbInstances = reservedDbInstances;
    }

    public List<ReservedDBInstance> getReservedDbInstances() {
        return reservedDbInstances;
    }

    public void setReservedDbInstances(final List<ReservedDBInstance> reservedDbInstances) {
        this.reservedDbInstances = reservedDbInstances;
    }
}

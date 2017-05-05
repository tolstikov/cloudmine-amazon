package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.ReservedNode;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 20:23
 */
public final class ReservedNodesResponse extends AmazonResponse {
    private List<ReservedNode> reservedNodes;

    public ReservedNodesResponse() {
    }

    public ReservedNodesResponse(final AmazonException exception) {
        super(exception);
    }

    public ReservedNodesResponse(final List<ReservedNode> reservedNodes, final String nextPage) {
        super(nextPage);
        this.reservedNodes = reservedNodes;
    }

    public List<ReservedNode> getReservedNodes() {
        return reservedNodes;
    }

    public void setReservedNodes(final List<ReservedNode> reservedNodes) {
        this.reservedNodes = reservedNodes;
    }
}

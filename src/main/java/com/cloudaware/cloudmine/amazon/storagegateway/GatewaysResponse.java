package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.GatewayInfo;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:44
 */
public final class GatewaysResponse extends AmazonResponse {
    private List<GatewayInfo> gateways;

    public GatewaysResponse() {
    }

    public GatewaysResponse(final AmazonException exception) {
        super(exception);
    }

    public GatewaysResponse(final List<GatewayInfo> gateways, final String nextPage) {
        super(nextPage);
        this.gateways = gateways;
    }

    public List<GatewayInfo> getGateways() {
        return gateways;
    }

    public void setGateways(final List<GatewayInfo> gateways) {
        this.gateways = gateways;
    }
}

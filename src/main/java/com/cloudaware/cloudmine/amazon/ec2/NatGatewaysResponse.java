package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.NatGateway;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 13:11
 */
public final class NatGatewaysResponse extends AmazonResponse {
    private List<NatGateway> natGateways;

    public List<NatGateway> getNatGateways() {
        return natGateways;
    }

    public void setNatGateways(final List<NatGateway> natGateways) {
        this.natGateways = natGateways;
    }
}

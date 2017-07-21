package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.CustomerGateway;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 13:11
 */
public final class CustomerGatewaysResponse extends AmazonResponse {
    private List<CustomerGateway> customerGateways;

    public List<CustomerGateway> getCustomerGateways() {
        return customerGateways;
    }

    public void setCustomerGateways(final List<CustomerGateway> customerGateways) {
        this.customerGateways = customerGateways;
    }
}

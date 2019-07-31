package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.InstanceCreditSpecification;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class InstanceCreditSpecificationsResponse extends AmazonResponse {
    private List<InstanceCreditSpecification> instanceCreditSpecifications;

    public List<InstanceCreditSpecification> getInstanceCreditSpecifications() {
        return instanceCreditSpecifications;
    }

    public void setInstanceCreditSpecifications(final List<InstanceCreditSpecification> instanceCreditSpecifications) {
        this.instanceCreditSpecifications = instanceCreditSpecifications;
    }
}

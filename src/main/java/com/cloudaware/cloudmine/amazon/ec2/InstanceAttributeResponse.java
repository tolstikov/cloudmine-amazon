package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.InstanceAttribute;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 17:30
 */
public final class InstanceAttributeResponse extends AmazonResponse {

    private InstanceAttribute instanceAttribute;

    public InstanceAttributeResponse() {
    }

    public InstanceAttributeResponse(final AmazonException exception) {
        super(exception);
    }

    public InstanceAttributeResponse(final InstanceAttribute instanceAttribute) {
        this.instanceAttribute = instanceAttribute;
    }

    public InstanceAttribute getInstanceAttribute() {
        return instanceAttribute;
    }

    public void setInstanceAttribute(final InstanceAttribute instanceAttribute) {
        this.instanceAttribute = instanceAttribute;
    }
}

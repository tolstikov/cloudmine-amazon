package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.GlobalTableDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class GlobalTableDescriptionResponse extends AmazonResponse {

    private GlobalTableDescription globalTableDescription;

    public GlobalTableDescription getGlobalTableDescription() {
        return globalTableDescription;
    }

    public void setGlobalTableDescription(final GlobalTableDescription globalTableDescription) {
        this.globalTableDescription = globalTableDescription;
    }
}

package com.cloudaware.cloudmine.amazon.iot;

import com.amazonaws.services.iot.model.ThingTypeDefinition;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ThingTypesResponse extends AmazonResponse {

    private List<ThingTypeDefinition> thingTypes;

    public void setThingTypes(final List<ThingTypeDefinition> thingTypes) {
        this.thingTypes = thingTypes;
    }

    public List<ThingTypeDefinition> getThingTypes() {
        return thingTypes;
    }
}

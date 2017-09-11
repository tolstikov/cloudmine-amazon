package com.cloudaware.cloudmine.amazon.iot;

import com.amazonaws.services.iot.model.ThingAttribute;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ThingsResponse extends AmazonResponse {

    private List<ThingAttribute> things;

    public void setThings(final List<ThingAttribute> things) {
        this.things = things;
    }

    public List<ThingAttribute> getThings() {
        return things;
    }
}

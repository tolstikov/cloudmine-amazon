package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.Layer;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class LayersResponse extends AmazonResponse {

    private List<Layer> layers;

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(final List<Layer> layers) {
        this.layers = layers;
    }
}

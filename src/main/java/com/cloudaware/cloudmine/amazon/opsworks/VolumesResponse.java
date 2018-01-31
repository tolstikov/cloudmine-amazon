package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.Volume;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VolumesResponse extends AmazonResponse {

    private List<Volume> volumes;

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(final List<Volume> volumes) {
        this.volumes = volumes;
    }
}

package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.VolumeInfo;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:47
 */
public final class VolumesResponse extends AmazonResponse {
    private List<VolumeInfo> volumes;

    public VolumesResponse() {
    }

    public VolumesResponse(final AmazonException exception) {
        super(exception);
    }

    public VolumesResponse(final List<VolumeInfo> volumes, final String nextPage) {
        super(nextPage);
        this.volumes = volumes;
    }

    public List<VolumeInfo> getVolumes() {
        return volumes;
    }

    public void setVolumes(final List<VolumeInfo> volumes) {
        this.volumes = volumes;
    }
}

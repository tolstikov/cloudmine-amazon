package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.StorediSCSIVolume;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:49
 */
public final class StoredVolumesResponse extends AmazonResponse {
    private List<StorediSCSIVolume> storedVolumes;

    public StoredVolumesResponse() {
    }

    public StoredVolumesResponse(final AmazonException exception) {
        super(exception);
    }

    public StoredVolumesResponse(final List<StorediSCSIVolume> storedVolumes) {
        this.storedVolumes = storedVolumes;
    }

    public List<StorediSCSIVolume> getStoredVolumes() {
        return storedVolumes;
    }

    public void setStoredVolumes(final List<StorediSCSIVolume> storedVolumes) {
        this.storedVolumes = storedVolumes;
    }
}

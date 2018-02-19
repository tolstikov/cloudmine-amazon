package com.cloudaware.cloudmine.amazon.guardduty;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DetectorIdsResponse extends AmazonResponse {

    private List<String> detectorIds;

    public List<String> getDetectorIds() {
        return detectorIds;
    }

    public void setDetectorIds(final List<String> detectorIds) {
        this.detectorIds = detectorIds;
    }
}

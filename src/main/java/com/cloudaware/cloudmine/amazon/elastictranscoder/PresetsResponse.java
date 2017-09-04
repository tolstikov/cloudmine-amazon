package com.cloudaware.cloudmine.amazon.elastictranscoder;

import com.amazonaws.services.elastictranscoder.model.Preset;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PresetsResponse extends AmazonResponse {
    private List<Preset> presets;

    public List<Preset> getPresets() {
        return presets;
    }

    public void setPresets(final List<Preset> presets) {
        this.presets = presets;
    }
}

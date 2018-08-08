package com.cloudaware.cloudmine.amazon.appstream;

import com.amazonaws.services.appstream.model.DirectoryConfig;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DirectoryConfigsResponse extends AmazonResponse {

    private List<DirectoryConfig> directoryConfigs;

    public List<DirectoryConfig> getDirectoryConfigs() {
        return directoryConfigs;
    }

    public void setDirectoryConfigs(final List<DirectoryConfig> directoryConfigs) {
        this.directoryConfigs = directoryConfigs;
    }
}

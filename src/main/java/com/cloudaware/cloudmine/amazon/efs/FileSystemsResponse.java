package com.cloudaware.cloudmine.amazon.efs;

import com.amazonaws.services.elasticfilesystem.model.FileSystemDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class FileSystemsResponse extends AmazonResponse {

    private List<FileSystemDescription> fileSystems;

    public List<FileSystemDescription> getFileSystems() {
        return fileSystems;
    }

    public void setFileSystems(final List<FileSystemDescription> fileSystems) {
        this.fileSystems = fileSystems;
    }
}

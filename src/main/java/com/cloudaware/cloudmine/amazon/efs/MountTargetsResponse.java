package com.cloudaware.cloudmine.amazon.efs;

import com.amazonaws.services.elasticfilesystem.model.MountTargetDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class MountTargetsResponse extends AmazonResponse {

    private List<MountTargetDescription> mountTargets;

    public List<MountTargetDescription> getMountTargets() {
        return mountTargets;
    }

    public void setMountTargets(final List<MountTargetDescription> mountTargets) {
        this.mountTargets = mountTargets;
    }
}

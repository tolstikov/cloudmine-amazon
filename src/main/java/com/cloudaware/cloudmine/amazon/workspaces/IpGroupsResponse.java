package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.services.workspaces.model.WorkspacesIpGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class IpGroupsResponse extends AmazonResponse {

    private List<WorkspacesIpGroup> ipGroups;

    public List<WorkspacesIpGroup> getIpGroups() {
        return ipGroups;
    }

    public void setIpGroups(final List<WorkspacesIpGroup> ipGroups) {
        this.ipGroups = ipGroups;
    }
}

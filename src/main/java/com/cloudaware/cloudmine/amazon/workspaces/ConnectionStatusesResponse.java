package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.services.workspaces.model.WorkspaceConnectionStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ConnectionStatusesResponse extends AmazonResponse {

    private List<WorkspaceConnectionStatus> connectionStatuses;

    public List<WorkspaceConnectionStatus> getConnectionStatuses() {
        return connectionStatuses;
    }

    public void setConnectionStatuses(final List<WorkspaceConnectionStatus> connectionStatuses) {
        this.connectionStatuses = connectionStatuses;
    }
}

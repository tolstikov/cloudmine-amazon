package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.services.workspaces.model.WorkspaceBundle;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 03:46
 */
public final class WorkspaceBundlesResponse extends AmazonResponse {
    private List<WorkspaceBundle> workspaceBundles;

    public WorkspaceBundlesResponse() {
    }

    public WorkspaceBundlesResponse(final AmazonException exception) {
        super(exception);
    }

    public WorkspaceBundlesResponse(final List<WorkspaceBundle> workspaceBundles, final String nextPage) {
        super(nextPage);
        this.workspaceBundles = workspaceBundles;
    }

    public List<WorkspaceBundle> getWorkspaceBundles() {
        return workspaceBundles;
    }

    public void setWorkspaceBundles(final List<WorkspaceBundle> workspaceBundles) {
        this.workspaceBundles = workspaceBundles;
    }
}

package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.Command;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:36
 */
public final class BootstrapActionsResponse extends AmazonResponse {
    private List<Command> bootstrapActions;

    public BootstrapActionsResponse() {
    }

    public BootstrapActionsResponse(final AmazonException exception) {
        super(exception);
    }

    public BootstrapActionsResponse(final List<Command> bootstrapActions, final String nextPage) {
        super(nextPage);
        this.bootstrapActions = bootstrapActions;
    }

    public List<Command> getBootstrapActions() {
        return bootstrapActions;
    }

    public void setBootstrapActions(final List<Command> bootstrapActions) {
        this.bootstrapActions = bootstrapActions;
    }
}

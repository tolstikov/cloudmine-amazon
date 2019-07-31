package com.cloudaware.cloudmine.amazon.mq;

import com.amazonaws.services.mq.model.ConfigurationRevision;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ConfigurationRevisionsResponse extends AmazonResponse {

    private List<ConfigurationRevision> revisions;

    public List<ConfigurationRevision> getRevisions() {
        return revisions;
    }

    public void setRevisions(final List<ConfigurationRevision> revisions) {
        this.revisions = revisions;
    }
}

package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.OptionGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class OptionGroupsResponse extends AmazonResponse {

    private List<OptionGroup> groups;

    public List<OptionGroup> getGroups() {
        return groups;
    }

    public void setGroups(final List<OptionGroup> groups) {
        this.groups = groups;
    }
}

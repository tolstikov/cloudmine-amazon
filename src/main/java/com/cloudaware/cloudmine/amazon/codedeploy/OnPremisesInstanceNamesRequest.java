package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.TagFilter;

import java.util.List;

public final class OnPremisesInstanceNamesRequest {

    private String registrationStatus;
    private List<TagFilter> tagFilters;

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(final String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public List<TagFilter> getTagFilters() {
        return tagFilters;
    }

    public void setTagFilters(final List<TagFilter> tagFilters) {
        this.tagFilters = tagFilters;
    }
}

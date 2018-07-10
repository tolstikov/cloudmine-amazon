package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.PlatformFilter;

import java.util.List;

public final class PlatformSummariesRequest {
    private List<PlatformFilter> filters;

    public List<PlatformFilter> getFilters() {
        return filters;
    }

    public void setFilters(final List<PlatformFilter> filters) {
        this.filters = filters;
    }
}

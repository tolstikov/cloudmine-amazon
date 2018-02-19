package com.cloudaware.cloudmine.amazon.guardduty;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class FindingIdsResponse extends AmazonResponse {

    private List<String> findingIds;

    public List<String> getFindingIds() {
        return findingIds;
    }

    public void setFindingIds(final List<String> findingIds) {
        this.findingIds = findingIds;
    }
}

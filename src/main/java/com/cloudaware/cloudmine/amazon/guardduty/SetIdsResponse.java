package com.cloudaware.cloudmine.amazon.guardduty;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SetIdsResponse extends AmazonResponse {

    private List<String> setIds;

    public List<String> getSetIds() {
        return setIds;
    }

    public void setSetIds(final List<String> setIds) {
        this.setIds = setIds;
    }
}

package com.cloudaware.cloudmine.amazon.iam;

import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.google.common.collect.Maps;

import java.util.Map;

public final class GetAccountSummaryResponse extends AmazonResponse {

    private Map<String, Integer> summaryMap = Maps.newHashMap();

    public Map<String, Integer> getSummaryMap() {
        return summaryMap;
    }

    public void setSummaryMap(final Map<String, Integer> summaryMap) {
        this.summaryMap = summaryMap;
    }

}

package com.cloudaware.cloudmine.amazon.guardduty;

import com.amazonaws.services.guardduty.model.FindingStatistics;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class FindingsStatisticsResponse extends AmazonResponse {

    private FindingStatistics findingStatistics;

    public FindingStatistics getFindingStatistics() {
        return findingStatistics;
    }

    public void setFindingStatistics(final FindingStatistics findingStatistics) {
        this.findingStatistics = findingStatistics;
    }
}

package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.amazonaws.services.stepfunctions.model.HistoryEvent;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ExecutionHistoryResponse extends AmazonResponse {

    private List<HistoryEvent> historyEvents;

    public List<HistoryEvent> getHistoryEvents() {
        return historyEvents;
    }

    public void setHistoryEvents(final List<HistoryEvent> historyEvents) {
        this.historyEvents = historyEvents;
    }
}

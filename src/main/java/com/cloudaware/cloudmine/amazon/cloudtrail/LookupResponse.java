package com.cloudaware.cloudmine.amazon.cloudtrail;

import com.amazonaws.services.cloudtrail.model.Event;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class LookupResponse extends AmazonResponse {
    private List<Event> events;

    public LookupResponse() {
    }

    public LookupResponse(final AmazonException exception) {
        super(exception);
    }

    public LookupResponse(final List<Event> events, final String nextPage) {
        super(nextPage);
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(final List<Event> events) {
        this.events = events;
    }

}

package com.cloudaware.cloudmine.amazon.dynamodbaccelerator;

import com.amazonaws.services.dax.model.Event;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class EventsResponse extends AmazonResponse {
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(final List<Event> events) {
        this.events = events;
    }
}

package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.EventDescription;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:42
 */
public final class EventsResponse extends AmazonResponse {
    private List<EventDescription> events;

    public EventsResponse() {
    }

    public EventsResponse(final AmazonException exception) {
        super(exception);
    }

    public EventsResponse(final List<EventDescription> events, final String nextPage) {
        super(nextPage);
        this.events = events;
    }

    public List<EventDescription> getEvents() {
        return events;
    }

    public void setEvents(final List<EventDescription> events) {
        this.events = events;
    }
}

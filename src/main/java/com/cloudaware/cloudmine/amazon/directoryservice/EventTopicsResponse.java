package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.EventTopic;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class EventTopicsResponse extends AmazonResponse {
    private List<EventTopic> eventTopics;

    public EventTopicsResponse() {
    }

    public EventTopicsResponse(final AmazonException excepiton) {
        super(excepiton);
    }

    public EventTopicsResponse(final List<EventTopic> eventTopics) {
        this.eventTopics = eventTopics;
    }

    public List<EventTopic> getEventTopics() {
        return eventTopics;
    }

    public void setEventTopics(final List<EventTopic> eventTopics) {
        this.eventTopics = eventTopics;
    }
}

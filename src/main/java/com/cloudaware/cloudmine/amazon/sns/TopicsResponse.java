package com.cloudaware.cloudmine.amazon.sns;

import com.amazonaws.services.sns.model.Topic;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 01:16
 */
public final class TopicsResponse extends AmazonResponse {
    private List<Topic> topics;

    public TopicsResponse() {
    }

    public TopicsResponse(final AmazonException exception) {
        super(exception);
    }

    public TopicsResponse(final List<Topic> topics, final String nextPage) {
        super(nextPage);
        this.topics = topics;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(final List<Topic> topics) {
        this.topics = topics;
    }
}

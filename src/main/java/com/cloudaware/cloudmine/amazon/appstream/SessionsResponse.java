package com.cloudaware.cloudmine.amazon.appstream;

import com.amazonaws.services.appstream.model.Session;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SessionsResponse extends AmazonResponse {
    private List<Session> sessions;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(final List<Session> sessions) {
        this.sessions = sessions;
    }
}

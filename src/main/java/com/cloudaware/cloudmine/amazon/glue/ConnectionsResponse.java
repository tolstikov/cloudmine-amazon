package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.Connection;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ConnectionsResponse extends AmazonResponse {

    private List<Connection> connections;

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(final List<Connection> connections) {
        this.connections = connections;
    }
}

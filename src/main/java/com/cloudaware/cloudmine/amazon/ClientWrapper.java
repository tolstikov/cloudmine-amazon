package com.cloudaware.cloudmine.amazon;

import com.amazonaws.AmazonWebServiceClient;

public final class ClientWrapper<T> implements AutoCloseable {

    private final T client;

    public ClientWrapper(final T client) {
        this.client = client;
    }

    public T getClient() {
        return client;
    }

    @Override
    public void close() throws Exception {
        ((AmazonWebServiceClient) client).shutdown();
    }
}

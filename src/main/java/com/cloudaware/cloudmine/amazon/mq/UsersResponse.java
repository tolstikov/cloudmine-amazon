package com.cloudaware.cloudmine.amazon.mq;

import com.amazonaws.services.mq.model.UserSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class UsersResponse extends AmazonResponse {

    private List<UserSummary> users;

    public List<UserSummary> getUsers() {
        return users;
    }

    public void setUsers(final List<UserSummary> users) {
        this.users = users;
    }
}

package com.cloudaware.cloudmine.amazon.appstream;

import com.amazonaws.services.appstream.model.Stack;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StacksResponse extends AmazonResponse {

    private List<Stack> stacks;

    public List<Stack> getStacks() {
        return stacks;
    }

    public void setStacks(final List<Stack> stacks) {
        this.stacks = stacks;
    }
}

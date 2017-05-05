package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.Stack;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 15:48
 */
public final class StacksResponse extends AmazonResponse {
    private List<Stack> stacks;

    public StacksResponse() {
    }

    public StacksResponse(final AmazonException exception) {
        super(exception);
    }

    public StacksResponse(final List<Stack> stacks, final String nextPage) {
        super(nextPage);
        this.stacks = stacks;
    }

    public List<Stack> getStacks() {
        return stacks;
    }

    public void setStacks(final List<Stack> stacks) {
        this.stacks = stacks;
    }
}

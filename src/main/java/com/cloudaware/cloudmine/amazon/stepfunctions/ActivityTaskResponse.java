package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class ActivityTaskResponse extends AmazonResponse {

    private String input;
    private String taskToken;

    public String getInput() {
        return input;
    }

    public void setInput(final String input) {
        this.input = input;
    }

    public String getTaskToken() {
        return taskToken;
    }

    public void setTaskToken(final String taskToken) {
        this.taskToken = taskToken;
    }
}

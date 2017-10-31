package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

public final class ExecutionResponse extends AmazonResponse {

    private String executionArn;
    private String input;
    private String name;
    private String output;
    private Date startDate;
    private String stateMachineArn;
    private String status;
    private Date stopDate;

    public String getExecutionArn() {
        return executionArn;
    }

    public void setExecutionArn(final String executionArn) {
        this.executionArn = executionArn;
    }

    public String getInput() {
        return input;
    }

    public void setInput(final String input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(final String output) {
        this.output = output;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public String getStateMachineArn() {
        return stateMachineArn;
    }

    public void setStateMachineArn(final String stateMachineArn) {
        this.stateMachineArn = stateMachineArn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(final Date stopDate) {
        this.stopDate = stopDate;
    }
}

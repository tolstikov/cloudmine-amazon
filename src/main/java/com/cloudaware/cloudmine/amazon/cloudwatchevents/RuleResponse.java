package com.cloudaware.cloudmine.amazon.cloudwatchevents;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class RuleResponse extends AmazonResponse {
    private String name;
    private String arn;
    private String eventPattern;
    private String scheduleExpression;
    private String state;
    private String description;
    private String roleArn;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getArn() {
        return arn;
    }

    public void setArn(final String arn) {
        this.arn = arn;
    }

    public String getEventPattern() {
        return eventPattern;
    }

    public void setEventPattern(final String eventPattern) {
        this.eventPattern = eventPattern;
    }

    public String getScheduleExpression() {
        return scheduleExpression;
    }

    public void setScheduleExpression(final String scheduleExpression) {
        this.scheduleExpression = scheduleExpression;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(final String roleArn) {
        this.roleArn = roleArn;
    }

}

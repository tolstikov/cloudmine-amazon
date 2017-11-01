package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

public final class StateMachineResponse extends AmazonResponse {

    private Date creationDate;
    private String definition;
    private String name;
    private String roleArn;
    private String stateMachineArn;
    private String status;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(final String definition) {
        this.definition = definition;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(final String roleArn) {
        this.roleArn = roleArn;
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
}

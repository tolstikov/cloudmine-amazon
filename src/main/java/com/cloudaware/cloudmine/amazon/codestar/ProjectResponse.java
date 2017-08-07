package com.cloudaware.cloudmine.amazon.codestar;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

public final class ProjectResponse extends AmazonResponse {

    private String arn;
    private String clientRequestToken;
    private Date createdTimeStamp;
    private String description;
    private String id;
    private String name;
    private String projectTemplateId;
    private String stackId;

    public String getArn() {
        return arn;
    }

    public void setArn(final String arn) {
        this.arn = arn;
    }

    public String getClientRequestToken() {
        return clientRequestToken;
    }

    public void setClientRequestToken(final String clientRequestToken) {
        this.clientRequestToken = clientRequestToken;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(final Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getProjectTemplateId() {
        return projectTemplateId;
    }

    public void setProjectTemplateId(final String projectTemplateId) {
        this.projectTemplateId = projectTemplateId;
    }

    public String getStackId() {
        return stackId;
    }

    public void setStackId(final String stackId) {
        this.stackId = stackId;
    }
}

package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

public final class ActivityResponse extends AmazonResponse {

    private String activityArn;
    private Date creationDate;
    private String name;

    public String getActivityArn() {
        return activityArn;
    }

    public void setActivityArn(final String activityArn) {
        this.activityArn = activityArn;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

package com.cloudaware.cloudmine.amazon.guardduty;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class SetResponse extends AmazonResponse {

    private String format;
    private String location;
    private String name;
    private String status;

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}

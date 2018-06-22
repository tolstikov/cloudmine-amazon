package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.App;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class AppsResponse extends AmazonResponse {

    private List<App> apps;

    public List<App> getApps() {
        return apps;
    }

    public void setApps(final List<App> apps) {
        this.apps = apps;
    }
}

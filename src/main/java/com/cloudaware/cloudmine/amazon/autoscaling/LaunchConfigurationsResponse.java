package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.model.LaunchConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 13:36
 */
public final class LaunchConfigurationsResponse extends AmazonResponse {
    private List<LaunchConfiguration> launchConfigurations;

    public LaunchConfigurationsResponse() {
    }

    public LaunchConfigurationsResponse(final AmazonException exception) {
        super(exception);
    }

    public LaunchConfigurationsResponse(final List<LaunchConfiguration> launchConfigurations, final String nextPage) {
        super(nextPage);
        this.launchConfigurations = launchConfigurations;
    }

    public List<LaunchConfiguration> getLaunchConfigurations() {
        return launchConfigurations;
    }

    public void setLaunchConfigurations(final List<LaunchConfiguration> launchConfigurations) {
        this.launchConfigurations = launchConfigurations;
    }
}

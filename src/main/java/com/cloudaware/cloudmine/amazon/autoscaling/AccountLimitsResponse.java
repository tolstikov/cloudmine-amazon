package com.cloudaware.cloudmine.amazon.autoscaling;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class AccountLimitsResponse extends AmazonResponse {

    private Integer maxNumberOfAutoScalingGroups;
    private Integer numberOfAutoScalingGroups;
    private Integer maxNumberOfLaunchConfigurations;
    private Integer numberOfLaunchConfigurations;

    public Integer getMaxNumberOfAutoScalingGroups() {
        return maxNumberOfAutoScalingGroups;
    }

    public void setMaxNumberOfAutoScalingGroups(final Integer maxNumberOfAutoScalingGroups) {
        this.maxNumberOfAutoScalingGroups = maxNumberOfAutoScalingGroups;
    }

    public Integer getNumberOfAutoScalingGroups() {
        return numberOfAutoScalingGroups;
    }

    public void setNumberOfAutoScalingGroups(final Integer numberOfAutoScalingGroups) {
        this.numberOfAutoScalingGroups = numberOfAutoScalingGroups;
    }

    public Integer getMaxNumberOfLaunchConfigurations() {
        return maxNumberOfLaunchConfigurations;
    }

    public void setMaxNumberOfLaunchConfigurations(final Integer maxNumberOfLaunchConfigurations) {
        this.maxNumberOfLaunchConfigurations = maxNumberOfLaunchConfigurations;
    }

    public Integer getNumberOfLaunchConfigurations() {
        return numberOfLaunchConfigurations;
    }

    public void setNumberOfLaunchConfigurations(final Integer numberOfLaunchConfigurations) {
        this.numberOfLaunchConfigurations = numberOfLaunchConfigurations;
    }
}

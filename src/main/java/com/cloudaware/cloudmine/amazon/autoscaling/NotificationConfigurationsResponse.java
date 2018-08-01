package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.model.NotificationConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class NotificationConfigurationsResponse extends AmazonResponse {

    private List<NotificationConfiguration> notificationConfigurations;

    public List<NotificationConfiguration> getNotificationConfigurations() {
        return notificationConfigurations;
    }

    public void setNotificationConfigurations(final List<NotificationConfiguration> notificationConfigurations) {
        this.notificationConfigurations = notificationConfigurations;
    }
}

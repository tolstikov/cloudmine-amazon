package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.ConfigurationSettingsDescription;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:34
 */
public final class ConfigurationSettingsResponse extends AmazonResponse {
    private List<ConfigurationSettingsDescription> configurationSettings;

    public ConfigurationSettingsResponse() {
    }

    public ConfigurationSettingsResponse(final AmazonException exception) {
        super(exception);
    }

    public ConfigurationSettingsResponse(final List<ConfigurationSettingsDescription> configurationSettings) {
        this.configurationSettings = configurationSettings;
    }

    public List<ConfigurationSettingsDescription> getConfigurationSettings() {
        return configurationSettings;
    }

    public void setConfigurationSettings(final List<ConfigurationSettingsDescription> configurationSettings) {
        this.configurationSettings = configurationSettings;
    }
}

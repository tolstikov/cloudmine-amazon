package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class LifecycleConfigurationResponse extends AmazonResponse {

    private boolean enabled;
    private List<BucketLifecycleConfiguration.Rule> rules;

    LifecycleConfigurationResponse(final AmazonException exception) {
        super(exception);
    }

    LifecycleConfigurationResponse(final List<BucketLifecycleConfiguration.Rule> rules) {
        enabled = true;
        this.rules = rules;
    }

    LifecycleConfigurationResponse() {
    }

    public List<BucketLifecycleConfiguration.Rule> getRules() {
        return rules;
    }

    public void setRules(final List<BucketLifecycleConfiguration.Rule> rules) {
        this.rules = rules;
    }

    public boolean isEnabled() {
        return enabled;
    }
}

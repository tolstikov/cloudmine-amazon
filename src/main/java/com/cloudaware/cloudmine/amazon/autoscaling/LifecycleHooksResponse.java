package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.model.LifecycleHook;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class LifecycleHooksResponse extends AmazonResponse {

    private List<LifecycleHook> lifecycleHooks;

    public List<LifecycleHook> getLifecycleHooks() {
        return lifecycleHooks;
    }

    public void setLifecycleHooks(final List<LifecycleHook> lifecycleHooks) {
        this.lifecycleHooks = lifecycleHooks;
    }
}

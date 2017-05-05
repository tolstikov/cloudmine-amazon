package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.Application;
import com.amazonaws.services.elasticmapreduce.model.BootstrapActionConfig;
import com.amazonaws.services.elasticmapreduce.model.Configuration;
import com.amazonaws.services.elasticmapreduce.model.JobFlowInstancesConfig;
import com.amazonaws.services.elasticmapreduce.model.StepConfig;
import com.amazonaws.services.elasticmapreduce.model.SupportedProductConfig;
import com.amazonaws.services.elasticmapreduce.model.Tag;

import java.util.List;

public final class JobFlowRunRequest {
    private String name;
    private String logUri;
    private String additionalInfo;
    private String amiVersion;
    private String releaseLabel;
    private JobFlowInstancesConfig instances;
    private List<StepConfig> steps;
    private List<BootstrapActionConfig> bootstrapActions;
    private List<String> supportedProducts;
    private List<SupportedProductConfig> newSupportedProducts;
    private List<Application> applications;
    private List<Configuration> configurations;
    private Boolean visibleToAllUsers;
    private String jobFlowRole;
    private String serviceRole;
    private List<Tag> tags;
    private String securityConfiguration;
    private String autoScalingRole;
    private String scaleDownBehavior;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLogUri() {
        return logUri;
    }

    public void setLogUri(final String logUri) {
        this.logUri = logUri;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(final String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getAmiVersion() {
        return amiVersion;
    }

    public void setAmiVersion(final String amiVersion) {
        this.amiVersion = amiVersion;
    }

    public String getReleaseLabel() {
        return releaseLabel;
    }

    public void setReleaseLabel(final String releaseLabel) {
        this.releaseLabel = releaseLabel;
    }

    public JobFlowInstancesConfig getInstances() {
        return instances;
    }

    public void setInstances(final JobFlowInstancesConfig instances) {
        this.instances = instances;
    }

    public List<StepConfig> getSteps() {
        return steps;
    }

    public void setSteps(final List<StepConfig> steps) {
        this.steps = steps;
    }

    public List<BootstrapActionConfig> getBootstrapActions() {
        return bootstrapActions;
    }

    public void setBootstrapActions(final List<BootstrapActionConfig> bootstrapActions) {
        this.bootstrapActions = bootstrapActions;
    }

    public List<String> getSupportedProducts() {
        return supportedProducts;
    }

    public void setSupportedProducts(final List<String> supportedProducts) {
        this.supportedProducts = supportedProducts;
    }

    public List<SupportedProductConfig> getNewSupportedProducts() {
        return newSupportedProducts;
    }

    public void setNewSupportedProducts(final List<SupportedProductConfig> newSupportedProducts) {
        this.newSupportedProducts = newSupportedProducts;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(final List<Application> applications) {
        this.applications = applications;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(final List<Configuration> configurations) {
        this.configurations = configurations;
    }

    public Boolean getVisibleToAllUsers() {
        return visibleToAllUsers;
    }

    public void setVisibleToAllUsers(final Boolean visibleToAllUsers) {
        this.visibleToAllUsers = visibleToAllUsers;
    }

    public String getJobFlowRole() {
        return jobFlowRole;
    }

    public void setJobFlowRole(final String jobFlowRole) {
        this.jobFlowRole = jobFlowRole;
    }

    public String getServiceRole() {
        return serviceRole;
    }

    public void setServiceRole(final String serviceRole) {
        this.serviceRole = serviceRole;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public String getSecurityConfiguration() {
        return securityConfiguration;
    }

    public void setSecurityConfiguration(final String securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    public String getAutoScalingRole() {
        return autoScalingRole;
    }

    public void setAutoScalingRole(final String autoScalingRole) {
        this.autoScalingRole = autoScalingRole;
    }

    public String getScaleDownBehavior() {
        return scaleDownBehavior;
    }

    public void setScaleDownBehavior(final String scaleDownBehavior) {
        this.scaleDownBehavior = scaleDownBehavior;
    }
}

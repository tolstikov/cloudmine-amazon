package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.OnFailure;
import com.amazonaws.services.cloudformation.model.Parameter;

import java.util.List;
import java.util.Map;

/**
 * User: urmuzov
 * Date: 1/18/13
 * Time: 7:24 PM
 */
public final class StackRequest {

    private String stackName;
    private List<Parameter> parameters;
    private Boolean disableRollback;
    private Integer timeoutInMinutes;
    private List<String> notificationArns;
    private List<String> capabilities;
    private Map<String, String> tags;
    private OnFailure onFailure;
    private String templateBody;
    private String templateUrl;

    public String getStackName() {
        return stackName;
    }

    public void setStackName(final String stackName) {
        this.stackName = stackName;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(final List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Boolean getDisableRollback() {
        return disableRollback;
    }

    public void setDisableRollback(final Boolean disableRollback) {
        this.disableRollback = disableRollback;
    }

    public Integer getTimeoutInMinutes() {
        return timeoutInMinutes;
    }

    public void setTimeoutInMinutes(final Integer timeoutInMinutes) {
        this.timeoutInMinutes = timeoutInMinutes;
    }

    public List<String> getNotificationArns() {
        return notificationArns;
    }

    public void setNotificationArns(final List<String> notificationArns) {
        this.notificationArns = notificationArns;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(final List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }

    public OnFailure getOnFailure() {
        return onFailure;
    }

    public void setOnFailure(final OnFailure onFailure) {
        this.onFailure = onFailure;
    }

    public String getTemplateBody() {
        return templateBody;
    }

    public void setTemplateBody(final String templateBody) {
        this.templateBody = templateBody;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(final String templateUrl) {
        this.templateUrl = templateUrl;
    }
}

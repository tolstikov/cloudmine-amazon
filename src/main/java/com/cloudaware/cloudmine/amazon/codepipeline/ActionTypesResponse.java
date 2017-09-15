package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.ActionType;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ActionTypesResponse extends AmazonResponse {

    private List<ActionType> actionTypes;

    public List<ActionType> getActionTypes() {
        return actionTypes;
    }

    public void setActionTypes(final List<ActionType> actionTypes) {
        this.actionTypes = actionTypes;
    }
}

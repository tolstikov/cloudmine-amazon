package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.amazonaws.services.stepfunctions.model.StateMachineListItem;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StateMachinesResponse extends AmazonResponse {

    private List<StateMachineListItem> stateMachines;

    public List<StateMachineListItem> getStateMachines() {
        return stateMachines;
    }

    public void setStateMachines(final List<StateMachineListItem> stateMachines) {
        this.stateMachines = stateMachines;
    }
}

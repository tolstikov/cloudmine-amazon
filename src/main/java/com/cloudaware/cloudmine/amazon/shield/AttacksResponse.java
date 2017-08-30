package com.cloudaware.cloudmine.amazon.shield;

import com.amazonaws.services.shield.model.AttackSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class AttacksResponse extends AmazonResponse {
    private List<AttackSummary> attackSummaries;

    public List<AttackSummary> getAttackSummaries() {
        return attackSummaries;
    }

    public void setAttackSummaries(final List<AttackSummary> attackSummaries) {
        this.attackSummaries = attackSummaries;
    }
}

package com.cloudaware.cloudmine.amazon.shield;

import com.amazonaws.services.shield.model.AttackDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class AttackResponse extends AmazonResponse {
    private AttackDetail attackDetail;

    public AttackDetail getAttackDetail() {
        return attackDetail;
    }

    public void setAttackDetail(final AttackDetail attackDetail) {
        this.attackDetail = attackDetail;
    }
}

package com.cloudaware.cloudmine.amazon.guardduty;

import com.amazonaws.services.guardduty.model.Invitation;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class InvitationsResponse extends AmazonResponse {

    private List<Invitation> invitations;

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(final List<Invitation> invitations) {
        this.invitations = invitations;
    }
}

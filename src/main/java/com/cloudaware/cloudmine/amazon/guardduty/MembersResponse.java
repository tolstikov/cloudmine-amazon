package com.cloudaware.cloudmine.amazon.guardduty;

import com.amazonaws.services.guardduty.model.Member;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class MembersResponse extends AmazonResponse {

    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(final List<Member> members) {
        this.members = members;
    }
}

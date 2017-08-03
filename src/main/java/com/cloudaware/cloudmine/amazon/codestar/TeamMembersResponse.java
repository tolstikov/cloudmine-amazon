package com.cloudaware.cloudmine.amazon.codestar;

import com.amazonaws.services.codestar.model.TeamMember;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TeamMembersResponse extends AmazonResponse {

    private List<TeamMember> teamMembers;

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(final List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }
}

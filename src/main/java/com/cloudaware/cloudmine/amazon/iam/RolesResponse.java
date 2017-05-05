package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.Role;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 02:03
 */
public final class RolesResponse extends AmazonResponse {

    private List<Role> roles;

    public RolesResponse() {
    }

    public RolesResponse(final AmazonException exception) {
        super(exception);
    }

    public RolesResponse(final List<Role> roles, final String nextPage) {
        super(nextPage);
        this.roles = roles;
    }

    public List<Role> getRoles() {

        return roles;
    }

    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }
}

package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.NetworkAcl;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 18:24
 */
public final class NetworkAclsResponse extends AmazonResponse {
    private List<NetworkAcl> networkAcls;

    public NetworkAclsResponse() {
    }

    public NetworkAclsResponse(final AmazonException exception) {
        super(exception);
    }

    public NetworkAclsResponse(final List<NetworkAcl> networkAcls) {
        this.networkAcls = networkAcls;
    }

    public List<NetworkAcl> getNetworkAcls() {
        return networkAcls;
    }

    public void setNetworkAcls(final List<NetworkAcl> networkAcls) {
        this.networkAcls = networkAcls;
    }
}

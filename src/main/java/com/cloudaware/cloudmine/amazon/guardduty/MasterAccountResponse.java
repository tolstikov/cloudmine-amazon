package com.cloudaware.cloudmine.amazon.guardduty;

import com.amazonaws.services.guardduty.model.Master;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class MasterAccountResponse extends AmazonResponse {

    private Master masterAccount;

    public Master getMasterAccount() {
        return masterAccount;
    }

    public void setMasterAccount(final Master masterAccount) {
        this.masterAccount = masterAccount;
    }
}

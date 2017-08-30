package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.WebACL;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public class WebAclResponse extends AmazonResponse {
    private WebACL webAcl;

    public final WebACL getWebAcl() {
        return webAcl;
    }

    public final void setWebAcl(final WebACL webAcl) {
        this.webAcl = webAcl;
    }
}

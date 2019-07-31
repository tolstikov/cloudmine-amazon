package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.CustomVerificationEmailTemplate;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class CustomVerificationEmailTemplatesResponse extends AmazonResponse {

    private List<CustomVerificationEmailTemplate> customVerificationEmailTemplates;

    public List<CustomVerificationEmailTemplate> getCustomVerificationEmailTemplates() {
        return customVerificationEmailTemplates;
    }

    public void setCustomVerificationEmailTemplates(final List<CustomVerificationEmailTemplate> customVerificationEmailTemplates) {
        this.customVerificationEmailTemplates = customVerificationEmailTemplates;
    }
}

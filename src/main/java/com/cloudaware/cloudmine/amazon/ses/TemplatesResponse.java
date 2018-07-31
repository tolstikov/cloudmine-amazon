package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.TemplateMetadata;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TemplatesResponse extends AmazonResponse {

    private List<TemplateMetadata> templates;

    public List<TemplateMetadata> getTemplates() {
        return templates;
    }

    public void setTemplates(final List<TemplateMetadata> templates) {
        this.templates = templates;
    }
}

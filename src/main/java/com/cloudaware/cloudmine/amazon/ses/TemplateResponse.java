package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.Template;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class TemplateResponse extends AmazonResponse {

    private Template template;

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(final Template template) {
        this.template = template;
    }
}

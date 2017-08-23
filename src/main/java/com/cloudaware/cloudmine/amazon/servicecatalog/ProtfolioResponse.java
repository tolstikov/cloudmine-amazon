package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.PortfolioDetail;
import com.amazonaws.services.servicecatalog.model.Tag;
import com.amazonaws.services.servicecatalog.model.TagOptionDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProtfolioResponse extends AmazonResponse {
    private PortfolioDetail portfolioDetail;
    private List<Tag> tags;
    private List<TagOptionDetail> tagOptions;

    public PortfolioDetail getPortfolioDetail() {
        return portfolioDetail;
    }

    public void setPortfolioDetail(final PortfolioDetail portfolioDetail) {
        this.portfolioDetail = portfolioDetail;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public List<TagOptionDetail> getTagOptions() {
        return tagOptions;
    }

    public void setTagOptions(final List<TagOptionDetail> tagOptions) {
        this.tagOptions = tagOptions;
    }
}

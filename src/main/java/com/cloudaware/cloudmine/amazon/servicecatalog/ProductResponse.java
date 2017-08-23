package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.ProductViewDetail;
import com.amazonaws.services.servicecatalog.model.ProvisioningArtifactSummary;
import com.amazonaws.services.servicecatalog.model.Tag;
import com.amazonaws.services.servicecatalog.model.TagOptionDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProductResponse extends AmazonResponse {
    private ProductViewDetail productViewDetail;
    private List<ProvisioningArtifactSummary> provisioningArtifactSummaries;
    private List<Tag> tags;
    private List<TagOptionDetail> tagOptions;

    public ProductViewDetail getProductViewDetail() {
        return productViewDetail;
    }

    public void setProductViewDetail(final ProductViewDetail productViewDetail) {
        this.productViewDetail = productViewDetail;
    }

    public List<ProvisioningArtifactSummary> getProvisioningArtifactSummaries() {
        return provisioningArtifactSummaries;
    }

    public void setProvisioningArtifactSummaries(final List<ProvisioningArtifactSummary> provisioningArtifactSummaries) {
        this.provisioningArtifactSummaries = provisioningArtifactSummaries;
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

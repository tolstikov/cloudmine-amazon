package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.FieldLevelEncryptionProfileSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class FieldLevelEncryptionProfilesResponse extends AmazonResponse {
    private List<FieldLevelEncryptionProfileSummary> fieldLevelEncryptionProfileSummaries;

    public List<FieldLevelEncryptionProfileSummary> getFieldLevelEncryptionProfileSummaries() {
        return fieldLevelEncryptionProfileSummaries;
    }

    public void setFieldLevelEncryptionProfileSummaries(final List<FieldLevelEncryptionProfileSummary> fieldLevelEncryptionProfileSummaries) {
        this.fieldLevelEncryptionProfileSummaries = fieldLevelEncryptionProfileSummaries;
    }
}

package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.FieldLevelEncryptionSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class FieldLevelEncryptionConfigsResponse extends AmazonResponse {
    private List<FieldLevelEncryptionSummary> fieldLevelEncryptionConfigSummaries;

    public List<FieldLevelEncryptionSummary> getFieldLevelEncryptionConfigSummaries() {
        return fieldLevelEncryptionConfigSummaries;
    }

    public void setFieldLevelEncryptionConfigSummaries(final List<FieldLevelEncryptionSummary> fieldLevelEncryptionConfigSummaries) {
        this.fieldLevelEncryptionConfigSummaries = fieldLevelEncryptionConfigSummaries;
    }
}

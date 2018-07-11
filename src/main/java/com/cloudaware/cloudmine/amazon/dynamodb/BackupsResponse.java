package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.BackupSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class BackupsResponse extends AmazonResponse {

    private List<BackupSummary> backupSummaries;

    public void setBackupSummaries(final List<BackupSummary> backupSummaries) {
        this.backupSummaries = backupSummaries;
    }

    public List<BackupSummary> getBackupSummaries() {
        return backupSummaries;
    }
}

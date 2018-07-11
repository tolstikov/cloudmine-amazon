package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.BackupDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class BackupDescriptionResponse extends AmazonResponse {

    private BackupDescription backupDescription;

    public void setBackupDescription(final BackupDescription backupDescription) {
        this.backupDescription = backupDescription;
    }

    public BackupDescription getBackupDescription() {
        return backupDescription;
    }
}

package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.ContinuousBackupsDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class TableContinuousBackupsResponse extends AmazonResponse {

    private ContinuousBackupsDescription continuousBackups;

    public ContinuousBackupsDescription getContinuousBackups() {
        return continuousBackups;
    }

    public void setContinuousBackups(final ContinuousBackupsDescription continuousBackups) {
        this.continuousBackups = continuousBackups;
    }
}

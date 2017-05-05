package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.ApplicationVersionDescription;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:29
 */
public final class ApplicationVersionsResponse extends AmazonResponse {
    private List<ApplicationVersionDescription> applicationVersions;

    public ApplicationVersionsResponse() {
    }

    public ApplicationVersionsResponse(final AmazonException exception) {
        super(exception);
    }

    public ApplicationVersionsResponse(final List<ApplicationVersionDescription> applicationVersions, final String nextPage) {
        super(nextPage);
        this.applicationVersions = applicationVersions;
    }

    public List<ApplicationVersionDescription> getApplicationVersions() {
        return applicationVersions;
    }

    public void setApplicationVersions(final List<ApplicationVersionDescription> applicationVersions) {
        this.applicationVersions = applicationVersions;
    }
}

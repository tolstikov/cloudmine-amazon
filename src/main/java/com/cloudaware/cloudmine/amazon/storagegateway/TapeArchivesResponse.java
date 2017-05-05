package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.TapeArchive;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:52
 */
public final class TapeArchivesResponse extends AmazonResponse {
    private List<TapeArchive> tapeArchives;

    public TapeArchivesResponse() {
    }

    public TapeArchivesResponse(final AmazonException exception) {
        super(exception);
    }

    public TapeArchivesResponse(final List<TapeArchive> tapeArchives, final String nextPage) {
        super(nextPage);
        this.tapeArchives = tapeArchives;
    }

    public List<TapeArchive> getTapeArchives() {
        return tapeArchives;
    }

    public void setTapeArchives(final List<TapeArchive> tapeArchives) {
        this.tapeArchives = tapeArchives;
    }
}

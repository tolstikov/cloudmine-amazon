package com.cloudaware.cloudmine.amazon.storagegateway;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:45
 */
public final class BandwidthRateLimitResponse extends AmazonResponse {
    private Long averageUploadRateLimitInBitsPerSec;
    private Long averageDownloadRateLimitInBitsPerSec;

    public BandwidthRateLimitResponse() {
    }

    public BandwidthRateLimitResponse(final AmazonException exception) {
        super(exception);
    }

    public BandwidthRateLimitResponse(final Long averageUploadRateLimitInBitsPerSec, final Long averageDownloadRateLimitInBitsPerSec) {
        this.averageUploadRateLimitInBitsPerSec = averageUploadRateLimitInBitsPerSec;
        this.averageDownloadRateLimitInBitsPerSec = averageDownloadRateLimitInBitsPerSec;
    }

    public Long getAverageUploadRateLimitInBitsPerSec() {
        return averageUploadRateLimitInBitsPerSec;
    }

    public void setAverageUploadRateLimitInBitsPerSec(final Long averageUploadRateLimitInBitsPerSec) {
        this.averageUploadRateLimitInBitsPerSec = averageUploadRateLimitInBitsPerSec;
    }

    public Long getAverageDownloadRateLimitInBitsPerSec() {
        return averageDownloadRateLimitInBitsPerSec;
    }

    public void setAverageDownloadRateLimitInBitsPerSec(final Long averageDownloadRateLimitInBitsPerSec) {
        this.averageDownloadRateLimitInBitsPerSec = averageDownloadRateLimitInBitsPerSec;
    }
}

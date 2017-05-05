package com.cloudaware.cloudmine.amazon;

/**
 * User: urmuzov
 * Date: 2017-02-20
 * Time: 17:51
 */
public final class AmazonBasicCredentials extends AmazonCredentials {
    private String accessKey;
    private String secretKey;

    public AmazonBasicCredentials(final String accessKey, final String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public String serialize() {
        return accessKey + "::" + secretKey;
    }
}

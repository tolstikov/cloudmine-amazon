package com.cloudaware.cloudmine.amazon;

/**
 * User: urmuzov
 * Date: 2017-02-20
 * Time: 17:51
 */
public final class AmazonSessionCredentials extends AmazonCredentials {
    private String accessKey;
    private String secretKey;
    private String sessionToken;

    public AmazonSessionCredentials(final String accessKey, final String secretKey, final String sessionToken) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.sessionToken = sessionToken;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    @Override
    public String serialize() {
        return accessKey + "::" + secretKey + "::" + sessionToken;
    }
}

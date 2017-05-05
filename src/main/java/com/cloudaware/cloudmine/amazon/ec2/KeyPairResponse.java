package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.KeyPair;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 18:02
 */
public final class KeyPairResponse extends AmazonResponse {
    private KeyPair keyPair;

    public KeyPairResponse() {
    }

    public KeyPairResponse(final AmazonException exception) {
        super(exception);
    }

    public KeyPairResponse(final KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(final KeyPair keyPair) {
        this.keyPair = keyPair;
    }
}

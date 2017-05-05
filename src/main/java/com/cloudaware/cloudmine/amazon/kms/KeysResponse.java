package com.cloudaware.cloudmine.amazon.kms;

import com.amazonaws.services.kms.model.KeyListEntry;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 17:10
 */
public final class KeysResponse extends AmazonResponse {
    private List<KeyListEntry> keys;

    public KeysResponse() {
    }

    public KeysResponse(final AmazonException exception) {
        super(exception);
    }

    public KeysResponse(final List<KeyListEntry> keys, final String nextPage) {
        super(nextPage);
        this.keys = keys;
    }

    public List<KeyListEntry> getKeys() {
        return keys;
    }

    public void setKeys(final List<KeyListEntry> keys) {
        this.keys = keys;
    }
}

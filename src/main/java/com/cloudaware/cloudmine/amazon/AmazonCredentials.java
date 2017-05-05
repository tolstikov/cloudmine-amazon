package com.cloudaware.cloudmine.amazon;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.cloudfront.model.InvalidArgumentException;
import com.google.common.base.Strings;

/**
 * User: urmuzov
 * Date: 2017-02-20
 * Time: 22:39
 */
public abstract class AmazonCredentials {
    public static AWSCredentials parse(final String serialized) {
        if (Strings.isNullOrEmpty(serialized)) {
            throw new IllegalArgumentException("Credentials are empty");
        }
        final String[] parts = serialized.split("::");
        if (parts.length == 2) {
            return new BasicAWSCredentials(parts[0], parts[1]);
        } else if (parts.length == 3) {
            return new BasicSessionCredentials(parts[0], parts[1], parts[2]);
        } else {
            throw new InvalidArgumentException("Unknown credentials format");
        }
    }

    public abstract String serialize();
}

package com.cloudaware.cloudmine.amazon;

import com.google.api.server.spi.ServiceException;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 17:09
 */
public class AmazonUnparsedException extends ServiceException {

    private static final int INTERNAL_SERVER_ERROR = 500;

    public AmazonUnparsedException(final Throwable t) {
        super(INTERNAL_SERVER_ERROR, "Unparsed exception: " + t.getClass().getName() + " " + t.getMessage(), t);
    }
}

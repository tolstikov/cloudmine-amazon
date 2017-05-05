package com.cloudaware.cloudmine.amazon.ecs;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 14:55
 */
public final class ArnsResponse extends AmazonResponse {
    private List<String> arns;

    public ArnsResponse() {
    }

    public ArnsResponse(final AmazonException exception) {
        super(exception);
    }

    public ArnsResponse(final List<String> arns, final String nextPage) {
        super(nextPage);
        this.arns = arns;
    }

    public List<String> getArns() {
        return arns;
    }

    public void setArns(final List<String> arns) {
        this.arns = arns;
    }
}

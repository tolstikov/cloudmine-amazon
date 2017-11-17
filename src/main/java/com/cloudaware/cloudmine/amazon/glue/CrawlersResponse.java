package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.Crawler;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class CrawlersResponse extends AmazonResponse {

    private List<Crawler> crawlers;

    public List<Crawler> getCrawlers() {
        return crawlers;
    }

    public void setCrawlers(final List<Crawler> crawlers) {
        this.crawlers = crawlers;
    }
}

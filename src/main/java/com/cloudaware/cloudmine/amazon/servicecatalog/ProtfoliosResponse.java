package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.PortfolioDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProtfoliosResponse extends AmazonResponse {
    private List<PortfolioDetail> portfolios;

    public List<PortfolioDetail> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(final List<PortfolioDetail> portfolios) {
        this.portfolios = portfolios;
    }
}

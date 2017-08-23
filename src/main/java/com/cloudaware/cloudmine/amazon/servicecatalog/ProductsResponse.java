package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.ProductViewDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProductsResponse extends AmazonResponse {
    private List<ProductViewDetail> products;

    public List<ProductViewDetail> getProducts() {
        return products;
    }

    public void setProducts(final List<ProductViewDetail> products) {
        this.products = products;
    }
}

package com.ua.rosella.viewModel;

import com.ua.rosella.model.Bouquet;
import com.ua.rosella.util.Pagination;

import java.util.List;

public class CatalogViewModel {
    List<Bouquet> products;
    Pagination pagination;

    public CatalogViewModel(List<Bouquet> products, Pagination pagination) {
        this.products = products;
        this.pagination = pagination;
    }

    public List<Bouquet> getProducts() {
        return products;
    }

    public void setProducts(List<Bouquet> products) {
        this.products = products;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}

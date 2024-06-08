package com.nimbleways.springboilerplate.services.processProducts;

import com.nimbleways.springboilerplate.entities.Product;

import com.nimbleways.springboilerplate.services.implementations.NotificationService;
import com.nimbleways.springboilerplate.services.implementations.ProductService;

public interface ProcessProduct {

    void procces(Product product, ProductService productService, NotificationService notificationService);
}

package com.nimbleways.springboilerplate.services.processProducts;

import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.services.implementations.NotificationService;
import com.nimbleways.springboilerplate.services.implementations.ProductService;

public class NormalProduct implements ProcessProduct {

    @Override
    public void procces(Product product, ProductService productService, NotificationService notificationService) {
        if (product.getAvailable() > 0) {
            product.setAvailable(product.getAvailable() - 1);
            productService.saveProduct(product);
        } else {
            int leadTime = product.getLeadTime();
            if (leadTime > 0) {
                product.setLeadTime(leadTime);
                productService.saveProduct(product);
                notificationService.sendDelayNotification(leadTime, product.getName());
            }
        }
    }
}

package com.nimbleways.springboilerplate.services.processProducts;

import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.repositories.ProductRepository;
import com.nimbleways.springboilerplate.services.implementations.NotificationService;
import com.nimbleways.springboilerplate.services.implementations.ProductService;

import java.time.LocalDate;

public class ExpirableProduct implements ProcessProduct {

    @Override
    public void procces(Product p, ProductService productService, NotificationService ns) {
        if (p.getAvailable() > 0 && p.getExpiryDate().isAfter(LocalDate.now())) {
            p.setAvailable(p.getAvailable() - 1);
            productService.saveProduct(p);
        } else {
            ns.sendExpirationNotification(p.getName(), p.getExpiryDate());
            p.setAvailable(0);
            productService.saveProduct(p);
        }
    }
}




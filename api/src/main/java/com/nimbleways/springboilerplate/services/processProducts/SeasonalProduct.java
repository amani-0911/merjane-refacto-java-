package com.nimbleways.springboilerplate.services.processProducts;

import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.services.implementations.NotificationService;
import com.nimbleways.springboilerplate.services.implementations.ProductService;

import java.time.LocalDate;

public class SeasonalProduct implements ProcessProduct {

    @Override
    public void procces(Product p, ProductService productService, NotificationService notificationService) {
        if (LocalDate.now().plusDays(p.getLeadTime()).isAfter(p.getSeasonEndDate())) {
            notificationService.sendOutOfStockNotification(p.getName());
            p.setAvailable(0);
            productService.saveProduct(p);
        } else if (p.getSeasonStartDate().isAfter(LocalDate.now())) {
            notificationService.sendOutOfStockNotification(p.getName());
            productService.saveProduct(p);
        } else {
            p.setLeadTime(p.getLeadTime());
            productService.saveProduct(p);
            notificationService.sendDelayNotification(p.getLeadTime(), p.getName());
        }
    }
}

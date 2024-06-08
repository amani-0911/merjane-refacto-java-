package com.nimbleways.springboilerplate.services.processProducts;

import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.services.implementations.NotificationService;
import com.nimbleways.springboilerplate.services.implementations.ProductService;

import java.time.LocalDate;

public class FlashSaleProduct implements ProcessProduct {
    @Override
    public void procces(Product product, ProductService productService, NotificationService notificationService) {
        LocalDate now = LocalDate.now();
        if (now.isAfter(product.getFlashSaleStartDate()) && now.isBefore(product.getFlashSaleEndDate())
                && product.getAvailable() > 0 && product.getAvailable() <= product.getMaxFlashSaleQuantity()) {
            product.setAvailable(product.getAvailable() - 1);
            productService.saveProduct(product);
        } else {
            // Handle the case when the flash sale is over or the quantity limit is reached

        }
    }
}

package com.nimbleways.springboilerplate.services.processProducts;

import com.nimbleways.springboilerplate.enumerations.ProductType;

public class ProcessProductFactory {

    public static ProcessProduct getProcessStrategy(ProductType type) {
        switch (type) {
            case NORMAL:
                return new NormalProduct();
            case SEASONAL:
                return new SeasonalProduct();
            case EXPIRABLE:
                return new ExpirableProduct();
            case FLASHSALE:
                return new FlashSaleProduct();
            default:
                throw new IllegalArgumentException("Unknown product type: " + type);
        }
    }
}

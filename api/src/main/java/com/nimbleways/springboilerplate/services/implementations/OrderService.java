package com.nimbleways.springboilerplate.services.implementations;


import com.nimbleways.springboilerplate.dto.product.ProcessOrderResponse;
import com.nimbleways.springboilerplate.entities.Order;
import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.enumerations.ProductType;
import com.nimbleways.springboilerplate.exceptions.OrderNotFoundException;
import com.nimbleways.springboilerplate.repositories.OrderRepository;
import com.nimbleways.springboilerplate.repositories.ProductRepository;
import com.nimbleways.springboilerplate.services.processProducts.ProcessProduct;
import com.nimbleways.springboilerplate.services.processProducts.ProcessProductFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    NotificationService notificationService;
    public ProcessOrderResponse processOrder(Long orderId) {

        Order order=orderRepository.findById(orderId).orElseThrow(
                ()->new OrderNotFoundException(String.valueOf(orderId))
        );

        Set<Product> products = order.getItems();
        for (Product p : products) {
            ProcessProduct processStrategy = ProcessProductFactory.getProcessStrategy(p.getType());
            processStrategy.procces(p, productService,notificationService);

        }

        return new ProcessOrderResponse(order.getId());
    }
}

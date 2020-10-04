package com.epam.anatolii.ageev.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Order extends LongID {
private OrderStatus orderStatus;
private String statusDetails;
private LocalDateTime orderDateTime;
private List<ProductsInOrder> userOrder;
private Long userId;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatusDetails() {
        return statusDetails;
    }

    public void setStatusDetails(String statusDetails) {
        this.statusDetails = statusDetails;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<ProductsInOrder> getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(List<ProductsInOrder> userOrder) {
        this.userOrder = userOrder;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

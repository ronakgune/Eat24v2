package com.main.eat24.Model;



public class OrderHistory {

    public String restName;
    public String orderId;
    public String orderPrice;
    public String delAddress;

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setDelAddress(String delAddress) {
        this.delAddress = delAddress;
    }

    public String getRestName() {
        return restName;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public String getDelAddress() {
        return delAddress;
    }



    public OrderHistory() {
    }

    public OrderHistory(String delAddress, String orderPrice, String restName, String orderId) {
        this.restName = restName;
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.delAddress = delAddress;
    }
}

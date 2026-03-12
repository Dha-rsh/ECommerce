package com.ecommerce.model;

import java.util.List;

public class Order {

    private int id;
    private int userId;
    private int total_price;
    private String status;
    private String address;
    private String phone;
    private List<Product> items;
    
    public Order() {
    }


    public Order(int id, int userId, int total_price, String status, String address, String phone) {
        this.id = id;
        this.userId = userId;
        this.total_price = total_price;
        this.status = status;
        this.address = address;
        this.phone = phone;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(int total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
     public List<Product> getItems(){
        return items;
    }

    public void setItems(List<Product> items){
        this.items = items;
    }
}
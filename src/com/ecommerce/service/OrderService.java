package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Order;
import com.ecommerce.repository.CartDao;
import com.ecommerce.repository.OrderDao;

public class OrderService {
    OrderDao od=new OrderDao();
    CartDao cd=new CartDao();
    public boolean placeOrder(int userId, String address, String phone) {
        
        int total=cd.getTotal(userId);
     int orderId=od.createOrder(userId,address,phone,total);
    if(orderId>0){ od.movetoOrderItems(userId,orderId);
     cd.clearCart(userId);
     return true;
    }
    return false;

    }
    public List<Order> getOrdersByUser(int userId)
    {
        return od.getOrderByUser(userId);
    }
}

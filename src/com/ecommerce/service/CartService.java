package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Product;
import com.ecommerce.repository.CartDao;

public class CartService {
    private CartDao cd=new CartDao();
    public boolean addProduct(int userId, int productId) {
        return cd.addProduct(userId,productId);
    }

    public List<Product> getProducts(int id) {
        return cd.getProducts(id);
    }
    
}

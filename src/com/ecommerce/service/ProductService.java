package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductDao;

public class ProductService {
    private ProductDao pd=new ProductDao();
    public boolean saveProduct(Product p)
    {
        return pd.saveProduct(p);
    }

    public List<Product> getProducts(String type)
    {
        return pd.gerProducts(type);
    }

     public List<Product> getUserProducts(int adminId)
    {
        return pd.getUserProducts(adminId);
    }
    public Product getProduct(int id)
    {
        return pd.getProduct(id);
    }

     public boolean editProduct(Product p) {
       return pd.editProduct(p);
     }

     public void delProduct(int id) {
         pd.delProduct(id);
     }
    
}

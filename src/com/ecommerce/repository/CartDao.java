package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.db.DBconnection;
import com.ecommerce.model.Product;

public class CartDao {
    private Connection con;
   public CartDao()
    {
        try{
        con=DBconnection.getConnection();
    }catch(Exception e)
    {
        e.printStackTrace();
    }
    }

    public boolean addProduct(int userId, int productId) {
        String checkSql = "select * from carts where user_id=? and product_id=?";
    String insertSql = "insert into carts(user_id,product_id,quantity) values (?,?,1)";
    String updateSql = "update carts set quantity = quantity + 1 where user_id=? and product_id=?";

    try {

        PreparedStatement check = con.prepareStatement(checkSql);
        check.setInt(1,userId);
        check.setInt(2,productId);

        ResultSet rs = check.executeQuery();

        if(rs.next()) {
            PreparedStatement update = con.prepareStatement(updateSql);
            update.setInt(1,userId);
            update.setInt(2,productId);
            return update.executeUpdate() > 0;
        } 
        else {
            PreparedStatement insert = con.prepareStatement(insertSql);
            insert.setInt(1,userId);
            insert.setInt(2,productId);
            return insert.executeUpdate() > 0;
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return false;
        
    }

    public List<Product> getProducts(int id) {
        String sql="select p.* ,c.quantity from carts c join products p on p.id=c.product_id where c.user_id=?";
        List<Product> prod=new ArrayList<>();
        
        try
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Product p=new Product(rs.getInt("id"),rs.getString("name"),rs.getInt("price"),
            rs.getInt("quantity"),rs.getString("type"),rs.getInt("adminId"),rs.getString("img_path"));
                prod.add(p);
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return prod;
    }

    public int getTotal(int userId)
{
    int total=0;
    String sql="select sum(p.price * c.quantity) as total from carts c join products p on p.id=c.product_id where c.user_id=?";
     try{
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1,userId);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            total=rs.getInt("total");
        }

     }catch(Exception e)
     {
        e.printStackTrace();
     }
     return total;
}
public void clearCart(int userId) {

        try
        {
            String sql="delete from carts where user_id=?";
             PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
            }
}

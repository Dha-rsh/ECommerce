package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.db.DBconnection;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;

public class OrderDao {
    private Connection con;
public OrderDao(){
    try{
        con=DBconnection.getConnection();
    }catch(Exception e)
    {
        e.printStackTrace();
    }
}

  
    public int createOrder(int userId, String address, String phone,int totalPrice) {
        int orderId=0;  
        String sql="insert into orders(user_id,total_price,address,phone,status) values(?,?,?,?,?)";
        try{   
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, userId);
            ps.setInt(2, totalPrice);
            ps.setString(3, address);
             ps.setString(4, phone);
            ps.setString(5, "Pending");

            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
            {
                orderId=rs.getInt(1);
            }
           }catch(Exception e)
           {
            e.printStackTrace();
           }
           return orderId;
    }
 
    public void movetoOrderItems(int userId, int orderId) {
        try{
            String sql="select * from carts where user_id=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                String insertQuery = "insert into order_items(order_id,product_id,quantity) values(?,?,?)";

                PreparedStatement ps2 = con.prepareStatement(insertQuery);

                ps2.setInt(1, orderId);
                ps2.setInt(2, productId);
                ps2.setInt(3, quantity);

                ps2.executeUpdate();  
                String update ="update products set stocks=stocks-? where id=?";
                PreparedStatement ps3=con.prepareStatement(update);
                ps3.setInt(1,quantity);
                ps3.setInt(2,productId);
                ps3.executeUpdate();


            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

           }

    public List<Order> getOrderByUser(int userId) {
       String sql="select * from orders where user_id = ?";
       List<Order> orders=new ArrayList<>();
       try{
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1,userId);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Order o=new Order(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("total_price"),
                rs.getString("status"),
                rs.getString("address"),
                rs.getString("phone")
            );
            List<Product> items=getOrderItems(o.getId());
            o.setItems(items);
            orders.add(o);
        }


       }catch(Exception e)
       {
        e.printStackTrace();
       }
       return orders;
    }
 public List<Product> getOrderItems(int orderId)
 {
    List<Product> lis=new ArrayList<>();
    String sql="select p.*,o.quantity from order_items o join products p on o.product_id=p.id where o.order_id=?";
    try{
    PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, orderId);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Product p=new Product(rs.getString("name"),rs.getInt("price"),rs.getInt("quantity"),rs.getString("type"),rs.getInt("adminId"),rs.getString("img_path"));
            lis.add(p);
        }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return lis;
 }
    
    
}

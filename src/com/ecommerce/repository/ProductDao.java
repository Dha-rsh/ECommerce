package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.db.DBconnection;
import com.ecommerce.model.Product;

public class ProductDao {
    public boolean saveProduct(Product p)
    {
        String sql="insert into products(name,price,stocks,type,adminId,img_path) values (?,?,?,?,?,?)";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,p.getName());
            ps.setInt(2,p.getPrice());
            ps.setInt(3,p.getStocks());
            ps.setString(4,p.getType());
             ps.setInt(5,p.getAdminId());
             ps.setString(6,p.getImage());
           return ps.executeUpdate()>0;
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> gerProducts(String type)
    {
        List<Product> prod=new ArrayList<>();
        String sql="select * from products";
        if(type!=null)
        {
            sql+=" where type=? ";
        }
        try(Connection con=DBconnection.getConnection()){
        PreparedStatement ps=con.prepareStatement(sql);
        if(type!=null)
        {
            ps.setString(1,type);
        }
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Product p=new Product(rs.getInt("id"),rs.getString("name"),rs.getInt("price"),rs.getInt("stocks"),
            rs.getString("type"),rs.getInt("adminId"),rs.getString("img_path"));
            prod.add(p);
        }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return prod;


    }


    public List<Product> getUserProducts(int adminId)
    {
        System.out.println(adminId);
        List<Product> prod=new ArrayList<>();
        String sql="select * from products where adminId=?";
        try(Connection con=DBconnection.getConnection()){
        PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,adminId);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Product p=new Product(rs.getInt("id"),rs.getString("name"),rs.getInt("price"),rs.getInt("stocks"),
            rs.getString("type"),rs.getInt("adminId"),rs.getString("img_path"));
            prod.add(p);
            System.out.println(p.toString());
        }
       

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return prod;


    }

    public Product getProduct(int id) {
          Product p=null;
        String sql="select * from products where id=?";
        try(Connection con=DBconnection.getConnection()){
        PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
             p=new Product(rs.getInt("id"),rs.getString("name"),rs.getInt("price"),rs.getInt("stocks"),
            rs.getString("type"),rs.getInt("adminId"),rs.getString("img_path"));
           
        }
       

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return p;
    }

    public boolean editProduct(Product p) {
       String sql="update products set name=?,price=?,stocks=?,type=?,img_path=? where id=?";
    
    try(Connection con=DBconnection.getConnection())
    {
        PreparedStatement ps=con.prepareStatement(sql);

        ps.setString(1,p.getName());
        ps.setInt(2,p.getPrice());
        ps.setInt(3,p.getStocks());
        ps.setString(4,p.getType());
        ps.setString(5,p.getImage());
        ps.setInt(6,p.getId());

        return ps.executeUpdate()>0;
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    return false;
    }

    public void delProduct(int id) {
        try{
        Connection con = DBconnection.getConnection();

        String sql = "delete from products where id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,id);

        ps.executeUpdate();

    }catch(Exception e){
        e.printStackTrace();
    }
 }
}

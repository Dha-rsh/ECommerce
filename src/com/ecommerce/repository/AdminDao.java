package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ecommerce.db.DBconnection;
import com.ecommerce.model.Admin;

public class AdminDao {
     public boolean insertAdmin(Admin a)
    {
        String sql="insert into admins(name,email,password) values (?,?,?)";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,a.getName());
            ps.setString(2,a.getEmail());
            ps.setString(3,a.getPass());
           return ps.executeUpdate() > 0;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Admin getAdmin(String email)
    {
        Admin a =null;
        String sql="select * from admins where email=?";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                a=new Admin(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("password"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(a.toString());
        return a;

    }
}

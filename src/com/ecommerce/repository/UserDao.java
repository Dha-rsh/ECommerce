package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ecommerce.db.DBconnection;
import com.ecommerce.model.User;

public class UserDao {
    public boolean insertUser(User u)
    {
        String sql="insert into users(name,email,password) values (?,?,?)";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,u.getName());
            ps.setString(2,u.getEmail());
            ps.setString(3,u.getPass());
           return ps.executeUpdate() > 0;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(String email)
    {
        User a =null;
        String sql="select * from users where email=?";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                return new User(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("password"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return a;

    }


    
}

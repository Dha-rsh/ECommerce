package com.ecommerce.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    private static final String url="jdbc:mysql://localhost:3306/ecommerce";
    private static final String user="root";
    private static final String pass="priya123.";
    static{
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    }

     public static Connection getConnection() throws Exception
     { 
        return DriverManager.getConnection(url, user, pass);   
     }
}

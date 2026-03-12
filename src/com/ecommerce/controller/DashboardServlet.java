package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.service.ProductService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

public class DashboardServlet extends HttpServlet {
    ProductService ps=new ProductService(); 
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException  
    {
        doPost(req,res);  
       
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
        HttpSession ses=req.getSession(false);
        if(ses==null)
        {
            res.sendRedirect("login.html");
            return;
        }
        User u=(User)ses.getAttribute("user");

        if(u==null)
{
    res.sendRedirect("login.html");
    return;
}
        String type=req.getParameter("type");
        List<Product> prod=ps.getProducts(type);
        req.setAttribute("products",prod);
        req.getRequestDispatcher("dashboard.jsp").forward(req,res);    
    }
    
}

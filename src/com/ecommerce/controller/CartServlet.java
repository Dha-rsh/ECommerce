package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.ProductService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
public class CartServlet extends HttpServlet {
    CartService ds=new CartService();
     protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException  
    {
         
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
    
       List<Product> p=ds.getProducts(u.getId());
       req.setAttribute("products",p);
       req.getRequestDispatcher("cart.jsp").forward(req,res);
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
    

    int productId=Integer.parseInt(req.getParameter("productId"));
    ds.addProduct(u.getId(),productId);
    RequestDispatcher rd = req.getRequestDispatcher("dashboard");
rd.forward(req,res);
   
}


    
}


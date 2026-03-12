package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.OrderService;

import jakarta.servlet.http.*;
import jakarta.servlet.*;

public class OrderServlet extends HttpServlet{
    OrderService os=new OrderService();
    CartService cs=new CartService();
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException ,ServletException
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
    String address=req.getParameter("address");
    String phone=req.getParameter("phone");
    List<Product> p=cs.getProducts(u.getId());
    if(os.placeOrder(u.getId(),address,phone)){
       req.setAttribute("products",p);
    res.sendRedirect("dashboard?order=success");
    }else{
        res.sendRedirect("dashboard?order=fail");
    }

    }
}

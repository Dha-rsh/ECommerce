package com.ecommerce.controller;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import com.ecommerce.model.Order;
import com.ecommerce.model.User;
import com.ecommerce.service.OrderService;

import jakarta.servlet.*;
public class ViewOrderServlet extends HttpServlet {
    OrderService os=new OrderService();
    
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
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
     int userId=u.getId();
    List<Order> orders=os.getOrdersByUser(userId);
    req.setAttribute("orders",orders);
    RequestDispatcher rd = req.getRequestDispatcher("orders.jsp");
        rd.forward(req, res);
    }
   
}

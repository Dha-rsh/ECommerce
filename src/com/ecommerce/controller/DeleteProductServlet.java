package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.model.Admin;
import com.ecommerce.service.ProductService;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
public class DeleteProductServlet extends HttpServlet{
    private ProductService ps=new ProductService();
     protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
         HttpSession ses=req.getSession(false);
        if(ses==null)
        {
            res.sendRedirect("login.html");
            return;
        }
        Admin u=(Admin)ses.getAttribute("admin");

        if(u==null)
{
    res.sendRedirect("login.html");
    return;

}
int id=Integer.parseInt(req.getParameter("id"));
ps.delProduct(id);
res.sendRedirect("addProduct");
    }
}

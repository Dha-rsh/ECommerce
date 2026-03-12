package com.ecommerce.controller;

import jakarta.servlet.http.*;

import java.io.IOException;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;
import com.ecommerce.service.AdminService;
import com.ecommerce.service.UserService;

import jakarta.servlet.*;
public class LoginServlet extends HttpServlet{
    private UserService us=new UserService();
    private AdminService as=new AdminService();
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
    {
        HttpSession ses=req.getSession();
        String type=req.getParameter("type");
        String mail=req.getParameter("email");
        String pass=req.getParameter("pass");
        System.out.println(type+" "+mail+" "+pass);

       if("user".equals(type))
{
    User u = us.getUser(mail);

    if(u == null || !u.getPass().equals(pass))
    {
        res.sendRedirect("register.html");
        return;
    }
    ses.setAttribute("user", u);

    res.sendRedirect("dashboard");
}

         if("admin".equals(type))
        {
            Admin u=as.getAdmin(mail);
             System.out.println(u.toString());
            if(!u.getPass().equals(pass))
            {
                res.sendRedirect("register.html");
                return;
            }
            ses.setAttribute("admin",u);
            res.sendRedirect("addProduct");
        }
    }
}

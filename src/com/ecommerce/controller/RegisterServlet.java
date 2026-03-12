package com.ecommerce.controller;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;
import com.ecommerce.service.AdminService;
import com.ecommerce.service.UserService;
public class RegisterServlet extends HttpServlet{
    private UserService us=new UserService();
    private AdminService as=new AdminService();
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
    {
        String type=req.getParameter("type");
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String pass=req.getParameter("pass");
        String cpass=req.getParameter("cpass");
        if(!pass.equals(cpass))
        {
            res.sendRedirect("register.html");
            return;
        }
        if("user".equals(type))
        {
            User u=new User(name,email,pass);
            us.insertUser(u);
            res.sendRedirect("login.html");
        }

        if("admin".equals(type))
        {
            Admin a=new Admin(name,email,pass);
            as.insertAdmin(a);
            res.sendRedirect("login.html");
        }



    }
}
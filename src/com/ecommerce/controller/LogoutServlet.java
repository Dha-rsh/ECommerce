package com.ecommerce.controller;
import jakarta.servlet.http.*;

import java.io.IOException;

import jakarta.servlet.*;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
    {
        HttpSession ses=req.getSession(false);
        if(ses!=null){
        ses.invalidate();
        }
        res.sendRedirect("login.html");
    }
}

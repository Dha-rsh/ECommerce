package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import java.io.File;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.service.ProductService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;  

@MultipartConfig
public class ProductServlet extends HttpServlet {
    private ProductService ps=new ProductService();

    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        HttpSession ses=req.getSession(false);
        if(ses==null)
        {
            res.sendRedirect("login.html");
            return;
        }
         Admin a=(Admin)ses.getAttribute("admin");
        
        List<Product> l=ps.getUserProducts(a.getId());
       req.setAttribute("products",l);
req.getRequestDispatcher("products.jsp").forward(req,res);


    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        HttpSession ses=req.getSession(false);
        if(ses==null)
        {
            res.sendRedirect("login.html");
            return;
        }
        Admin a=(Admin)ses.getAttribute("admin");
        String name=req.getParameter("name");
        String type=req.getParameter("type");
        int price=Integer.parseInt(req.getParameter("price"));
        int stock=Integer.parseInt(req.getParameter("stock"));
        Part filePart=req.getPart("image");
        String fileName=filePart.getSubmittedFileName();

        String upload=getServletContext().getRealPath("")+"images";

        filePart.write(upload+"/"+fileName);
        int adminId=a.getId();

        Product p=new Product(name,price,stock,type,adminId,fileName);
        if( ps.saveProduct(p))
        {
            res.sendRedirect("addProduct");

        }else{
            res.sendRedirect("products.jsp");
        }
       


    }
    
}

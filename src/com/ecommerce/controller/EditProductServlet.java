package com.ecommerce.controller;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.nio.file.Paths;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

import jakarta.servlet.*;
@MultipartConfig
public class EditProductServlet extends HttpServlet {
    ProductService ps=new ProductService();
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
Product p=ps.getProduct(id);
req.setAttribute("product",p);
req.setAttribute("products",ps.getProducts(null));
 RequestDispatcher rd = req.getRequestDispatcher("products.jsp");
        rd.forward(req, res);

    }

     protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
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
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        int price = Integer.parseInt(req.getParameter("price"));
        int stocks = Integer.parseInt(req.getParameter("stocks"));
        Part filePart = req.getPart("image");
        String imageName=null;

        if(filePart!=null && filePart.getSize()>0)
            {
                imageName=Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String path=getServletContext().getRealPath("/images");
                filePart.write(path+"/"+imageName);
            }else
                {
                   Product old=ps.getProduct(id);
                   imageName=old.getImage(); 
                }
                Product p = new Product(id,name,price,stocks,type,u.getId(),imageName);

        ps.editProduct(p);
        res.sendRedirect("addProduct");
    }

}

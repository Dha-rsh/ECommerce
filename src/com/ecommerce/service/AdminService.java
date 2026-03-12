package com.ecommerce.service;

import com.ecommerce.model.Admin;
import com.ecommerce.repository.AdminDao;

public class AdminService
{
    AdminDao ad=new AdminDao();
     public boolean insertAdmin(Admin a)
    {
        return ad.insertAdmin(a);
    }

    public Admin getAdmin(String email)
    {
        return ad.getAdmin(email);
    }
}
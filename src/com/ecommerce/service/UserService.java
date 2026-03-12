package com.ecommerce.service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserDao;

public class UserService {
    private UserDao us=new UserDao();
    public boolean insertUser(User u)
    {
        return us.insertUser(u);
    }

    public User getUser(String email)
    {
        return us.getUser(email);
    }
    
}

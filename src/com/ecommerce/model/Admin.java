package com.ecommerce.model;

public class Admin {

    private int id;
    private String name;
    private String email;
    private String pass;

   public Admin(int id,String name,String email,String pass)
    {
        this.id=id;
        this.name=name;
        this.email=email;
        this.pass=pass;
    }

    public Admin(String name,String email,String pass)
    {
        this.name=name;
        this.email=email;
        this.pass=pass;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

public String toString() {
    return "Admin{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", pass='" + pass + '\'' +
            '}';
}
}
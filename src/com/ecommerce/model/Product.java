package com.ecommerce.model;

public class Product {

    private int id;
    private String name;
    private int price;
    private int stocks;
    private String type;
    private int adminId;
    private String imgPath;

    public Product() {
    }


    public Product(int id, String name, int price, int stocks,String type,int adminId,String imgPath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stocks = stocks;
        this.type=type;
        this.adminId=adminId;
        this.imgPath=imgPath;
    }
 
    
public Product(String name, int price, int stocks,String type,int adminId,String imgPath) {
        this.name = name;
        this.price = price;
        this.stocks = stocks;
        this.type=type;
        this.adminId=adminId;
         this.imgPath=imgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage()
    {
        return imgPath;
    }
    public void setImage(String imgPath)
    {
        this.imgPath=imgPath;
    }

}

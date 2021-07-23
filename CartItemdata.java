package com.example.saree360.datavalues;

public class CartItemdata {

    String id;
    String image;
    String sareename;
    String brand;
    String quantity;
    String price;
    int total;
    public CartItemdata(String id, String image, String sareename, String brand, String quantity, String price, int total)
    {
        this.id = id;
        this.image = image;
        this.sareename = sareename;
        this.brand = brand;
        this.quantity= quantity;
        this.price = price;
        this.total = total;
    }
    public String getId()
    {
        return id;
    }
    public  String getImage()
    {
        return image;
    }
    public String getSareename()
    {
        return sareename;
    }
    public String getBrand()
    {
        return brand;
    }
    public String getQuantity(){return quantity;}
    public String getPrice()
    {
        return price;
    }
    public int getTotal(){return total;}
    public void setImage(String image) {
        this.image = image;
    }
    public String setItemQuantity(String quantity) {
        this.quantity = quantity;
        return quantity;
    }
}

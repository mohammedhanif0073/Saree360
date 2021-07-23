package com.example.saree360.datavalues;

public class Cart {

    String id;
    String image;
    String sareename;
    String brand;
    String quantity;
    String price;
public Cart(String id,String image,String sareename,String brand,String quantity,String price)
{
    this.id = id;
    this.image = image;
    this.sareename = sareename;
    this.brand = brand;
    this.quantity= quantity;
    this.price = price;
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
    public void setImage(String image) {
        this.image = image;
    }

}

package p;

import java.util.*;

//product class is used to create a product object which is being sold by the company. every product has attributes of name , price and id. it has get methods to access all these
//attributes. it also has set methods for name and price.

class Product {
    private int id;
    private String name;
    private double price;
    private int purchaseCount;
    private int rating;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.purchaseCount = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }
    
    public int getPurchaseCount() {
        return purchaseCount;
    }

    public int getRating(){
        return this.rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "Product: {" +
                "\n -------------"+
                "\n id=" + id +
                "\n name='" + name + '\'' +
                "\n price=" + price +
                '}';
    }
}

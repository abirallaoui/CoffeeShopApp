package com.example.movies_project.Model;

public class Product {

    private  int image;
    private  String title,description,price;
    private double NutritionalValues,productRate;

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }


    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public double getNutritionalValues() {
        return NutritionalValues;
    }

    public double getProductRate() {
        return productRate;
    }

    public Product(int image, String title, String description, String price, double nutritionalValues, double productRate) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
        NutritionalValues = nutritionalValues;
        this.productRate = productRate;
    }
}

package com.example.javaendassignment.model;

import java.io.Serializable;

public class Product implements Serializable {
  private String name;
  private String category;
  private String description;
  private int quantity;
  private double price;
  private int stock;
  private double totalPrice;
  public Product(int stock, String name, String  category, double price, String description){
    this.name = name;
    this.category = category;
    this.description = description;
    this.price = price;
    this.stock = stock;
  }
  public Product(int quantity, String name, String category,double totalPrice ){
    this(0,name,category,0,null);
    this.quantity = quantity;
    this.totalPrice = totalPrice;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String  category) {
    this.category = category;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
  public double getTotalPrice() {
    return totalPrice*quantity;
  }
  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public void decreaseStock(int quantity){
    if (stock> quantity){
      stock -= quantity;
    }
  }
}

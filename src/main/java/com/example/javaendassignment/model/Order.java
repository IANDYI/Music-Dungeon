package com.example.javaendassignment.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
  private final Customer customer;
  private ArrayList<Product> products;
  private final String orderDate;
  public Order(String orderDate, Customer customer,  ArrayList<Product> products){
    this.customer = customer;
    this.products = products;
    this.orderDate= orderDate;
  }

  public String getOrderDate() {
    return orderDate;
  }
  public Customer getCustomer() {
    return customer;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }
}

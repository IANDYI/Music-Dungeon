package com.example.javaendassignment.database;

import com.example.javaendassignment.model.Customer;
import com.example.javaendassignment.model.Order;
import com.example.javaendassignment.model.Product;
import com.example.javaendassignment.model.Role;
import com.example.javaendassignment.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {
  private final ArrayList<User> users;
  private final ArrayList<Product> products;
  private final ArrayList<Order> orders;

  public Database() {
    users = new ArrayList<>();
    products = new ArrayList<>();
    orders = new ArrayList<>();

    products.add(new Product(10, "My guitar", "Instruments", 150, "Best guitar ever"));
    products.add(new Product(23, "My violin", "Instruments", 230, "Better than a guitar ever"));

    users.add(new User("Kaldor", "Draigo", "a", "1", Role.MANAGER));
    users.add(new User("Malkaan", "Feirros", "Malkaan", "harrowhand", Role.SALES));
  }

  public User authenticateUser(String username, String password) {
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return user; // User authenticated
      }
    }
    return null; // Authentication failed
  }

  public ArrayList<Product> getProducts() {
    return products;
  }
  public ArrayList<Order> getOrders() {
    return orders;
  }

  public Product getProductByName(String name) { // will be changed to id in the future
    for(Product product:products) {
      if(Objects.equals(product.getName(), name)) {
        return product;
      }
    }
    return null;
  }

  public void increaseProductStock(String name, int toAdd) {
    Product product = getProductByName(name);
    int temp = product.getStock();
    product.setStock(temp+toAdd);
  }

  public void reduceProductStock(String name, int toDeduct) {
    Product product = getProductByName(name);
    int temp = product.getStock();
    product.setStock(temp-toDeduct);
  }

  public void addOrder(Order order){
    orders.add(order);
  }
  public void addProduct(Product product) {
    products.add(product);
  }
  public void removeProduct(Product product) {
    products.remove(product);
  }
}

package com.example.javaendassignment.database;

import com.example.javaendassignment.model.Order;
import com.example.javaendassignment.model.Product;
import com.example.javaendassignment.model.Role;
import com.example.javaendassignment.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {
  private final List<User> users;
  private final List<Product> products;
  private final List<Order> orders;

  public Database() {
    users = new ArrayList<>();
    products = new ArrayList<>();
    orders = new ArrayList<>();

    products.add(new Product(10, "banana", "fruits", 1.15, "This bananas are the best. Just trust me, old good monkey..."));

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

  public List<Product> getProducts(){
    return products;
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
}

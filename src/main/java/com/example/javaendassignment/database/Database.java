package com.example.javaendassignment.database;

import com.example.javaendassignment.model.Order;
import com.example.javaendassignment.model.Product;
import com.example.javaendassignment.model.Role;
import com.example.javaendassignment.model.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database implements Serializable {
  private final ArrayList<User> users;
  private final ArrayList<Product> products;
  private final ArrayList<Order> orders;

  public Database() {
    users = new ArrayList<>();
    products = new ArrayList<>();
    orders = new ArrayList<>();

    users.add(new User("Kaldor", "Draigo", "a", "1", Role.MANAGER));
    users.add(new User("Malkaan", "Feirros", "Malkaan", "harrowhand", Role.SALES));
  }

  public void saveDatabase(Database database) throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream("database.ser");
    ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
    out.writeObject(database);
    out.close();
    fileOutputStream.close();
    System.out.printf("database is saved");
  }

  public User authenticateUser(String username, String password) {
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return user; // User authenticated
      }
    }
    return null; // Authentication failed
  }

  public List<Product> getProducts() {
    return products;
  }
  public List<Order> getOrders() {
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

package com.example.javaendassignment.database;

import com.example.javaendassignment.model.Product;
import com.example.javaendassignment.model.Role;
import com.example.javaendassignment.model.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
  private List<User> users;
  private List<Product> products;

  public Database() {
    users = new ArrayList<>();
    products = new ArrayList<>();

    products.add(new Product(10, "banana", "fruits", 1.15, "This bananas are the best. Just trust me, old good monkey..."));
    users.add(new User("Kaldor", "Draigo", "kaldor_graigo@imperium.terra", "a", "1", "+666115634", Role.MANAGER));
    users.add(new User("Malkaan", "Feirros", "malkaan_feirros@imprerium.terra", "Malkaan", "harrowhand", "+666234565", Role.SALES));
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
}

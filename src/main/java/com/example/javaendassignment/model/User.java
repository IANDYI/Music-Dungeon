package com.example.javaendassignment.model;

import java.io.Serializable;

public class User implements Serializable {
  private final String firstname;
  private final String lastname;
  private final String password;
  private final String username;
  private final Role role;

  public User(String firstname, String lastname, String username, String password, Role role) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public String getName() {
    return (firstname + " " + lastname);
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public Role getRole() {
    return role;
  }
}

package com.example.javaendassignment.model;

import java.io.Serializable;

public class User implements Serializable {
  private final String firstname;
  private final String lastname;
  private final String email;
  private final String password;
  private final String username;
  private final String phoneNumber;
  private final Role role;

  public User(String firstname, String lastname, String email, String username, String password, String phoneNumber, Role role) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.username = username;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.role = role;
  }

  public String getName() {
    return (firstname + " " + lastname);
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Role getRole() {
    return role;
  }
}

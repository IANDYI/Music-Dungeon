package com.example.javaendassignment.model;

import java.io.Serializable;

public class Customer implements Serializable {
  private final String firstname;
  private final String lastname;
  private final String email;
  private final String phoneNumber;

  public Customer(String firstname, String lastname, String email, String phoneNumber) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return (firstname + " " + lastname);
  }
  @Override
  public String toString() {
    return (firstname + " " + lastname + " " + email + " " + phoneNumber);
  }
}

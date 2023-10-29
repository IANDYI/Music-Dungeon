package com.example.javaendassignment.controller;

import com.example.javaendassignment.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
  @FXML
  private Label welcomeLabel;
  @FXML
  private Label roleLabel;
  @FXML
  private Label dateTimeLabel;
  private User user;

  public void setData(User user) {
    this.user = user;
  }
  private void displayUserInfo() {
    welcomeLabel.setText("Welcome, " + user.getName() + " !");
    roleLabel.setText("Your role is: " + user.getRole().toString() + ".");
    dateTimeLabel.setText("It is now: " + setDateTime());
  }

  private String setDateTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return LocalDateTime.now().format(formatter);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    displayUserInfo();
  }
}

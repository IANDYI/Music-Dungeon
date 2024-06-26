package com.example.javaendassignment.controller;

import com.example.javaendassignment.model.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable, Controller {
  @FXML
  private Label welcomeLabel;
  @FXML
  private Label roleLabel;
  @FXML
  private Label dateTimeLabel;
  private User user;

  @Override
  public void setData(Object user) {
    this.user = (User) user;
  }
  private void displayUserInfo() {
    welcomeLabel.setText("Welcome, " + user.getName() + " !");
    roleLabel.setText("Your role is: " + user.getRole().toString() + ".");
    displayTime();
  }

  private void displayTime() {
    dateTimeLabel.setText("It is now: " + setDateTime());
  }

  private String setDateTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return LocalDateTime.now().format(formatter);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    displayUserInfo();
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> displayTime()));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }
}

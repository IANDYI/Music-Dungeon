package com.example.javaendassignment.controller;

import com.example.javaendassignment.MusicApplication;
import com.example.javaendassignment.database.Database;
import com.example.javaendassignment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

  @FXML
  private Label errorMessage;
  @FXML
  private TextField usernameField;
  @FXML
  private PasswordField passwordField;
  private Database database;

  public void setDatabase(Database database) {
    this.database = database;
  }

  @FXML
  private void clickLogin(ActionEvent event) throws IOException {
    String enteredUsername = usernameField.getText();
    String enteredPassword = passwordField.getText();

    //Note: there is no "Invalid username" message, because of the security reasons.
    if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
      showError("Please fill both fields");
    }
    else {
      User user = database.authenticateUser(enteredUsername, enteredPassword);
      if (user != null) {
          createStage(user);
          closeWindow(event);
      }
      else {
        showError("Incorrect combination");
      }
    }
  }

  private void createStage(User authenticatedUser) throws IOException {
    FXMLLoader mainLoader = new FXMLLoader(MusicApplication.class.getResource("main-view.fxml"));
    Stage mainStage = new Stage();
    mainLoader.setControllerFactory(controllerClass -> new MainController(authenticatedUser, mainStage));
    mainStage.setScene(new Scene(mainLoader.load()));
    mainStage.setResizable(false);
    mainStage.show();
  }

  private void closeWindow(ActionEvent actionEvent) { //Using event that triggered method to avoid additional dependencies
    Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  private void showError(String error) {
    errorMessage.setText(error);
  }
}
package com.example.javaendassignment.controller;

import com.example.javaendassignment.MusicApplication;
import com.example.javaendassignment.database.Database;
import com.example.javaendassignment.exception.AccountLockedException;
import com.example.javaendassignment.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

  @FXML
  private Label errorMessage;
  @FXML
  private TextField usernameField;
  @FXML
  private PasswordField passwordField;
  private Database database;
  private int unsuccessfulAttemptCount = 0;

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
    } else {
      try {
        // Attempt to authenticate the user
        User user = database.authenticateUser(enteredUsername, enteredPassword);

        if (user != null) {
          createStage(user);
          closeWindow(event);
        } else {
          // Authentication failed, increment the unsuccessful attempt count
          handleUnsuccessfulAttempt();
        }
      } catch (AccountLockedException e) {
        // Handle the AccountLockedException by displaying an alert dialog
        showAccountLockAlert(e.getMessage());
      }
    }
  }

  private void createStage(User authenticatedUser) throws IOException {
    FXMLLoader mainLoader = new FXMLLoader(MusicApplication.class.getResource("main-view.fxml"));
    Stage mainStage = new Stage();
    mainLoader.setControllerFactory(controllerClass -> new MainController(database, authenticatedUser, mainStage));
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

  private void handleUnsuccessfulAttempt() throws AccountLockedException {
    unsuccessfulAttemptCount++;

    int maxAttempts = 4;
    if (unsuccessfulAttemptCount >= maxAttempts) {
      throw new AccountLockedException("Your account has been locked");
    }
    else {
      showError("Incorrect combination. Attempt " + unsuccessfulAttemptCount + " of " + maxAttempts);
    }
  }

  private void showAccountLockAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Exception");
    alert.setHeaderText("Account Locked");
    alert.setContentText(message);

    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(okButton);

    Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
    okBtn.setOnAction(e -> Platform.exit());

    alert.showAndWait();
  }
}
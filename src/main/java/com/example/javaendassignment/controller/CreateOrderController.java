package com.example.javaendassignment.controller;

import com.example.javaendassignment.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateOrderController {
  @FXML
  private TextField inputPhoneNumber;
  @FXML
  private TextField inputEmail;
  @FXML
  private TextField inputLastName;
  @FXML
  private TextField inputFirstName;
  private Database database;
  public void setData(Database database){
    this.database = database;
  }

  @FXML
  private void onAddProductClick(ActionEvent actionEvent) {
    //
  }

  @FXML
  private void onDeleteProductClick(ActionEvent actionEvent) {
    //
  }

  @FXML
  private void onCreateOrderClick(ActionEvent actionEvent) {
    //
  }
}

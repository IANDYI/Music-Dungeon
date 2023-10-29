package com.example.javaendassignment.controller;

import com.example.javaendassignment.MusicApplication;
import com.example.javaendassignment.database.Database;
import com.example.javaendassignment.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateOrderController implements Initializable, Controller {
  @FXML
  private TableView<Product> tableProducts;
  @FXML
  private Label labelMessage;
  @FXML
  private TextField inputPhoneNumber;
  @FXML
  private TextField inputEmail;
  @FXML
  private TextField inputLastName;
  @FXML
  private TextField inputFirstName;
  private Database database;
  private final ObservableList<Product> orderedProductsList = FXCollections.observableArrayList();
  public void setData(Object database){
    this.database = (Database) database;
  }
  @FXML
  private void onAddProductClick() throws IOException {
    FXMLLoader loader = new FXMLLoader(MusicApplication.class.getResource("add-product-dialog.fxml"));
    DialogPane addProductDialog = loader.load();
    AddProductController controller = loader.getController();
    controller.setDialog(database);
    Dialog<Product> dialog = new Dialog<>();
    dialog.getDialogPane().setContent(addProductDialog);
    dialog.setTitle("Add Product");
    dialog.showAndWait();
  }
  @FXML
  private void onDeleteProductClick(ActionEvent actionEvent) {
    //
  }
  @FXML
  private void onCreateOrderClick(ActionEvent actionEvent) {
    //
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tableProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    tableProducts.setItems(orderedProductsList);
  }
}

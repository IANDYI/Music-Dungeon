package com.example.javaendassignment.controller;

import com.example.javaendassignment.database.Database;
import com.example.javaendassignment.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AddProductController {
  @FXML
  private TableColumn<Product, Integer> stockColumn;
  @FXML
  private TableColumn<Product, String>nameColumn;
  @FXML
  private TableColumn<Product, String> categoryColumn;
  @FXML
  private TableColumn<Product, Double> priceColumn;
  @FXML
  private TableColumn<Product, String> descriptionColumn;
  @FXML
  private TableView<Product> tableProducts;
  @FXML
  private Spinner<Integer> inputQuantity;
  @FXML
  private Label labelMessage;
  private Database database;
  public void setDialog(Database database){
    this.database = database;
    setTableProducts();
  }
  private void setTableProducts() {
    List<Product> products = database.getProducts();
    ObservableList<Product> observableProducts = FXCollections.observableArrayList(products);
    tableProducts.setItems(observableProducts);

    stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
  }
}

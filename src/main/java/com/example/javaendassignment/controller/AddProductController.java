package com.example.javaendassignment.controller;

import com.example.javaendassignment.database.Database;
import com.example.javaendassignment.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
  private int currentQuantity;
  private Database database;
  private CreateOrderController orderController;
  public void setDialog(Database database, CreateOrderController orderController){
    this.database = database;
    this.orderController = orderController;
    setTableProducts();
    initializeSpinner();
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

  private void initializeSpinner() {
    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
    valueFactory.setValue(1);
    inputQuantity.setValueFactory(valueFactory);
    currentQuantity = inputQuantity.getValue();
    labelMessage.setText(Integer.toString(currentQuantity));
    inputQuantity.valueProperty().addListener((observableValue, integer, t1) -> {
      currentQuantity = inputQuantity.getValue();
      labelMessage.setText(Integer.toString(currentQuantity));
    });
  }


  @FXML
  private void onOkClick(ActionEvent event) {
    try{
      Product selection = tableProducts.getSelectionModel().getSelectedItem();
      if(selection == null){
        labelMessage.setText("Please Choose a Product");
        return;
      }
      if(currentQuantity <= selection.getStock()){
        Product orderedProduct = new Product(currentQuantity, selection.getName(), selection.getCategory(), selection.getPrice());
        database.reduceProductStock(selection.getName(), currentQuantity);
        orderController.getOrderedProduct(orderedProduct);
        labelMessage.setText("");
        onCancelClick(event);
      }
      else {
        labelMessage.setText("Not Enough Stock");
      }
    }catch (Exception ex){
      labelMessage.setText("Error Occured While Adding Product");
      ex.printStackTrace();
    }
  }

  @FXML
  private void onCancelClick(ActionEvent actionEvent) {
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    stage.close();
  }
}

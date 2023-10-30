package com.example.javaendassignment.controller;

import com.example.javaendassignment.database.Database;
import com.example.javaendassignment.model.Product;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProductInventoryController implements Initializable, Controller {
  @FXML
  public TextField inputStock;
  @FXML
  public TextField inputCategory;
  @FXML
  public TextField inputDescription;
  @FXML
  public TextField inputPrice;
  @FXML
  public TextField inputName;
  @FXML
  public Label labelMessage;
  @FXML
  private TableView<Product> tableInventory;
  @FXML
  private TableColumn<Product, Integer> columnStock;
  @FXML
  private TableColumn<Product, String> columnName;
  @FXML
  private TableColumn<Product, String> columnCategory;
  @FXML
  private TableColumn<Product, Double> columnPrice;
  @FXML
  private TableColumn<Product, String> columnDescription;
  private ObservableList<Product> observableProducts;
  private Database database;

  public void setData(Object data) {
    database = (Database) data;
  }

  public void onAddClick() {

    if(inputStock.getText().isEmpty() || columnName.getText().isEmpty() || columnCategory.getText().isEmpty() || columnPrice.getText().isEmpty() || columnDescription.getText().isEmpty()){
      displayMessage("Please Fill All Fields");
    }
    else {
      try {
        int stock = Integer.parseInt(inputStock.getText());
        String productName = inputName.getText();
        String productCategory = inputCategory.getText();
        double productPrice = Double.parseDouble(inputPrice.getText());
        String productDescription = inputDescription.getText();

        Product product =new Product(stock,productName,productCategory,productPrice,productDescription);
        database.addProduct(product);
        observableProducts.add(product);
        clearTextFields();
        displayMessage("Product Successfully added!");
      } catch (NumberFormatException e) {
        displayMessage("Incorrect input");
      }
    }
  }

  private void clearTextFields() {
    inputStock.clear();
    inputName.clear();
    inputCategory.clear();
    inputPrice.clear();
    inputDescription.clear();
  }

  public void onEditClick() {
    ObservableList<Product> currentTableData = tableInventory.getItems();
    if(inputStock.getText().isEmpty() || columnName.getText().isEmpty() || columnCategory.getText().isEmpty() || columnPrice.getText().isEmpty() || columnDescription.getText().isEmpty()) {
      displayMessage("Please Fill All Fields");
    }
    else {
      for (Product product : currentTableData) {
        if(Objects.equals(product.getName(), inputName.getText())){
          try {
            product.setStock(Integer.parseInt(inputStock.getText()));
            product.setName(inputName.getText());
            product.setCategory(inputCategory.getText());
            product.setPrice(Double.parseDouble(inputPrice.getText()));
            product.setDescription(inputDescription.getText());
            tableInventory.setItems(currentTableData);
            tableInventory.refresh();
            clearTextFields();
            tableInventory.getSelectionModel().clearSelection();
            displayMessage("Product was successfully changed");
            break;
          } catch (NumberFormatException e){
            displayMessage("Please check correctness of your input");
          }
        }
      }
    }
  }

  public void onDeleteClick() {
    if(!tableInventory.getSelectionModel().getSelectedItems().isEmpty()) {
      try{
        ObservableList<Product> selectedProducts = tableInventory.getSelectionModel().getSelectedItems();
        for(Product product:selectedProducts){
          database.removeProduct(product);
        }
        observableProducts.removeAll(selectedProducts);
        displayMessage("Product/s Successfully Deleted!");
      }catch (Exception e){
        displayMessage("Product/s Deletion Failed!");
      }
    }
    else{
      displayMessage("Please select product");
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    observableProducts = FXCollections.observableArrayList(database.getProducts());
    tableInventory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    tableInventory.setItems(observableProducts);

    columnStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
  }

  private void displayMessage(String message) {
    labelMessage.setText(message);
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(2), event -> labelMessage.setText(""))
    );
    timeline.play();
  }

  public void rowClicked() {
    Product product = tableInventory.getSelectionModel().getSelectedItem();
    inputStock.setText(String.valueOf(product.getStock()));
    inputName.setText(String.valueOf(product.getName()));
    inputCategory.setText(String.valueOf(product.getCategory()));
    inputPrice.setText(String.valueOf(product.getPrice()));
    inputDescription.setText(String.valueOf(product.getDescription()));
  }
}

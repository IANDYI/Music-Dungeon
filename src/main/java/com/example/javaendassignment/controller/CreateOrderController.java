package com.example.javaendassignment.controller;

import com.example.javaendassignment.MusicApplication;
import com.example.javaendassignment.database.Database;
import com.example.javaendassignment.model.Customer;
import com.example.javaendassignment.model.Order;
import com.example.javaendassignment.model.Product;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateOrderController implements Initializable, Controller {
  @FXML
  private TableColumn<Product, Integer> columnQuantity;
  @FXML
  private TableColumn<Product, String> columnName;
  @FXML
  private TableColumn<Product, String> columnCategory;
  @FXML
  private TableColumn<Product, Double> columnPrice;
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
  private final ObservableList<Product> observableProducts = FXCollections.observableArrayList();
  private Database database;
  public void setData(Object database){
    this.database = (Database) database;
  }
  @FXML
  private void onAddProductClick() throws IOException {
    FXMLLoader loader = new FXMLLoader(MusicApplication.class.getResource("add-product-dialog.fxml"));
    Parent addProductDialog = loader.load();
    AddProductController controller = loader.getController();
    controller.setDialog(database, this);
    Dialog<Product> dialog = new Dialog<>();
    dialog.getDialogPane().setContent(addProductDialog);
    dialog.setTitle("Add Product");
    dialog.showAndWait();
  }
  @FXML
  private void onDeleteProductClick() {
    try {
      Product selectedProduct = tableProducts.getSelectionModel().getSelectedItem();
      if (selectedProduct != null) {
        observableProducts.remove(selectedProduct);
        database.increaseProductStock(selectedProduct.getName(), selectedProduct.getQuantity());
      }
    } catch (Exception e){
      displayMessage("Error Occurred While Deleting the Product, Please Try Again");
    }
  }
  @FXML
  private void onCreateOrderClick() {
    String lastName = inputLastName.getText();
    String emailAddress = inputEmail.getText();
    String phoneNumber = inputPhoneNumber.getText();
    String firstName = inputFirstName.getText();
    if (firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || phoneNumber.isEmpty()){
      displayMessage("Please fill all fields");
    }
    else if (observableProducts.isEmpty()) {
      displayMessage("Your didn't choose any products");
    }
    else {
      try{
        Customer customer = new Customer(firstName,lastName,emailAddress,phoneNumber);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String dateTime = now.format(formatter);
        Order order = new Order(dateTime,customer,new ArrayList<>(observableProducts));

        database.addOrder(order);
        cleanPage();
        displayMessage("Your order was placed");
      }catch (Exception ex){
        displayMessage("Error Occurred While Creating Order");
      }
    }
  }

  public void getOrderedProduct(Product product) {
    observableProducts.add(product);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tableProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    tableProducts.setItems(observableProducts);

    columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    columnPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
  }

  private void cleanPage(){
    inputFirstName.clear();
    inputLastName.clear();
    inputEmail.clear();
    inputPhoneNumber.clear();
    tableProducts.setItems(FXCollections.observableArrayList());
    labelMessage.setText("");
  }

  private void displayMessage(String message) {
    labelMessage.setText(message);
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(2), event -> labelMessage.setText(""))
    );
    timeline.play();
  }
}

package com.example.javaendassignment.controller;

import com.example.javaendassignment.MusicApplication;
import com.example.javaendassignment.database.Database;
import com.example.javaendassignment.model.Role;
import com.example.javaendassignment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

  @FXML
  private Button btnProductInventory;
  @FXML
  private Button btnCreateOrder;
  @FXML
  private BorderPane layout;
  @FXML
  private void onDashBoardClick() {
    loadScene("dashboard-view.fxml", user);
  }
  @FXML
  private void onCreateOrderClick() {
    loadScene("create-order-view.fxml", database);
  }
  @FXML
  private void onProductInventoryClick() {
    //
  }
  @FXML
  private void onOrderHistoryClick() {
    //
  }
  @FXML
  private void onLogoutClick(ActionEvent event) {
    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }

  Database database;
  private final User user;

  public MainController(User user) {
    this.user = user;
  }
  private void limitAccess() {
    if(user.getRole() == Role.MANAGER){
      btnCreateOrder.setDisable(false);
    }
    else {
      btnProductInventory.setDisable(true);
    }
  }
  private void loadScene(String fxmlPath, Object data) {
    try {
      FXMLLoader loader = new FXMLLoader(MusicApplication.class.getResource(fxmlPath));

      loader.setControllerFactory(controllerClass -> {
        try {
          Object controller = controllerClass.getConstructor().newInstance();
          checkController(controller, data);
          return controller;

        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });

      Parent root = loader.load();
      layout.setCenter(root);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void checkController(Object controller, Object data) {
    if(controller instanceof DashboardController) {
      ((DashboardController) controller).setData((User) data);
    } else if (controller instanceof CreateOrderController) {
      ((CreateOrderController) controller).setData((Database) data);
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    database = new Database();
    limitAccess();
    loadScene("dashboard-view.fxml", user);
  }
}

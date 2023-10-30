package com.example.javaendassignment;

import com.example.javaendassignment.controller.LoginController;
import com.example.javaendassignment.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MusicApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("login-view.fxml"));
    stage.setScene(new Scene(fxmlLoader.load()));
    Database database = new Database();
    stage.setResizable(false);
    LoginController controller = fxmlLoader.getController();
    controller.setDatabase(database);
    stage.setTitle("Login");
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
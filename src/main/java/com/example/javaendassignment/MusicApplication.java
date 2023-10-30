package com.example.javaendassignment;

import com.example.javaendassignment.controller.LoginController;
import com.example.javaendassignment.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MusicApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException, ClassNotFoundException {
    FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("login-view.fxml"));
    stage.setScene(new Scene(fxmlLoader.load()));
    Database database;
    if(readDatabase() != null){
      database = readDatabase();
    } else {
      database = new Database();
    }
    stage.setResizable(false);
    LoginController controller = fxmlLoader.getController();
    controller.setDatabase(database);
    stage.setTitle("Login");
    stage.show();
  }

  private Database readDatabase() throws IOException, ClassNotFoundException {
    FileInputStream fileInputStream = new FileInputStream("database.ser");
    ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
    Database database = (Database) inputStream.readObject();
    inputStream.close();
    fileInputStream.close();
    return database;
  }

  public static void main(String[] args) {
    launch();
  }
}
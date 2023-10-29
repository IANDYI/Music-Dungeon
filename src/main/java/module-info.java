module com.example.javaendassignment {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.logging;


  opens com.example.javaendassignment.controller to javafx.fxml;
  opens com.example.javaendassignment.model to javafx.base;
  exports com.example.javaendassignment;
}
module com.example.javaendassignment {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.logging;


  opens com.example.javaendassignment.controller to javafx.fxml;
  exports com.example.javaendassignment;
}
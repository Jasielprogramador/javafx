open module javafx {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.swing;


    exports ehu.isad;
}

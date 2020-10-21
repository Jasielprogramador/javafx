package ehu.isad.controller.ui;

import ehu.isad.Book;
import ehu.isad.Main;
import ehu.isad.controller.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class KautotuKud implements Initializable {

    // Reference to the main application.
    private Main mainApp;

    @FXML
    private ComboBox comboZerbitzua;

    @FXML
    private TextField txtErabiltzaile;

    @FXML
    private PasswordField txtPasahitza;


    @FXML
    private Button kautotuBotoia;

    @FXML
    private Button kautotuBotoia2;


    @FXML
    void onClick2(ActionEvent event) throws SQLException {
        comboZerbitzua.getItems().remove(comboZerbitzua.getValue());
        ZerbitzuKud.getInstance().kenduZerbitzua(comboZerbitzua.getValue().toString());
    }

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    @FXML
    public void onClick(ActionEvent actionEvent) {

        comboZerbitzua.getItems().add(comboZerbitzua.getValue());
        ZerbitzuKud.getInstance().gehitu(comboZerbitzua.getValue().toString());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Book> herrialdeakList = ZerbitzuKud.getInstance().lortuLiburuak();
        ObservableList<Book> herrialdeak = FXCollections.observableArrayList(herrialdeakList);

        comboZerbitzua.setItems( herrialdeak );
        comboZerbitzua.setEditable(true);

    }

}

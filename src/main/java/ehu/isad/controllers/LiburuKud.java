package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    // Reference to the main application.
    private Main mainApp;


    @FXML
    public ComboBox comboZerbitzua;


    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    @FXML
    public void onClick(ActionEvent actionEvent) throws IOException {
        mainApp.xehetasunakErakutsi();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(
                new Book("1491910399", "R for Data Science"),
                new Book("1491946008", "Fluent Python"),
                new Book("9781491906187", "Data Algorithms"),
                new Book("1491978236","Natural Language Processing with PyTorch"),
                new Book("9781491920497","Blockchain: Blueprint for a New Economy"));

        comboZerbitzua.setItems(books);
        comboZerbitzua.getSelectionModel().selectFirst();
        comboZerbitzua.setEditable(false);

        comboZerbitzua.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book book) {
                if (book==null)
                    return "";
                return book.getTitle();

            }

            @Override
            public Book fromString(String string) {
                return null;
            }
        });
    }








}
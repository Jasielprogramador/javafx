package ehu.isad.controller.ui;

import ehu.isad.Book;
import ehu.isad.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class XehetasunakKud implements Initializable {

    private Main mainApp;



    @FXML
    private Label lblIzenburua;


    @FXML
    private Label lblArgitaletxea;

    @FXML
    private Label lblOrriKop;

    @FXML
    private ImageView imgIrudia;

    @FXML
    private Button btnAtzera;

    @FXML
    public void onClick(ActionEvent actionEvent) throws IOException {
        mainApp.mainErakutsi();
    }


    public Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }

    public String saveToFile(Image image, String isbn) {
        File outputFile = new File("~/home/asiertxu/Descargas/"+isbn+".jpg");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "jpg", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputFile.getAbsolutePath();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void putInfo(Book b) throws IOException {
        lblIzenburua.setText(b.getDetails().getTitle());
        lblOrriKop.setText(Integer.toString(b.getDetails().getNumber_of_pages()));
        lblArgitaletxea.setText(b.getDetails().getPublishers()[0]);
        //imgIrudia.setImage(createImage(b.getThumbnail_url().replace("S","M")));
    }
    public void setMainApp(Main main) {
        this.mainApp = main;
    }

}


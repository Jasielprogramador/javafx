package ehu.isad.controller.ui;

import ehu.isad.Book;
import ehu.isad.Main;
import ehu.isad.controller.db.ZerbitzuKud;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void putInfo(Book b,String isbn) throws IOException, SQLException {
        lblIzenburua.setText(b.getDetails().getTitle());
        lblOrriKop.setText(Integer.toString(b.getDetails().getNumber_of_pages()));
        lblArgitaletxea.setText(b.getDetails().getPublishers()[0]);


        if (ZerbitzuKud.getInstance().liburuaJadaKargatuta(isbn)) {
            imgIrudia.setImage(ZerbitzuKud.getInstance().irudiaLortu(b.getThumbnail_url()));

        }
        else{
            imgIrudia.setImage(ZerbitzuKud.getInstance().createImage(b.getThumbnail_url().replace("S","M")));
        }



    }
    public void setMainApp(Main main) {
        this.mainApp = main;
    }

}


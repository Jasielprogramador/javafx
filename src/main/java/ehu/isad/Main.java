package ehu.isad;

import com.google.gson.Gson;
import ehu.isad.controllers.LiburuKud;
import ehu.isad.controllers.XehetasunakKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main extends Application {

  private Parent liburuUI;
  private Parent xehetasunUI;
  private Stage stage;
  private Scene sceneLiburuak;
  private Scene sceneXehetasunak;
  public LiburuKud liburuKud;
  public XehetasunakKud xehetasunakKud;
  private Book book;




  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;
    pantailakKargatu();


    Book b=(Book)liburuKud.comboZerbitzua.getValue();

    stage.setScene(sceneLiburuak);
    stage.show();
  }


  public void mainErakutsi(){
    stage.setScene(sceneLiburuak);
    stage.show();
  }

  public void xehetasunakErakutsi() throws IOException {
    liburuaLortu();
    xehetasunakKud.putInfo(this.book);
    stage.setScene(sceneXehetasunak);
    stage.show();
  }


  private void pantailakKargatu() throws IOException {

    FXMLLoader loaderLiburu = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
    liburuUI = (Parent) loaderLiburu.load();
    liburuKud = loaderLiburu.getController();
    liburuKud.setMainApp(this);
    sceneLiburuak=new Scene(liburuUI);


    FXMLLoader loaderXehetasun = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
    xehetasunUI = (Parent) loaderXehetasun.load();
    xehetasunakKud= loaderXehetasun.getController();
    xehetasunakKud.setMainApp(this);
    sceneXehetasunak=new Scene(xehetasunUI);

  }

  //Liburu osoa ematen dizu
  public void liburuaLortu() throws IOException {
    Book b=(Book)liburuKud.comboZerbitzua.getValue();
    readFromUrl(b.isbn);
  }



  public void readFromUrl(String isbn) throws IOException {
    URL api;
    String inputLine = "";

    try {
      api = new URL(" https://openlibrary.org/api/books?bibkeys=ISBN:"+isbn+"&jscmd=details&format=json");
      URLConnection yc = api.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
      inputLine = in.readLine();

      String[] zatiak = inputLine.split("ISBN:"+isbn+"\":");
      inputLine = zatiak[1].substring(0, zatiak[1].length()-1);

      Gson gson = new Gson();
      this.book = gson.fromJson(inputLine, Book.class);

      in.close();

    }
    catch (MalformedURLException e){
      e.printStackTrace();
    }

  }



  public static void main(String[] args) {
    launch(args);
  }

}

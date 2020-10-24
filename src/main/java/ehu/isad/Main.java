package ehu.isad;

import com.google.gson.Gson;
import ehu.isad.controller.ui.XehetasunakKud;
import ehu.isad.controller.db.ZerbitzuKud;
import ehu.isad.controller.ui.LiburuakKud;
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
import java.sql.SQLException;

public class Main extends Application {

  private Parent liburuUI;
  private Parent xehetasunUI;
  private Stage stage;
  private Scene sceneLiburuak;
  private Scene sceneXehetasunak;
  public LiburuakKud liburuakKud;
  public XehetasunakKud xehetasunakKud;
  private Book book;


  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;
    pantailakKargatu();

    stage.setScene(sceneLiburuak);
    stage.show();
  }


  public void mainErakutsi(){
    stage.setScene(sceneLiburuak);
    stage.setX(500);
    stage.setY(200);
    stage.show();
  }

  //Liburu osoa ematen dizu, xehetasunak
  public void liburuaLortu() throws IOException, SQLException {
    Book b=ZerbitzuKud.getInstance().lortuLiburua(liburuakKud.txtTestua.getText());
    readFromUrl(b.isbn);
  }

  //AQUI HACES LO DE MIRAR SI YA ESTA DENTRO O NO
  public void xehetasunakErakutsi() throws IOException, SQLException {

    baldintzak();
    stage.setScene(sceneXehetasunak);
    stage.setX(300);
    stage.setY((100));
    stage.show();
  }

  private void baldintzak() throws SQLException, IOException {

    Book b=ZerbitzuKud.getInstance().lortuLiburua(liburuakKud.txtTestua.getText());
    if(ZerbitzuKud.getInstance().liburuaJadaKargatuta(b.getIsbn())){
      Book book1=new Book(b.isbn,b.title);
      Book b2 = ZerbitzuKud.getInstance().jadaKargatutakoLiburuaErabili(book1);
      System.out.println("Jada datu basean kargatuta");
      xehetasunakKud.putInfo(b2,b.getIsbn());
    }
    else{
      System.out.println("Datu basean kargatu");
      liburuaLortu();
      String path=ZerbitzuKud.getInstance().irudiaGorde(this.book.getThumbnail_url().replace("S","M"),b.getIsbn());
      this.book.setThumbnail_url(path);
      ZerbitzuKud.getInstance().datuBaseanSartu(b,this.book);
      xehetasunakKud.putInfo(this.book,b.getIsbn());
    }

  }


  private void pantailakKargatu() throws IOException {

    FXMLLoader loaderLiburu = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
    liburuUI = (Parent) loaderLiburu.load();
    liburuakKud = loaderLiburu.getController();
    liburuakKud.setMainApp(this);
    sceneLiburuak=new Scene(liburuUI);


    FXMLLoader loaderXehetasun = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
    xehetasunUI = (Parent) loaderXehetasun.load();
    xehetasunakKud= loaderXehetasun.getController();
    xehetasunakKud.setMainApp(this);
    sceneXehetasunak=new Scene(xehetasunUI);

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

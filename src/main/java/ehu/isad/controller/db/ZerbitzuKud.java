package ehu.isad.controller.db;

import ehu.isad.Book;
import ehu.isad.BookDetails;
import ehu.isad.utils.Utils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }

    public List<Book> lortuLiburuak() {

        String query = "select isbn,title from liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Book> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                String kodea = rs.getString("isbn");
                String izena = rs.getString("title");
                System.out.println(kodea + ":" + izena);
                emaitza.add(new Book(kodea,izena));

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public boolean liburuaJadaKargatuta(String isbn) throws SQLException {

        String query = "select orriKop from liburua where isbn='" + isbn + "'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {
            while (rs.next()) {
                int orriKopurua = rs.getInt("orriKop");
                if(orriKopurua>0){
                     return true;
                }
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return false;

    }

    public Book jadaKargatutakoLiburuaErabili(Book b) {

        String query = "select isbn,title,orriKop,argitaletxea,irudia from liburua where isbn="+b.getIsbn()+";";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {
                while(rs.next()) {
                    String kodea = rs.getString("isbn");
                    String izena = rs.getString("title");
                    int orriKop = rs.getInt("orriKop");
                    String argitaletxea = rs.getString("argitaletxea");
                    String irudia=rs.getString("irudia");

                    b=gordeDatuak(kodea,izena,irudia,orriKop,argitaletxea);
                }

        } catch(SQLException | IOException throwables){
            throwables.printStackTrace();
        }

        return b;
    }


    public Book gordeDatuak(String kodea,String izena,String irudia,int orriKop,String argitaletxea) throws IOException, SQLException {
        //Irudia gorde

        Book b=new Book(kodea,izena);
        BookDetails det = new BookDetails();
        b.setDetails(det);
        b.setThumbnail_url(irudia);
        b.getDetails().setNumber_of_pages(orriKop);
        b.getDetails().setTitle(izena);
        String[] e = new String[20];
        e[0] = argitaletxea;
        b.getDetails().setPublishers(e);

        return b;
    }
    public void datuBaseanSartu(Book b,Book details){
        String query = "update liburua set orriKop = '"+details.getDetails().getNumber_of_pages()+"' , argitaletxea = '"+details.getDetails().getPublishers()[0].replace("\'","\'\'")+"' , irudia = '"+details.getThumbnail_url().replace("S","M")+"' where (isbn = '"+b.getIsbn()+"');";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);

        System.out.println("datu basea ondo kargatuta");

    }

    //IRUDIAK KARGATZEKO METODOAK
    public Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }

    private String saveToFile(Image image, String isbn) {
        Properties properties = Utils.lortuEzarpenak();
        File outputFile = new File(properties.getProperty("imagePath")+isbn+".jpg");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "jpg", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputFile.getAbsolutePath();
    }

    public String irudiaGorde(String irudia,String kodea) throws IOException {
        Image i=createImage(irudia);
        String path=saveToFile(i,kodea);
        return path;
    }

    public Image irudiaLortu(String path){
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(img, null);
        return image;
    }



}


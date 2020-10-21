package ehu.isad.controller.db;

import ehu.isad.Book;
import ehu.isad.BookDetails;
import ehu.isad.controller.ui.DBKudeatzaile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public boolean liburuaJadaKargatuta(String isbn) {

        String query = "select count(*) from liburua where isbn='" + isbn + "' and orriKop>0";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        int count = 0;
        try {
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        if(count==0){
            return false;
        }else{
            return true;
        }
    }

    public void jadaKargatutakoLiburuaErabili(Book b) {

        String query = "select isbn,title,orriKop,argitaletxea,irudia from liburua where isbn='"+b.getIsbn()+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        
        try {
            String kodea = rs.getString("isbn");
            String izena = rs.getString("title");
            int orriKop=rs.getInt("orriKop");
            String argitaletxea=rs.getString("argitaletxea");
            String irudia=rs.getString("irudia");

            b.getDetails().setNumber_of_pages(orriKop);
            b.getDetails().setTitle(izena);
            String[] e=new String[20];
            e[0]=argitaletxea;
            b.getDetails().setPublishers(e);

        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

    }

    public void datuBaseanSartu(Book b){
        String query = "insert into liburua (isbn,title,orriKop,argitaletxea,irudia) values ('"+b.getIsbn()+"','"+b.getTitle()+"','"+b.getDetails().getNumber_of_pages()+"','"+b.getDetails().getPublishers()[0]+"','"+b.getThumbnail_url()+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);


    }
}


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

    public boolean liburuaJadaKargatuta(String isbn) throws SQLException {

        String query = "select orriKop from liburua where isbn='" + isbn + "'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        //int orriKopurua=rs.getInt("orriKop");

        boolean emaitza=false;

        try {
            while (rs.next()) {

                int orriKopurua = rs.getInt("orriKop");
                System.out.println(orriKopurua);

                if(orriKopurua>0){
                    emaitza=true;
                }

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;

    }

    public void jadaKargatutakoLiburuaErabili(Book b,Book details) {

        String query = "select isbn,title,orriKop,argitaletxea,irudia from liburua where isbn="+b.getIsbn()+";";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {

            String kodea = rs.getString("isbn");
            String izena = rs.getString("title");
            int orriKop=rs.getInt("orriKop");
            String argitaletxea=rs.getString("argitaletxea");
            String irudia=rs.getString("irudia");

            details.getDetails().setNumber_of_pages(orriKop);
            details.getDetails().setTitle(izena);
            String[] e=new String[20];
            e[0]=argitaletxea;
            details.getDetails().setPublishers(e);

        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

    }

    public void datuBaseanSartu(Book liburua,Book details){
        String query = "update liburua set orriKop = '"+details.getDetails().getNumber_of_pages()+"' , argitaletxea = '"+details.getDetails().getPublishers()[0]+"' , irudia = '"+liburua.getThumbnail_url().replace("S","M")+"' where (isbn = '"+liburua.getIsbn()+"');";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);

        System.out.println("base de datos");



    }
}


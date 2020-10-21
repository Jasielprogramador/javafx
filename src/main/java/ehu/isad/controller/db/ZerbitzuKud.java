package ehu.isad.controller.db;

import ehu.isad.Book;
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

        String query = "select isbn,title  from liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Book> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                String kodea = rs.getString("isbn");
                String izena = rs.getString("izena");
                emaitza.add(new Book(kodea,izena));

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void gehitu(String s){
        String query = "insert into zerbitzuak.services (izena) values ('"+s+"');";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();

        dbKudeatzaile.execSQL(query);
    }
}

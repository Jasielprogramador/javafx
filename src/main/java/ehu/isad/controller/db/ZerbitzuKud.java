package ehu.isad.controller.db;

import ehu.isad.controller.ui.KautotuKud;

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

    public List<String> lortuZerbitzuak() {

        String query = "select id, izena from services";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                int kodea = rs.getInt("id");
                String izena = rs.getString("izena");

                System.out.println(kodea + ":" + izena);
                emaitza.add(izena);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void kendu(String s){
        String query = "delete from zerbitzuak.services where izena='"+s+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public void gehitu(int i,String s){
        String query = "INSERT INTO zerbitzuak.services (id, izena) VALUES ("+i+","+s+");";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }
}

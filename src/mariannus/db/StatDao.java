package mariannus.db;

import javafx.scene.chart.XYChart;
import mariannus.model.Item;
import mariannus.model.ObjectsStorage;

import java.sql.*;

/**
 * Created by Tim on 29.6.2016.
 */
public class StatDao {
    private Connection con;
    private Statement st;
    private static StatDao instance;

    public static StatDao getInstance() {
        if (instance == null) {
            instance = new StatDao();
            return instance;
        }
        else return instance;
    }

    private StatDao() {
    }

    public float dayPrice(String date){
        PreparedStatement pst=null;
        ResultSet rs = null;
        float suma = 0;
        try{
            con = Conection.getConnection();
            pst = con.prepareStatement("SELECT sum(cena) as suma FROM items, olist, `order` WHERE items.idItem=olist.idi and olist.ido =`order`.idO AND `order`.datum = ? and olist.paid=TRUE");
            pst.setString(1,date);
            rs = pst.executeQuery();
            rs.next();
            suma = rs.getFloat("suma");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pst);
            DBUtil.close(con);
            DBUtil.close(rs);
        }
        return suma;
    }

    public void getSellest(int id,int year){
        ResultSet rs = null;
        PreparedStatement pst = null;
        ObjectsStorage.getInstance().getSellestItem().clear();

        try{
            con = Conection.getConnection();
            pst = con.prepareStatement("SELECT nazov, count(nazov) AS pocet FROM items, olist, `order` WHERE items.idItem=olist.idi and olist.ido =`order`.idO AND MONTH(`order`.datum )= ? AND YEAR(`order`.datum) = ? and olist.paid=TRUE GROUP BY nazov ORDER BY pocet DESC");
            pst.setInt(1,id);
            pst.setInt(2,year);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObjectsStorage.getInstance().getSellestItem().add(new Item(0,rs.getInt("pocet"),rs.getString("nazov"),0,0.0,false,0));
            }
        }
        catch (SQLException e){e.printStackTrace();}
        finally {
            DBUtil.close(rs);
            DBUtil.close(pst);
            DBUtil.close(con);
        }
    }

    public void getDayLine(int month,int year){
        ResultSet rs = null;
        PreparedStatement pst = null;
        ObjectsStorage.getInstance().getDaySeria().getData().clear();

        try{
            con = Conection.getConnection();
            pst = con.prepareStatement("SELECT day(datum) as den, sum(cena) as cena FROM items, olist, `order` WHERE items.idItem=olist.idi and olist.ido =`order`.idO AND MONTH(`order`.datum) = ? AND year(`order`.datum) =? and olist.paid=TRUE GROUP BY den ");
            pst.setInt(1,month);
            pst.setInt(2,year);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObjectsStorage.getInstance().getDaySeria().getData().add(new XYChart.Data<>(String.valueOf(rs.getInt("den")), rs.getDouble("cena")));
            }
        }
        catch (SQLException e){e.printStackTrace();}
        finally {
            DBUtil.close(rs);
            DBUtil.close(pst);
            DBUtil.close(con);
        }
    }

    public void getMonthLine(int year){
        ResultSet rs = null;
        PreparedStatement pst = null;
        ObjectsStorage.getInstance().getMonthSeria().getData().clear();

        try{
            con = Conection.getConnection();
            pst = con.prepareStatement("SELECT month(datum) as mes, sum(cena) as trzba FROM items, olist, `order` WHERE items.idItem=olist.idi and olist.ido =`order`.idO AND year(`order`.datum) =? and olist.paid=TRUE GROUP BY mes ");
            pst.setInt(1,year);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObjectsStorage.getInstance().getMonthSeria().getData().add(new XYChart.Data<>(String.valueOf(rs.getInt("mes")), rs.getDouble("trzba")));
            }
        }
        catch (SQLException e){e.printStackTrace();}
        finally {
            DBUtil.close(rs);
            DBUtil.close(pst);
            DBUtil.close(con);
        }
    }
}

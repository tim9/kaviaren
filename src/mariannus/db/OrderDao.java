package mariannus.db;

import mariannus.model.Item;
import mariannus.model.ObjectsStorage;
import mariannus.model.Order;

import java.sql.*;

/**
 * Created by Tim on 7.6.2016.
 */
public class OrderDao{
    private Connection con;
    private Statement st;
    private static OrderDao instance;

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
            return instance;
        }
        else return instance;
    }
    private OrderDao() {
    }
    public void getOItems(int ido){
        ResultSet rs = null;
        PreparedStatement pst = null;
        ObjectsStorage.getInstance().getoItems().clear();
        try{
            con = Conection.getConnection();
            pst = con.prepareStatement("SELECT  idItem,kod,idCat,nazov,cena,paid,id FROM items, olist WHERE items.idItem=olist.idi and olist.ido =?");
            pst.setInt(1,ido);
            rs = pst.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getInt("idItem"),rs.getInt("kod"),rs.getString("nazov"),rs.getInt("idCat"),rs.getDouble("cena"),rs.getBoolean("paid"),rs.getInt("id"));
                ObjectsStorage.getInstance().getoItems().add(item);
            }
        }
        catch (SQLException e){e.printStackTrace();}
        finally {
            DBUtil.close(rs);
            DBUtil.close(pst);
            DBUtil.close(con);
        }
    }

    //ak je objednavka na stole vrati true a nacita danu objednavku ak nie je vrati false
    public boolean isOrder(int id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = Conection.getConnection();
            pst = con.prepareStatement("SELECT * from `order` where idT= ? AND ok =FALSE");
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if (rs.first())
                ObjectsStorage.getInstance().setOrder(new Order(rs.getInt("idO"),rs.getInt("idT"),rs.getString("name")));
            else return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DBUtil.close(pst);
            DBUtil.close(con);
            DBUtil.close(rs);
        }
        return true;
    }
    public float getPrice(int ido){
        PreparedStatement pst = null;
        ResultSet rs = null;
        float cena = 0;
        try {
            con = Conection.getConnection();
            pst = con.prepareStatement("SELECT sum(cena) as suma FROM items, olist WHERE items.idItem=olist.idi and olist.ido =? AND olist.paid=FALSE");
            pst.setInt(1,ido);
            rs = pst.executeQuery();
            rs.next();
            cena =rs.getFloat("suma");
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DBUtil.close(pst);
            DBUtil.close(con);
            DBUtil.close(rs);
        }
        return cena;
    }
    public void changeTab(int newTab, int oldtab,String name){
        String query = "UPDATE `order` SET idT = ?, name = ? WHERE idO = (SELECT idO from (SELECT * FROM `order`) as x where idT= ? AND ok =FALSE)";
        exeQuery(newTab,oldtab,name,query);
    }
    public void pay(int id, boolean b){
        String query = "UPDATE olist SET paid = ? WHERE id = ?";
        exeQuery(boolToInt(b),id,null,query);
    }
    public void newOrder(int idt, String name) {
        String query = "INSERT INTO `order`(idT, name,ok,datum) VALUES (?,?,false,now())";
        exeQuery(idt,-1,name,query);
    }
    public void orderItem(int idi, int ido){
        String query = "INSERT INTO `olist`(idi, ido, paid) VALUES (?,?,false)";
        exeQuery(idi,ido,null,query);
    }
    public void endOrder(int id){
        String query = "UPDATE `order` SET ok = true where idO =?";
        exeQuery(id,-1,null,query);
    }
    public void delItem(int id){
        String query ="DELETE FROM olist WHERE id = ?";
        exeQuery(id,-1,null,query);
    }
    public void delOrder(int id){
        String query = "DELETE FROM `order` WHERE idO = ?";
        exeQuery(id,-1,null,query);
    }
    private void exeQuery(int id1, int ido, String name, String query){
        PreparedStatement pst=null;
        try{
            con = Conection.getConnection();
            pst = con.prepareStatement(query);
            pst.setInt(1,id1);
            if (ido>=0)
                pst.setInt(2,ido);
            if (id1>0 && ido>0 && name!=null){
                pst.setString(2,name);
                pst.setInt(3,ido);
            }else if (name!=null)
                pst.setString(2,name);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pst);
            DBUtil.close(con);
        }
    }
    private int boolToInt(boolean b) {
        return b ? 1 : 0;
    }
}

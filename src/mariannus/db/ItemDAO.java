package mariannus.db;

import javafx.scene.control.Alert;
import mariannus.model.Category;
import mariannus.model.Item;
import mariannus.model.ObjectsStorage;

import java.sql.*;

/**
 * Created by Tim on 2.6.2016.
 */
public class ItemDAO {
    private static ItemDAO instance;
    private Connection con;
    private Statement st;

    private ItemDAO(){

    }
    public static ItemDAO getInstance(){
        if (instance == null) {
            instance = new ItemDAO();
            return instance;
        }
        else return instance;
    }

    public void getCat(){
        ResultSet rs = null;
        try {
            con = Conection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT id,meno FROM cats ORDER BY meno ASC");
            while (rs.next()) {
                ObjectsStorage.getInstance().getCategoryList().add(new Category(rs.getString("meno"), rs.getInt("id")));
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Start programu");
            alert.setHeaderText("Nepripojenie do sieti");
            alert.setContentText("Skontroluj, či je PC na wifi, pozri sa či funguje raspberry(mala biela krabicka) a či je zapojena v sieti.");
            alert.showAndWait();
        }
        finally {
            DBUtil.close(rs);
            DBUtil.close(st);
            DBUtil.close(con);
        }
    }
    public void getItems(){
        ResultSet rs = null;
        try{
            con = Conection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM items");
            while (rs.next()){
                Item item = new Item(rs.getInt("idItem"),rs.getInt("kod"),rs.getString("nazov"),rs.getInt("idCat"),rs.getDouble("cena"),false,0);
                ObjectsStorage.getInstance().getItems().addAll(item);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.close(rs);
            DBUtil.close(st);
            DBUtil.close(con);
        }
    }

    public void delCat(int id) throws SQLException {
        exeQuery("DELETE FROM cats WHERE id =?",id,"");
    }
    public void addCat(String name) throws SQLException {
        exeQuery("INSERT INTO cats (meno) VALUES (?)", 0, name);
    }
    public void delItem(int id) throws SQLException{
        exeQuery("DELETE FROM items WHERE idItem =?",id,"");
    }
    public void addItem(int ean, String meno, int cat, double cena){
        String query ="INSERT INTO items(kod, idCat, nazov, cena) VALUES (?,?,?,?)";
        handleItem(ean,meno,cat,cena,query);
    }
    public void updateItem(int id, int ean, String meno, int cat, double cena) {
        String query ="Update items set kod = ?, idCat=?, nazov=?, cena=? where idItem ="+id;
        handleItem(ean,meno,cat,cena,query);
    }
    private void handleItem(int ean, String meno, int cat, double cena, String query){
        PreparedStatement pst=null;
        try{
            con = Conection.getConnection();
            pst = con.prepareStatement(query);
            pst.setInt(1,ean);
            pst.setInt(2,cat);
            pst.setString(3,meno);
            pst.setDouble(4,cena);
            pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.close(pst);
            DBUtil.close(con);
        }
    }

    private void exeQuery(String query, int id, String param) throws SQLException {
        PreparedStatement pst=null;
        try{
            con = Conection.getConnection();
            pst = con.prepareStatement(query);
            if (id>0)
                pst.setInt(1,id);
            else pst.setString(1,param);

            pst.executeUpdate();
        }finally {
            DBUtil.close(pst);
            DBUtil.close(con);
        }
    }
}

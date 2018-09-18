package mariannus.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

/**
 * Created by Tim on 11.2.2016.
 */
public class ObjectsStorage {
    private static ObjectsStorage instance;
    private ObservableList<Item> items;
    private ObservableList<Category> categoryList;
    private ObservableList<Item> oItems;
    private ObservableList<Item> sellestItem;
    private XYChart.Series<String,Double> daySeria;
    private XYChart.Series<String,Double> monthSeria;

    private Order order;
    private int mode =1; //sluzi na prepnutie aplikacie do modu zmen stol.

    private ObjectsStorage() {
        items = FXCollections.observableArrayList();
        categoryList = FXCollections.observableArrayList();
        oItems = FXCollections.observableArrayList();
        sellestItem = FXCollections.observableArrayList();
        order = new Order();
        daySeria = new XYChart.Series<>();
        monthSeria = new XYChart.Series<>();
    }

    public static ObjectsStorage getInstance() {
        if (instance == null) {
            instance = new ObjectsStorage();
            return instance;
        }
        else return instance;
    }

    public ObservableList<Item> getoItems() {
        return oItems;
    }

    public void setoItems(ObservableList<Item> oItems) {
        this.oItems = oItems;
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public void setItems(ObservableList<Item> items) {
        this.items = items;
    }

    public ObservableList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ObservableList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Order getOrder() {
        return order;
    }

    public ObservableList<Item> getSellestItem() {
        return sellestItem;
    }

    public XYChart.Series<String, Double> getDaySeria() {
        return daySeria;
    }

    public XYChart.Series<String, Double> getMonthSeria() {
        return monthSeria;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}

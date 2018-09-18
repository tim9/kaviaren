package mariannus.model;

import javafx.beans.property.*;

/**
 * Created by Tim on 11.2.2016.
 */
public class Item {
    private final int id;
    private final IntegerProperty code;
    private final StringProperty name;
    private final IntegerProperty category;
    private final DoubleProperty price;
    private int selId;
    private boolean paid;

    public Item(int id, Integer code, String name, Integer category, Double price, boolean paid,int selId) {
        this.code = new SimpleIntegerProperty(code);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleIntegerProperty(category);
        this.price =new SimpleDoubleProperty(price);
        this.id = id;
        this.paid = paid;
        this.selId = selId;
    }

    public Item() {
        id = 0;
        code = new SimpleIntegerProperty(8);
        name = new SimpleStringProperty("a");
        category = new SimpleIntegerProperty(1);
        price = new SimpleDoubleProperty(9.1);
    }

    public int getCode() {
        return code.get();
    }

    public IntegerProperty codeProperty() {
        return code;
    }

    public void setCode(int code) {
        this.code.set(code);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getCategory() {
        return category.get();
    }

    public IntegerProperty categoryProperty() {
        return category;
    }

    public void setCategory(int category) {
        this.category.set(category);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getId() {
        return id;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getSelId() {
        return selId;
    }

    public void setSelId(int selId) {
        this.selId = selId;
    }
}

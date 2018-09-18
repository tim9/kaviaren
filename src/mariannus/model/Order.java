package mariannus.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Tim on 19.2.2016.
 */
public class Order {
    private final StringProperty name;
    private int idT, id;
    private boolean ok;

    public Order(int id, int idt, String name) {
        this.name = new SimpleStringProperty(name);
        this.idT = idt;
        this.id = id;
        this.ok = false;
    }
    Order(){
        name = new SimpleStringProperty("");
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}

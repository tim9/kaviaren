package mariannus.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Tim on 12.2.2016.
 */
public class Category {
    private final StringProperty category;
    private final int id;

    public Category(String category, int id) {
        this.category = new SimpleStringProperty(category);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }
}

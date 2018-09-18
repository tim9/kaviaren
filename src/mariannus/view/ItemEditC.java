package mariannus.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import mariannus.db.ItemDAO;
import mariannus.model.Category;
import mariannus.model.Item;
import mariannus.model.ObjectsStorage;

import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Created by Tim on 13.2.2016.
 */
public class ItemEditC {
    @FXML private TextField code;
    @FXML private TextField name;
    @FXML private ComboBox<String> category;
    @FXML private TextField price;

    private Item item;
    private Stage stage;
    private ObservableList<String> sCategories = FXCollections.observableArrayList();
    private boolean clik=false,valid = false;

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setValid(boolean valid) {
        this.valid = valid;
    }

    boolean isValid() {
        return valid;
    }

    void setClik(boolean clik) {
        this.clik = clik;
    }

    ComboBox<String> getCategory() {
        return category;
    }

    @FXML private void initialize(){
        sCategories.addAll(ObjectsStorage.getInstance().getCategoryList().stream().map(Category::getCategory).collect(Collectors.toList()));
        category.setItems(sCategories);
    }
    @FXML private void handleOk(){
    if (isInputValid()) {
        int id = ObjectsStorage.getInstance().getCategoryList().get(category.getSelectionModel().getSelectedIndex()).getId();
        if (clik) {
            ItemDAO.getInstance().updateItem(item.getId(),Integer.parseInt(code.getText()),name.getText(),id,Double.parseDouble(price.getText()));
            ObjectsStorage.getInstance().getItems().clear();
            ItemDAO.getInstance().getItems();
        } else {
            valid = true;
            ItemDAO.getInstance().addItem(Integer.parseInt(code.getText()), name.getText(), id, Double.parseDouble(price.getText()));
//            Item item = new Item(Integer.parseInt(code.getText()), name.getText(), category.getSelectionModel().getSelectedItem().getId(), Double.parseDouble(price.getText()));
//            ObjectsStorage.getInstance().getItems().add(item);
            ObjectsStorage.getInstance().getItems().clear();
            ItemDAO.getInstance().getItems();
        }
        valid = true;
    }
    stage.close();
}

    @FXML private void handleCancel(){
        stage.close();
    }

    @FXML
    void setDialog(Item item){
        code.setText(String.valueOf(item.getCode()));
        name.setText(item.getName());
        price.setText(String.valueOf(item.getPrice()));
        this.item = item;
    }
    @FXML
    public void keyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case ENTER: {
                handleOk();
                break;
            }
            case ESCAPE:{
                handleCancel();
                break;
            }
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (code.getText() == null || code.getText().length() == 0) {
            errorMessage += "do pola zadaj cislo\n";
        }else {
            try {
                Integer.parseInt(code.getText());
            } catch (NumberFormatException e) {
                errorMessage += "kod musi byt cislo\n";
            }
        }

        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "nazov nie je zadane\n";
        }

        if (price.getText() == null || price.getText().length() == 0) {
            errorMessage += "cena nie je zadana!\n";
        }else {
            try {
                Double.parseDouble(price.getText());
            } catch (NumberFormatException e) {
                errorMessage += "cena musi byt cislo\n";
            }
        }
            if (errorMessage.length() == 0)
            return true;
            else {
                // Show the error message.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(stage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            return false;
        }
    }
}

package mariannus.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mariannus.Main;
import mariannus.db.ItemDAO;
import mariannus.model.Category;
import mariannus.model.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;

import static mariannus.model.ObjectsStorage.getInstance;

public class ItemViewC {
    @FXML
    private TableView<Category> shortTable;
    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableColumn<Category, String> category;
    @FXML
    private TableColumn<Item, Integer> code;
    @FXML
    private TableColumn<Item, String> name;
    @FXML
    private TableColumn<Item, Double> price;

    private boolean editclk = false;
    @FXML
    void initialize() throws SQLException {
        getInstance().getCategoryList().clear();
        getInstance().getItems().clear();
        ItemDAO.getInstance().getCat();
        ItemDAO.getInstance().getItems();
        category.setCellValueFactory(param ->  param.getValue().categoryProperty());
        shortTable.setItems(getInstance().getCategoryList());
        shortTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showIDetails(newValue));
        code.setCellValueFactory(param -> param.getValue().codeProperty().asObject());
        name.setCellValueFactory(param -> param.getValue().nameProperty());
        price.setCellValueFactory(param -> param.getValue().priceProperty().asObject());
        itemTable.setItems(getInstance().getItems());
    }

    private void showIDetails(Category category) { //po kliknuti na katgoriou sa vpravo zobrazia vsetky polozky v nej
        ObservableList<Item> selectedData = FXCollections.observableArrayList();
        selectedData.addAll(getInstance().getItems().stream().filter(item -> item.getCategory() == category.getId()).collect(Collectors.toList()));
        code.setCellValueFactory(param -> param.getValue().codeProperty().asObject());
        name.setCellValueFactory(param -> param.getValue().nameProperty());
        price.setCellValueFactory(param -> param.getValue().priceProperty().asObject());
        itemTable.setItems(selectedData);
    }

    @FXML
    void deleteCategory() throws SQLException {
        Category index = shortTable.getSelectionModel().getSelectedItem();
        if (index!=null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("vymazanie kategorie");
            alert.setHeaderText("Potvrdenie vymazania");
            alert.setContentText("Pozor vymazanim danej kategorie sa vymazu vsetky polozky ktore do nej patria\nChcete pokracovat");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ItemDAO.getInstance().delCat(index.getId());
                ItemDAO.getInstance().getCat();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("Nezvolena kategoria");
            alert.setContentText("Prosim zvol polozku, ktoru chces vymazat");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteItem() throws SQLException {
        Item index = itemTable.getSelectionModel().getSelectedItem();
        if (index!=null) {
            getInstance().getItems().remove(index);
            ItemDAO.getInstance().delItem(index.getId());
            showIDetails(shortTable.getSelectionModel().getSelectedItem());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("Nezvolena polozka");
            alert.setContentText("Prosim zvol polozku, ktoru chces vymazat");
            alert.showAndWait();
        }
    }

    @FXML
    void newCategory() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("pridanie kategorie");
        dialog.setHeaderText(null);
        dialog.setContentText("zadaj novu kategoriu");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            try {
                ItemDAO.getInstance().addCat(name);
                ItemDAO.getInstance().getCat();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void newItem() throws IOException {
        editclk = false;
        showEdit();
    }
    @FXML
    void editItem() throws IOException {
        editclk = true;
        showEdit();
    }
    @FXML void showAll() throws SQLException {
        getInstance().getItems().clear();
        ItemDAO.getInstance().getItems();
        code.setCellValueFactory(param -> param.getValue().codeProperty().asObject());
        name.setCellValueFactory(param -> param.getValue().nameProperty());
        price.setCellValueFactory(param -> param.getValue().priceProperty().asObject());
        itemTable.setItems(getInstance().getItems());
    }


    private void showEdit() throws IOException {
        Category index = shortTable.getSelectionModel().getSelectedItem();
        if (index!=null) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ItemEdit.fxml"));
            AnchorPane pane = loader.load();
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(itemTable.getScene().getWindow());
            ItemEditC itemEditC = loader.getController();
            itemEditC.getCategory().getSelectionModel().select(shortTable.getSelectionModel().getSelectedItem().getCategory());
            itemEditC.setStage(stage);

            if (editclk) {
                Item item = itemTable.getSelectionModel().getSelectedItem();
                if (item != null) {
                    itemEditC.setClik(editclk);
                    itemEditC.setDialog(item);
                } else editclk = false;
            }
            itemEditC.setClik(editclk);
            stage.showAndWait();
            if (itemEditC.isValid())
                showIDetails(shortTable.getSelectionModel().getSelectedItem());
            itemEditC.setValid(false);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("Nezvolena kategoria");
            alert.setContentText("Ak chces pridavat alebo upravovat polozku, musis zvolit kategoriu z vedlajsej tabulky");
            alert.showAndWait();
        }
    }
}

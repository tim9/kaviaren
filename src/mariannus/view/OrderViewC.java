package mariannus.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mariannus.Main;
import mariannus.db.OrderDao;
import mariannus.model.Item;
import mariannus.model.ObjectsStorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static mariannus.model.ObjectsStorage.getInstance;

/**
 * Created by Tim on 19.2.2016.
 */
public class OrderViewC {
    @FXML
    private Label tableName;
    @FXML
    private Label suma;
    @FXML
    private Label medzisucet;
    @FXML
    private TableView<Item> orderedItems;
    @FXML
    private TableColumn<Item, Integer> code;
    @FXML
    private TableColumn<Item, String> name;
    @FXML
    private TableColumn<Item, Double> price;
    @FXML
    private TableColumn<ArrayList[], String> paid;
    @FXML
    Button bOk;

    private Stage stage;
    private Label label;
    private float medzi = 0;

    @FXML
    void initialize() {
    }

    @FXML
    void add(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/NewOView.fxml"));
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(pane));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(suma.getScene().getWindow());
        NewOViewC newOViewC = loader.getController();
        newOViewC.setStage(stage);
        newOViewC.setOrderViewC(this);
        stage.showAndWait();
    }

    @FXML
    void delete() {
        Item delIndex = orderedItems.getSelectionModel().getSelectedItem();
        OrderDao.getInstance().delItem(delIndex.getSelId());
        loadOrderedItems();
    }

    @FXML
    private void handleOk() {
        if(getInstance().getoItems().isEmpty()){
            stage.close();
            return;
        }
        if (isAllPaied()) {
            releseSlot();
            resetTable(label);
        }
        stage.close();
    }

    @FXML public void markAll(){
        for (Item i: getInstance().getoItems()) {
            i.setPaid(true);
            OrderDao.getInstance().pay(i.getSelId(),true);
            subSum(i);
        }
        loadOrderedItems();
        loadSum();
    }

    @FXML
    public void buttonPressed(KeyEvent e) {
        switch (e.getCode()) {
            case ENTER: {
                bOk.fire();
                break;
            }
            case P:{
                add();
                break;
            }
            case DELETE:{
                delete();
                break;
            }
        }
    }
    @FXML public void changeTable(){
        getInstance().setMode(2);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zmena Stola");
        alert.setHeaderText(null);
        alert.setContentText("Po potrvrdeni tohto okna klikni na stol NA ktory sa ma preniest objednavka.");
        alert.showAndWait();
        resetTable(label);
        handleOk();
    }
    @FXML public void delOrder(){
        if (getInstance().getoItems().isEmpty()) {
            OrderDao.getInstance().delOrder(getInstance().getOrder().getId());
            resetTable(label);
            stage.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vymazanie Objednavky");
            alert.setHeaderText("Objednavka sa neda vymazat");
            alert.setContentText("Na vybranom stole su objednane polozky, vymazat sa da len prazdny stol");
            alert.showAndWait();
        }
    }

    //  funkcia nacita a zobrazi polozky, ktore si ludia objednali pri stole.
    void loadOrderedItems() {
        OrderDao.getInstance().getOItems(getInstance().getOrder().getId());
        code.setCellValueFactory(param -> param.getValue().codeProperty().asObject());
        name.setCellValueFactory(param -> param.getValue().nameProperty());
        price.setCellValueFactory(param -> param.getValue().priceProperty().asObject());
        paid.setCellFactory(param -> new TableCell<ArrayList[], String>() { //cellfactory ktory prida do tabulky checkbox a jemu eventhandler.
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    CheckBox checkBox = new CheckBox();
                    if (!getInstance().getoItems().get(this.getIndex()).isPaid()) {
                        checkBox.setOnAction(event -> {
                            if (checkBox.isSelected()) { //ten if osetruje aby sa po zakliknut odkliknuti priratavala odratavala cena
                                subSum(getInstance().getoItems().get(this.getIndex()));
                                getInstance().getoItems().get(this.getIndex()).setPaid(true);
                                OrderDao.getInstance().pay(getInstance().getoItems().get(this.getIndex()).getSelId(), true);
                                loadSum();
                            } else {
                                addSum(getInstance().getoItems().get(this.getIndex()));
                                getInstance().getoItems().get(this.getIndex()).setPaid(false);
                                OrderDao.getInstance().pay(getInstance().getoItems().get(this.getIndex()).getSelId(), false);
                                loadSum();
                            }
                        });
                    }
//                    setStyle("-fx-background-color: red");
                    else if (getInstance().getoItems().get(this.getIndex()).isPaid()) {
                        checkBox.setSelected(true);
                        checkBox.setDisable(true);
                    }
                    setGraphic(checkBox);
                }
            }
        });
        orderedItems.setItems(ObjectsStorage.getInstance().getoItems());
        loadSum();
    }

    //funkcia zisti ci bol vsetok tovar v konkretnej objednavke zaplateny
    private boolean isAllPaied() {
        for (Item it : getInstance().getoItems()) {
            if (!it.isPaid())
                return false;
        }
        return true;
    }

    //    funkcia uvolni prislusny slot
    private void releseSlot() {
        getInstance().getoItems().clear();
        OrderDao.getInstance().endOrder(ObjectsStorage.getInstance().getOrder().getId());
    }

    String getNamefromD(String tabName) {
        TextInputDialog dialog = new TextInputDialog(tabName);
        dialog.setTitle("Zadaj názov");
        dialog.setHeaderText(null);
        dialog.setContentText("zadaj meno stola");
        Optional<String> name = dialog.showAndWait();
        if (name.isPresent()) {
            return name.get();
        }
        return null;
    }

    private String getOrigName(Label label){
        int tab = Integer.parseInt(label.getId());
        if (tab>0 && tab< 10 )
            return "stol "+tab;
        else if (tab ==10 || tab == 11)
            return "bar "+(tab-9);
        else if (tab>11 && tab < 16)
            return "f "+(tab-11);
        else if (tab>15)
            return "stol "+(tab-15);
        return null;
    }

    private void resetTable(Label label){
        label.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,200,0,1) , 25, 0.2 , 0 , 1 )");
        label.setText(getOrigName(label));
    }

    Label getTableName() {
        return tableName;
    }

    private void loadSum() {
        float sum = OrderDao.getInstance().getPrice(getInstance().getOrder().getId());
        suma.setText(String.valueOf(sum));
    }

    //    funkcia odrata zakliknutu sumu od celkovej sumy a aktualizuje vypis
    private void subSum(Item item) {
        medzi += item.getPrice();
        medzisucet.setText(String.valueOf(Math.floor(medzi * 100) / 100));
    }

    private void addSum(Item item) {
        medzi -= item.getPrice();
        medzisucet.setText(String.valueOf(Math.floor(medzi * 100) / 100));
    }

    void setStage(Stage stage, Label label) {
        this.stage = stage;
        this.label = label;
    }
}

package mariannus.view;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import mariannus.Main;
import mariannus.db.OrderDao;
import mariannus.model.ObjectsStorage;

import java.io.IOException;

/**
 * Created by Tim on 19.2.2016.
 */
public class MainViewC {
    @FXML
    private TabPane tabpane;
    @FXML
    private Label tab1;
    @FXML
    private Label tab2;
    @FXML
    private Label tab3;
    @FXML
    private Label tab4;
    @FXML
    private Label tab5;
    @FXML
    private Label tab6;
    @FXML
    private Label tab7;
    @FXML
    private Label tab8;
    @FXML
    private Label tab9;
    @FXML
    private Label tab10;
    @FXML
    private Label tab11;
    @FXML
    private Label tab12;
    @FXML
    private Label tab13;
    @FXML
    private Label tab14;
    @FXML
    private Label tab15;
    @FXML
    private Label tab16;
    @FXML
    private Label tab17;
    @FXML
    private Label tab18;
    @FXML
    private Label tab19;
    @FXML
    private Label tab20;
    @FXML
    private Label tab21;
    @FXML
    private Label tab22;
    @FXML
    private Label tab23;
    @FXML
    private Label tab24;
    @FXML
    private Label tab25;
    @FXML
    private Label tab26;
    @FXML
    private Label tab27;
    @FXML
    private Label tab28;
    @FXML
    private Label tab29;
    @FXML
    private Label tab30;
    @FXML
    private Label tab31;
    @FXML
    private Label tab32;
    @FXML
    private Label tab33;
    @FXML
    private Label tab34;
    @FXML
    private Label tab35;
    @FXML
    private Label tab36;

    @FXML
    void initialize() {
        tab1.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab2.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab3.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab4.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab5.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab6.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab7.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab8.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab9.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab10.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab11.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab12.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab13.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab14.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab15.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab16.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab17.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab18.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab19.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab20.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab21.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab22.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab23.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab24.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab25.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab26.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab27.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab28.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab29.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab30.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab31.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab32.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab33.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab34.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab35.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());
        tab36.addEventHandler(MouseEvent.MOUSE_CLICKED, new HandleClickTable());

        tabpane.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(2))
                login((Integer) oldValue);
        });
    }

    @FXML
    void login(int old) {
        String name = "stefan";
        String pass = "lasupa";
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Pre pokračovanie sa musite prihlasit");
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(username::requestFocus);
        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                if (username.getText().equals(name) && password.getText().equals(pass)) {
                    tabpane.getSelectionModel().select(2);
                    return new Pair<>("asf", "af");
                }
            } else tabpane.getSelectionModel().clearAndSelect(old);
            return null;
        });
        dialog.showAndWait();
    }

    private class HandleClickTable implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/OrderView.fxml"));
            AnchorPane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert pane != null;
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tab1.getScene().getWindow());

            Label label = (Label) event.getSource();
            int tabId = Integer.parseInt(label.getId());
            OrderViewC orderViewC = loader.getController();
            orderViewC.setStage(stage, label);

            if (ObjectsStorage.getInstance().getMode() == 1)  //ak nie je kliknuty mod pre zmenu stola
                //ak vrati nulu vytvor novu objednavku. inak nacitaj polozky v danej objednavke
                //ak nie je objednavka na stole alebo ak je dana objednavka uz zaplatena vytvore novu objednavku
                if (!OrderDao.getInstance().isOrder(tabId) || ObjectsStorage.getInstance().getOrder().isOk())
                    newOrder(orderViewC, tabId, label, stage);
                else  //nacitaj objednavku na stole
                    loadOrder(orderViewC, label, stage);
            else
                changeTable(tabId, orderViewC,label);
        }
    }

    private void changeTable(int tabId, OrderViewC orderViewC, Label label) {
        if (!OrderDao.getInstance().isOrder(tabId)) {
            String name = orderViewC.getNamefromD(ObjectsStorage.getInstance().getOrder().getName());
            OrderDao.getInstance().changeTab(tabId, ObjectsStorage.getInstance().getOrder().getIdT(), name);
            label.setText(name);//nastavia sa vlastnosti na stol
            label.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(200,0,0,1) , 25, 0.0 , 0 , 1 );");
            ObjectsStorage.getInstance().setMode(1);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Zmena Stola");
            alert.setHeaderText("Na vybrany stol sa neda objednavka prelozit");
            alert.setContentText("Na vybranom stole uz niekto sedi. Opatovne si vyber stol ktory chces premiestnit a potom klikni na novy stol");
            ObjectsStorage.getInstance().setMode(1);
            alert.showAndWait();
        }
    }

    private void newOrder(OrderViewC orderViewC, int tabId, Label label, Stage stage) {
        String name = orderViewC.getNamefromD(label.getText());
        if (name != null) { //ak stlacim kancel pri vytvoreni obednavky nic sa neudeje
            OrderDao.getInstance().newOrder(tabId, name);
            orderViewC.getTableName().setText(name);
            OrderDao.getInstance().isOrder(tabId);
            stage.showAndWait();
            label.setText(name);//nastavia sa vlastnosti na stol
            label.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(200,0,0,1) , 25, 0.0 , 0 , 1 );");
        }
    }

    private void loadOrder(OrderViewC orderViewC, Label label, Stage stage) {
        label.setText(ObjectsStorage.getInstance().getOrder().getName());
        orderViewC.getTableName().setText(ObjectsStorage.getInstance().getOrder().getName());
        orderViewC.loadOrderedItems();
        label.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(200,0,0,1) , 25, 0.0 , 0 , 1 );");
        stage.showAndWait();
    }
}

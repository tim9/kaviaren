package mariannus.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import mariannus.db.OrderDao;
import mariannus.model.Category;
import mariannus.model.ObjectsStorage;
import mariannus.model.Order;

import static mariannus.model.ObjectsStorage.getInstance;

/**
 * Created by Tim on 19.2.2016.
 */
public class NewOViewC {
    @FXML private GridPane categories;
    @FXML private GridPane items;
    @FXML private Label cLabel;
    private int counter = 0;
    private int tempId;
    private Stage stage;
    private OrderViewC orderViewC;

    @FXML
    void initialize(){
        int i = 0, j = 0;
        for (Category cat: getInstance().getCategoryList()){
            Button button = new Button(cat.getCategory());
            button.setId(String.valueOf(cat.getId()));
            button.getStyleClass().add("bucat");
            button.setOnAction(event -> showItemsInCat(Integer.parseInt(button.getId())));
            button.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue)
                    button.fire();
            });
            categories.add(button,j,i);
            i++;
            if (i==12){
                i=0;
                j++;
            }
        }
    }
    private void showItemsInCat(int category){ //funkcia vykresli polozky, ktore su v kategorii
        items.getChildren().clear();
        final int[] i = {0};
        final int[] j = { 0 };
        cLabel.setText("0");
        getInstance().getItems().stream().filter(item -> item.getCategory()==category).forEach(item -> {
            Button b = new Button(item.getName());
            b.setId(String.valueOf(item.getId()));
            b.getStyleClass().add("buit");
            b.setOnAction(this::addItem);
            items.add(b, j[0], i[0]);
            i[0]++;
            if (i[0] == 10) {
                i[0] = 0;
                j[0]++;
            }
        });
    }

//    funkcia prida polozku do objednavky (cize co si dany stol objednal)
    private void addItem(Event event){
        Button button = (Button)event.getSource();
        OrderDao.getInstance().orderItem(Integer.parseInt(button.getId()),getInstance().getOrder().getId());
        count(Integer.parseInt(button.getId()));
    }

    @FXML
    private void handleOk(){
        orderViewC.loadOrderedItems();
        stage.close();
    }

    @FXML
    public void buttonPressed(KeyEvent e) {
        switch (e.getCode()){
            case ENTER:{
                handleOk();
                break;
            }
            case ESCAPE:{
                stage.close();
                break;
            }
        }
    }
    @FXML void traverse(KeyEvent e){
        String key = e.getCode().getName();
        for (int i=0; i<categories.getChildren().size();i++){
            Button b = (Button) categories.getChildren().get(i);
            if (b.getText().substring(0,1).equals(key)){
                b.requestFocus();
                b.fire();
                break;
            }
        }
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setOrderViewC(OrderViewC orderViewC) {
        this.orderViewC = orderViewC;
    }

    private void count(int id){
        if (id!=tempId){
            counter=0;
            tempId=id;
        }
        counter++;
        cLabel.setText(String.valueOf(counter));
    }
}

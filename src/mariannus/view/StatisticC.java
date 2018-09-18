package mariannus.view;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mariannus.db.StatDao;
import mariannus.model.Item;
import mariannus.model.ObjectsStorage;

import java.time.LocalDate;

/**
 * Created by Tim on 29.6.2016.
 */
public class StatisticC {
    @FXML
    private DatePicker picker;
    @FXML
    private Label income;
    @FXML
    private LineChart<String, Double> dayLine;
    @FXML
    private LineChart<String, Double> monthLine;
    @FXML
    private TableView<Item> mostSell;
    @FXML
    private TableColumn<Item, String> name;
    @FXML
    private TableColumn<Item, Integer> count;

    @FXML
    void initialize() {
        picker.setValue(LocalDate.now());
        dayIncome();
        mostSelItem();
        lineMonth();
        lineDay();

    }

    @FXML
    void drawStat() {
        dayIncome();
        mostSelItem();
        lineMonth();
        lineDay();
    }

    private void dayIncome() {
        float suma = StatDao.getInstance().dayPrice(picker.getValue().toString());
        income.setText(String.valueOf(suma));
    }

    private void mostSelItem() {
        StatDao.getInstance().getSellest(picker.getValue().getMonth().getValue(), picker.getValue().getYear());
        count.setCellValueFactory(param -> param.getValue().codeProperty().asObject());
        name.setCellValueFactory(param -> param.getValue().nameProperty());
        mostSell.setItems(ObjectsStorage.getInstance().getSellestItem());
    }

    private void lineDay() {
        StatDao.getInstance().getDayLine(picker.getValue().getMonth().getValue(), picker.getValue().getYear());
        dayLine.getData().add(ObjectsStorage.getInstance().getDaySeria());
    }

    private void lineMonth() {
        StatDao.getInstance().getMonthLine(picker.getValue().getYear());
        monthLine.getData().add(ObjectsStorage.getInstance().getMonthSeria());
    }
}

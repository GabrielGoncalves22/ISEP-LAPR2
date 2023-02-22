package app.ui.gui;

import app.core.controller.AnalyzeCenterPerformanceController;
import app.core.domain.model.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnalyzePerformanceUI {
    private CenterCoordinatorMenuUI centerCoordinatorMenuUI;
    private AnalyzeCenterPerformanceController controller;

    @FXML
    private Button calculatePerformanceButton;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private DatePicker choseDate;

    @FXML
    private TextField choseIntervalMinute;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField sumTxt;

    @FXML
    private TextField timeIntervalTxt;

    @FXML
    void calculatePerformace(ActionEvent event) {

        LocalDate localDate = choseDate.getValue();

        if (localDate != null && !choseIntervalMinute.getText().equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Date date = Date.convertStringToDate(choseDate.getValue().format(formatter));

            if (date.isMajor(Date.currentDate())) {

                errorLabel.setText("Invalid date");
                choseDate.setUserData("");
                calculatePerformanceButton.setDisable(false);
            }

            try {
                int m = Integer.parseInt(choseIntervalMinute.getText());
                if (controller.validateTimeIntervalAnalyze(m) == false) {
                    errorLabel.setText("Invalid Time Interval");
                    choseIntervalMinute.setUserData("");
                    calculatePerformanceButton.setDisable(false);

                } else {
                    errorLabel.setText("");

                    controller.getArrivalSNSUserList(date);
                    controller.getVaccineAdministrationList(date);

                    //Maximum sum contiguous sublist
                    int[] maxSumSubList = controller.getMaxSumSubList(m);
                    //Sum
                    int maxSum = controller.getMaxSum();
                    //Sublist time range
                    String timeInterval = controller.timeIntervalMaxSumSubList(date, m);

                    //Fill in the chart
                    graphicFill(maxSumSubList);

                    sumTxt.setText(Integer.toString(maxSum));
                    timeIntervalTxt.setText(timeInterval);
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("Interval time cannot be letters!");
                calculatePerformanceButton.setDisable(false);
            }

        } else {
            errorLabel.setText("Missing Data");
            calculatePerformanceButton.setDisable(false);
        }
    }

    @FXML
    void clear(ActionEvent event) {
        choseDate.setUserData("");
        choseIntervalMinute.setText("");
        errorLabel.setText("");
        sumTxt.setText("");
        timeIntervalTxt.setText("");
        barChart.getData().clear();
    }

    public void connectParentUI(CenterCoordinatorMenuUI centerCoordinatorMenuUI) {
        this.centerCoordinatorMenuUI = centerCoordinatorMenuUI;
        this.controller = new AnalyzeCenterPerformanceController(centerCoordinatorMenuUI.getSelectVaccinationCenterUI().getSelectedVcId());
    }

    public void graphicFill(int[] maxSumSubList) {
        barChart.getData().clear();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("People Difference");
        for (int i = 1; i < maxSumSubList.length + 1; i++) {

            series1.getData().add(new XYChart.Data(Integer.toString(i), maxSumSubList[i - 1]));


        }
        barChart.getData().addAll(series1);
    }
}

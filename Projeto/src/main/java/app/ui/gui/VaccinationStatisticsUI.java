package app.ui.gui;

import app.core.controller.VaccinationStatisticsController;
import app.core.domain.model.Date;
import app.core.domain.model.VaccinationStatistics;
import app.core.domain.model.WriteToFile;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the GUI (graphical user interface) that interacts with the user.
 *
 * @author Group 40
 */
public class VaccinationStatisticsUI {

    /**
     * Represents the center coordinator menu UI.
     */
    private CenterCoordinatorMenuUI centerCoordinatorMenuUI;

    /**
     * Represents the vaccination statistics controller.
     */
    private VaccinationStatisticsController controller;

    /**
     * This variable holds a FXML text field.
     */
    @FXML
    private TextField fileNameTextField;

    /**
     * This variable holds a FXML date picker.
     */
    @FXML
    private DatePicker firstDatePicker;

    /**
     * This variable holds a FXML date picker
     */
    @FXML
    private DatePicker secondDatePicker;

    @FXML
    private Label successfulLabel;

    @FXML
    private Label unsuccessfulLabel;

    @FXML
    private BarChart<?, ?> barChart;

    /**
     * This function is associated to a FXML button that cleans the text field and date picker.
     */
    @FXML
    void clean() {
        fileNameTextField.setText("");
        successfulLabel.setText("");
        unsuccessfulLabel.setText("");
        firstDatePicker.setValue(null);
        secondDatePicker.setValue(null);
        barChart.getData().clear();
    }

    /**
     * This function is associated to the FXML button that calls the validation of the dates and calls the creation and writing of the file.
     */
    @FXML
    void confirm() throws FileNotFoundException {
        LocalDate localDate1 = firstDatePicker.getValue();
        LocalDate localDate2 = secondDatePicker.getValue();

        if (localDate1 != null && localDate2 != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Date date1Date = Date.convertStringToDate(localDate1.format(formatter));
            Date date2Date = Date.convertStringToDate(localDate2.format(formatter));

            VaccinationStatistics vaccinationStatistics = new VaccinationStatistics(date1Date, date2Date);

            if (vaccinationStatistics.verifyDateInterval()) {

                String fileName = fileNameTextField.getText();
                Date tempDate1 = Date.newDate(date1Date);
                Date tempDate2 = Date.newDate(date1Date);
                List<Integer> vaccineArray = controller.totalNumberFullyVaccinatedInInterval(date1Date, date2Date);

                graphicFill(vaccineArray, tempDate1);
                boolean writeFile = new WriteToFile().writeToFile(tempDate2, fileName, vaccineArray);

                if (writeFile) {
                    successfulLabel.setText("The file has been successfully exported.");
                    unsuccessfulLabel.setText("");
                } else {
                    unsuccessfulLabel.setText("Exportation of the file unsuccessful.");
                }
            } else {
                unsuccessfulLabel.setText("Impossible to analize date interval.");
            }
        } else {
            unsuccessfulLabel.setText("Impossible to analize date interval.");
        }
    }

    /**
     * This function connects the parent UI
     *
     * @param centerCoordinatorMenuUI represents the center coordinator menu UI.
     */
    public void connectParentUI(CenterCoordinatorMenuUI centerCoordinatorMenuUI) {
        this.centerCoordinatorMenuUI = centerCoordinatorMenuUI;
        this.controller = new VaccinationStatisticsController(centerCoordinatorMenuUI.getSelectVaccinationCenterUI().getSelectedVcId());
    }

    public void graphicFill(List<Integer> vaccineArray, Date startDate) {
        barChart.getData().clear();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("People Full Vaccinated");

        for (int i = 0; i < vaccineArray.size(); i++) {
            series1.getData().add(new XYChart.Data(startDate.toDayMonthYearString(), vaccineArray.get(i)));
            startDate.addOneDay();
        }

        barChart.getData().addAll(series1);
    }
}

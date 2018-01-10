package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.KeyEventHandler;
import sample.Model.Password;
import sample.Model.Work;
import sample.TxtControllers.PasswordController;
import sample.TxtControllers.WorkController;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by simon on 20/12/2017.
 */
public class WorkViewController {


    @FXML
    private ComboBox<String> month_ComboBox;
    @FXML
    private Label hoursXMonth;
    @FXML
    private TableView<Work> workTableView;
    @FXML
    private TableColumn<Work, String> company;
    @FXML
    private TableColumn <Work , String>  hours;
    @FXML
    private TableColumn <Work , String>  workdate;
    @FXML
    private
    TextField company_TextField ,hours_TextField;
    @FXML
    private DatePicker date_DatePicker;

    private int[] hoursMonthList = new int[12];

    private WorkController workController = new WorkController();
    private ObservableList<Work> workMoments = workController.getWorkListFromTxt();


    public void initialize(){
        addMonthsToMonth_Combobox();
        month_ComboBox.setValue(getThisMonth());


        company.setCellValueFactory(new PropertyValueFactory<Work, String>("COMPANY"));
        hours.setCellValueFactory(new PropertyValueFactory<Work, String>("HOURS"));
        workdate.setCellValueFactory(new PropertyValueFactory<Work, String>("WORKDATE"));


        workTableView.setItems(workMoments);


        workMoments.forEach(this::sortHoursByMonth);
        updateHoursXMonth();
    }

    public void updateHoursXMonth(){
        if((month_ComboBox.getValue().equals("January"))){
            hoursXMonth.setText( hoursMonthList[0] + " hours");
        }
        else if(month_ComboBox.getValue().equals("February")){
            hoursXMonth.setText( hoursMonthList[1] + " hours");
        }
        else if(month_ComboBox.getValue().equals("March")){
            hoursXMonth.setText( hoursMonthList[2] + " hours");
        }
        else if(month_ComboBox.getValue().equals("April")){
            hoursXMonth.setText( hoursMonthList[3] + " hours");
        }
        else if(month_ComboBox.getValue().equals("May")){
            hoursXMonth.setText( hoursMonthList[4] + " hours");
        }
        else if(month_ComboBox.getValue().equals("June")){
            hoursXMonth.setText( hoursMonthList[5] + " hours");
        }
        else if(month_ComboBox.getValue().equals("July")){
            hoursXMonth.setText( hoursMonthList[6] + " hours");
        }
        else if(month_ComboBox.getValue().equals("August")){
            hoursXMonth.setText( hoursMonthList[7] + " hours");
        }
        else if(month_ComboBox.getValue().equals("September")){
            hoursXMonth.setText( hoursMonthList[8] + " hours");
        }
        else if(month_ComboBox.getValue().equals("Oktober")){
            hoursXMonth.setText( hoursMonthList[9] + " hours");
        }
        else if(month_ComboBox.getValue().equals("November")){
            hoursXMonth.setText( hoursMonthList[10] + " hours");
        }
        else if(month_ComboBox.getValue().equals("December")){
            hoursXMonth.setText( hoursMonthList[11] + " hours");
        }

    }

    public void handleAddButton(){
        if(!isTextFieldEmpty() && isNumberLegal(getInputHours(hours_TextField)) && !isDatepickerEmpty()) {
            Work work = new Work();
            work.setCOMPANY(company_TextField.getText());
            work.setHOURS(getInputHours(hours_TextField));
            work.setWORKDATE(date_DatePicker.getValue());

            sortHoursByMonth(work);
            updateHoursXMonth();
            workTableView.getItems().addAll(work);
            workController.addWorkToList(work);


            company_TextField.clear();
            hours_TextField.clear();
        }
    }

    public void handleWorkTableKeyEvent(KeyEvent event){
        KeyEventHandler keyEventHandler = new KeyEventHandler();

        if(keyEventHandler.isItemSelectedInTable(workTableView)){
            if(keyEventHandler.deleteKeyIsPressed(event)){
                workMoments.removeAll(workTableView.getSelectionModel().getSelectedItems());
                workController.rewriteWorkList(workMoments);

            }

        }


    }

    private void sortHoursByMonth(Work work){
        switch (work.getWORKDATE().getMonth().toString()) {
            case "JANUARY"  : hoursMonthList[0] += work.getHOURS();
            break;
            case "FEBRUARY" : hoursMonthList[1] += work.getHOURS();
                break;
            case "MARCH"    : hoursMonthList[2] += work.getHOURS();
                break;
            case "APRIL"    : hoursMonthList[3] += work.getHOURS();
                break;
            case "MAY"      : hoursMonthList[4] += work.getHOURS();
                break;
            case "JUNE"     : hoursMonthList[5] += work.getHOURS();
                break;
            case "JULY"     : hoursMonthList[6] += work.getHOURS();
                break;
            case "AUGUST"   : hoursMonthList[7] += work.getHOURS();
                break;
            case "SEPTEMBER": hoursMonthList[8] += work.getHOURS();
                break;
            case "OCTOBER"  : hoursMonthList[9] += work.getHOURS();
                break;
            case "NOVEMBER" : hoursMonthList[10] += work.getHOURS();
                break;
            case "DECEMBER" : hoursMonthList[11] += work.getHOURS();
                break;

        }


    }

    private void addMonthsToMonth_Combobox(){
        // adds all different options to month combobox
        month_ComboBox.getItems().addAll(
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "Oktober",
                "November",
                "December"
        );


    }

    public Boolean isTextFieldEmpty(){
        return company_TextField.getText().replace(" ", "").equals("");
    }

    public Boolean isNumberLegal(int inputNumber){
        return inputNumber >0;
    }

    public Boolean isDatepickerEmpty(){
        return date_DatePicker.getValue() == null;
    }

    public String getThisMonth(){
        LocalDate todaysDate = LocalDate.now();
       return parseMonthStringToCorrectFormat(todaysDate);
    }

    public String parseMonthStringToCorrectFormat(LocalDate date){
        // take month from date.now() convert it to lower case then capitalise the first letter
       return date.getMonth().toString().toLowerCase().substring(0, 1).toUpperCase() + date.getMonth().toString().toLowerCase().substring(1).toLowerCase();
    }

    public int getInputHours(TextField textField){
        try {
           return  Integer.parseInt(textField.getText());
        }catch (Exception e){}
        return -1;
    }


}

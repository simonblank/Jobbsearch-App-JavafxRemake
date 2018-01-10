package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.KeyEventHandler;
import sample.Model.Interview;
import sample.Model.Job;
import sample.TxtControllers.InterviewController;

import java.time.LocalDate;

/**
 * Created by simon on 13/12/2017.
 */
public class interviewViewController {

    @FXML
    private TableView upcomingInterviewTable;

    @FXML
    private TableColumn<Interview , String> upCompany;
    @FXML
    private TableColumn<Interview , String> upDate;

    @FXML
    private TableView pastInterviewTable;

    @FXML
    private DatePicker date_DatePicker;

    @FXML
    private TableColumn<Interview , String> pastCompany;
    @FXML
    private TableColumn<Interview , String> pastDate;

    @FXML
    private TextField company_Textfield ;


    private InterviewController interviewController = new InterviewController();
    private ObservableList<Interview> allInterviewsList = FXCollections.observableArrayList();
    private ObservableList<Interview> upcomingInterviews = FXCollections.observableArrayList();
    private ObservableList<Interview> pastInterviews = FXCollections.observableArrayList();


    public void initialize(){
        interviewController.getInterviewListFromTxt().forEach(this::sortInterviewsByDate);

        upCompany.setCellValueFactory(new PropertyValueFactory<Interview, String>("COMPANY"));
        upDate.setCellValueFactory(new PropertyValueFactory<Interview, String>("INTERVIEWDAY"));



        upcomingInterviewTable.setItems(upcomingInterviews);


        pastCompany.setCellValueFactory(new PropertyValueFactory<Interview, String>("COMPANY"));
        pastDate.setCellValueFactory(new PropertyValueFactory<Interview, String>("INTERVIEWDAY"));



        pastInterviewTable.setItems(pastInterviews);

    }

    public void sortInterviewsByDate(Interview interview){
            if(interview.getINTERVIEWDAY().isAfter(LocalDate.now())){
                upcomingInterviews.addAll(interview);
            }
            else {
                pastInterviews.addAll(interview);
            }

    }

    public void addButtonHandleClick(){

        if(date_DatePicker.getValue() != null) {
            Interview interview = new Interview();
            interview.setCOMPANY(company_Textfield.getText());
            LocalDate localDate = LocalDate.parse(date_DatePicker.getValue().toString());
            interview.setINTERVIEWDAY(localDate);
            interviewController.addInterviewToList(interview);



            sortInterviewsByDate(interview);
            allInterviewsList.addAll(interview);

            company_Textfield.clear();
        }
    }

    public void handleUpcomingTableKeyEvent(KeyEvent event){
        KeyEventHandler keyEventHandler = new KeyEventHandler();

        if(keyEventHandler.isItemSelectedInTable(upcomingInterviewTable)){
            if(keyEventHandler.deleteKeyIsPressed(event)){
                upcomingInterviews.removeAll(upcomingInterviewTable.getSelectionModel().getSelectedItems());
                rewriteInterviewList();

            }

        }

    }

    public void handlePastTableKeyEvent(KeyEvent event){
        KeyEventHandler keyEventHandler = new KeyEventHandler();

        if(keyEventHandler.isItemSelectedInTable(pastInterviewTable)){
            if(keyEventHandler.deleteKeyIsPressed(event)){

                pastInterviews.removeAll(pastInterviewTable.getSelectionModel().getSelectedItems());
                 rewriteInterviewList();


            }

        }

    }

    public void rewriteInterviewList(){
        allInterviewsList.clear();
        allInterviewsList.addAll(upcomingInterviews);
        allInterviewsList.addAll(pastInterviews);
        System.out.println(allInterviewsList.size());

        interviewController.rewriteInterviewList(allInterviewsList);


    }



}

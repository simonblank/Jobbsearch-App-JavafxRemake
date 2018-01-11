package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import sample.KeyEventHandler;
import sample.Model.Job;
import sample.Model.Password;
import sample.TxtControllers.JobWebsiteListController;
import sample.TxtControllers.JobbListController;
import sample.TxtControllers.PasswordController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

/**
 * Created by simon on 12/12/2017.
 */
public class JobbListViewController {

    @FXML
    private AnchorPane add_View , list_View;

    @FXML
    private ImageView ADD , CANCEL;

    @FXML
    private TextField title_TextField, company_TextField ,url_TextField;

    @FXML
    private ComboBox<String> jobWebsites_comboBox;

    @FXML
    private TextField search_TextField;
    @FXML
    private TableView<Job> jobListTableView;
    @FXML
    TableColumn<Job , String> title;
    @FXML
    TableColumn <Job , String>  company;
    @FXML
    TableColumn <Job , String>  date;

    private JobbListController jobbListController = new JobbListController();
    private ObservableList<Job> jobs = jobbListController.getSearchedJobListFromTxt();



    public void initialize(){
        addWebsitesTo_JobSearchingWebsite_List();

        title.setCellValueFactory(new PropertyValueFactory<Job, String>("TITLE"));
        company.setCellValueFactory(new PropertyValueFactory<Job, String>("COMPANY"));
        date.setCellValueFactory(new PropertyValueFactory<Job, String>("dateapplied"));

        jobListTableView.setItems(jobs);
        sortTableByDateDesc();
    }

    public void JobListTableHandleKeyClick(KeyEvent event){
        KeyEventHandler keyEventHandler = new KeyEventHandler();

        if(keyEventHandler.isItemSelectedInTable(jobListTableView)) {

            if (keyEventHandler.key_1_IsPressed(event)) {
                keyEventHandler.addToClipBoard(jobListTableView.getSelectionModel().getSelectedItems().get(0).getTITLE());

            } else if (keyEventHandler.key_2_IsPressed(event)) {
                keyEventHandler.addToClipBoard(jobListTableView.getSelectionModel().getSelectedItems().get(0).getCOMPANY());

            } else if (keyEventHandler.key_3_IsPressed(event)){
                keyEventHandler.addToClipBoard(jobListTableView.getSelectionModel().getSelectedItems().get(0).getDateapplied().toString());

            } else if (keyEventHandler.key_4_IsPressed(event)) {
                keyEventHandler.addToClipBoard(jobListTableView.getSelectionModel().getSelectedItems().get(0).getURL());

            } else if (keyEventHandler.deleteKeyIsPressed(event)) {
                jobs.removeAll(jobListTableView.getSelectionModel().getSelectedItems());
                jobbListController.rewriteAppliedJobList(jobs);

            }


        }
    }

    public void searchfieldHandleKeyClick(KeyEvent event){
        KeyEventHandler keyEventHandler = new KeyEventHandler();

        if(keyEventHandler.enterKeyIsPressed(event)){
            searchButtonHandleClick();
        }
    }


    public void searchButtonHandleClick(){
       ObservableList<Job> searchList = FXCollections.observableArrayList();


        for(Job job : jobbListController.getSearchedJobListFromTxt()){
            if(job.getTITLE().toLowerCase().contains(search_TextField.getText().toLowerCase())){
                searchList.addAll(job);
            }
            else if(job.getCOMPANY().toLowerCase().contains(search_TextField.getText().toLowerCase())){
                searchList.addAll(job);
            }
        }

        jobListTableView.getItems().removeAll(jobs);
        jobListTableView.getItems().addAll(searchList);

    }

    public void addSearchedJob(){
        Job job = new Job();
        job.setTITLE(title_TextField.getText());
        job.setCOMPANY(company_TextField.getText());
        job.setURL(url_TextField.getText());
        LocalDate localDate =LocalDate.now();
        job.setDateapplied(localDate);

        jobs.add(0,job);
        jobbListController.addJobToList(job);


        title_TextField.clear();
        company_TextField.clear();
        url_TextField.clear();
    }

    public void addSearchedJobHandleClick(MouseEvent event){

        if (event.getTarget() == ADD){
            add_View.setVisible(true);
            list_View.setVisible(false);
        }
        if (event.getTarget() == CANCEL){
            add_View.setVisible(false);
            list_View.setVisible(true);
        }

    }

    public void visitWebsiteHandleClick(){
        try{
            try {
                Desktop.getDesktop().browse(new URI(jobWebsites_comboBox.getValue()));

            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }


        }catch (Exception e){}

    }

    private void sortTableByDateDesc(){
            date.setComparator(date.getComparator().reversed());
            jobListTableView.getSortOrder().add(date);

    }

    private void addWebsitesTo_JobSearchingWebsite_List(){
        JobWebsiteListController jobWebsiteListController = new JobWebsiteListController();
        jobWebsites_comboBox.getItems().addAll(jobWebsiteListController.getJobWebsiteListFromTxt());
        jobWebsites_comboBox.setValue("www.arbetsformedlingen.se/");

    }




}

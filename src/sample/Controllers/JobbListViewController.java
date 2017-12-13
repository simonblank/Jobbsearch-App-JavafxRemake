package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import sample.Model.Job;
import sample.Model.Password;
import sample.TxtControllers.JobbListController;
import sample.TxtControllers.PasswordController;

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
        title.setCellValueFactory(new PropertyValueFactory<Job, String>("TITLE"));
        company.setCellValueFactory(new PropertyValueFactory<Job, String>("COMPANY"));
        date.setCellValueFactory(new PropertyValueFactory<Job, String>("dateapplied"));

        jobListTableView.setItems(jobs);
    }

    public void JobListTableHandleKeyClick(KeyEvent event){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();

        if(!jobListTableView.getSelectionModel().getSelectedItems().isEmpty()) {

            if (event.getText().equals("1")) {
                clipboardContent.putString(jobListTableView.getSelectionModel().getSelectedItems().get(0).getTITLE());
                clipboard.setContent(clipboardContent);

            } else if (event.getText().equals("2")) {
                clipboardContent.putString(jobListTableView.getSelectionModel().getSelectedItems().get(0).getCOMPANY());
                clipboard.setContent(clipboardContent);

            } else if (event.getText().equals("3")) {
                clipboardContent.putString(jobListTableView.getSelectionModel().getSelectedItems().get(0).getDateapplied().toString());
                clipboard.setContent(clipboardContent);

            } else if (event.getText().equals("4")) {
                clipboardContent.putString(jobListTableView.getSelectionModel().getSelectedItems().get(0).getURL());
                clipboard.setContent(clipboardContent);

            } else if (event.getCode().equals(KeyCode.DELETE)) {
                ObservableList<Job> passwordSelected ;
                passwordSelected = jobListTableView.getSelectionModel().getSelectedItems();

                jobs.removeAll(passwordSelected);
                jobbListController.rewriteAppliedJobList(jobs);
            }


        }
    }

    public void searchButtonHandleClick(){
       ObservableList<Job> searchList = FXCollections.observableArrayList();

        for(Job job : jobbListController.getSearchedJobListFromTxt()){
            if(job.getTITLE().contains(search_TextField.getText())){
                searchList.addAll(job);
            }
            else if(job.getCOMPANY().contains(search_TextField.getText())){
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

        jobListTableView.getItems().addAll(job);
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


}

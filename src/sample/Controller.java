package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import sample.Controllers.PassViewController;
import sample.Model.Password;
import sample.TxtControllers.PasswordController;

import java.util.Observable;


public class Controller  {

    @FXML
    private ImageView PASSWORD , INTERVIEW , LIST , EXIT , INFORMATION , WORK;

    @FXML
    private AnchorPane  interviewTab ,jobListTab , passwordTab , informationTab ,workTab;


    @FXML
    private void handleButtonClick(MouseEvent event){

        if (event.getTarget() == PASSWORD){
            showOrHideTabs(passwordTab);

        }
       else if (event.getTarget() == LIST){
            showOrHideTabs(jobListTab);


        }
        else if (event.getTarget() == INTERVIEW){
             showOrHideTabs(interviewTab);


        }
        else if (event.getTarget() == WORK){
            showOrHideTabs(workTab);


        }
        else if (event.getTarget() == INFORMATION){
            showOrHideTabs(informationTab);


        }
        else if (event.getTarget() == EXIT){
            System.exit(0);
        }


    }

    public void showOrHideTabs(AnchorPane tab){
        passwordTab.setVisible(false);
        interviewTab.setVisible(false);
        jobListTab.setVisible(false);
        workTab.setVisible(false);
        informationTab.setVisible(false);
        tab.setVisible(true);

    }


}

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
    private ImageView PASSWORD , INTERVIEW , LIST , EXIT;

    @FXML
    private AnchorPane  interviewTab ,jobListTab , passwordTab;


    @FXML
    private void handleButtonClick(MouseEvent event){
        if (event.getTarget() == PASSWORD){
            passwordTab.setVisible(true);
            interviewTab.setVisible(false);
            jobListTab.setVisible(false);

        }
       else if (event.getTarget() == LIST){
            passwordTab.setVisible(false);
            interviewTab.setVisible(false);
            jobListTab.setVisible(true);

        }
        else if (event.getTarget() == INTERVIEW){
            passwordTab.setVisible(false);
            interviewTab.setVisible(true);
            jobListTab.setVisible(false);

        }
        else if (event.getTarget() == EXIT){
            System.exit(0);
        }


    }


}

package sample.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import sample.Model.Password;
import sample.TxtControllers.PasswordController;

/**
 * Created by simon on 12/12/2017.
 */
public class PassViewController {


    @FXML
    private TextField text_PassPlace , text_PassPassword;

    @FXML
    private TableView<Password> passwordTableView;
    @FXML
    TableColumn<Password , String> place;
    @FXML
    TableColumn <Password , String>  password;

    private PasswordController passwordController = new PasswordController();
    private ObservableList<Password> passwords = passwordController.getPasswordListFromTxt();

    public void initialize(){
        place.setCellValueFactory(new PropertyValueFactory<Password, String>("PLACE"));
        password.setCellValueFactory(new PropertyValueFactory<Password, String>("PASSWORD"));


        passwordTableView.setItems(passwords);
    }

    public void handleAddPasswordClick(){
        Password password = new Password();
        password.setPLACE(text_PassPlace.getText());
        password.setPASSWORD(text_PassPassword.getText());
        passwordTableView.getItems().addAll(password);
        passwordController.addPasswordToList(password);

        text_PassPlace.clear();
        text_PassPassword.clear();

    }

    public void handleRemovePasswordClick(){
        ObservableList<Password> passwordSelected ;
        passwordSelected = passwordTableView.getSelectionModel().getSelectedItems();

        passwords.removeAll(passwordSelected);
        passwordController.rewritePasswordList(passwords);
    }


    public void passwordTableHandleKeyClick(KeyEvent event){

        if(event.getText().equals("c") && !passwordTableView.getSelectionModel().getSelectedItems().isEmpty()) {

            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(passwordTableView.getSelectionModel().getSelectedItems().get(0).getPASSWORD());
            clipboard.setContent(clipboardContent);
        }
    }



}

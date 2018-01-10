package sample.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import sample.KeyEventHandler;
import sample.Model.Password;
import sample.TxtControllers.PasswordController;
import sample.TxtControllers.TxtReader;

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
     /*   TxtReader txtReader = new TxtReader("JobbList.txt");
        System.out.println(
        txtReader.getTextFromTxt()
        );*/

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
        removePassword();
    }

    public void passwordTableHandleKeyClick(KeyEvent event){
        KeyEventHandler keyEventHandler = new KeyEventHandler();

        if(keyEventHandler.isItemSelectedInTable(passwordTableView)){

            if(keyEventHandler.key_1_IsPressed(event) ) {
                keyEventHandler.addToClipBoard(passwordTableView.getSelectionModel().getSelectedItems().get(0).getPLACE());

            }
            else if(keyEventHandler.key_2_IsPressed(event) ) {
                keyEventHandler.addToClipBoard(passwordTableView.getSelectionModel().getSelectedItems().get(0).getPASSWORD());

            }
            else if(keyEventHandler.deleteKeyIsPressed(event)){
                removePassword();
            }

        }

    }

    private void removePassword(){
        passwords.removeAll(passwordTableView.getSelectionModel().getSelectedItems());

        passwordController.rewritePasswordList(passwords);

    }



}

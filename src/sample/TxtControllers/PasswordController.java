package sample.TxtControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Password;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by simon on 11/12/2017.
 */
public class PasswordController {

    private static String txtFile = "Passwords.txt";


    public PasswordController() {
        createTxtFileIfDontExists();
    }

    public void createTxtFileIfDontExists(){

        Path textfile = Paths.get(txtFile);
        if (!Files.exists(textfile)) {
            try {
                Files.createFile(textfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void addPasswordToList(Password newPassword){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(txtFile, true));
            bw.write(
                    "%#&!" + newPassword.getPLACE() +
                        "%#&!" + newPassword.getPASSWORD());
            bw.newLine();
            bw.flush();




        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Password> getPasswordListFromTxt(){

        ObservableList<Password>   passwordList = FXCollections.observableArrayList();


        try {
            Scanner in = new Scanner(new FileReader(txtFile)).useDelimiter("%#&!");

            while(in.hasNext()){

                Password password = new Password();
                password.setPLACE(in.next());
                password.setPASSWORD(in.next());

                passwordList.add(password);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return passwordList;
    }

    public void rewritePasswordList(ObservableList<Password> passwordList){
        try {
            FileWriter fileWriter = new FileWriter(txtFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Password password : passwordList){
              bufferedWriter.write(
                      "%#&!" + password.getPLACE() +
                          "%#&!" + password.getPASSWORD());
            }


            bufferedWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}

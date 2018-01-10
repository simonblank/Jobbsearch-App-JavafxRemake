package sample.TxtControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Password;
import sun.nio.cs.UTF_32;

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
            bw.close();




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
            FileInputStream fileInputStream = new FileInputStream(txtFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF8");

            Scanner in = new Scanner(inputStreamReader).useDelimiter("%#&!");

            while(in.hasNext()){

                Password password = new Password();
                password.setPLACE(in.next());
                password.setPASSWORD(in.next());

                passwordList.add(password);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return passwordList;
    }

    public void rewritePasswordList(ObservableList<Password> passwordList){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(txtFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream , "UTF8");


            for(Password password : passwordList){
               addPasswordToList(password);
            }


            outputStreamWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}

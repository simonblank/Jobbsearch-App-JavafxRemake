package sample.TxtControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Password;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JobWebsiteListController {
    private static String txtFile = "JobWebsiteList.txt";


    public JobWebsiteListController() {
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



    public ObservableList<String> getJobWebsiteListFromTxt(){

        ObservableList<String>   JobWebsiteList = FXCollections.observableArrayList();


        try {
            Scanner in = new Scanner(new FileReader(txtFile));

            while(in.hasNext()){
                JobWebsiteList.add(in.next());


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return JobWebsiteList;
    }






}

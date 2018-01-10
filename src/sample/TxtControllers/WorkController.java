package sample.TxtControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Password;
import sample.Model.Work;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class WorkController {

    private static String txtFile = "WorkList.txt";


    public WorkController() {
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


    public void addWorkToList(Work work){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(txtFile, true));
            bw.write(
                    "%#&!" + work.getCOMPANY() +
                            "%#&!" + work.getHOURS()+
                            "%#&!" + work.getWORKDATE());
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

    public ObservableList<Work> getWorkListFromTxt(){

        ObservableList<Work>   workList = FXCollections.observableArrayList();


        try {
            FileInputStream fileInputStream = new FileInputStream(txtFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF8");

            Scanner in = new Scanner(inputStreamReader).useDelimiter("%#&!");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

            while(in.hasNext()){

                Work work = new Work();
                work.setCOMPANY(in.next());
                work.setHOURS(in.nextInt());
                LocalDate date = LocalDate.parse(in.next().substring( 0,10), formatter);
                work.setWORKDATE(date);

                workList.add(work);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return workList;
    }

    public void rewriteWorkList(ObservableList<Work> workList){
        try {
            FileWriter fileWriter = new FileWriter(txtFile);

            for(Work work : workList){
                addWorkToList(work);
            }





        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}

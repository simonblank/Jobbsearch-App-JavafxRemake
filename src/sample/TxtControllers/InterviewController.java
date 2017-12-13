package sample.TxtControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Interview;
import sample.Model.Job;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by simon on 13/12/2017.
 */
public class InterviewController {
    private static String txtFile = "InterviewList.txt";

    public InterviewController() {
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

    public ObservableList<Interview> getInterviewListFromTxt(){
        ObservableList<Interview> interviewList = FXCollections.observableArrayList();

        try {
            Scanner in = new Scanner(new FileReader(txtFile)).useDelimiter("%#&!");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

            while(in.hasNext()){

                Interview interview = new Interview();

                interview.setCOMPANY(in.next());
                LocalDate date = LocalDate.parse(in.next().substring( 0,10), formatter);
                interview.setINTERVIEWDAY(date);

                interviewList.add(interview);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return interviewList;
    }

    public void addInterviewToList(Interview newInterview){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(txtFile, true));
            bw.write(
                    "%#&!" + newInterview.getCOMPANY() +
                            "%#&!" + newInterview.getINTERVIEWDAY()
            );
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

    public void rewriteInterviewList(ObservableList<Interview> interviewList){

        try {
            FileWriter fileWriter = new FileWriter(txtFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Interview interview : interviewList){
                bufferedWriter.write(
                        "%#&!" + interview.getCOMPANY() +
                                "%#&!" + interview.getCOMPANY()
                );
                bufferedWriter.newLine();

            }


            bufferedWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}

package sample.TxtControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Job;
import sample.Model.Password;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by simon on 12/12/2017.
 */
public class JobbListController {
    private static String txtFile = "JobbList.txt";


    public JobbListController() {
        createTxtFileIfDontExists();

        rewriteAppliedJobList(getSearchedJobbListFromOldTxt());

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

    public ObservableList<Job> getSearchedJobListFromTxt(){
        ObservableList<Job> jobs = FXCollections.observableArrayList();

        try {
            Scanner in = new Scanner(new FileReader(txtFile)).useDelimiter("%#&!");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

            while(in.hasNext()){

                Job job = new Job();
                job.setTITLE(in.next());
              //  System.out.println(in.next());
                job.setCOMPANY(in.next());
                job.setURL(in.next());
                LocalDate date = LocalDate.parse(in.next().substring( 0,10), formatter);
                job.setDateapplied(date);

                jobs.add(job);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jobs;
    }

    public void addJobToList(Job newJob){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(txtFile, true));
            bw.write(
                    "%#&!" + newJob.getTITLE() +
                    "%#&!" + newJob.getCOMPANY() +
                    "%#&!" + newJob.getURL() +
                    "%#&!" + newJob.getDateapplied()
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

    public void rewriteAppliedJobList(ObservableList<Job> jobbList){

        try {
            BufferedWriter out = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(txtFile), StandardCharsets.UTF_8));



            for(Job jobs : jobbList){
                out.write(
                     "%#&!" + jobs.getTITLE() +
                         "%#&!" + jobs.getCOMPANY() +
                         "%#&!" + jobs.getURL() +
                         "%#&!" + jobs.getDateapplied() );
                out.newLine();

            }


            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public ObservableList<Job> getSearchedJobbListFromOldTxt(){
        ObservableList<Job> jobs = FXCollections.observableArrayList();

        try {
            Scanner in = new Scanner(new FileReader("searchedJobbs.txt"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

            while(in.hasNext()){

                Job jobb = new Job();
                jobb.setTITLE(in.next().replace("�","ö"));
                jobb.setURL(in.next());
                LocalDate date = LocalDate.parse(in.next(), formatter);
                jobb.setDateapplied(date);

                jobs.add(jobb);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jobs;
    }

}

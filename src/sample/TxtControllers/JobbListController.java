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
            FileInputStream fileInputStream = new FileInputStream(txtFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF8");


            Scanner in = new Scanner(inputStreamReader).useDelimiter("%#&!");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

            while(in.hasNext()){

                Job job = new Job();

                job.setTITLE(in.next());
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


           bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile, true), StandardCharsets.UTF_8));
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
               addJobToList(jobs);
            }


            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private ObservableList<Job> getSearchedJobbListFromOldTxt(){
        ObservableList<Job> jobs = FXCollections.observableArrayList();

        try {
            FileInputStream fileInputStream = new FileInputStream("searchedJobbs.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF8");


            Scanner in = new Scanner(inputStreamReader);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

            while(in.hasNext()){

                Job jobb = new Job();
                jobb.setTITLE(in.next());
                jobb.setURL(in.next());
                LocalDate date = LocalDate.parse(in.next(), formatter);
                jobb.setDateapplied(date);

                System.out.println(jobb.getTITLE());
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

package sample.TxtControllers;

import sample.Model.Password;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class TxtReader {
    private String txtFile = "";


    public TxtReader(String txtFile) {
        this.txtFile = txtFile;
    }

    public void setTxtFile(String txtFile) {
        this.txtFile = txtFile;
    }

    public String getTextFromTxt(){

        File logFile = new File(txtFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile)))
        {
            StringBuilder fileContents = new StringBuilder();
            Stream<String> fileContentStream = reader.lines();
            fileContentStream.forEach(l -> fileContents.append(l).append(System.lineSeparator()));
            return fileContents.toString();
        }
        catch (IOException ioe)
        {

        }

        return null;
    }



}

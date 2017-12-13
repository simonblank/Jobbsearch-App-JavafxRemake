package sample.Model;

import java.time.LocalDate;

/**
 * Created by simon on 12/12/2017.
 */
public class Job {

    private String TITLE;
    private String COMPANY;
    private String URL;
    LocalDate dateapplied;

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public LocalDate getDateapplied() {
        return dateapplied;
    }

    public void setDateapplied(LocalDate dateapplied) {
        this.dateapplied = dateapplied;
    }
}

package sample.Model;

import java.time.LocalDate;

/**
 * Created by simon on 20/12/2017.
 */
public class Work {

    private String COMPANY;
    private int HOURS;
    private LocalDate WORKDATE;


    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public int getHOURS() {
        return HOURS;
    }

    public void setHOURS(int HOURS) {
        this.HOURS = HOURS;
    }

    public LocalDate getWORKDATE() {
        return WORKDATE;
    }

    public void setWORKDATE(LocalDate WORKDATE) {
        this.WORKDATE = WORKDATE;
    }
}

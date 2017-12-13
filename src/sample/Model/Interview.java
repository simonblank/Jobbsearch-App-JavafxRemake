package sample.Model;

import java.time.LocalDate;

/**
 * Created by simon on 13/12/2017.
 */
public class Interview {

    private String COMPANY;
    private LocalDate INTERVIEWDAY;

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public LocalDate getINTERVIEWDAY() {
        return INTERVIEWDAY;
    }

    public void setINTERVIEWDAY(LocalDate INTERVIEWDAY) {
        this.INTERVIEWDAY = INTERVIEWDAY;
    }
}

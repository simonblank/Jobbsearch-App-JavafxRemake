package Tests.Controllers;

import org.junit.Test;
import sample.Controllers.interviewViewController;
import sample.TxtControllers.InterviewController;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class interviewViewControllerTest {

    interviewViewController interviewViewController = new interviewViewController();

    @Test
    public void isTodayOrAfterToday() throws Exception {

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);

        // check date of today should be true
        assertEquals(interviewViewController.isTodayOrAfterToday(today), true);

        // check date of tomorrow should be true
        assertEquals(interviewViewController.isTodayOrAfterToday(tomorrow), true);

        // check date of yesterday should be false
        assertEquals(interviewViewController.isTodayOrAfterToday(yesterday), false );

    }

}
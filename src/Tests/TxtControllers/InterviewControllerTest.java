package Tests.TxtControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;
import sample.Model.Interview;
import sample.Model.Job;
import sample.TxtControllers.InterviewController;
import sample.TxtControllers.JobbListController;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class InterviewControllerTest {
    InterviewController interviewController = new InterviewController();

    @Test
    public void testReadFromTxt_NotNull() {
        // read content from txt should not return null
        assertNotNull(interviewController.getInterviewListFromTxt());

    }

    @Test
    public void test_AddToTxt() {
        ObservableList<Interview> testList = interviewController.getInterviewListFromTxt();


        // create sample job and add it to txt and testlist
        interviewController.addInterviewToList(get_TestInterview());
        testList.add(get_TestInterview());


        // check so both lists has same amount of objects after add
        assertEquals(testList.size(), interviewController.getInterviewListFromTxt().size());


        // check so both list objects has the same content
        for (int i = 0; i < interviewController.getInterviewListFromTxt().size(); i++) {

            assertEquals(testList.get(i).getCOMPANY(), interviewController.getInterviewListFromTxt().get(i).getCOMPANY());
            assertEquals(testList.get(i).getINTERVIEWDAY(), interviewController.getInterviewListFromTxt().get(i).getINTERVIEWDAY());
        }
    }

    @Test
    public void test_RewriteTxtFile(){
        ObservableList<Interview> testList = FXCollections.observableArrayList();
        // testjob
        interviewController.addInterviewToList(get_TestInterview());

        // testjob should not be in the the new list. Add all other jobs
        for (Interview interview: interviewController.getInterviewListFromTxt()) {

            if(     !interview.getCOMPANY().equals(get_TestInterview().getCOMPANY()) ){

                testList.add(interview);
            }

        }




        // overwrite txt
        interviewController.rewriteInterviewList(testList);

        // check so testjobb is not in the txtlist
        interviewController.getInterviewListFromTxt().
                forEach((interview)->assertNotSame(get_TestInterview(),interview));

    }

    @Test
    public void checkForUnreadableChars(){
        for (Interview interview: interviewController.getInterviewListFromTxt()) {
            assertFalse(interview.getCOMPANY().contains("�"));
        }
    }

    @Test
    public void checkIfCanHandleSweChars(){
        /// checks if test interview gets changed during the process

        for (Interview interview : interviewController.getInterviewListFromTxt()) {
            // loop until find Testjob

            if(     interview.getCOMPANY().equals(get_TestInterview().getCOMPANY()) )
            {
                // when found check so its the same as before and no char has changed
                assertEquals(interview.getCOMPANY() ,get_TestInterview().getCOMPANY());
            }


        }
    }


    @AfterClass
    public static void cleanUp() { // cleans up any testjobs that may be left behind
        InterviewController interviewController = new InterviewController();
        ObservableList<Interview> testList = FXCollections.observableArrayList();
        for (Interview interview : interviewController.getInterviewListFromTxt()) {

            if (!interview.getCOMPANY().equals(get_TestInterview().getCOMPANY()))

                testList.add(interview);
        }

        interviewController.rewriteInterviewList(testList);

    }


    public static Interview get_TestInterview(){
      Interview interview = new Interview();
      interview.setCOMPANY("kålomålarens pistach fabrik");
      interview.setINTERVIEWDAY(LocalDate.now());
      return interview;
    }

}
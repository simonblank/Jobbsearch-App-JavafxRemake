package Tests.TxtControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sample.Model.Job;
import sample.TxtControllers.JobbListController;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class JobbListControllerTest {
    private JobbListController jobbListController = new JobbListController();

    @Test
    public void testReadFromTxt_NotNull(){
        // read content from txt should not return null
        assertNotNull(jobbListController.getSearchedJobListFromTxt());

    }

    @Test
    public void test_AddToTxt(){
        ObservableList<Job> testList =jobbListController.getSearchedJobListFromTxt();


        // create sample job and add it to txt and testlist
        jobbListController.addJobToList(get_TestJob());
        testList.add(get_TestJob());


        // check so both lists has same amount of objects after add
        assertEquals(testList.size() , jobbListController.getSearchedJobListFromTxt().size() );


        // check so both list objects has the same content
       for(int i=0  ; i<jobbListController.getSearchedJobListFromTxt().size() ;i++ ){

        assertEquals(testList.get(i).getTITLE(),jobbListController.getSearchedJobListFromTxt().get(i).getTITLE());
        assertEquals(testList.get(i).getCOMPANY(),jobbListController.getSearchedJobListFromTxt().get(i).getCOMPANY());
        assertEquals(testList.get(i).getDateapplied(),jobbListController.getSearchedJobListFromTxt().get(i).getDateapplied());
        assertEquals(testList.get(i).getURL(),jobbListController.getSearchedJobListFromTxt().get(i).getURL());

       }
    }

    @Test
    public void test_ContentFromTxt(){
        jobbListController.getSearchedJobListFromTxt().forEach(Assert::assertNotNull);
    }

    @Test
    public void test_RewriteTxtFile(){
        ObservableList<Job> testList = FXCollections.observableArrayList();
        // testjob
        jobbListController.addJobToList(get_TestJob());

        // testjob should not be in the the new list. Add all other jobs
        for (Job job: jobbListController.getSearchedJobListFromTxt()) {

            if(     !job.getTITLE().equals(get_TestJob().getTITLE()) &&
                    !job.getCOMPANY().equals(get_TestJob().getCOMPANY()) &&
                    !job.getURL().equals(get_TestJob().getURL())  ){
                testList.add(job);
            }

    }




        // overwrite txt
        jobbListController.rewriteAppliedJobList(testList);

        // check so testjobb is not in the txtlist
        jobbListController.getSearchedJobListFromTxt().
                forEach((job)->assertNotSame(get_TestJob(),job));

    }

    @Test
    public void checkForUnreadableChars(){
        for (Job job: jobbListController.getSearchedJobListFromTxt()) {
            assertFalse(job.getTITLE().contains("�"));
        }
    }

    @Test
    public void checkIfCanHandleSweChars(){
        for (Job job : jobbListController.getSearchedJobListFromTxt()) {
            // loop until find Testjob

            if(     job.getTITLE().equals(get_TestJob().getTITLE())  &&
                    job.getCOMPANY().equals(get_TestJob().getCOMPANY()) &&
                    job.getDateapplied().equals(get_TestJob().getDateapplied()) &&
                    job.getURL().equals(get_TestJob().getURL()))
            {
                // when found check so its the same as before and no char has changed
                assertEquals(job.getCOMPANY() ,get_TestJob().getCOMPANY());
            }


        }

    }

    @AfterClass
    public static void cleanUp(){ // cleans up any testjobs that may be left behind
        JobbListController jobbListController = new JobbListController();
        ObservableList<Job> testList = FXCollections.observableArrayList();
        for (Job job: jobbListController.getSearchedJobListFromTxt()) {

            if(     !job.getTITLE().equals(get_TestJob().getTITLE()) &&
                    !job.getCOMPANY().equals(get_TestJob().getCOMPANY()) &&
                    !job.getURL().equals(get_TestJob().getURL())  ){
                testList.add(job);
            }

        }




        // overwrite txt
        jobbListController.rewriteAppliedJobList(testList);

    }


    public static Job get_TestJob(){
        Job job = new Job();
        job .setTITLE("great job");
        job.setCOMPANY("skånska");
        LocalDate localDate = LocalDate.now();
        job.setDateapplied(localDate);
        job.setURL("www.google.se");
        return job;
    }

}

/** to test
 * test that i actually get content from get getfromtxt
 * test adding a job
 * check so list is fine after adding new job
 * remove the new job
 * check so the list is fine again
 * check åäö
 *
 * fix something anti crash?
 *
 *
 *
 *
 *
 * */








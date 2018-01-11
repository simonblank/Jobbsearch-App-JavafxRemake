package Tests.TxtControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import sample.Model.Job;
import sample.Model.Password;
import sample.TxtControllers.JobbListController;
import sample.TxtControllers.PasswordController;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PasswordControllerTest {
    private PasswordController passwordController = new PasswordController();

    @Test
    public void testReadFromTxt_NotNull(){
        // read content from txt should not return null
        assertNotNull(passwordController.getPasswordListFromTxt());

    }

    @Test
    public void test_AddToTxt(){
        ObservableList<Password> testList =passwordController.getPasswordListFromTxt();


        // create sample job and add it to txt and testlist
        passwordController.addPasswordToList(get_TestPassword());
        testList.add(get_TestPassword());


        // check so both lists has same amount of objects after add
        assertEquals(testList.size() , passwordController.getPasswordListFromTxt().size() );


        // check so both list objects has the same content
        for(int i=0  ; i<passwordController.getPasswordListFromTxt().size() ;i++ ){

            assertEquals(testList.get(i).getPLACE(),passwordController.getPasswordListFromTxt().get(i).getPLACE());

        }
    }

    @Test
    public void test_ContentFromTxt(){
        passwordController.getPasswordListFromTxt().forEach(Assert::assertNotNull);
    }

    @Test
    public void test_RewriteTxtFile(){
        ObservableList<Password> testList = FXCollections.observableArrayList();
        // testjob
        passwordController.addPasswordToList(get_TestPassword());

        // testjob should not be in the the new list. Add all other jobs
        for (Password password: passwordController.getPasswordListFromTxt()) {

            if(     !password.getPLACE().equals(get_TestPassword().getPLACE()) &&
                    !password.getPASSWORD().equals(get_TestPassword().getPASSWORD())  ){
                testList.add(password);
            }

        }




        // overwrite txt
        passwordController.rewritePasswordList(testList);

        // check so testjobb is not in the txtlist
        passwordController.getPasswordListFromTxt().
                forEach((password)->assertNotSame(get_TestPassword(),password));

    }

    @Test
    public void checkForUnreadableChars(){
        for (Password password: passwordController.getPasswordListFromTxt()) {
            assertFalse(password.getPLACE().contains("�"));
            assertFalse(password.getPASSWORD().contains("�"));
        }
    }

    @Test
    public void checkIfCanHandleSweChars(){
        for (Password password : passwordController.getPasswordListFromTxt()) {
            // loop until find Testjob

            if(     password.getPASSWORD().equals(get_TestPassword().getPASSWORD())  &&
                    password.getPLACE().equals(get_TestPassword().getPLACE()) )

            {
                // when found check so its the same as before and no char has changed
                assertEquals(password.getPASSWORD() ,get_TestPassword().getPASSWORD());
                assertEquals(password.getPLACE() ,get_TestPassword().getPLACE());
            }


        }

    }

    @AfterClass
    public static void cleanUp(){ // cleans up any testjobs that may be left behind
        PasswordController passwordController = new PasswordController();
        ObservableList<Password> testList = FXCollections.observableArrayList();
        for (Password password: passwordController.getPasswordListFromTxt()) {

            if(     !password.getPLACE().equals(get_TestPassword().getPLACE()) &&
                    !password.getPASSWORD().equals(get_TestPassword().getPASSWORD())  ){
                testList.add(password);
            }

        }




        // overwrite txt
        passwordController.rewritePasswordList(testList);

    }


    public static Password get_TestPassword(){
        Password password = new Password();
        password.setPLACE("aplacethatdonåy exist");
        password.setPASSWORD("randomwhitttypassword");
        return password;
    }




}
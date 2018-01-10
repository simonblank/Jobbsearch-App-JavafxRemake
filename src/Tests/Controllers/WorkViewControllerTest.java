package Tests.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import sample.Controllers.WorkViewController;
import sample.Main;

import java.time.LocalDate;
import java.util.concurrent.TimeoutException;


import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextFlowMatchers.hasText;

public class WorkViewControllerTest extends ApplicationTest {
    WorkViewController workViewController = new WorkViewController();

    @Before
    public void setUpClass() throws Exception{
    ApplicationTest.launch(Main.class);

    }

    @Override
    public void start (Stage stage)throws  Exception{
        stage.show();

    }

    @After
    public void afterEachTest()throws TimeoutException{
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }








    @Test public void testHandleAddButton() {
        // button should have text Add
        verifyThat("#add_Button", (Button button)->{
            String text = button.getText();
            return text.contains("Add");
        });
    }

    @Test public void testHandleAddButto2n() {
        // button should have text Add
        verifyThat("#add_Button", (Button button)->{
            String text = button.getText();
            return text.contains("Add");
        });
    }




/*


    @Test
    public void testGetThisMonth(){
        // get string of current month with first letter capitalized
        assertEquals(containsMonth(workViewController.getThisMonth()) , true);

    }

    @Test
    public void  testParseMonthStringToCorrectFormat(){
        LocalDate localDate =  LocalDate.now();
        // convert local date. now string to string of current month with first letter capitalized
        assertEquals(containsMonth(workViewController.parseMonthStringToCorrectFormat(localDate)) , true);
    }

    private Boolean containsMonth(String month){
        if(month.equals("January")){return true;}
        if(month.equals("February")){return true;}
        if(month.equals("March")){return true;}
        if(month.equals("April")){return true;}
        if(month.equals("May")){return true;}
        if(month.equals("June")){return true;}
        if(month.equals("July")){return true;}
        if(month.equals("August")){return true;}
        if(month.equals("September")){return true;}
        if(month.equals("Oktober")){return true;}
        if(month.equals("November")){return true;}
        if(month.equals("December")){return true;}
        else return false;
    }

    //*/


}
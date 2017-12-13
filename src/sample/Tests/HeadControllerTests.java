package sample.Tests;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobotException;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

/**
 * Created by simon on 13/12/2017.
 */
public class HeadControllerTests extends ApplicationTest {

    @Start
    void onStart(Stage stage) {
        Button button = new Button("click me!");
        stage.show();
    }


    @Test
    void should_contain_button() {
        // expect:


        verifyThat("ImageView", hasText("click me!"));
    }

}

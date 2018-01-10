package Tests;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.junit.Test;
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

    @Override public void start(Stage stage) {
        Parent sceneRoot = new AnchorPane();
        Scene scene = new Scene(sceneRoot, 100, 100);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void test(){



    }
}

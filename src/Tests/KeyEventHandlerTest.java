package Tests;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.junit.Test;
import org.testfx.framework.junit5.ApplicationTest;
import sample.KeyEventHandler;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;



public class KeyEventHandlerTest extends ApplicationTest {
    KeyEventHandler keyEventHandler = new KeyEventHandler();

    @Test
    public void testDeleteKeyPressed(){


    }

    @Test
    public void testAddToClipBoard(){

        new javafx.embed.swing.JFXPanel();
        keyEventHandler.addToClipBoard("test");

        try {
            assertEquals("test", Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

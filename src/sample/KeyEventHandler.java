package sample;

import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler {




    public Boolean isItemSelectedInTable(TableView table){
        return !table.getSelectionModel().getSelectedItems().isEmpty();
    }

    public Boolean deleteKeyIsPressed(KeyEvent event){
        return event.getCode().equals(KeyCode.DELETE);
    }

    public Boolean key_1_IsPressed(KeyEvent event){
        return event.getText().equals("1");
    }

    public Boolean key_2_IsPressed(KeyEvent event){
        return event.getText().equals("2");
    }
    public Boolean key_3_IsPressed(KeyEvent event){
        return event.getText().equals("3");
    }
    public Boolean key_4_IsPressed(KeyEvent event){
        return event.getText().equals("4");
    }

    public void addToClipBoard(String input){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();

        clipboardContent.putString(input);
        clipboard.setContent(clipboardContent);
    }
}

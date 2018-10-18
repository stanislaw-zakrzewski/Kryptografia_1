package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    public TextArea t1, t2;
    public Button button, fileChooser;

    private String file;

    public Controller() {

    }

    public void pressButton(ActionEvent event) {
        t2.setText(t1.getText());
    }

    public void pressChooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        System.out.println(file);
    }

    public String getFile() {
        return file;
    }
}

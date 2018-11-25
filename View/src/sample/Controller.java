package sample;

import algorithm.Algorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;

public class Controller {
    private String message;
    private String codedMessage;
    private String keyString;
    private Algorithm algorithm;
    private File file;

    @FXML
    public TextArea key, messages;
    @FXML
    public Button browse, encode, decode, randomKey;

    public Controller() {
        algorithm = new Algorithm();
    }

    public void pressBrowse(ActionEvent event) {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            messages.setText("press encode code file");
        }
        browse.setDisable(true);
        randomKey.setDisable(true);
        key.setDisable(true);
        encode.setDisable(false);

    }

    public void pressEncode(ActionEvent event) {
        messages.setText("press decode to decode file");
        decode.setDisable(false);
    }

    public void pressDecode(ActionEvent event) {
        messages.setText("your file has been successfully decoded");
    }

    public void pressRandomKey(ActionEvent event) {

    }
}

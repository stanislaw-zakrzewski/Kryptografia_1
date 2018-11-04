package sample;

import algorithm.Algorithm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import methods.Conversions;

import java.io.File;

public class Controller {
    private String message;
    private String codedMessage;
    private String keyString;
    private Algorithm algorithm;

    @FXML
    public TextArea nonCoded, coded, key;
    @FXML
    public Button encode, decode;

    public Controller() {
        algorithm = new Algorithm();
    }

    public void pressEncode(ActionEvent event) {
        message = nonCoded.getText();
        if(message.length() > 0) {
            keyString = key.getText();
            if(keyString.length() == 24) {
                coded.setText(algorithm.encode3DES(message, keyString));
            }
        }
    }

    public void pressDecode(ActionEvent event) {
        codedMessage = coded.getText();
        if(codedMessage.length() > 0) {
            keyString = key.getText();
            if(keyString.length() == 24) {
                nonCoded.setText(algorithm.decode3DES(codedMessage, keyString));
            }
        }
    }
}

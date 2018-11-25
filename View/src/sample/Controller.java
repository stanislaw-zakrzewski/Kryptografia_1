package sample;

import algorithm.Algorithm;
import data.SaveToFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import keys.GenerateKey;
import methods.Conversions;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
    private List<Byte> encoded;
    private List<Byte> decoded;
    private Algorithm algorithm;
    private File file;
    private SaveToFile saveToFile;
    private GenerateKey generateKey;
    private String end;

    @FXML
    public TextArea key, messages;
    @FXML
    public Button browse, encode, decode, randomKey;

    public Controller() {
        algorithm = new Algorithm();
        saveToFile = new SaveToFile();
        generateKey = new GenerateKey();
    }

    public void pressBrowse(ActionEvent event) {

        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            messages.setText("press encode code file");
            encode.setDisable(false);
            String s = file.getName();
            System.out.println(s);
            char[] pom = s.toCharArray();
            int pom2 = pom.length-1;
            while (pom[pom2] != '.') {
                pom2--;
            }
            end = s.substring(pom2);
        }
    }

    public void pressEncode(ActionEvent event) {
        System.out.println(key.getText().length());
        if(file != null && key.getText().length() == 48) {
            encode.setDisable(true);
            List<Byte> list = new ArrayList<>();
            try {
                byte[] bytes = Files.readAllBytes(file.toPath());
                for(Byte b : bytes) {
                    List<Byte> pom = Conversions.numberTo8Byte(b);
                    list.addAll(pom);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }/*
            for(byte b : list) {
                System.out.print(b);
            }*/
            encoded = algorithm.encode3DES(list, key.getText());
            messages.setText("press decode to decode file");
            browse.setDisable(true);
            randomKey.setDisable(true);
            key.setDisable(true);
            decode.setDisable(false);
        }
    }

    public void pressDecode(ActionEvent event) {
        if(encode != null) {
            decoded = algorithm.decode3DES(encoded, key.getText());
            /*System.out.println();
            System.out.println("Decode ponizej");
            for(byte b : decoded) {
                System.out.print(b);
            }*/
            saveToFile.save("decode" + end, decoded, algorithm.toRemoveBytes);
            messages.setText("your file has been successfully decoded");
            decode.setDisable(true);
        }
    }

    public void pressRandomKey(ActionEvent event) {
        key.setText(generateKey.getRandomKey());
    }
}

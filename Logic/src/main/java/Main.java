import algorithm.Algorithm;
import methods.Conversions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            byte[] bytes = Files.readAllBytes(new File("oko.pdf").toPath());
            for(Byte b : bytes) {
                List<Byte> pom = Conversions.numberTo8Byte(b);
                for(byte bb : pom) {
                    sb.append((bb));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        Algorithm a = new Algorithm();

        List<Byte> list = new ArrayList<>();
        String s = sb.toString();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1') {
                list.add((byte) 1);
            } else {
                list.add((byte) 0);
            }
        }

        List<Byte> encoded = a.encode3DES(list,"qwertyuioppoiuytrewqasdf");
        //TODO binarka do filea
        List<Byte> decoded = a.decode3DES(encoded, "qwertyuioppoiuytrewqasdf");
        for(byte b : decoded) {
            System.out.print(b);
        }
    }
}

import algorithm.Algorithm;
import methods.Conversions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String s = "";
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
        String encoded = a.encode3DES(sb.toString(),"qwertyuioppoiuytrewqasdf");
        String decoded = a.decode3DES(encoded, "qwertyuioppoiuytrewqasdf");
        System.out.println(decoded);
    }
}

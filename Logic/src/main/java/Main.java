
import algorithm.Algorithm;
import keys.Key;
import methods.Conversions;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Helloo");
        String dupa = "aaaaaaaa";


        Algorithm a = new Algorithm();
        String key = "dddddddupa";
        String message = "Witam serdecznie";

        System.out.println(a.encode(dupa, new Key(key)));

        List<List<Byte>> oko = Conversions.stringTo64Byte(dupa);
        oko.forEach(o -> o.forEach(System.out::print));
    }
}

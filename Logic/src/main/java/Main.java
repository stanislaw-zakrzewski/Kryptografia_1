
import algorithm.Algorithm;
import keys.Key;
import methods.Conversions;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Algorithm a = new Algorithm();
        String dupa = "aaaaaaaa";
        String key = "dddddddupa";

        Key k = new Key(key);

        System.out.println("Encoded:");
        String encoded = a.encode(dupa, k);
        System.out.println(encoded);
        System.out.println();

        System.out.println("Decoded:");
        String decoded = a.decode(encoded, k);
        System.out.println(decoded);
        System.out.println();

        System.out.println("How it should look:");
        List<List<Byte>> oko = Conversions.stringTo64Byte(dupa);
        oko.forEach(o -> o.forEach(System.out::print));
    }
}

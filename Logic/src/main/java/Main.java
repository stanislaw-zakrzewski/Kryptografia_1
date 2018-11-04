
import algorithm.Algorithm;
import keys.Key;
import methods.Conversions;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Algorithm a = new Algorithm();
        //String dupa = "0000000100100011010001010110011110001001101010111100110111101111";
        //String key = "0001001100110100010101110111100110011011101111001101111111110001";
        /*String dupa = "chujkurwa";
        String key = "dupadupa";

        Key k = new Key(key);

        System.out.println("Encoded:");
        String encoded = a.encode(dupa, k);
        System.out.println(encoded);
        System.out.println();

        /*System.out.println("How it should look:");
        System.out.println("1000010111101000000100110101010000001111000010101011010000000101");
        System.out.println("\n");

        System.out.println("Decoded:");
        String decoded = a.decode(encoded, k);
        System.out.println(decoded);
        System.out.println();

        System.out.println("How it should look:");
        List<List<Byte>> oko = Conversions.stringTo64Byte(dupa);
        oko.forEach(o -> o.forEach(System.out::print));
        System.out.println();

        System.out.println("Sth");*/

        String w1 = a.encode3DES("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem.","012345678901234567891234");
        System.out.println("Zakodowano:");
        System.out.println(Conversions.toNormalCharacters(w1));

        String w2 = a.decode3DES(w1, "012345678901234567891234");
        System.out.println("Zdekodowano:");
        System.out.println(w2);

    }
}

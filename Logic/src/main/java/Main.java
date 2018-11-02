
import methods.Conversions;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Helloo");
        String dupa = "dupadupa2";
        List<List<Byte>> b = Conversions.stringTo64Byte(dupa);
        b.forEach(I -> {I.forEach(System.out::print); System.out.println();});

        System.out.println(b.get(0).get(1));

        List<Byte> bb = b.get(0);



    }
}

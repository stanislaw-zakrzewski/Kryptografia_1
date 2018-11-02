
import methods.Conversions;
import methods.Permutacjon;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Helloo");
        String dupa = "dupaapud";
        List<List<Byte>> b = Conversions.stringTo64Byte(dupa);
        b.forEach(I -> {I.forEach(System.out::print); System.out.println();});

        List<Byte> bb = b.get(0);

        List<Byte> gotwe = Permutacjon.IPPerm(bb);

        gotwe.forEach(System.out::print);

    }
}

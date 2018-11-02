
import methods.Conversions;
import methods.FunctionF;
import methods.Permutacjon;

import java.util.ArrayList;
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


        List<Byte> test1 = new ArrayList<>();
        List<Byte> test2 = new ArrayList<>();

        for (int i=0;i<32;i++) {
            test1.add(bb.get(i));
        }
        for (int i=0;i<48;i++) {
            test2.add(bb.get(i));
        }

        FunctionF f = new FunctionF();
        f.functionF(test1,test2);
    }
}

package methods;

import java.util.ArrayList;
import java.util.List;

public class Conversions {
    public static List<List<Byte>> stringTo64Byte(String s) {
        byte[]  bytes = s.getBytes();
        List<Integer> pom3 = new ArrayList<>();
        for(byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                pom3.add((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }

        List<List<Byte>> readyBytes = new ArrayList<>();
        int pom1 = 0;
        int pom2 = 0;
        readyBytes.add(new ArrayList<>());
        for(int i : pom3) {
            if(pom1 == 64) {
                pom2++;
                readyBytes.add(new ArrayList<>());
                pom1 = 0;
            }
            readyBytes.get(pom2).add((byte)i);
            pom1++;
        }
        return readyBytes;
    }
}

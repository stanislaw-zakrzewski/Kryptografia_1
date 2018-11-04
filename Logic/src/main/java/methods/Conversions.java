package methods;

import java.util.ArrayList;
import java.util.List;

public class Conversions {
    public static String byteToString() {
        return "oko";
    }

    public static List<List<Byte>> stringTo64Byte(String s) {
        byte[] bytes = s.getBytes();
        List<Integer> pom3 = new ArrayList<>();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                pom3.add((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }

        List<List<Byte>> readyBytes = new ArrayList<>();
        int pom1 = 0;
        int pom2 = 0;
        readyBytes.add(new ArrayList<>());
        for (int i : pom3) {
            if (pom1 == 64) {
                pom2++;
                readyBytes.add(new ArrayList<>());
                pom1 = 0;
            }
            readyBytes.get(pom2).add((byte) i);
            pom1++;
        }
        if (readyBytes.get(readyBytes.size() - 1).size() < 64) {
            int sizef = 64 - readyBytes.get(readyBytes.size() - 1).size();
            for (int i = 0; i < sizef; i++) {
                readyBytes.get(readyBytes.size() - 1).add((byte) 0);
            }
        }
        return readyBytes;
    }

    public static List<Byte> numberTo4Byte(byte number) {
        List<Byte> ret = new ArrayList<>();
        int val = number;
        for (int i = 0; i < 4; i++) {
            ret.add((byte)((val & 16) == 0 ? 0 : 1));
            val <<= 1;
        }
        return ret;
    }

    public static List<List<Byte>> encodedMessageTo64Byte(String message) {
        List<List<Byte>> ret = new ArrayList<>();
        for(int i = 0; i < message.length()/64; i++) {
            ret.add(new ArrayList<>());
            for(int j = 0; j < 64; j++) {
                ret.get(i).add(message.getBytes()[i*64+j]);
            }
        }
        return ret;
    }
}

package algorithm;

import keys.Key;
import methods.Conversions;
import methods.FunctionF;
import methods.LogicOperators;
import methods.Permutation;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {


    /**
     * Kodowanie trzykrotnym DESem
     *
     * @param message - ciag binarny
     * @param key  - klucz w formie 48 znakow kodowania szesnastkowego
     * @return - zakodowany ciag binarny w postaci string
     */
    public String encode3DES (String message, String key){
        Key k1 = new Key(key.substring(0, 8)); //TODO
        Key k2 = new Key(key.substring(8, 16)); //TODO
        Key k3 = new Key(key.substring(16, 24)); //TODO

        String encode1 = encode(message, k1);
        String decode1 = decode(encode1, k2);
        return encode(decode1, k3);
    }

    /**
     * Dekodowanie trzykrotnym DESem
     *
     * @param message
     * @param key
     * @return
     */
    public String decode3DES(String message, String key) {
        Key k1 = new Key(key.substring(0, 8));
        Key k2 = new Key(key.substring(8, 16));
        Key k3 = new Key(key.substring(16, 24));

        String s1, s2, s3, w;
        w = message;
        s1 = decode(w, k3);
        s2 = encode(s1, k2);
        s3 = decode(s2, k1);
        w = Conversions.toNormalCharacters(s3);
        return w;
    }

    /**
     * Kodowanie pojedyńczym DESem
     *
     * @param message - ciag binarny
     * @param key - klucz 16 znakow w kodowaniu hexadecymalnym
     * @return - zakodowany ciag binarny przy pomocy podanego klucza
     */
    private String encode(String message, Key key) {
        List<List<Byte>> ret = new ArrayList<>();
        List<List<Byte>> pom1 = Conversions.encodedMessageTo64Byte(message);

        for(List<Byte> lb : pom1) {
            ret.add(encode64(lb, key));
        }

        StringBuilder sb = new StringBuilder();
        ret.forEach(r -> r.forEach(rr -> sb.append(rr == 1 ? "1" : "0")));
        return sb.toString();
    }

    /**
     * Kodowanie 64-bitowego bloku danych
     *
     * @param message - 64 bity
     * @param key - 16 znakow kodowanych w systemie hexadecymalnym
     * @return - zakodowane 64 bity przy pomocy podanego klucza
     */
    private List<Byte> encode64(List<Byte> message, Key key) {
        List<Byte> ret = new ArrayList<>();
        List<Byte> pom1 = Permutation.IPPerm(message);
        List<Byte> L = new ArrayList<>(pom1.subList(0,32));
        List<Byte> R = new ArrayList<>(pom1.subList(32,64));
        List<Byte> pomL = new ArrayList<>(L);
        List<Byte> pomR = new ArrayList<>(R);
        ArrayList<List<Byte>> keys = key.keyTableForEveryRound();

        for(int i = 0; i < 16; i++) {
            L = new ArrayList<>(pomR);
            R = new ArrayList<>(FunctionF.functionF(R,keys.get(i)));

            for(int j = 0; j < R.size(); j++) {
                R.set(j, LogicOperators.xor(R.get(j), pomL.get(j)));
            }
            pomL = new ArrayList<>(L);
            pomR = new ArrayList<>(R);
        }
        ret.addAll(L);
        ret.addAll(R);
        ret = Permutation.FPPerm(ret);
        return ret;
    }

    //Dekodowanie pojedyńczym DESem
    private String decode(String message, Key key) {
        List<List<Byte>> ret = new ArrayList<>();
        List<List<Byte>> pom1 = Conversions.encodedMessageTo64Byte(message);

        for(List<Byte> lb : pom1) {
            ret.add(decode64(lb, key));
        }

        StringBuilder sb = new StringBuilder();
        ret.forEach(r -> r.forEach(rr -> sb.append(rr == 1 ? "1" : "0")));
        return sb.toString();
    }

    //Dekodowanie 64-bitowego bloku danych
    private List<Byte> decode64(List<Byte> message, Key key) {
        List<Byte> ret = new ArrayList<>();
        List<Byte> pom1 = Permutation.IPPerm(message);
        List<Byte> L = new ArrayList<>(pom1.subList(0,32));
        List<Byte> R = new ArrayList<>(pom1.subList(32,64));
        List<Byte> pomL = new ArrayList<>(L);
        List<Byte> pomR = new ArrayList<>(R);
        ArrayList<List<Byte>> keys = key.keyTableForEveryRound();

        for(int i = 0; i < 16; i++) {
            R = new ArrayList<>(pomL);
            L = new ArrayList<>(FunctionF.functionF(L,keys.get(15-i)));

            for(int j = 0; j < L.size(); j++) {
                L.set(j, LogicOperators.xor(L.get(j), pomR.get(j)));
            }
            pomR = new ArrayList<>(R);
            pomL = new ArrayList<>(L);
        }
        ret.addAll(L);
        ret.addAll(R);
        ret = Permutation.FPPerm(ret);
        return ret;
    }
}

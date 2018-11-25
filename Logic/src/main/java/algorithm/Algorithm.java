package algorithm;

import keys.Key;
import methods.Conversions;
import methods.FunctionF;
import methods.LogicOperators;
import methods.Permutation;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    public int toRemoveBytes;


    /**
     * Kodowanie trzykrotnym DESem
     *
     * @param message - ciag binarny
     * @param key  - klucz w formie 48 znakow kodowania hexadecymalnego
     * @return - zakodowany ciag binarny w postaci string
     */
    public List<Byte> encode3DES (List<Byte> message, String key){
        Key k1 = new Key(key.substring(0, 16)); //TODO
        Key k2 = new Key(key.substring(16, 32)); //TODO
        Key k3 = new Key(key.substring(32, 48)); //TODO

        System.out.println(message.size());
        List<Byte> encode = encode(message, k1);
        System.out.println(encode.size());
        List<Byte> decode = decode(encode, k2);
        return encode(decode, k3);
    }

    /**
     * Dekodowanie trzykrotnym DESem
     *
     * @param message - ciag binarny
     * @param key - klucz w formnie 48znakow kodowania hexadecymalnego
     * @return - zwracana odkodowana wiadomosc w ciagu binarnym
     */
    public List<Byte> decode3DES(List<Byte> message, String key) {
        Key k1 = new Key(key.substring(0, 16)); //TODO
        Key k2 = new Key(key.substring(16, 32)); //TODO
        Key k3 = new Key(key.substring(32, 48)); //TODO

        List<Byte> decode = decode(message, k3);
        List<Byte> encode = encode(decode, k2);
        return decode(encode, k1);
    }

    /**
     * Kodowanie pojedyńczym DESem
     *
     * @param message - ciag binarny
     * @param key - klucz 16 znakow w kodowaniu hexadecymalnym
     * @return - zakodowany ciag binarny przy pomocy podanego klucza
     */
    private List<Byte> encode(List<Byte> message, Key key) {
        List<List<Byte>> ret = new ArrayList<>();
        List<List<Byte>> pom1 = Conversions.encodedMessageTo64Byte(message);
        toRemoveBytes = Conversions.cropValue;
        for(List<Byte> lb : pom1) {
            ret.add(encode64(lb, key));
        }
        List<Byte> pom  = new ArrayList<>();
        for(List<Byte> b : ret) {
            pom.addAll(b);
        }
        return pom;
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

    /**
     * Dekodowanie pojedyńczym DESem
     *
     * @param message - ciag binarny
     * @param key - klucz w formie 16hexow
     * @return - ciag binarny
     */
    private List<Byte> decode(List<Byte> message, Key key) {
        List<List<Byte>> ret = new ArrayList<>();
        List<List<Byte>> pom1 = Conversions.encodedMessageTo64Byte(message);

        for(List<Byte> lb : pom1) {
            ret.add(decode64(lb, key));
        }
        List<Byte> pom  = new ArrayList<>();
        for(List<Byte> b : ret) {
            pom.addAll(b);
        }
        return pom;
    }

    /**
     * Dekodowanie 64-bitowego bloku danych
     *
     * @param message - ciag binaryny
     * @param key - klucz 16 hexow
     * @return - ciag binarny
     */
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

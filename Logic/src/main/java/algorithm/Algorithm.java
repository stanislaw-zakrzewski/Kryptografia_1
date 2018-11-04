package algorithm;

import keys.Key;
import methods.Conversions;
import methods.FunctionF;
import methods.LogicOperators;
import methods.Permutacjon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {

    public String encode(String message, Key key) {
        List<List<Byte>> ret = new ArrayList<>();
        List<List<Byte>> pom1 = Conversions.stringTo64Byte(message);

        for(List<Byte> lb : pom1) {
            ret.add(encode64(lb, key));
        }

        StringBuilder sb = new StringBuilder();
        ret.forEach(r -> r.forEach(rr -> sb.append(rr == 1 ? "1" : "0")));
        return sb.toString();
    }

    private List<Byte> encode64(List<Byte> message, Key key) {
        List<Byte> ret = new ArrayList<>();
        List<Byte> pom1 = Permutacjon.IPPerm(message);
        List<Byte> L = new ArrayList<>(pom1.subList(0,32));
        List<Byte> R = new ArrayList<>(pom1.subList(32,64));
        List<Byte> pomL = new ArrayList<>(L);
        List<Byte> pomR = new ArrayList<>(R);

        for(int i = 0; i < 16; i++) {
            L = new ArrayList<>(pomR);
            R = new ArrayList<>(FunctionF.functionF(R,key.keyForPermutationI(i)));
            for(int j = 0; j < R.size(); j++) {
                R.set(j, LogicOperators.xor(R.get(j), pomL.get(j)));
            }
            pomL = new ArrayList<>(L);
            pomR = new ArrayList<>(R);
        }
        ret.addAll(L);
        ret.addAll(R);
        return ret;
    }

    public String decode(List<List<Byte>> codedMesage) {
        String ret = new String();


        return ret;
    }
}
package algorithm;

import methods.Conversions;
import methods.FunctionF;
import methods.LogicOperators;
import methods.Permutacjon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {

    public String encode(String message, List<Byte> key) {
        List<List<Byte>> ret = new ArrayList<>();
        List<List<Byte>> pom1 = Conversions.stringTo64Byte(message);

        for(List<Byte> lb : pom1) {
            ret.add(encode64(lb, key));
        }

        StringBuilder sb = new StringBuilder();
        ret.forEach(r -> r.forEach(rr -> sb.append(r)));
        return sb.toString();
    }

    private List<Byte> encode64(List<Byte> message, List<Byte> key) {
        List<Byte> ret = new ArrayList<>();
        List<Byte> pom1 = Permutacjon.IPPerm(message);
        List<Byte> L = pom1.subList(0,32);
        List<Byte> R = pom1.subList(32,64);
        List<Byte> pomL = L;
        List<Byte> pomR = R;

        for(int i = 0; i < 16; i++) {
            L = pomR;
            R = FunctionF.functionF(R,key);
            for(int j = 0; j < 32; j++) {
                R.set(j, LogicOperators.xor(R.get(i), pomL.get(i)));
            }
            pomL = L;
            pomR = R;
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

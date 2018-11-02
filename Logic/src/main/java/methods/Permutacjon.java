package methods;

import data.Tables;

import java.util.ArrayList;
import java.util.List;

public class Permutacjon {
    public static List<Byte> IPPerm (List<Byte> list) {
        List<Byte> ret = new ArrayList<>();

        for (int i : Tables.IP) {
            ret.add(list.get(i-1));
        }


        return ret;
    }
}

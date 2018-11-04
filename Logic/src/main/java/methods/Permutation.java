package methods;

import data.Tables;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static List<Byte> IPPerm(List<Byte> list) {
        List<Byte> ret = new ArrayList<>();
        for (int i : Tables.IP) {
            ret.add(list.get(i - 1));
        }
        return ret;
    }

    public static List<Byte> EPerm(List<Byte> list) {
        List<Byte> ret = new ArrayList<>();
        for (int i : Tables.E) {
            ret.add(list.get(i - 1));
        }
        return ret;
    }

    public static List<Byte> PPerm(List<Byte> list) {
        List<Byte> ret = new ArrayList<>();
        for (int i : Tables.P) {
            ret.add(list.get(i - 1));
        }
        return ret;
    }

    public static List<Byte> PC1Perm(List<Byte> list) {
        List<Byte> ret = new ArrayList<>();
        for (int i : Tables.PC1) {
            ret.add(list.get(i - 1));
        }
        return ret;
    }

    public static List<Byte> PC2Perm(List<Byte> list) {
        List<Byte> ret = new ArrayList<>();
        for (int i : Tables.PC2) {
            ret.add(list.get(i - 1));
        }
        return ret;
    }

    public static List<Byte> FPPerm(List<Byte> list) {
        List<Byte> ret = new ArrayList<>();
        for (int i : Tables.FP) {
            ret.add(list.get(i - 1));
        }
        return ret;
    }
}

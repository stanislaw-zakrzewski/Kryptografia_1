package keys;

import data.Tables;
import methods.Conversions;
import methods.Permutacjon;

import java.util.ArrayList;
import java.util.List;

public class Key {
    private List<Byte> ki;
    public Key(String ki){
        List<List<Byte>> pom;
        pom = Conversions.stringTo64Byte(ki);
        this.ki = pom.get(0);
        //System.out.println(this.ki.size());
    }
    public List<Byte> keyForPermutationI(int permutationNumber){
        List<Byte> keyAfterPC1 = Permutacjon.PC1Perm(ki);
        ArrayList<Byte> keyFirstHalf = new ArrayList(keyAfterPC1.subList(0,28));
        ArrayList<Byte> keySecendHalf = new ArrayList<>(keyAfterPC1.subList(28,56));
        for(int i = 0; i < Tables.R[permutationNumber];i++){
            keyFirstHalf.remove(0);
            keyFirstHalf.add(new Byte((byte)0));
            keySecendHalf.remove(0);
            keySecendHalf.add(new Byte((byte)0));
        }
        keyFirstHalf.addAll(keySecendHalf);
        //System.out.println(keyFirstHalf.size());
        return Permutacjon.PC2Perm(keyFirstHalf);
    }

    public List<Byte> getKi() {
        return ki;
    }

    public void genKi() {

    }


}

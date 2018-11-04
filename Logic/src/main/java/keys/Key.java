package keys;

import data.Tables;
import methods.Conversions;
import methods.Permutation;

import java.util.ArrayList;
import java.util.List;

public class Key {
    private List<Byte> ki;
    public Key(String ki){
        List<List<Byte>> pom;
        pom = Conversions.stringTo64Byte(ki);
        this.ki = pom.get(0);
    }

    public ArrayList<List<Byte>> keyTableForEveryRound(){
        ArrayList<List<Byte>> result = new ArrayList<>();
        List<Byte> keyAfterPC1 = Permutation.PC1Perm(ki);
        ArrayList<Byte> keyFirstHalf;
        ArrayList<Byte> keySecondHalf;

        for(int permutationNumber = 0; permutationNumber < 16 ; permutationNumber++){
            keyFirstHalf = new ArrayList<>(keyAfterPC1.subList(0,28));
            keySecondHalf = new ArrayList<>(keyAfterPC1.subList(28,56));

            for(int i = 0; i < Tables.R[permutationNumber];i++){
                keyFirstHalf.remove(0);
                keyFirstHalf.add((byte) 0);
                keySecondHalf.remove(0);
                keySecondHalf.add((byte) 0);
            }

            keyFirstHalf.addAll(keySecondHalf);
            result.add(Permutation.PC2Perm(keyFirstHalf));
            keyAfterPC1 = keyFirstHalf;

        }
        return result;
    }


}

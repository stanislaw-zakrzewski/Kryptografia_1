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
        //pom = Conversions.encodedMessageTo64Byte(ki);//todo
        this.ki = pom.get(0);
        //System.out.println(this.ki);
    }
    public List<Byte> keyForPermutationI(int permutationNumber){
        List<Byte> keyAfterPC1 = Permutacjon.PC1Perm(ki);
        ArrayList<Byte> keyFirstHalf = new ArrayList(keyAfterPC1.subList(0,28));
        ArrayList<Byte> keySecendHalf = new ArrayList<>(keyAfterPC1.subList(28,56));
        for(int i = 0; i < Tables.R[permutationNumber];i++){
            keyFirstHalf.remove(0);
            keyFirstHalf.add((byte) 0);
            keySecendHalf.remove(0);
            keySecendHalf.add((byte)0);
        }
        //System.out.println(keyAfterPC1);
        //System.out.println(keyFirstHalf + " + " + keySecendHalf);
        keyFirstHalf.addAll(keySecendHalf);
        //System.out.println(keyFirstHalf.size());
        return Permutacjon.PC2Perm(keyFirstHalf);
    }
    public ArrayList<List<Byte>> keyTableForEveryRound(){
        ArrayList<List<Byte>> resoult = new ArrayList<>();
        List<Byte> keyAfterPC1 = Permutacjon.PC1Perm(ki);
        ArrayList<Byte> keyFirstHalf;
        ArrayList<Byte> keySecendHalf;
        for(int permutationNumber = 0; permutationNumber < 16 ; permutationNumber++){
            //System.out.println(keyAfterPC1.size());
            keyFirstHalf = new ArrayList(keyAfterPC1.subList(0,28));
            keySecendHalf = new ArrayList<>(keyAfterPC1.subList(28,56));
            for(int i = 0; i < Tables.R[permutationNumber];i++){
                keyFirstHalf.remove(0);
                keyFirstHalf.add(new Byte((byte) 0));
                keySecendHalf.remove(0);
                keySecendHalf.add((byte) 0);
            }
            //System.out.println(keyAfterPC1);
            //System.out.println(keyFirstHalf + " + " + keySecendHalf);
            keyFirstHalf.addAll(keySecendHalf);
            //System.out.println(keyFirstHalf.size());
            resoult.add(Permutacjon.PC2Perm(keyFirstHalf));
            keyAfterPC1 = keyFirstHalf;

        }
        return resoult;
    }

    public List<Byte> getKi() {
        return ki;
    }

    public void genKi() {

    }


}

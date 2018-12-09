package keys;

import data.Tables;
import methods.Conversions;
import methods.Permutation;

import java.util.ArrayList;
import java.util.List;

public class Key {
    private List<Byte> key;
    private List<Byte> keytest;

    //Przyjmuje klucz w postaci ciągu znaków
    public Key(String keys){
        //List<List<Byte>> pom;
        //pom = Conversions.stringTo64Byte(keys);
        //this.key = pom.get(0);
        key = hexToBin(keys);

    }

    //Zwraca listę z wszystkimi podkluczami potrzebnymi do kodowania
    public ArrayList<List<Byte>> keyTableForEveryRound(){
        ArrayList<List<Byte>> result = new ArrayList<>();
        //Permutacja PC-1 - początkowa dla klucza
        List<Byte> keyAfterPC1 = Permutation.PC1Perm(key);
        ArrayList<Byte> keyFirstHalf;
        ArrayList<Byte> keySecondHalf;

        for(int permutationNumber = 0; permutationNumber < 16 ; permutationNumber++){
            //Podzial kluczna na 28-bitowe polowy
            keyFirstHalf = new ArrayList<>(keyAfterPC1.subList(0,28));
            keySecondHalf = new ArrayList<>(keyAfterPC1.subList(28,56));

            //Przesunięcia bitowe w zależności od iteracji
            for(int i = 0; i < Tables.R[permutationNumber];i++){
                keyFirstHalf.remove(0);
                keyFirstHalf.add((byte) 0);
                keySecondHalf.remove(0);
                keySecondHalf.add((byte) 0);
            }

            keyFirstHalf.addAll(keySecondHalf);
            //Permutacja PC-2
            result.add(Permutation.PC2Perm(keyFirstHalf));
            keyAfterPC1 = keyFirstHalf;

        }
        return result;
    }
    private List<Byte> hexToBin(String hex){
        ArrayList<Byte> resoult = new ArrayList<>();
        for(int i = 0;i<hex.length();i++){

            int bin = Integer.parseInt(hex.substring(i,i+1),16);
            String pom = Integer.toBinaryString(bin);
            for(int j = 3; j >= 0;j--){
                if(j<pom.length()){
                    if(pom.charAt(j)=='1'){
                        resoult.add((byte) 1);
                    }else {
                        resoult.add((byte) 0);
                    }
                }else {
                    resoult.add((byte) 0);
                }
            }
        }
        return resoult;
    }


}

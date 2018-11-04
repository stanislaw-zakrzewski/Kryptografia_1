package keys;

import data.Tables;
import methods.Conversions;
import methods.Permutation;

import java.util.ArrayList;
import java.util.List;

public class Key {
    private List<Byte> key;

    //Przyjmuje klucz w postaci ciągu znaków
    public Key(String keys){
        List<List<Byte>> pom;
        pom = Conversions.stringTo64Byte(keys);
        this.key = pom.get(0);
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


}

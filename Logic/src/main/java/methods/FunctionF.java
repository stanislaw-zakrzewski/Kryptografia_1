package methods;

import data.Tables;

import java.util.ArrayList;
import java.util.List;

public class FunctionF {
    public List<Byte> functionF(List<Byte> r, List<Byte> key) {
        List<Byte> ret = new ArrayList<>();

        //1 permutacja z rozszerzeniem (tabela E)
        List<Byte> after1 = Permutacjon.EPerm(r);

        //2 łączenie za pomocą sumy modulo 2 z 48 bitami przesuniętego i spermutowanego klucza
        List<Byte> after2 = new ArrayList<>();
        for(int i = 0; i < after1.size(); i++) {
            after2.add(LogicOperators.xor(after1.get(i), key.get(i)));
        }

        //3 i 4 dzielimy ciąg z 2 na 8 części i wprowadzamy do skrzynek S-bloków
        List<List<Byte>> after3 = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            after3.add(new ArrayList<>());
            for(int j = 0; j < 6; j++) {
                after3.get(i).add(after2.get(i*6+j));
            }
        }
        List<Byte> after4 = new ArrayList<>();
        int pom4 = 0;
        for(List<Byte> list : after3) {
            after4.addAll(Conversions.numberTo4Byte((byte)(Tables.SB[pom4][(list.get(0)*2 + list.get(5))*16 + list.get(1)*8 + list.get(2)*4 + list.get(3)*2 + list.get(4)])));
            pom4++;
        }

        //5 poddanie permutacji P
        ret = Permutacjon.PPerm(after4);

        return ret;
    }
}

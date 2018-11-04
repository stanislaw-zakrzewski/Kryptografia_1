import algorithm.Algorithm;
import methods.Conversions;


public class Main {
    public static void main(String[] args) {
        Algorithm a = new Algorithm();

        String w1 = a.encode3DES("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem.","012345678901234567891234");
        System.out.println("Zakodowano:");
        System.out.println(Conversions.toNormalCharacters(w1));

        String w2 = a.decode3DES(w1, "012345678901234567891234");
        System.out.println("Zdekodowano:");
        System.out.println(w2);

        String s = "witamwtiam";
        String s2 = Conversions.toBinaryString(s);
        String s3 = Conversions.toNormalCharacters(s2);
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);

    }
}

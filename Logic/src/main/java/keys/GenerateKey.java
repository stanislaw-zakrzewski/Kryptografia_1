package keys;

import java.util.Random;

public class GenerateKey {
    public String getRandomKey() {
        Random rng = new Random();
        String key = "";
        int num;
        for (int i = 0; i < 17; i++) {
            num = rng.nextInt(16);
            switch (num) {
                case 10:
                    key += 'A';
                    break;
                case 11:
                    key += 'B';
                    break;
                case 12:
                    key += 'C';
                    break;
                case 13:
                    key += 'D';
                    break;
                case 14:
                    key += 'E';
                    break;
                case 15:
                    key += 'F';
                    break;
                default:
                    key+=String.valueOf(i);
                    break;
            }
        }
        return key;
    }
}

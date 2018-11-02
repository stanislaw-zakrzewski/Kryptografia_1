package methods;

public class LogicOperators {
    public static byte xor(byte x, byte y) {
        boolean a = x == 1;
        boolean b = y == 1;
        return (byte)(((a || b) && !(a && b)) ? 1 : 0);
    }
}

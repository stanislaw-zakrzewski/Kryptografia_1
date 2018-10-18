package keys;

public class Key {
    private byte[] ki;

    public byte[] getKi() {
        return ki;
    }

    public void genKi() {
        ki = new byte[5];
    }


}

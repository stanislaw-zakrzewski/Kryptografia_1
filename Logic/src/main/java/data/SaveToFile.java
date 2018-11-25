package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SaveToFile {
    /***
     * Zapisuje do wskazanego pliki wskazana wiadomosc
     *
     * @param path - Scieżka do pliku (miejsca na dysku)
     * @param message - Lista bitów do zapisania
     */
    public void save(String path, List<Byte> message){
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(makeTableFromList(message));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private byte[] makeTableFromList(List<Byte> list) {
        Byte[] array = new Byte[list.size()];
        array = (Byte[])list.toArray();
        byte[] ret = new byte[array.length];
        for (int i=0;i<array.length;i++) {
            ret[i] = array[i];
        }
        return ret;
    }

}

package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveToFile {
    /***
     * Zapisuje do wskazanego pliki wskazana wiadomosc
     *
     * @param path - Scieżka do pliku (miejsca na dysku)
     * @param message - Lista bitów do zapisania
     */
    public void save(String path, List<Byte> message, int toRemoveBytes){
        List<Byte> pom = message;
        System.out.println();
        System.out.println("Save to file przed ponizej");
        for(byte b : pom) {
            System.out.print(b);
        }
        for(int i = 0; i < toRemoveBytes; i++) {
            pom.remove(pom.size()-1);
        }
        System.out.println();
        System.out.println("Save to file ponizej");
        for(byte b : pom) {
            System.out.print(b);
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(makeTableFromList(pom));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private byte[] makeTableFromList(List<Byte> list) {
        List<Byte> l2 = new ArrayList<>();


        for (int i=0;i<list.size();i+=8) {
            l2.add(getOneByte(list,i));
        }

        byte[] ret = new byte[l2.size()];
        for (int i=0;i<l2.size();i++) {
            ret[i] = l2.get(i);
        }
        return ret;
    }

    private byte getOneByte(List<Byte> list, int offset) {
        byte ret = 0;
        for (int i = offset; (i<list.size() && ((i+offset) < 8)); i++) {
            ret += (list.get(i) == 0) ? (1<<i) : 0;
        }
        return ret;
    }

}

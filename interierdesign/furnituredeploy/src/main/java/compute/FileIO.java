package compute;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {


    private static FileIO fileIO = new FileIO();

    private FileIO() {
    }

    public static FileIO getInstance() {
        return fileIO;
    }

    //Nacítanie suboru
    static ArrayList<String> readFile(String fin) throws IOException {


        File f = new File(fin);
        Scanner s = new Scanner(f);
        ArrayList<String> list = new ArrayList<String>();

        try {
            s = new Scanner(f);
            while (s.hasNext()) {
                list.add(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
        return list;
    }
    //Zapis do suboru
    static void writeFile(String content,String FILENAME) throws IOException {


        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);
            bw.write(content);

            System.out.println("Response:\r\n"+content+"\nis write to "+FILENAME+".");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
    }
    }

}

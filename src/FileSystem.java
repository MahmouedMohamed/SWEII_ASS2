import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class FileSystem {

    private String FileName = "";

    public FileSystem(String name) throws FileNotFoundException, UnsupportedEncodingException {
        FileName +=name + ".txt";
        PrintWriter writer = new PrintWriter(FileName );
        writer.close();
    }

    public void WriteToFile(String data) {
        try {

            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(FileName, true));
            out.write(data);
            out.newLine();
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }



    public static void main(String[] args) throws IOException {
        /*FileSystem f1 = new FileSystem();
        System.out.println(f1.FileName);
        FileSystem f11 = new FileSystem();
        System.out.println(f11.FileName);
        FileSystem f111 = new FileSystem();
        System.out.println(f111.FileName);
        f1.WriteToFile("yaseen");
        f1.WriteToFile("yaseen");
        f1.WriteToFile("yaseen");
        f1.WriteToFile("yaseen");*/
    }
}

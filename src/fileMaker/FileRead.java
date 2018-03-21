package fileMaker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Jacob Vikas Mahto
 */
public class FileRead {

    public static void main(String[] args) {
        BufferedReader a = null;
        BufferedReader b = null;
        try {

            a = new BufferedReader(new FileReader("E:\\jacob.txt"));
            //One way of reading the file
            System.out.println("Reading the file using readLine() method:");
            String contentLine = a.readLine();
            int term = 1;
            while (contentLine != null) {
                System.out.println(contentLine);

                contentLine = a.readLine();
            }

            //
            /* b = new BufferedReader(new FileReader("E:\\jacob.txt"));
            //Second way of reading the file
            System.out.println("Reading the file using read() method:");
            int num = 0;
            char ch;
            while ((num = b.read()) != -1) {
                ch = (char) num;
                System.out.print(ch);
            }
             */
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (a != null) {
                    a.close();
                }
                if (b != null) {
                    b.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error in closing the BufferedReader");
            }
        }
    }

}

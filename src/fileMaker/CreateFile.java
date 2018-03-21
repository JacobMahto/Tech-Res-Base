package fileMaker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jacob Vikas Mahto
 */
public class CreateFile {

    public static void main(String[] args) {
        BufferedWriter bw = null;

        try {
            File file = new File("E:\\jacob.txt");
            boolean fvar = file.createNewFile(); //if file gets created , true is returned , and if file is already present then false is returned.

            /* This logic will make sure that the file 
	  * gets created if it is not present at the
	  * specified location*/
//	  if (!file.exists()) {
//	     file.createNewFile();
//	  }
//writing content to file
            String mycontent = "Hello my name is jacob";
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(mycontent);
            System.out.println("File written Successfully");

        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();

        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }

    }

}

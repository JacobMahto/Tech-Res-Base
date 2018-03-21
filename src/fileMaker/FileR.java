package fileMaker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Jacob Vikas Mahto
 */
public class FileR {

    public static void main(String[] args) {
        String[] sub = null;
        BufferedReader a = null;
        try {
            a = new BufferedReader(new FileReader("E:\\jacob.txt"));
            String contentLine = a.readLine();
            int term = 1;
            while (contentLine != null) {
                System.out.println(contentLine);
                if (contentLine.trim().equals(1)) {
                    term = 1;
                } else if (contentLine.trim().equals(2)) {
                    term = 2;
                } else if (contentLine.trim().equals(3)) {
                    term = 3;
                } else if (contentLine.trim().equals(4)) {
                    term = 4;
                } else if (contentLine.trim().equals(5)) {
                    term = 5;
                }
                int elementCounter = 0, tabInitial = 0, tabFinal = 0;
                sub=new String[20];
                for (tabFinal = 0; tabFinal < contentLine.length(); tabFinal++) {
                    System.out.println("JA"+tabFinal+"---cl"+contentLine.length());
                   if(tabFinal == contentLine.length()-1){
                        System.out.println("yesno"); 
                        sub[elementCounter++] = contentLine.substring(tabInitial, tabFinal+1); 
                          System.out.println("NONO"+tabInitial+"---"+tabFinal);
                           }
                    try {
                        if (contentLine.charAt(tabFinal) == '\t' || contentLine.charAt(tabFinal) == '\n') {
                           
                            
                            try{
                           sub[elementCounter++] = contentLine.substring(tabInitial, tabFinal);
                           System.out.println(tabInitial+"---"+tabFinal);}
                            catch(Exception e){
                            sub[elementCounter++] ="";
                            }
                           
                            tabInitial = tabFinal+1;
                            
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
               System.out.println(Arrays.toString(sub));
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

            } catch (IOException ioe) {
                System.out.println("Error in closing the BufferedReader");
            }
        }
    }

}

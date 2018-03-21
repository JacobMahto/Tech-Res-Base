/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileMaker;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Jacob Vikas Mahto
 */
public class FileExp {
public static File file = null;
    public static void main(String[] args) {        
        JFileChooser win = new JFileChooser();
        int returnVal = win.showOpenDialog(null);
        //System.out.println(returnVal);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = win.getSelectedFile();
           // System.out.println(file.getName());
            //System.out.println(file.getAbsolutePath());

        }

    }

}

package printFinal;

import printPackage.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static printPackage.reportCardE.stIm;
import program.enterMarks;



/**
 *
 * @author Jacob Mahto
 */
public class printSimpRepE implements Printable{
  static JFrame f=new JFrame();
  static String[]sname,fname,mname,medium,sr,dob;
  static String session="2017";
  static int countPage;
  static int printPages;
  int markerForHandler=0;
  
  
  
  
  //to fetch the basic details of selected students
    public static void jac() {
        System.out.println("Entered Method jac()!!!");
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String query3 = "SELECT name,fname,mname,class,medium,sr,dob,session FROM studentInfo WHERE class='" + reportCardE.stdCB.getSelectedItem() + "' AND session='"+reportCardE.sessionCB.getSelectedItem()+"';";//ver2

            stmt.execute(query1);
            stmt.execute(query2);

            ResultSet rs = stmt.executeQuery(query3);
            rs.last();
            int[] selected = new int[reportCardE.listLB.getSelectedIndices().length];
            selected = reportCardE.listLB.getSelectedIndices();
            sname = new String[selected.length];
            mname=new String[selected.length];
            sr=new String[selected.length];
            fname=new String[selected.length];
            dob=new String[selected.length];
            medium=new String[selected.length];
            session=(String)reportCardE.sessionCB.getSelectedItem();
            

            rs.beforeFirst();
            int i = 0;
            int arrayPos = 0;
            //System.out.println("------"+selected[0]);
            // System.out.println("---"+selected[1]);

            
           
            while (rs.next()) {
                int j = 0;
                while (j < selected.length) {
                    if (i == selected[j]) {
                        sname[arrayPos] = rs.getString(1);
                        fname[arrayPos] = rs.getString(2);
                        mname[arrayPos] = rs.getString(3);
                        medium[arrayPos] = rs.getString(5);
                        sr[arrayPos] = rs.getString("sr");
                        dob[arrayPos] = rs.getString("dob");
                        arrayPos++;
                        //System.out.println("I = "+i+" J = "+j+ " && value="+selected[j]);
                    }

                    j++;
                }
                i++;

            }

            con.close();
             
           // JOptionPane.showMessageDialog(null, "JACOB IS VERY GOOD");
            
            //Giving data for printing
            int numberOfPages;
            if (sname.length % 2 == 0) {
                numberOfPages = sname.length / 2;
            } else {
                numberOfPages = sname.length / 2 + 1;
            }

            System.out.println("Number of pages" + numberOfPages);
            
            
            
            //following loop is to check number of pages to be printed with two graphcs on each page ,else no use
            for (i = 0; i < numberOfPages; i++) {
                countPage = i;
                             }       
        System.out.println("jac() finished!!!");
        } 
        catch (Exception E) {
            JOptionPane.showMessageDialog(null, E + " --^pppppppppppppppppppp^-- ");
        }

    }
  
  public static void setFormat(int posn){
      //clearing fields
      reportCardE.sNameTF.setText("");
      reportCardE.fNameTF.setText("");
      reportCardE.mNameTF.setText("");
      reportCardE.medTF.setText("");
      reportCardE.dobTF.setText("");
      reportCardE.srTF.setText("");
      reportCardE.stdTF.setText("");
    try{ stIm.setText("");}// i don't know why i have to affix this try & catch statement
    catch(Exception e){
        }
           
      if(reportCardE.headerAlter==0){
      
      }
      else{reportCardE.sessTF.setText("");
      int sess=Integer.parseInt(session);
reportCardE.sessTF.setText("Annual Examination : "+(sess-1)+"-"+sess);
      }
      
      
      
reportCardE.sNameTF.setText(sname[posn]);
reportCardE.fNameTF.setText("Mr. "+fname[posn]);
reportCardE.mNameTF.setText("Mrs. "+mname[posn]);
if(fname[posn].startsWith("Lt.")||fname[posn].startsWith("Late")||fname[posn].startsWith("lt.")||fname[posn].startsWith("late")){
reportCardE.fNameTF.setText(""+fname[posn]);
}
if(mname[posn].startsWith("Lt.")||mname[posn].startsWith("Late")||mname[posn].startsWith("lt.")||mname[posn].startsWith("late")){
reportCardE.fNameTF.setText(""+mname[posn]);
}

reportCardE.medTF.setText(medium[posn]);
try{
    reportCardE.dobTF.setText("");
    if(reportCardE.dob==0){if(dob[posn].length()==8){
    reportCardE.dobTF.setText(dob[posn].substring(0,2)+"-"+dob[posn].substring(2,4)+"-"+dob[posn].substring(4,8));
    }
    else{
     reportCardE.dobTF.setText(dob[posn].substring(0,1)+"-"+dob[posn].substring(1,3)+"-"+dob[posn].substring(3,7));
    }}
    

}catch(Exception e){}

if(reportCardE.sr==0){reportCardE.srTF.setText(sr[posn]);}

if(reportCardE.stdCB.getSelectedIndex()>13 || reportCardE.stdCB.getSelectedIndex()<4){reportCardE.stdTF.setText((String)reportCardE.stdCB.getSelectedItem());}
else{reportCardE.stdTF.setText((String)reportCardE.stdCB.getSelectedItem()+" ("+reportCardE.stdToText((String)reportCardE.stdCB.getSelectedItem())+ ")");}

try{
    OUTER :{
    for(int i=0;i<sr[posn].length();i++){
   System.out.println("YAAR"+i);
        if(sr[posn].substring(i,i+1).equals("/") ){
    
    sr[posn]=sr[posn].substring(0,i)+sr[posn].substring((i+1));
    System.out.println("HELLO I AM"+sr[posn]);
    break OUTER;
    }
    }}
    System.out.println("yes");
    reportCardE.stIm.setIcon(new ImageIcon("C:/Tech-Res Image Bank/Report Final/"+sr[posn]+".jpg"));
System.out.println("IMAGE NAME :"+sr[posn]);
}
catch(Exception e){
System.out.println("JACOB S"+e);
}


}

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        System.out.println("ENTERED PRINT METHOD!!!"+pageIndex+"------marker---"+markerForHandler+"------"+printPages);
       
        if(pageIndex<printPages){
        Graphics2D g=(Graphics2D)graphics;
        
        g.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
        System.out.println(pageFormat.getImageableX()+"   "+pageFormat.getImageableY());
        System.out.println(pageFormat.getImageableWidth()+"   "+pageFormat.getImageableHeight());      
        
            
      //  System.out.println("WELCOME X="+p1.getWidth());
      //  System.out.println("WELCOME Y="+p1.getHeight());
         // g.scale(.73,.85);//scaling only once    
          g.scale(reportCardE.xlength, reportCardE.yheight);
               // g.translate(36, 36);    System.out.println("hi"); 
                g.translate(reportCardE.aorigin, reportCardE.borigin);
            reportCardE.mainPanel.paint(g);
                                        System.out.println("hdi"); 
                
                if(pageIndex!=markerForHandler){
                markerForHandler=pageIndex;
                MultPHandlerE.handler(1, 1, markerForHandler);
                }
             
               
                
                    System.out.println("----------------------------ooooooooooooooooooooooooooooo__________________________________");
                  
                
        return PAGE_EXISTS;
        }
         else{return(NO_SUCH_PAGE);}
        
        
    }
    
    public static void main(String[] args){
    PrinterJob job=PrinterJob.getPrinterJob();
    job.setPrintable(new printSimpRepE());   
      Paper paper=new Paper();
       paper.setImageableArea(0, 0, 595, 842);
      paper.setSize(595, 842);       
            
   
    
     boolean ok=job.printDialog();
    //set upt the attribute set for landscape page
      //  PrintRequestAttributeSet aset=new HashPrintRequestAttributeSet();
      //aset.add(OrientationRequested.LANDSCAPE);
      PageFormat pf=new PageFormat();
      pf.setPaper(paper);
pf.setOrientation(PageFormat.LANDSCAPE);
     
      //pf.setOrientation(PageFormat.LANDSCAPE);
      job.pageDialog(pf);
      //job.pageDialog(aset);
      
     
    
   
   
    if(ok){
        try {
            job.print();
        } catch (PrinterException ex) {
            Logger.getLogger(printSimpRepE.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    }
    
    
    
    
    
}

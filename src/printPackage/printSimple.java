package printPackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jacob Mahto
 */
public class printSimple implements Printable{
  static JFrame f=new JFrame();
  

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if(pageIndex>0){return(NO_SUCH_PAGE);}
        
        Graphics2D g=(Graphics2D)graphics;
        g.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
        System.out.println(pageFormat.getImageableX()+"   "+pageFormat.getImageableY());
        System.out.println(pageFormat.getImageableWidth()+"   "+pageFormat.getImageableHeight());
        
       
        for(int i=(printAdm.countPage)*4;i<printAdm.countPage*4+4;i++){
        System.out.println("Vinita="+i+" "+printAdm.countPage);
        int item=i%4;
        try{printAdm.setFormat(i);
        System.out.println(printAdm.sname[i]);
        }catch(Exception e){
        System.out.println("Error"+i);
        }
        JPanel p1=printAdm.contP;
        
        System.out.println("WELCOME X="+p1.getWidth());
        System.out.println("WELCOME Y="+p1.getHeight());
       
        
       System.out.println("JACOB="+item);
        try{switch(item){
                case 0:{g.scale(.7, .9);//scaling only once
                
                g.translate(36, 36);
            
                p1=printAdm.contP;
                p1.print(g);
                
                } ;
                break;
                case 1:{
                    g.translate(570, 0); 
                    
                    printAdm.contP.print(g);
                } ;
                break;
                case 2:{
                    
                   g.translate(-570 , 310);
                printAdm.contP.print(g);
                } ;
                break;
                case 3:{
                    g.translate(570 , 0);
                    
                printAdm.contP.print(g);
                } ;
                break;
        }}
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
            
            
        }        
        return PAGE_EXISTS;
        
    }
    
    public static void main(String[] args){
    PrinterJob job=PrinterJob.getPrinterJob();
    job.setPrintable(new printSimple());
   
      Paper paper=new Paper();
       paper.setImageableArea(0, 0, 600, 841);
      paper.setSize(595, 841);
        
       
        
   //to diaplay advance option     
   
 
    //boolean ok=job.printDialog();
    
    
     boolean ok=job.printDialog();
    //set upt the attribute set for landscape page
      //  PrintRequestAttributeSet aset=new HashPrintRequestAttributeSet();
      //aset.add(OrientationRequested.LANDSCAPE);
      PageFormat pf=new PageFormat();
      pf.setPaper(paper);
      
      pf.setPaper(paper);
      pf.setOrientation(PageFormat.LANDSCAPE);
      job.pageDialog(pf);
      //job.pageDialog(aset);
      
     
    
   
   
    if(ok){
        try {
            
            job.print();
        } catch (PrinterException ex) {
            Logger.getLogger(printSimple.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    }
    
    
    
    
    
}

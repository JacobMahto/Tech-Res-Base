/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feeR;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.LookAndFeel;
import javax.swing.plaf.TableUI;
import printPackage.MultPHandlerE;
import printPackage.printSimple;
import printPackage.reportCardE;

/**
 *
 * @author Jacob Mahto
 */
public class feeRec extends javax.swing.JFrame implements Printable{
    static int rows=0;
    static String[] arrayL=new String[12];
    static String[] arrayA=new String[12];
    static double defScaleX=0.70,defScaleY=0.70,xForAll=30,yForFirst=20,yForSecond=565;
    static int totalLabels=20;
    
    public static String stdToText(String a){
        String stdWord="";
        if(a.equals("I")){stdWord="First";}
        if(a.equals("II")){stdWord="Second";}
        if(a.equals("III")){stdWord="Third";}
        if(a.equals("IV")){stdWord="Fourth";}
        if(a.equals("V")){stdWord="Fifth";}
        if(a.equals("VI")){stdWord="Sixth";}
        if(a.equals("VII")){stdWord="Seventh";}
        if(a.equals("VIII")){stdWord="Eighth";}
        if(a.equals("IX")){stdWord="Ninth";}
        if(a.equals("X")){stdWord="Tenth";}
       
    return(stdWord);
    }
    
    
    public static double getTotal(){
    DefaultTableModel t=(DefaultTableModel)jTable1.getModel();
    double total=0;
    for(int i=0;i<12;i++){        
        try{
            if(t.getValueAt(i, 0).equals("")==false){
            total+=Double.parseDouble(t.getValueAt(i,1).toString().trim());
            }
            }
        catch(Exception e){}    
    }
     return(total);   
        
    }
    
    
    public static void validRows(){
    int count=0;
        DefaultTableModel t=(DefaultTableModel)jTable1.getModel();
        for(int i=0;i<totalLabels;i++){
        try{
        Double a=Double.parseDouble(t.getValueAt(i, 1).toString().trim());
        if(a>0){
        
        arrayL[count]=t.getValueAt(i, 0).toString();
        arrayA[count]=t.getValueAt(i, 1).toString();
        
        count++;
        }
        }
        catch(Exception e){}
        }
     rows=count;  
    }
    
    public static String oneDigitToWord(int b){
       String a="";
    switch(b){
           case 1 : a="One";
                break;
                 case 2 : a="Two";
                break;
                 case 3 : a="Three";
                break;
                 case 4 : a="Four";
                break;
                 case 5 : a="Five";
                break;
                 case 6 : a="Six";
                break;
                 case 7 : a="Seven";
                break;
                 case 8 : a="Eight";
                break;
                 case 9 : a="Nine";
                break;
                case 10 : a="Ten";
                break;
                case 11 : a="Eleven";
                break;
                case 12 : a="Twelve";
                break;
                case 13 : a="Thirteen";
                break;
                case 14 : a="Fourteen";
                break;
                case 15 : a="Fifteen";
                break;
                case 16 : a="Sixteen";
                break;
                case 17 : a="Seventeen";
                break;
                case 18 : a="Eighteen";
                break;
                case 19 : a="Nineteen";
                break;
          }
    return(a);
    }
    
    public static String tensWord(int a){
        String tens="";
    switch(a){
                
                 case 2 : tens="Twenty";
                break;
                 case 3 : tens="Thirty";
                break;
                 case 4 : tens="Forty";
                break;
                 case 5 : tens="Fifty";
                break;
                 case 6 : tens="Sixty";
                break;
                 case 7 : tens="Seventy";
                break;
                 case 8 : tens="Eighty";
                break;
                 case 9 : tens="Ninty";
                break;
            }
    return(tens);
    }
    
    
    public static String amtToWords(double value){
        String data="";
        int lakh=0,thou=0,hund=0,ten=0,one=0;
        String tens="",ones="";
        int tempVal=(int) value;
        if(tempVal>=100000){  lakh=tempVal/100000;
        tempVal%=100000;
        }
        if(tempVal>=1000){ thou=tempVal/1000;
        tempVal%=1000;}
         if(tempVal>=100){hund=tempVal/100;
        tempVal%=100; }
          if(tempVal>=20){
            ten=tempVal/10;
           
              tens=tensWord(ten);
             tempVal%=10; 
          }
        
          
          if(tempVal>0){
              one=tempVal;
              ones=oneDigitToWord(tempVal);
          }
          
          System.out.println(lakh+"----"+thou+"----"+hund+"-----"+tens+"----"+ones);
          
          if(lakh!=0){
              if(lakh>19){
              data+=tensWord(lakh/10)+" ";
              lakh%=((lakh/10)*10);
              }
              if(lakh>0){data+=oneDigitToWord(lakh)+" ";}
          data+="Lakh ";
          }
          if(thou!=0){
              if(thou>19){
              data+=tensWord(thou/10)+" ";
              thou%=((thou/10)*10);
              }
              if(thou>0){data+=oneDigitToWord(thou)+" ";}
          data+="Thousand ";
          }
          
          
          if(hund!=0){
              if(hund>9){
              data+=tensWord(hund/10)+" ";
              hund%=10;
              }
              if(hund>0){data+=oneDigitToWord(hund)+" ";}
          data+="Hundered ";
          
      
          }
          if(ten!=0){
          data+=" "+tens+" ";
          }
        if(one!=0){
        data+=" "+ones+" ";
        }
        data+=" Rupees Only.";
        
        
        
    
    
    
    return(data);
    }
    
    public static void loadLabels(){
    try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String query3 = "SELECT * from labels ;";

            stmt.execute(query1);
            stmt.execute(query2);

            ResultSet rs=stmt.executeQuery(query3);
            int i=0;
            DefaultTableModel tab=(DefaultTableModel)jTable1.getModel();
           
            while(rs.next()){
                 tab.addRow(new Object[]{
                    rs.getString("name")               }
                );
               
                i++;

            }
            for(int k=8;k<=12;k++){
            tab.addRow(new Object[]{
                                   }
                );
            }
                     
            con.close();

        } catch (Exception E) {

        }
    
    }
    
    
    
   @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
       
        if(pageIndex>0){return(NO_SUCH_PAGE);}
        
        Graphics2D g=(Graphics2D)graphics;
        g.translate(pageFormat.getImageableX()+xForAll,pageFormat.getImageableY()+yForFirst);
        System.out.println(pageFormat.getImageableX()+"   "+pageFormat.getImageableY());
        System.out.println(pageFormat.getImageableWidth()+"   "+pageFormat.getImageableHeight());
        
        g.scale(defScaleX, defScaleY);
       jPanel3.print(g); 
       
       g.translate(0, pageFormat.getImageableY()+yForSecond);
       jPanel3.print(g);
            
        return PAGE_EXISTS;
        
    }

    /**
     * Creates new form feeRec
     */
    public feeRec() {
        initComponents();
    }
    
    public class priPan extends JPanel{
     @Override
       protected void paintComponent(Graphics g){
           NumberFormat nf=NumberFormat.getInstance();
           nf.setMaximumFractionDigits(2);
           nf.setMinimumFractionDigits(2);
           nf.setRoundingMode(RoundingMode.HALF_EVEN);
            
            int cellwidth=25;
           int RemebmerOpHorizontalPos=-50;
           
       super.paintComponent(g);
       System.out.println("JACOB TO DRAW");
       
       Graphics2D gd=(Graphics2D)g;
       
       System.out.println(jPanel5.getSize());
       gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
RenderingHints.VALUE_ANTIALIAS_ON);
       gd.drawRect(2, 2, 725, 220);
       gd.drawLine(50, 2, 50, 220);
       gd.drawLine(600, 2, 600, 220);
       gd.drawRect(2, 2, 725, 25);
       gd.drawLine(2, 190, 725, 190);
       
       gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
RenderingHints.VALUE_ANTIALIAS_ON);
       Font f1=new Font("Adobe Hebrew",Font.PLAIN,18);
       gd.setFont(f1);  
       gd.drawString("S.N.", 13, 22);
       gd.drawString("PARTICULAR(S)", 260, 22);
       gd.drawString("AMOUNT", 623, 22);
       gd.drawString("TOTAL", 280, 210);
      
       //f1=new Font("Arial",Font.BOLD,16);
      // gd.setFont(f1);
       if(rows<7){
           f1=new Font("californian fb",Font.BOLD,16);
       gd.setFont(f1); 
           
       int xpos=20,ypos=52;
       for(int i=0;i<rows;i++,ypos+=25,xpos=20){
           
           gd.drawString((i+1)+"", xpos, ypos);
           xpos=65;
           gd.drawString(arrayL[i], xpos, ypos);
           xpos=645;
           
           try{ gd.drawString(nf.format(Double.parseDouble(arrayA[i])), xpos, ypos);      }
           catch(Exception j){}
          
                 
       }
       
       try{gd.drawString(nf.format(Double.parseDouble(totTF.getText())), 645, 210);}
       catch(Exception e){
       
       try{gd.drawString(totTF.getText(), 645, 210);}
       catch(Exception ex){}
       
       }
       
       }
       
       else if(rows<11){
           f1=new Font("californian fb",Font.BOLD,16);
       gd.setFont(f1); 
           
       int xpos=20,ypos=45;
       for(int i=0;i<rows;i++,ypos+=15,xpos=20){
           
           gd.drawString((i+1)+"", xpos, ypos);
           xpos=65;
           gd.drawString(arrayL[i], xpos, ypos);
           xpos=645;
           try{gd.drawString(nf.format(Double.parseDouble(arrayA[i])), xpos, ypos);    }
           catch(Exception ja){}
             
                 
       }
       
       try{gd.drawString(nf.format(Double.parseDouble(totTF.getText())), 645, 210);}
       catch(Exception e){
       try{gd.drawString(totTF.getText(), 645, 210);}
       catch(Exception ex){}
       }
       
       }
       
       
       
       
    }}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField16 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        listLB = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        listLB1 = new javax.swing.JList<>();
        stdCB = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        sCB = new javax.swing.JComboBox<>();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jPanel5 = new priPan();
        jLabel3 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        sNameTF = new javax.swing.JTextField();
        fNameTF = new javax.swing.JTextField();
        srTF = new javax.swing.JTextField();
        stdTF = new javax.swing.JTextField();
        recTF = new javax.swing.JTextField();
        dateTF = new javax.swing.JTextField();
        amtTF = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        cdTF = new javax.swing.JTextField();
        cnTF = new javax.swing.JTextField();
        bnTF = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField15 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        totTF = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        recIsTF = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FEE DATABASE-TECH-RES ULTIMATE BY JACOB MAHTO");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setText("Issue(d) to  : ");

        jLabel2.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel2.setText("Select details : ");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Explore");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Issue");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "F.Name", "Std.", "S.R.No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTextField16.setBackground(new java.awt.Color(204, 204, 204));
        jTextField16.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField16.setForeground(new java.awt.Color(102, 102, 102));
        jTextField16.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField16.setText("TECH-RES ULTIMATE");
        jTextField16.setBorder(null);

        listLB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listLBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listLBMouseEntered(evt);
            }
        });
        listLB.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listLBValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(listLB);

        jScrollPane5.setViewportView(listLB1);

        stdCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        stdCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PG", "NUR", "LKG", "UKG", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI Arts-A", "XI Arts-B", "XI Arts-C", "XI Commerce-A", "XI Commerce-B", "XI Commerce-C", "XII Arts-A", "XII Arts-B", "XII Arts-C", "XII Commerce-A", "XII Commerce-B", "XII Commerce-C" }));
        stdCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdCBActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Hide SR");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("Anonymous");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("Custom");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Increment Standard");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        sCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        sCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
        sCB.setSelectedIndex(1);

        jCheckBox3.setText("by Cash");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addGap(10, 10, 10)
                                .addComponent(jRadioButton4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox2)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox3))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(sCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stdCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(316, 316, 316)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addGap(393, 393, 393)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox3))
                        .addGap(9, 9, 9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(stdCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mone.jpg"))); // NOI18N

        jTextField2.setFont(new java.awt.Font("Trajan", 1, 28)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("m . k . public school");
        jTextField2.setBorder(null);

        jTextField3.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("237-239 , 21-South Colony , Niwaru Road , Jhotwara , Jaipur (Rajasthan)");
        jTextField3.setBorder(null);

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField5.setText("Receipt No. :");
        jTextField5.setBorder(null);

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField6.setText("Student's Name :");
        jTextField6.setBorder(null);

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField7.setText("Standard :");
        jTextField7.setBorder(null);

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField8.setText("Date :");
        jTextField8.setBorder(null);

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField9.setText("Father's Name :");
        jTextField9.setBorder(null);

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField11.setText("S.R.No.");
        jTextField11.setBorder(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(51, 0, 51));
        jLabel3.setFont(new java.awt.Font("Adobe Hebrew", 3, 22)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField10.setText("Amount in words :");
        jTextField10.setBorder(null);

        jTextField12.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField12.setText("PRINCIPAL");
        jTextField12.setBorder(null);

        jTextField13.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField13.setText("RECEIVER");
        jTextField13.setBorder(null);

        jTextField17.setFont(new java.awt.Font("Adobe Hebrew", 3, 14)); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setText("If the payment has been made other than in cash , then receipt is valid upon the realisation of the same by the school.");
        jTextField17.setBorder(null);

        jTextField18.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.setText("Ph. No.:0141-2341265");
        jTextField18.setBorder(null);

        sNameTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        sNameTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        sNameTF.setBorder(null);
        sNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sNameTFActionPerformed(evt);
            }
        });

        fNameTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        fNameTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fNameTF.setBorder(null);
        fNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fNameTFActionPerformed(evt);
            }
        });

        srTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        srTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        srTF.setBorder(null);
        srTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srTFActionPerformed(evt);
            }
        });

        stdTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        stdTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        stdTF.setBorder(null);
        stdTF.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                stdTFInputMethodTextChanged(evt);
            }
        });
        stdTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdTFActionPerformed(evt);
            }
        });

        recTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        recTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        recTF.setBorder(null);
        recTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recTFActionPerformed(evt);
            }
        });

        dateTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        dateTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        dateTF.setBorder(null);
        dateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateTFActionPerformed(evt);
            }
        });

        amtTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        amtTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        amtTF.setBorder(null);
        amtTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amtTFActionPerformed(evt);
            }
        });

        jTextField20.setEditable(false);
        jTextField20.setBackground(new java.awt.Color(255, 255, 255));
        jTextField20.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField20.setText("Cheque Date (if any):");
        jTextField20.setBorder(null);

        jTextField21.setEditable(false);
        jTextField21.setBackground(new java.awt.Color(255, 255, 255));
        jTextField21.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField21.setText("Cheque No. :");
        jTextField21.setBorder(null);

        jTextField22.setEditable(false);
        jTextField22.setBackground(new java.awt.Color(255, 255, 255));
        jTextField22.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField22.setText("Bank Name :");
        jTextField22.setBorder(null);

        cdTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        cdTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        cdTF.setBorder(null);
        cdTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdTFActionPerformed(evt);
            }
        });

        cnTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        cnTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        cnTF.setBorder(null);
        cnTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnTFActionPerformed(evt);
            }
        });

        bnTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        bnTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        bnTF.setBorder(null);
        bnTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTFActionPerformed(evt);
            }
        });

        jTextField23.setFont(new java.awt.Font("Californian FB", 1, 14)); // NOI18N
        jTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23.setText("R.No. : 1325/JPR/2002-03");
        jTextField23.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(586, 586, 586)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amtTF))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(cnTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bnTF))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(203, 203, 203)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(fNameTF))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(sNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(recTF, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(140, 140, 140)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateTF)
                                    .addComponent(srTF))))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stdTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(srTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amtTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cnTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setShowGrid(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Label", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField15.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField15.setText("PARTICULARS");
        jTextField15.setBorder(null);

        jButton5.setText("Restore Default");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField14.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField14.setText("TOTAL :");
        jTextField14.setBorder(null);

        totTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        totTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        totTF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        totTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totTFActionPerformed(evt);
            }
        });

        jButton6.setText("Preview");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Refresh");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addGap(0, 59, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totTF)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        jButton3.setText("Issue");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rec.No.", "Name", "F. Name", "Std.", "Date", "Amt", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jTable3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable3KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        recIsTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        recIsTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        recIsTF.setBorder(null);
        recIsTF.setEnabled(false);
        recIsTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recIsTFActionPerformed(evt);
            }
        });

        jTextField19.setBackground(new java.awt.Color(0, 0, 0));
        jTextField19.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField19.setForeground(new java.awt.Color(255, 255, 255));
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField19.setText("Rec. No.");
        jTextField19.setBorder(null);

        jButton2.setText("View");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton8.setText("Delete");
        jButton8.setEnabled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Cancel");
        jButton9.setEnabled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(recIsTF, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4, jButton8});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recIsTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton3, jButton4, jButton8});

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listLBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listLBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_listLBMouseClicked

    private void listLBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listLBMouseEntered

        // TODO add your handling code here:
    }//GEN-LAST:event_listLBMouseEntered

    private void listLBValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listLBValueChanged
      listLB1.setSelectedIndices(listLB.getSelectedIndices());
     try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            
            
            String query3 = "SELECT sr,name,fname,mname FROM studentInfo WHERE class='"+stdCB.getSelectedItem().toString()+"' and name='"+listLB.getSelectedValue()+"' ;";    
String query4="SELECT max(recno) FROM recdata;";
            stmt.execute(query1);
            stmt.execute(query2);

            ResultSet rs=stmt.executeQuery(query3);
            System.out.println(listLB.getSelectedValue());
            rs.next();
            srTF.setText(rs.getString("sr"));
            sNameTF.setText(rs.getString("name"));
            fNameTF.setText(rs.getString("fname"));
            
            if(stdCB.getSelectedIndex()>3 && stdCB.getSelectedIndex()<14){
            stdTF.setText(stdCB.getSelectedItem().toString()+" ("+stdToText(stdCB.getSelectedItem().toString())+")");
            }
            else{
            stdTF.setText(stdCB.getSelectedItem().toString());
            }
            
           
          try{ rs=stmt.executeQuery(query4);
          rs.next();
              recTF.setText(rs.getInt(1)+1+"");}
          catch(Exception e){
         // JOptionPane.showMessageDialog(null, e);
          }
            rs.close();
            con.close();

        } catch (Exception E) {
//JOptionPane.showMessageDialog(null, E);
        }
     
     
     try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
             DefaultTableModel t3=(DefaultTableModel)jTable3.getModel();
            try {
            int y = t3.getRowCount();
            y--;
            while (y >= 0) {
                t3.removeRow(y);
                y--;
            }
        } catch (Exception e) {
        }
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String queryS="";
            
            
           queryS = "SELECT recno,sname,fname,std,date,total,status FROM recdata WHERE sname LIKE '%"+listLB.getSelectedValue()+"%' and sr LIKE '%"+listLB1.getSelectedValue()+"%';";
           stmt.execute(query1);
           stmt.execute(query2);
           ResultSet rs=stmt.executeQuery(queryS);
           
            
           while(rs.next()){
           t3.addRow(new Object[]{
                    rs.getString(1),rs.getString(2)   ,rs.getString(3),rs.getString(4) ,rs.getString(5) ,rs.getString(6)  ,rs.getString(7)        }
                );
           
           
           }
           
           
           rs.close();
           con.close();

            

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, E);

        }

     

        // TODO add your handling code here:
    }//GEN-LAST:event_listLBValueChanged

    private void stdCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdCBActionPerformed
        String std=(String)stdCB.getSelectedItem();
      DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal=Calendar.getInstance();
        
        dateTF.setText(df.format(cal.getTime()));
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String query3 = "SELECT sr,name FROM studentInfo WHERE class='"+std+"' and session='"+sCB.getSelectedItem()+"';";

            stmt.execute(query1);
            stmt.execute(query2);

            ResultSet rs=stmt.executeQuery(query3);
            rs.last();
            String[] nm=new String[rs.getRow()];
            String[] sr=new String[rs.getRow()];
            rs.beforeFirst();
            int i=0;
            while(rs.next()){
                nm[i]=rs.getString("Name");
                sr[i]=rs.getString("sr");
                i++;
            }

            listLB.setListData(nm);
            listLB1.setListData(sr);
            con.close();

        } catch (Exception E) {

        }

       
        // TODO add your handling code here:
    }//GEN-LAST:event_stdCBActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String query3="";
            String date=dateTF.getText();
            
            /*
            String day="",mon="",year="";
            try{
            if(date.length()==10){
                
                day=date.substring(0,2);
                System.out.println(day);
                mon=date.substring(3, 5);
                System.out.println(mon);
                year=date.substring(6);
                System.out.println(year);
            
            }
            else{
                day=date.substring(0,1);
                System.out.println(day);
                mon=date.substring(2, 4);
                System.out.println(mon);
                year=date.substring(5);
                System.out.println(year);
            }
            }
            catch(Exception e){
            System.out.println(e);
            
            }
            date=day+mon+year;
            System.out.println("---"+date);
            
            System.out.println(date);*/
            try{
            Double a=Double.parseDouble(cnTF.getText());
            
                    query3 = "INSERT INTO recdata VALUES("+recTF.getText()+",'"+sNameTF.getText()+"','"+fNameTF.getText()+"','"+srTF.getText()+"','"+stdTF.getText()+"','"+date+"',"+totTF.getText()+",'"+bnTF.getText()+"','"+cdTF.getText()+"','"+cnTF.getText()+"'";
            System.out.println("BLOCK1");
            }
            catch(Exception e){
                
            query3 = "INSERT INTO recdata VALUES("+recTF.getText()+",'"+sNameTF.getText()+"','"+fNameTF.getText()+"','"+srTF.getText()+"','"+stdTF.getText()+"','"+date+"',"+totTF.getText()+",'',0,'0'";
            System.out.println("BLOCK2");
            }
            

            DefaultTableModel t=(DefaultTableModel)jTable1.getModel();            
            for(int i=0;i<totalLabels;i++){ 
            
                try{
                if(Double.parseDouble(t.getValueAt(i, 1).toString())!=0){
            query3+=",'"+t.getValueAt(i,0).toString()+"',"+t.getValueAt(i,1);
            }
                }
                catch(Exception e){
                    
                    try{query3+=",'"+t.getValueAt(i,0).toString()+"',"+0;}
                    catch(Exception f){
                    query3+=",'"+"',"+0;
                    }
                 
                
                }
                
            }
            query3+=",'CNF');";
            stmt.execute(query1);
            stmt.execute(query2);
            System.out.println(query3);
stmt.execute(query3);
JOptionPane.showMessageDialog(null, "Successfully Issued !");

//to save labels
try{
   
String[] arrayOfNewL=new String[totalLabels];
String query="";
int cnt=0;
    for(int i=0;i<totalLabels;i++){
        
        try{
        if(t.getValueAt(i, 0)==null || (t.getValueAt(i, 0).toString()).trim().equals("")){        
        }
        else{
        arrayOfNewL[i]=t.getValueAt(i, 0).toString();
        cnt++;
        }
        }
        catch(Exception j){}
        
    }
    query="DELETE from labels;";
    stmt.execute(query);
    for(int i=0;i<cnt;i++){
    query="INSERT INTO labels VALUES('"+arrayOfNewL[i]+"');";
    stmt.execute(query);
    }
    
    
    
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
}





        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Issue Failed ! Fill/Correct all neccessary details ! ");
            
//JOptionPane.showMessageDialog(null, E);
        }

        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void sNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sNameTFActionPerformed

    private void fNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fNameTFActionPerformed

    private void srTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_srTFActionPerformed

    private void stdTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stdTFActionPerformed

    private void recTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recTFActionPerformed

    private void dateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateTFActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String queryDel="delete from labels;";
            
            String[] query=new String[12];
           query[0] = "INSERT INTO labels values('PROSPECTUS');";
           query[1] = "INSERT INTO labels values('ADMISSION FEE');";
           query[2] = "INSERT INTO labels values('YEARLY FEE');";
           query[3] = "INSERT INTO labels values('MONTHLY FEE');";
           query[4] = "INSERT INTO labels values('CAUTION MONEY');";
           query[5] = "INSERT INTO labels values('COMPUTER FEE');";
           query[6] = "INSERT INTO labels values('TERM FEE');";
           

            stmt.execute(query1);
            stmt.execute(query2);
stmt.execute(queryDel);
            for(int i=0;i<=6;i++){
            stmt.execute(query[i]);
            }
                   JOptionPane.showMessageDialog(null, "Defaults restored !");
            con.close();
            dispose();
            feeRec obj=new feeRec();
            obj.setVisible(true);

        } catch (Exception E) {

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void totTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totTFActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
totTF.setText(getTotal()+"");
amtTF.setText(amtToWords(Double.parseDouble(totTF.getText().trim())));
jButton6.doClick();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void amtTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amtTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amtTFActionPerformed

    private void jTable3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3KeyReleased

    private void recIsTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recIsTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recIsTFActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
if(jRadioButton1.isSelected()==true){
dispose();
feeRec obj=new feeRec();
obj.setVisible(true);
obj.jRadioButton1.setSelected(true);
obj.jButton2.setEnabled(true);
        obj.recIsTF.setEnabled(true);
        obj.jTable1.setEnabled(false);
obj.jButton3.setEnabled(false);
obj.jButton8.setEnabled(true);
obj.jButton9.setEnabled(true);

}
else{
jButton7.doClick();

}
        
        
        
//jButton4.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed

        
        if(jRadioButton2.isSelected()==true){
        
            
            
            dispose();
feeRec obj=new feeRec();
obj.setVisible(true);
        obj.loadLabels();
        obj.jRadioButton2.setSelected(true);
        obj.jButton6.doClick();
        obj.jButton1.doClick();
        }
        else {
         jButton7.doClick();

        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
try {
                Class.forName("java.sql.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
                Statement stmt = con.createStatement();
                                               
                String query0="CREATE DATABASE IF NOT EXISTS rsl;";
                stmt.execute(query0);                
                String query1 = "USE rsl;";
                stmt.execute(query1);
                String query2="Select * from scale where code='feerec';";
                    ResultSet rs=stmt.executeQuery(query2);
                    rs.next();
                    
                    try{
                    defScaleX=rs.getDouble("x");
                    defScaleY=rs.getDouble("y");
                    xForAll=rs.getDouble("a");
                    yForFirst=rs.getDouble("b");
                    yForSecond=rs.getDouble("p");
                    }
                    catch(Exception e){}
                    
                

            } catch (Exception E) {
               System.out.println(E);
                
            }
        
        
        PrinterJob job=PrinterJob.getPrinterJob();
    job.setPrintable(this);
   
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
      pf.setOrientation(PageFormat.PORTRAIT);
      job.pageDialog(pf);
      //job.pageDialog(aset);
      
     
    
   
   
    if(ok){
        try {            
            job.print();
            System.out.println("YESNO");
        } catch (PrinterException ex) {
           JOptionPane.showMessageDialog(null, ex);
        } 
    }
    

        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cdTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cdTFActionPerformed

    private void cnTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cnTFActionPerformed

    private void bnTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bnTFActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
validRows();
        

jPanel5.repaint();
try{amtTF.setText(amtToWords(Double.parseDouble(totTF.getText().trim())));}
catch(Exception e){}
System.out.println(arrayL[0]+"---"+arrayL[2]);
       // jPanel5.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            DefaultTableModel t=(DefaultTableModel)jTable2.getModel();
           
            try {
            int y = t.getRowCount();
            y--;
            while (y >= 0) {
                t.removeRow(y);
                y--;
            }
        } catch (Exception e) {
        }
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String queryS="";
            
            
           queryS = "SELECT * FROM studentinfo WHERE name LIKE '%"+jTextField1.getText()+"%' ;";
           stmt.execute(query1);
           stmt.execute(query2);
           ResultSet rs=stmt.executeQuery(queryS);
           
            
           while(rs.next()){
           t.addRow(new Object[]{
                    rs.getString("name"),rs.getString("fname")   ,rs.getString("class"),rs.getString("sr")            }
                );
           
           
           }
           
           
           

           rs.close();
           con.close();

        } catch (Exception E) {
           // JOptionPane.showMessageDialog(null, E);

        }



try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
             DefaultTableModel t3=(DefaultTableModel)jTable3.getModel();
            try {
            int y = t3.getRowCount();
            y--;
            while (y >= 0) {
                t3.removeRow(y);
                y--;
            }
        } catch (Exception e) {
        }
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String queryS="";
            
            
           queryS = "SELECT recno,sname,fname,std,date,total,status FROM recdata WHERE sname LIKE '%"+jTextField1.getText()+"%';";
           stmt.execute(query1);
           stmt.execute(query2);
           ResultSet rs=stmt.executeQuery(queryS);
           
            
           while(rs.next()){
           t3.addRow(new Object[]{
                    rs.getString(1),rs.getString(2)   ,rs.getString(3),rs.getString(4) ,rs.getString(5),rs.getString(6),rs.getString(7)           }
                );
           
           
           }
           
           
           rs.close();
           con.close();

            

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, E);

        }


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
try{
    Point p=evt.getPoint();
int row=jTable2.rowAtPoint(p);
int col=jTable2.columnAtPoint(p);
//JOptionPane.showMessageDialog(null, "row is"+row+"col"+col);
if(evt.getClickCount()==2){
    DefaultTableModel tab=(DefaultTableModel)jTable2.getModel();
    String std=tab.getValueAt(row, 2).toString();
    
    for(int i=0;i<stdCB.getItemCount();i++){
    if(std.equals(stdCB.getItemAt(i))){
    stdCB.setSelectedIndex(i);
     ListModel<String> model1 = listLB.getModel();
     ListModel<String> model2 = listLB1.getModel();
    for(int k=0;k<model1.getSize();k++){
        
       if((model1.getElementAt(k).toString().equals(tab.getValueAt(row, 0)))&&(model2.getElementAt(k).toString().equals(tab.getValueAt(row, 3)))){
       listLB.setSelectedIndex(k);
       
       }       
        
    }    }
    }}}
catch(Exception e){
//JOptionPane.showMessageDialog(null, e);
}
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
String recn=recIsTF.getText();
jLabel3.setText("");
try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
             DefaultTableModel t3=(DefaultTableModel)jTable3.getModel();
            
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String queryS="";
            
            
           queryS = "SELECT * FROM recdata WHERE recno="+recn+";";
           stmt.execute(query1);
           stmt.execute(query2);
           ResultSet rs=stmt.executeQuery(queryS);
           
           rs.next();
           sNameTF.setText(rs.getString("sname"));
           fNameTF.setText(rs.getString("fname"));
           srTF.setText(rs.getString("sr"));
           stdTF.setText(rs.getString("std"));
           recTF.setText(rs.getString("recno"));
           String stat=rs.getString("status");
          
           if(stat.equals("XXX")){
               
            jLabel3.setText("RENDERED INVALID AND CANCELLED ! PAYMENT NOT RECEIVED !");
            
          System.out.println("YES JACOB INOW");
           }
           /*
           String tempDate=rs.getString("date");
           
           try{
           if(tempDate.length()==8){
           tempDate=tempDate.substring(0,2)+"/"+tempDate.substring(2,4)+"/"+tempDate.substring(4);
           }
           else{
           tempDate=tempDate.substring(0,1)+"/"+tempDate.substring(1,3)+"/"+tempDate.substring(3);
           }
           }
           catch(Exception e){}
           */
           dateTF.setText(rs.getString("date"));
           cdTF.setText(rs.getString("chekdate"));
           cnTF.setText(rs.getString("chekno"));
           bnTF.setText(rs.getString("bank"));
           
           /*
           try{
               tempDate=cdTF.getText();
           if(tempDate.length()==8){
           tempDate=tempDate.substring(0,2)+"/"+tempDate.substring(2,4)+"/"+tempDate.substring(4);
           }
           else{
           tempDate=tempDate.substring(0,1)+"/"+tempDate.substring(1,3)+"/"+tempDate.substring(3);
           }
           cdTF.setText(tempDate);
           }
           catch(Exception e){}*/
           
           try{
           
           }
           catch(Exception e){}
           
           DefaultTableModel t=(DefaultTableModel)jTable1.getModel();
           try {
            int y = t.getRowCount();
            y--;
            while (y >= 0) {
                t.removeRow(y);
                y--;
            }
        } catch (Exception e) {
        }
           
           
           
           for(int i=11;i<=33;i+=2){
           
               try{
                   if(rs.getDouble(i+1)==0){
                   
                   }
                   else{ t.addRow(new Object[]{
                    rs.getString(i),rs.getDouble(i+1)          }
                );}
              
           
               }
               catch(Exception e){
               JOptionPane.showMessageDialog(null, e);
               }
               
           }
           jButton6.doClick();
           
           
           try{
           if(Double.parseDouble(cdTF.getText())==0){
           cdTF.setText("");
           cnTF.setText("");
           bnTF.setText("");
           }
           }
           catch(Exception e){}
           
           
           rs.close();
           con.close();
           totTF.setText(getTotal()+"");
 jButton6.doClick();
            

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, E);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
if(jCheckBox1.isSelected()){
srTF.setForeground(Color.WHITE);
}
else{
srTF.setForeground(Color.BLACK);
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
dispose();
feeRec o=new feeRec();
o.setVisible(true);
jButton6.doClick();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
totTF.setText(getTotal()+"");
amtTF.setText(amtToWords(Double.parseDouble(totTF.getText().trim())));
jButton6.doClick();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
try{
    Point p=evt.getPoint();
int row=jTable3.rowAtPoint(p);
int col=jTable3.columnAtPoint(p);
//JOptionPane.showMessageDialog(null, "row is"+row+"col"+col);
if(evt.getClickCount()==2){
    DefaultTableModel tab=(DefaultTableModel)jTable3.getModel();
    String recno=tab.getValueAt(row, 0).toString();
    recIsTF.setText(recno);
    jButton2.doClick();
    
    
    }}
catch(Exception e){
//JOptionPane.showMessageDialog(null, e);
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
      if(jRadioButton3.isSelected()==true){
          dispose();
          feeRec obj=new feeRec();
          obj.setVisible(true);
          obj.loadLabels();
          obj.jRadioButton3.setSelected(true);
  obj.jButton1.doClick();
      
     try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            
            
              
String query4="SELECT max(recno) FROM recdata;";
            stmt.execute(query1);
            stmt.execute(query2);

                
          try{ 
              
             ResultSet rs=stmt.executeQuery(query4);
          rs.next();
              obj.recTF.setText(rs.getInt(1)+1+"");
          rs.close();
          }
          catch(Exception e){
         // JOptionPane.showMessageDialog(null, e);
          }
            
            con.close();

        } catch (Exception E) {
//JOptionPane.showMessageDialog(null, E);
        }   
      }
      else{
      jButton7.doClick();
      }
      
             // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
if(jRadioButton4.isSelected()==true){
          dispose();
          feeRec obj=new feeRec();
          obj.setVisible(true);
          obj.loadLabels();
          obj.jRadioButton4.setSelected(true);
          obj.jButton1.doClick();
          obj.jTextField6.setEditable(true);
          obj.jTextField7.setEditable(true);
          obj.jTextField8.setEditable(true);
          obj.jTextField9.setEditable(true);
          obj.jTextField10.setEditable(true);
          obj.jTextField11.setEditable(true);
          obj.jTextField5.setEditable(true);
          obj.jTextField20.setEditable(true);
          obj.jTextField21.setEditable(true);
          obj.jTextField22.setEditable(true);
          
      
     try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            
            
              
String query4="SELECT max(recno) FROM recdata;";
            stmt.execute(query1);
            stmt.execute(query2);

                
          try{ 
              
             ResultSet rs=stmt.executeQuery(query4);
          rs.next();
              obj.recTF.setText(rs.getInt(1)+1+"");
          rs.close();
          }
          catch(Exception e){
         // JOptionPane.showMessageDialog(null, e);
          }
            
            con.close();

        } catch (Exception E) {
//JOptionPane.showMessageDialog(null, E);
        }   
      }
      else{
      jButton7.doClick();
      }
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void stdTFInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_stdTFInputMethodTextChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_stdTFInputMethodTextChanged

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
if(jCheckBox2.isSelected()){
if(stdCB.getSelectedIndex()>2 && stdCB.getSelectedIndex()<13){
            stdTF.setText(stdCB.getItemAt(stdCB.getSelectedIndex()+1).toString()+" ("+stdToText(stdCB.getItemAt(stdCB.getSelectedIndex()+1).toString())+")");
            }
            else{
            stdTF.setText(stdCB.getItemAt(stdCB.getSelectedIndex()+1)+"");
            }
jCheckBox2.setSelected(false);
}
else{
    //listLB.setSelectedIndex(listLB.getSelectedIndex());
    
}

        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
DefaultTableModel tab=(DefaultTableModel)jTable3.getModel();
int rowNo=jTable3.getSelectedRowCount();
    int[] selectedRows = jTable3.getSelectedRows();

    
for(int i=0;i<rowNo;i++){
String rec=(tab.getValueAt(selectedRows[i], 0).toString());
try{
            Class.forName("java.sql.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost","root","lion");
                Statement stmt=con.createStatement();
                              String query1="USE rsl;";
                
                stmt.execute(query1);
                String querys="DELETE FROM recdata WHERE recno="+rec+";";
                  stmt.execute(querys);
                       JOptionPane.showMessageDialog(null, "Selected entries deleted from the database !","Success !",1);
}
catch(Exception E){
System.out.println(E);
JOptionPane.showMessageDialog(null,E+"\n Error in Deletion !");
} 
}

//JOptionPane.showMessageDialog(null, "Selected entries deleted from the database !","Success !",1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
if(jCheckBox3.isSelected()==true){
cnTF.setText("");
cdTF.setText("");
bnTF.setText("");
jTextField20.setText("");
jTextField21.setText("PAID BY CASH");
jTextField22.setText("");

}
else{
jTextField20.setText("Cheque Date (if any):");
jTextField21.setText("Cheque No.:");
jTextField22.setText("Bank Name :");

}
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
DefaultTableModel tab=(DefaultTableModel)jTable3.getModel();
int rowNo=jTable3.getSelectedRowCount();
    int[] selectedRows = jTable3.getSelectedRows();

    
for(int i=0;i<rowNo;i++){
String rec=(tab.getValueAt(selectedRows[i], 0).toString());
try{
            Class.forName("java.sql.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost","root","lion");
                Statement stmt=con.createStatement();
                              String query1="USE rsl;";
                
                stmt.execute(query1);
                String querys="UPDATE recdata set status='XXX' WHERE recno="+rec+";";
                  stmt.execute(querys);
                       JOptionPane.showMessageDialog(null, "Selected entries Cancelled from the database !","Successfully Rendered Invalid !",1);
}
catch(Exception E){
System.out.println(E);
JOptionPane.showMessageDialog(null,E+"\n Error in Cancellation !");
} 
}

        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(feeRec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(feeRec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(feeRec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(feeRec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new feeRec().setVisible(true);
                loadLabels();
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField amtTF;
    public static javax.swing.JTextField bnTF;
    public static javax.swing.JTextField cdTF;
    public static javax.swing.JTextField cnTF;
    public static javax.swing.JTextField dateTF;
    public static javax.swing.JTextField fNameTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    public static javax.swing.JList<String> listLB;
    public static javax.swing.JList<String> listLB1;
    public static javax.swing.JTextField recIsTF;
    public static javax.swing.JTextField recTF;
    private javax.swing.JComboBox<String> sCB;
    public static javax.swing.JTextField sNameTF;
    public static javax.swing.JTextField srTF;
    public static javax.swing.JComboBox<String> stdCB;
    public static javax.swing.JTextField stdTF;
    public static javax.swing.JTextField totTF;
    // End of variables declaration//GEN-END:variables
}

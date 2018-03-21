/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ID;
import static feeR.feeRec.jPanel5;
import static feeR.feeRec.totTF;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.RoundingMode;
import program.studentInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import printFinal.MultPHandlerE;
import printFinal.reportCardE;
import printPackage.printSimple;

/**
 *
 * @author Jacob Mahto
 */
public class ICardCreateT extends javax.swing.JFrame implements Printable{
public static String[] name,father,mobile,dob,address,roll,exaSession,empno,designation;
public static String std;
public static int countPage=0;
public static int check=0;
int numberOfPages;
double y=60,x=60,xScale=.45,yScale=.56;




    /**
     * Creates new form ICardCreate
     * CREATE TABLE `rsl`.`empinfo` (
  `empno` VARCHAR(100) NOT NULL,
  `Name` VARCHAR(100) NOT NULL,
  `father` VARCHAR(100) NULL,
  `spouse` VARCHAR(100) NULL,
  `designation` VARCHAR(100) NULL,
  `dob` VARCHAR(45) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `dpt` VARCHAR(100) NULL,
  PRIMARY KEY (`empno`));

     */
    public ICardCreateT() {
        initComponents();
    }
    
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
    
    
    public void setFormat(String student,String father,String contact,String dob,String add,String sr,String designation){
    
        //clearing the fields
        sNameTF.setText("");
    fNameTF.setText("");
    stdTF.setText("");
    conTF.setText("");
    dobTF.setText("");
    addTA.setText("");
    imgIc.setText("");
        
        
        //setting the fields
        sNameTF.setText(student);
    fNameTF.setText(father);
    
    conTF.setText(contact);  
    
    int dobLength=dob.length();
    if(dobLength==8){
        dob=dob.substring(0, 2)+"-"+dob.substring(2, 4)+"-"+dob.substring(4,8);
    }
    else{
    dob=dob.substring(0, 1)+"-"+dob.substring(1, 3)+"-"+dob.substring(3,7);
    }
    dobTF.setText(dob);
    addTA.setText(add);
    stdTF.setText(designation);
    codeTF.setText(sr);
    //getting image
    try{
    for(int i=0;i<sr.length();i++){
    int flag=0;
        if(sr.substring(i,i+1).equals("/") && flag==0){
    flag=1;
    sr=sr.substring(0,i)+sr.substring((i+1));
    }
    }
    
    imgIc.setIcon(new ImageIcon("C:/Tech-Res Image Bank/icard/"+sr+".jpg"));}
catch(Exception e){}
    
    
    
    System.out.println("SET FORMAT----------"+student);
    }
    
   @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        
       
        if(pageIndex<numberOfPages){
        Graphics2D g=(Graphics2D)graphics;
        
        g.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
        System.out.println(pageFormat.getImageableX()+"   "+pageFormat.getImageableY());
        System.out.println(pageFormat.getImageableWidth()+"   "+pageFormat.getImageableHeight());      
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
RenderingHints.VALUE_ANTIALIAS_ON);
        g.scale(xScale,yScale);
        
        int inCol=4;
        //g.translate(36,36);
        for(int i=pageIndex*9;i<name.length && i<((pageIndex+1)*9);i++){
        if(i%9==0){
        g.translate(x, y);
        }
        else if(i%9==3){
        
        g.translate(-1800, 350);
        }
        else if(i%9==6){
        g.translate(-1800, 350);
        }
            
            
        setFormat(name[i], father[i], mobile[i],dob[i], address[i],empno[i],designation[i]); 
        contP.print(g);
        g.translate(600, 0);//y , x
            
        }
              
        System.out.println("PI="+pageIndex);
        
        
           countPage++;     
        return PAGE_EXISTS;
        }
         else{
            countPage=0;
            System.out.println("PI="+pageIndex);
            return(NO_SUCH_PAGE);}
        
        
    }  
 public class priPan extends JPanel{
     @Override
       protected void paintComponent(Graphics g){
           
            
            int cellwidth=25;
           int RemebmerOpHorizontalPos=-50;
           
       super.paintComponent(g);
       System.out.println("JACOB TO DRAW");
       
       Graphics2D gd=(Graphics2D)g;
       Color header=new Color(0, 0, 51);
       gd.setColor(header);
       
       gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
RenderingHints.VALUE_ANTIALIAS_ON);
     
      gd.fillRect(0 , 0, 550, 85);
      gd.setColor(Color.RED);
     gd.fillRect(0  , 85, 550, 2);
       
       gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
RenderingHints.VALUE_ANTIALIAS_ON);
      
       
       
    }}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contP = new javax.swing.JPanel();
        jPanel2 = new priPan();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        sNameTF = new javax.swing.JTextField();
        fNameTF = new javax.swing.JTextField();
        stdTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        s2L = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        imgIc = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        conTF = new javax.swing.JTextField();
        dobTF = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        addTA = new javax.swing.JTextArea();
        jTextField14 = new javax.swing.JTextField();
        codeTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        stdCB = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listLB = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        listLB1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        showTA = new javax.swing.JTextArea();
        confB = new javax.swing.JButton();
        printM = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        sesCB = new javax.swing.JComboBox<>();
        xTF = new javax.swing.JTextField();
        yTF = new javax.swing.JTextField();
        xSTF = new javax.swing.JTextField();
        ySTF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        spCB = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("I-CARD PRINT");
        setResizable(false);

        contP.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(529, 250));

        jLabel1.setBackground(new java.awt.Color(0, 0, 51));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Picture1.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel7.setText("Father's Name :");

        jLabel8.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel8.setText("Contact No.:");

        jLabel9.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel9.setText("Designation : ");

        sNameTF.setFont(new java.awt.Font("Adobe Hebrew", 1, 18)); // NOI18N
        sNameTF.setForeground(new java.awt.Color(153, 0, 0));
        sNameTF.setText("Jacob Mahto");
        sNameTF.setBorder(null);

        fNameTF.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        fNameTF.setText("Sample Check");
        fNameTF.setBorder(null);

        stdTF.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        stdTF.setText("XII");
        stdTF.setBorder(null);

        jLabel15.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel15.setText("Principal");

        jTextField8.setBackground(new java.awt.Color(0, 0, 51));
        jTextField8.setFont(new java.awt.Font("Trajan-Regular", 1, 21)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(255, 255, 255));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("M.K. Public sr. sec. School");
        jTextField8.setBorder(null);
        jTextField8.setOpaque(false);

        jTextField9.setBackground(new java.awt.Color(0, 0, 51));
        jTextField9.setFont(new java.awt.Font("Calisto MT", 0, 15)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(255, 255, 255));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setText("237-239, 21-South Niwaru Road , Jhotwara , Jaipur-Raj.");
        jTextField9.setBorder(null);
        jTextField9.setOpaque(false);
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jTextField10.setBackground(new java.awt.Color(0, 0, 51));
        jTextField10.setFont(new java.awt.Font("Trajan", 1, 18)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(255, 255, 255));
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setText("STAFF id card");
        jTextField10.setBorder(null);
        jTextField10.setOpaque(false);

        s2L.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        s2L.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/prsign.jpg"))); // NOI18N

        jTextField11.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jTextField11.setText("Address :");
        jTextField11.setBorder(null);

        imgIc.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField12.setBackground(new java.awt.Color(0, 0, 51));
        jTextField12.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(255, 255, 255));
        jTextField12.setText("Session : 2017-18");
        jTextField12.setBorder(null);
        jTextField12.setOpaque(false);
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jTextField13.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jTextField13.setText("D.O.B.:");
        jTextField13.setBorder(null);

        conTF.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        conTF.setText("XII");
        conTF.setBorder(null);

        dobTF.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        dobTF.setText("XII");
        dobTF.setBorder(null);

        jTextField15.setBackground(new java.awt.Color(0, 0, 51));
        jTextField15.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(255, 255, 255));
        jTextField15.setText("Ph. 2341265");
        jTextField15.setBorder(null);
        jTextField15.setOpaque(false);
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jTextField16.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jTextField16.setForeground(new java.awt.Color(51, 0, 51));
        jTextField16.setText("ELECTRONIC CARD - PROPERTY OF M.K. PUBLIC SCHOOL");
        jTextField16.setBorder(null);
        jTextField16.setCaretColor(new java.awt.Color(0, 0, 102));
        jTextField16.setDisabledTextColor(new java.awt.Color(51, 0, 51));
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        addTA.setColumns(20);
        addTA.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        addTA.setLineWrap(true);
        addTA.setRows(2);
        addTA.setToolTipText("");
        addTA.setWrapStyleWord(true);
        addTA.setBorder(null);
        addTA.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jTextField14.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jTextField14.setText("E. Code : ");
        jTextField14.setBorder(null);

        codeTF.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        codeTF.setText("XII");
        codeTF.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel15))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(s2L, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imgIc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField15))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(sNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fNameTF)
                                            .addComponent(addTA)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(stdTF)
                                                    .addComponent(conTF))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(8, 8, 8))
                                                    .addComponent(codeTF))))))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8)
                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(stdTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(codeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(conTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgIc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(s2L, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addTA, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout contPLayout = new javax.swing.GroupLayout(contP);
        contP.setLayout(contPLayout);
        contPLayout.setHorizontalGroup(
            contPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );
        contPLayout.setVerticalGroup(
            contPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel16.setText("Dept.");

        stdCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        stdCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TEACHING", "ADMINISTRATION" }));
        stdCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdCBActionPerformed(evt);
            }
        });

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

        jScrollPane2.setBackground(new java.awt.Color(51, 255, 153));

        showTA.setBackground(new java.awt.Color(51, 0, 51));
        showTA.setColumns(20);
        showTA.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        showTA.setForeground(new java.awt.Color(255, 255, 255));
        showTA.setLineWrap(true);
        showTA.setRows(5);
        showTA.setText("SYSTEM GENERTED CODE.\nDO NOT CHANGE WITHOUT KNOWLEDGE.\n\nTHE MATH ENGINE & TURBO-9 Print\nENGINE.\n");
        jScrollPane2.setViewportView(showTA);

        confB.setText("Confirm");
        confB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confBActionPerformed(evt);
            }
        });

        printM.setText("Print");
        printM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printMActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel17.setText("Session :");

        sesCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        sesCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
        sesCB.setSelectedIndex(1);
        sesCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sesCBActionPerformed(evt);
            }
        });

        xTF.setText("60");

        yTF.setText("60");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        spCB.setText("Spouse");
        spCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                        .addComponent(contP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(xTF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yTF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xSTF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ySTF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(15, 15, 15)
                        .addComponent(sesCB, 0, 172, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(43, 43, 43)
                        .addComponent(stdCB, 0, 1, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(confB)
                                .addGap(18, 18, 18)
                                .addComponent(printM, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1)
                            .addComponent(spCB))
                        .addGap(0, 97, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(sesCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(stdCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)))
                    .addComponent(contP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xSTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ySTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(printM)
                            .addComponent(confB))
                        .addGap(17, 17, 17)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spCB)))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void stdCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdCBActionPerformed
        String std=(String)stdCB.getSelectedItem();

        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String query3 = "SELECT * FROM empInfo WHERE dpt='"+std+"' and session='"+(String)sesCB.getSelectedItem()+"' ORDER BY NAME;";

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
                sr[i]=rs.getString("empno");
                i++;
            }

            listLB.setListData(nm);
            listLB1.setListData(sr);

        } catch (Exception E) {

        }
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_stdCBActionPerformed

    private void listLBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listLBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_listLBMouseClicked

    private void listLBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listLBMouseEntered

        // TODO add your handling code here:
    }//GEN-LAST:event_listLBMouseEntered

    private void listLBValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listLBValueChanged
        showTA.setText("");
        listLB1.setSelectedIndices(listLB.getSelectedIndices());

        // TODO add your handling code here:
    }//GEN-LAST:event_listLBValueChanged

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void confBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confBActionPerformed
try{
x=Double.parseDouble(xTF.getText());
y=Double.parseDouble(yTF.getText());
xScale=Double.parseDouble(xSTF.getText());
yScale=Double.parseDouble(ySTF.getText());
}
catch(Exception e){}
                
        List<String> selectedValuesList = listLB.getSelectedValuesList();
        System.out.println(selectedValuesList);
        showTA.setText("");
        showTA.append(selectedValuesList+"");

        // TODO add your handling code here:
    }//GEN-LAST:event_confBActionPerformed

    private void printMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printMActionPerformed

        std=(String)stdCB.getSelectedItem();
        confB.doClick();
        if(showTA.getText().equals("[]")){
            JOptionPane.showMessageDialog(null, "Nothing Selected. Want to do Manual print ?");

        }

        try {

            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String query3;
            if(spCB.isSelected()){
            query3 = "SELECT name,spouse,mobile,empno,dob,address,designation FROM empInfo WHERE dpt='"+std+"' and session='"+(String)sesCB.getSelectedItem()+"' ORDER BY name;";
            }
            else{
            query3 = "SELECT name,father,mobile,empno,dob,address,designation FROM empInfo WHERE dpt='"+std+"' and session='"+(String)sesCB.getSelectedItem()+"' ORDER BY name;";
            }
            

            stmt.execute(query1);
            stmt.execute(query2);

            ResultSet rs=stmt.executeQuery(query3);
            rs.last();
            int[] selected=new int[listLB.getSelectedIndices().length];
            selected=listLB.getSelectedIndices();
            name=new String[selected.length];
            father=new String[selected.length];
            mobile=new String[selected.length];
            dob=new String[selected.length];
            address=new String[selected.length];
            designation=new String[selected.length];
            empno=new String[selected.length];
            rs.beforeFirst();
            int i=0;
            int arrayPos=0;
            //System.out.println("------"+selected[0]);
            // System.out.println("---"+selected[1]);

            while(rs.next()){
                int j=0;
                while(j<selected.length){
                    if(i==selected[j]){
                        name[arrayPos]=rs.getString(1);
                        father[arrayPos]=rs.getString(2);
                        mobile[arrayPos]=rs.getString("mobile");
                        empno[arrayPos]=rs.getString("empno");
                        address[arrayPos]=rs.getString("address");
                        dob[arrayPos]=rs.getString("dob");
                        designation[arrayPos]=rs.getString("designation");
                        arrayPos++;
                        //System.out.println("I = "+i+" J = "+j+ " && value="+selected[j]);
                    }

                    j++;
                }
                i++;

            }

            //Giving data for printing

            
            if(name.length %9 ==0){
                numberOfPages=name.length/9;
            }
            else{
                numberOfPages=name.length/9+1;
            }

            System.out.println("Number of pages"+numberOfPages);
            i=0;
            for(i=0;i<numberOfPages;i++){
                countPage=i;
                System.out.println("i="+i+" && countPage="+countPage);

                System.out.println(name.length);
                //System.out.println(sname[0]);
                //System.out.println(sname[1]);
                
                System.out.println("BYE");

               
            }
            countPage=0;
 System.out.println("Hello jac");
                //printing
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
        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, E+" --^^-- ");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_printMActionPerformed

    private void sesCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sesCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sesCBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
jLabel1.setIcon(null); 
s2L.setIcon(null);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void spCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spCBActionPerformed
if(spCB.isSelected()){
    jLabel7.setText("Spouse's Name :");
    }
else {
        jLabel7.setText("Father's Name :");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_spCBActionPerformed

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
            java.util.logging.Logger.getLogger(ICardCreateT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ICardCreateT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ICardCreateT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ICardCreateT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ICardCreateT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addTA;
    private static javax.swing.JTextField codeTF;
    private static javax.swing.JTextField conTF;
    private javax.swing.JButton confB;
    public static javax.swing.JPanel contP;
    private static javax.swing.JTextField dobTF;
    private static javax.swing.JTextField fNameTF;
    public static javax.swing.JLabel imgIc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JList<String> listLB;
    private javax.swing.JList<String> listLB1;
    private javax.swing.JButton printM;
    private javax.swing.JLabel s2L;
    private static javax.swing.JTextField sNameTF;
    public static javax.swing.JComboBox<String> sesCB;
    private javax.swing.JTextArea showTA;
    private javax.swing.JCheckBox spCB;
    public static javax.swing.JComboBox<String> stdCB;
    private static javax.swing.JTextField stdTF;
    private javax.swing.JTextField xSTF;
    private javax.swing.JTextField xTF;
    private javax.swing.JTextField ySTF;
    private javax.swing.JTextField yTF;
    // End of variables declaration//GEN-END:variables
}

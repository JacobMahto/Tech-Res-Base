package program;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import printPackage.printAdm;




/**
 *
 * @author Jacob Mahto
 */
public class studentInfo extends javax.swing.JFrame {
private Dimension ds,rs;
private Webcam wc;
private WebcamPanel wp;
String tempSr="";
    public void initial(){
    
    }
    

    
    
 public void destroy() {
 		System.out.println("Destroy");
 		wc.close();
		wc.shutdown();
	
 		System.out.println("Destroyed");
 	}   
    
    
    
    
    
    int infoSize=22;
String[] pI=new String[infoSize];
String insQuery="";
int override=0;
public int bulkDelete=0;

public int checkNull(){
if(srTF.getText().equals("") || dobTF.getText().trim().equals("") || doaTF.getText().trim().equals(""))
{

return(1);
}
  return(0);  
}


public void getSrDet(String a){
try {
                Class.forName("java.sql.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
                Statement stmt = con.createStatement();
                String query1 = "USE rsl;";
                stmt.execute(query1);
                String query2="SELECT * from studentinfo where sr='"+a.trim()+"';";
                ResultSet rs=stmt.executeQuery(query2);
                rs.next();
                
                nameTF.setText(rs.getString("name"));
                fNameTF.setText(rs.getString("fname"));
                mNameTF.setText(rs.getString("mname"));
                fCTF.setText(rs.getString("fcon"));
                mCTF.setText(rs.getString("mcon"));
                gCTF.setText(rs.getString("gcon"));
                gNameTF.setText(rs.getString("guard"));
                guardRelTF.setText(rs.getString("guardRel"));
                dobTF.setText(rs.getString("dob"));
                doaTF.setText(rs.getString("doa"));
                sexCB.setSelectedItem(rs.getString("sex"));
                addTF.setText(rs.getString("address"));
                statusCB.setSelectedItem(rs.getString("status"));
                admInClassCB.setSelectedItem(rs.getString("admInClass"));
                rmkTF.setText(rs.getString("rmk"));
                sessionCB.setSelectedItem(rs.getString("session"));
               classCB.setSelectedItem(rs.getString("class"));
                uidTF.setText(rs.getString("uid"));
                 religionCB.setSelectedItem(rs.getString("religion"));
                  castCB.setSelectedItem(rs.getString("caste"));
                     medCB.setSelectedItem(rs.getString("medium"));
                     srTF.setText(a.trim());
                     guardianCB.setSelectedItem(rs.getString("guardRel"));
                     rs.close();
                     con.close();
                
            } catch (Exception E) {
             //   JOptionPane.showMessageDialog(null, E);
                
            }
}

public void convertToQuote(String[] a){
    for(int i=0;i<infoSize;i++){
    a[i]="'"+a[i]+"'";    
    }
}

public String createInsertQuery(String[] a){
String q="INSERT INTO studentinfo VALUES(";
for(int i=0;i<infoSize;i++){
    if(i!=(infoSize-1)){
    q+=a[i]+",";
    }
    else{
    q+=a[i];
    }    
}
q+=");";
System.out.println(q);
return(q);
}

public void setVal(){
try {
                Class.forName("java.sql.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
                Statement stmt = con.createStatement();
                String query1 = "USE rsl;";
                stmt.execute(query1);
                if(override==1){
                override=0;
                stmt.execute("DELETE FROM studentinfo WHERE sr='"+srTF.getText()+"';");
                stmt.execute(insQuery);
            //    stmt.execute("UPDATE marks set sr=' ");
                if(bulkDelete==1){
                bulkDelete=0;
                }
                else{JOptionPane.showMessageDialog(null, "Replaced with success !","OVERRIDDEN !",1);}
                }
                else{
                stmt.execute(insQuery); 
                JOptionPane.showMessageDialog(null, "Student Information saved !","SUCCESS !",1);
                if(tempSr.equals(srTF.getText())){
                if(medCB.getSelectedIndex()==0){
re(100);
} 
if(medCB.getSelectedIndex()==1){
re(101);
}
                }
                
                
                }
                
                
            } catch (Exception E) {
                JOptionPane.showMessageDialog(null, E);
                System.out.println(E);
            }
}

    public void getDet(){
    
    pI[0]=nameTF.getText();
    pI[1]=fNameTF.getText().trim();
    pI[2]=mNameTF.getText().trim();
    pI[3]=dobTF.getText();
    pI[4]=sexCB.getSelectedItem().toString();
    pI[5]=addTF.getText().trim();
    pI[6]=srTF.getText().trim();
    pI[7]=doaTF.getText().trim();
    pI[8]=rmkTF.getText();
    pI[9]=classCB.getSelectedItem().toString();
    
    
    pI[10]=religionCB.getSelectedItem().toString();
    pI[11]=castCB.getSelectedItem().toString();
    pI[12]=medCB.getSelectedItem().toString();
    pI[13]=admInClassCB.getSelectedItem().toString();
    pI[14]=sessionCB.getSelectedItem().toString();
    
    //extra table columns
    pI[15]=fCTF.getText().trim();
    pI[16]=mCTF.getText().trim();
    pI[17]=gCTF.getText().trim();
    pI[18]=gNameTF.getText().trim();
    pI[21]=guardRelTF.getText().trim();
   // pI[21]="Jacob";
    pI[19]=uidTF.getText();      
    pI[20]=statusCB.getSelectedItem().toString().trim();
    }
    
    public void setDet(){
    try {
                Class.forName("java.sql.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
                Statement stmt = con.createStatement();
                String query1 = "USE rsl;";
                stmt.execute(query1);
                String query2="SELECT * FROM scale where code='infomarks';";
                ResultSet rs=stmt.executeQuery(query2);
               
                String query3="INSERT INTO studentinfo values(";                
                
                
                
            } catch (Exception E) {
                JOptionPane.showMessageDialog(null, E);
                System.out.println(E);
            }
    
    
    
    }
    
    
    
    public void re(int x){
try {
                Class.forName("java.sql.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
                Statement stmt = con.createStatement();
                String query1 = "USE rsl;";
                stmt.execute(query1);
                String query2="SELECT * FROM scale where code='sr';";
                ResultSet rs=stmt.executeQuery(query2);
                rs.next();
                if(x==0){
                  //english medium  
                  tempSr=rs.getInt("x")+"/"+rs.getInt("y");
                    srTF.setText(tempSr);
                    
                }
                if(x==1){
                  //hindi medium  
                  tempSr=rs.getInt("a")+"/"+rs.getInt("b");
                    srTF.setText(tempSr);
                }
                if(x==100){
                query2="UPDATE scale set x=x+1 where code='sr';";
                stmt.executeUpdate(query2);
                
                }
                if(x==101){
                query2="UPDATE scale set a=a+1 where code='sr';";
                stmt.executeUpdate(query2);
                }
            } catch (Exception E) {
                JOptionPane.showMessageDialog(null, E);
                System.out.println(E);
            }}
    
    /**
     * Creates new form studentInfo
     */
    public studentInfo() {
        initComponents();
        try{
            
ds=new Dimension(320   , 240);
rs=WebcamResolution.VGA.getSize();
wc=Webcam.getDefault();
wp=new WebcamPanel(wc, ds, false); 
            
            
            
            wc.setViewSize(rs);
         
        wp.setFillArea(true);
        jPanel5.setLayout(new FlowLayout());
        
        jPanel5.add(wp);    }
        catch(Exception e){}
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        statusCB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        addTF = new javax.swing.JTextArea();
        sexCB = new javax.swing.JComboBox<>();
        dobTF = new javax.swing.JTextField();
        dateL = new javax.swing.JLabel();
        guardianCB = new javax.swing.JComboBox<>();
        guardRelTF = new javax.swing.JTextField();
        gCTF = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        gNameTF = new javax.swing.JTextField();
        mNameTF = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        mCTF = new javax.swing.JTextField();
        fCTF = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        fNameTF = new javax.swing.JTextField();
        nameTF = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        religionCB = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        castCB = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        uidTF = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        doaTF = new javax.swing.JTextField();
        admInClassCB = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        rmkTF = new javax.swing.JTextArea();
        classCB = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        dateL1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        sessionCB = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        medCB = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        srTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        srFTF = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        dateL2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("STUDENT DATABASE ENGINE -- TECH-RES 2017 ULTIMATE --- ALGORITHM - THE MATH ENGINE");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name :** ");

        jLabel2.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Father's Name :");

        jLabel3.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mother's Name : ");

        jLabel10.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Guardian :");

        jLabel22.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Relation");

        jLabel5.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Date of Birth :**");

        jLabel6.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Sex :**");

        jLabel7.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Address :");

        jLabel21.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Status :");

        statusCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        statusCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Deactive" }));
        statusCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                statusCBFocusLost(evt);
            }
        });
        statusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusCBActionPerformed(evt);
            }
        });

        addTF.setColumns(20);
        addTF.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        addTF.setLineWrap(true);
        addTF.setRows(5);
        jScrollPane1.setViewportView(addTF);

        sexCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        sexCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE", "NOT KNOWN" }));

        dobTF.setFont(new java.awt.Font("Adobe Hebrew", 2, 18)); // NOI18N
        dobTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                dobTFCaretUpdate(evt);
            }
        });
        dobTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobTFFocusLost(evt);
            }
        });
        dobTF.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                dobTFInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        dobTF.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dobTFPropertyChange(evt);
            }
        });

        dateL.setFont(new java.awt.Font("Adobe Hebrew", 2, 24)); // NOI18N
        dateL.setForeground(new java.awt.Color(255, 255, 255));
        dateL.setText("ddmmyyyy");

        guardianCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        guardianCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Grandfather", "Grandmother", "Mat. Uncle", "Mat. Aunt", "Uncle", "Aunt", "Brother", "Sister", "Other" }));
        guardianCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                guardianCBFocusLost(evt);
            }
        });
        guardianCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardianCBMouseClicked(evt);
            }
        });
        guardianCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardianCBActionPerformed(evt);
            }
        });

        guardRelTF.setFont(new java.awt.Font("Adobe Hebrew", 3, 18)); // NOI18N
        guardRelTF.setForeground(new java.awt.Color(0, 102, 102));
        guardRelTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardRelTFActionPerformed(evt);
            }
        });
        guardRelTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guardRelTFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                guardRelTFKeyReleased(evt);
            }
        });

        gCTF.setFont(new java.awt.Font("Adobe Hebrew", 3, 18)); // NOI18N
        gCTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gCTFActionPerformed(evt);
            }
        });
        gCTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gCTFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                gCTFKeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Contact No.");

        gNameTF.setFont(new java.awt.Font("Adobe Hebrew", 2, 18)); // NOI18N
        gNameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                gNameTFKeyReleased(evt);
            }
        });

        mNameTF.setFont(new java.awt.Font("Adobe Hebrew", 2, 18)); // NOI18N
        mNameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mNameTFKeyReleased(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Contact No.");

        mCTF.setFont(new java.awt.Font("Adobe Hebrew", 3, 18)); // NOI18N
        mCTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCTFActionPerformed(evt);
            }
        });
        mCTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mCTFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mCTFKeyReleased(evt);
            }
        });

        fCTF.setFont(new java.awt.Font("Adobe Hebrew", 3, 18)); // NOI18N
        fCTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fCTFActionPerformed(evt);
            }
        });
        fCTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fCTFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fCTFKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Contact No.");

        fNameTF.setFont(new java.awt.Font("Adobe Hebrew", 2, 18)); // NOI18N
        fNameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fNameTFKeyReleased(evt);
            }
        });

        nameTF.setFont(new java.awt.Font("Adobe Hebrew", 2, 18)); // NOI18N
        nameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameTFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameTFKeyTyped(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Adobe Hebrew", 0, 14)); // NOI18N
        jButton2.setText("WHAT'S NEW");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel21)
                            .addComponent(jLabel10)
                            .addComponent(jLabel22))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTF)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateL, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(sexCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(guardianCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(guardRelTF, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(gNameTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                    .addComponent(mNameTF, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fNameTF, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fCTF, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                    .addComponent(mCTF)
                                    .addComponent(gCTF)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(gNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardianCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardRelTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateL, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(sexCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        jButton3.setFont(new java.awt.Font("Adobe Hebrew", 0, 14)); // NOI18N
        jButton3.setText("START");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        religionCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        religionCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hindu", "Muslim", "Jain", "Sikh", "Christian", "Sindhi", "Parsi", "Other" }));
        religionCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                religionCBFocusLost(evt);
            }
        });
        religionCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                religionCBActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Religion :");

        jLabel14.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Category :");

        castCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        castCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERAL", "OBC", "SC", "ST", "OTHER" }));
        castCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                castCBFocusLost(evt);
            }
        });
        castCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                castCBActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Aadhar No.");

        uidTF.setFont(new java.awt.Font("Adobe Hebrew", 2, 18)); // NOI18N
        uidTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                uidTFCaretUpdate(evt);
            }
        });
        uidTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                uidTFFocusLost(evt);
            }
        });
        uidTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidTFActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        jButton9.setFont(new java.awt.Font("Adobe Hebrew", 0, 14)); // NOI18N
        jButton9.setText("CAPTURE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Adobe Hebrew", 0, 14)); // NOI18N
        jButton10.setText("CLOSE");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(25, 25, 25)
                        .addComponent(castCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(religionCB, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uidTF, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(uidTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(religionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(castCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));

        jLabel8.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Date of Admission :**");

        doaTF.setFont(new java.awt.Font("Adobe Hebrew", 2, 18)); // NOI18N
        doaTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                doaTFCaretUpdate(evt);
            }
        });
        doaTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                doaTFFocusLost(evt);
            }
        });

        admInClassCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        admInClassCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PG", "NUR", "LKG", "UKG", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI Arts-A", "XI Arts-B", "XI Arts-C", "XI Commerce-A", "XI Commerce-B", "XI Commerce-C", "XII Arts-A", "XII Arts-B", "XII Arts-C", "XII Commerce-A", "XII Commerce-B", "XII Commerce-C" }));
        admInClassCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                admInClassCBFocusLost(evt);
            }
        });
        admInClassCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admInClassCBActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Admitted to Class :**");

        jLabel11.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Addtional Remarks :");

        rmkTF.setColumns(20);
        rmkTF.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        rmkTF.setLineWrap(true);
        rmkTF.setRows(5);
        jScrollPane2.setViewportView(rmkTF);

        classCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        classCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PG", "NUR", "LKG", "UKG", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI Arts-A", "XI Arts-B", "XI Arts-C", "XI Commerce-A", "XI Commerce-B", "XI Commerce-C", "XII Arts-A", "XII Arts-B", "XII Arts-C", "XII Commerce-A", "XII Commerce-B", "XII Commerce-C" }));
        classCB.setSelectedIndex(1);
        classCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                classCBFocusLost(evt);
            }
        });
        classCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classCBActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Present class :");

        dateL1.setFont(new java.awt.Font("Adobe Hebrew", 2, 24)); // NOI18N
        dateL1.setForeground(new java.awt.Color(255, 255, 255));
        dateL1.setText("ddmmyyyy");

        jLabel20.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Present Session :** ");

        sessionCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        sessionCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
        sessionCB.setSelectedIndex(1);
        sessionCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sessionCBFocusLost(evt);
            }
        });
        sessionCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessionCBActionPerformed(evt);
            }
        });

        jButton1.setText("SAVE NEW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("REPLACE & SAVE (CAUTION !!!)");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("REFRESH");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("EXIT");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(dateL1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20))
                        .addContainerGap(192, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(29, 29, 29)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(20, 20, 20)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addComponent(admInClassCB, 0, 194, Short.MAX_VALUE))
                    .addGap(405, 405, 405)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(sessionCB, 0, 165, Short.MAX_VALUE)
                        .addComponent(classCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(7, 7, 7)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(doaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(dateL1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(78, 78, 78))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sessionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(admInClassCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(classCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(64, 64, 64)))
        );

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));

        jLabel18.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Medium :");

        medCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        medCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Hindi" }));
        medCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                medCBFocusLost(evt);
            }
        });
        medCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medCBActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("S/R no. :** ");

        srTF.setFont(new java.awt.Font("Adobe Hebrew", 2, 18)); // NOI18N
        srTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                srTFCaretUpdate(evt);
            }
        });
        srTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                srTFFocusLost(evt);
            }
        });
        srTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srTFActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Find S/R :");

        srFTF.setFont(new java.awt.Font("Adobe Hebrew", 2, 18)); // NOI18N
        srFTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                srFTFCaretUpdate(evt);
            }
        });
        srFTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                srFTFFocusLost(evt);
            }
        });
        srFTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srFTFActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Adobe Hebrew", 0, 14)); // NOI18N
        jButton5.setText("FIND");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        dateL2.setFont(new java.awt.Font("Adobe Hebrew", 2, 24)); // NOI18N
        dateL2.setForeground(new java.awt.Color(255, 255, 255));
        dateL2.setText("STUDENT INFORMATION DATABASE--TECH-RES 2017");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateL2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(25, 25, 25)
                        .addComponent(medCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(srTF, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(srFTF, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateL2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(srTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(srFTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(medCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void statusCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_statusCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_statusCBFocusLost

    private void statusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusCBActionPerformed

    private void dobTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_dobTFCaretUpdate
        try{
            String dob=dobTF.getText();
            dateL.setText(dob.substring(0,2)+"-"+dob.substring(2, 4)+"-"+dob.substring(4,8));

        }
        catch(Exception E){
        }      // TODO add your handling code here:
    }//GEN-LAST:event_dobTFCaretUpdate

    private void dobTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobTFFocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_dobTFFocusLost

    private void dobTFInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dobTFInputMethodTextChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_dobTFInputMethodTextChanged

    private void dobTFPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dobTFPropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_dobTFPropertyChange

    private void guardianCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_guardianCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_guardianCBFocusLost

    private void guardianCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardianCBMouseClicked


        // TODO add your handling code here:
    }//GEN-LAST:event_guardianCBMouseClicked

    private void guardianCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardianCBActionPerformed
guardRelTF.setText(guardianCB.getSelectedItem().toString());        // TODO add your handling code here:
    }//GEN-LAST:event_guardianCBActionPerformed

    private void guardRelTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardRelTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardRelTFActionPerformed

    private void guardRelTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guardRelTFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardRelTFKeyPressed

    private void guardRelTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guardRelTFKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_guardRelTFKeyReleased

    private void gCTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gCTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gCTFActionPerformed

    private void gCTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gCTFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_gCTFKeyPressed

    private void gCTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gCTFKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_gCTFKeyReleased

    private void gNameTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gNameTFKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_gNameTFKeyReleased

    private void mNameTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mNameTFKeyReleased
        String name = mNameTF.getText();

        try {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            mNameTF.setText(name);
        } catch (Exception e) {
        }

        name = mNameTF.getText();
        try {
            int l = name.length();

            int i = 0;

            while (i < l) {

                if (name.substring(i, i + 1).equals(" ")) {

                    name = name.substring(0, i + 1) + name.substring(i + 1, i + 2).toUpperCase() + name.substring(i + 2);
                    System.out.println("i" + i);

                }

                mNameTF.setText(name);
                i++;

            }

        } catch (Exception e) {

        }        // TODO add your handling code here:
    }//GEN-LAST:event_mNameTFKeyReleased

    private void mCTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mCTFActionPerformed

    private void mCTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mCTFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mCTFKeyPressed

    private void mCTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mCTFKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_mCTFKeyReleased

    private void fCTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fCTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fCTFActionPerformed

    private void fCTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fCTFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_fCTFKeyPressed

    private void fCTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fCTFKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fCTFKeyReleased

    private void fNameTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fNameTFKeyReleased
        String name = fNameTF.getText();

        try {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            fNameTF.setText(name);
        } catch (Exception e) {
        }

        name = fNameTF.getText();
        try {
            int l = name.length();

            int i = 0;

            while (i < l) {

                if (name.substring(i, i + 1).equals(" ")) {

                    name = name.substring(0, i + 1) + name.substring(i + 1, i + 2).toUpperCase() + name.substring(i + 2);
                    System.out.println("i" + i);

                }

                fNameTF.setText(name);
                i++;

            }

        } catch (Exception e) {

        }        // TODO add your handling code here:
    }//GEN-LAST:event_fNameTFKeyReleased

    private void nameTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTFKeyReleased
        String name = nameTF .getText();

        try {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            nameTF.setText(name);
        } catch (Exception e) {
        }

        name = nameTF.getText();
        try {
            int l = name.length();

            int i = 0;

            while (i < l) {

                if (name.substring(i, i + 1).equals(" ")) {

                    name = name.substring(0, i + 1) + name.substring(i + 1, i + 2).toUpperCase() + name.substring(i + 2);
                    System.out.println("i" + i);

                }

                nameTF.setText(name);
                i++;

            }

        } catch (Exception e) {

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_nameTFKeyReleased

    private void nameTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTFKeyTyped

        // TODO add your handling code here:
    }//GEN-LAST:event_nameTFKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
Thread t=new Thread(){

@Override
public void run(){
    wc.getLock();
wp.start();
}
};

t.setDaemon(true);
t.start();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(null, "Added Auto-Capitalise Option.\nAdded input colours.\nChanged interface.\nAdded lower classes.\nModified Date input Processing.\nEnforced line wrap.\nAdded SPACE input protection to SR field.\nReplaced contact text field to text area.\ncaste retrieval corrected.");

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void religionCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_religionCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_religionCBFocusLost

    private void religionCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_religionCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_religionCBActionPerformed

    private void castCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_castCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_castCBFocusLost

    private void castCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_castCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_castCBActionPerformed

    private void medCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_medCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_medCBFocusLost

    private void medCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medCBActionPerformed
re(medCB.getSelectedIndex());
        // TODO add your handling code here:
    }//GEN-LAST:event_medCBActionPerformed

    private void srTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_srTFCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_srTFCaretUpdate

    private void srTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_srTFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_srTFFocusLost

    private void srTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_srTFActionPerformed

    private void doaTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_doaTFCaretUpdate
        try{
            String doa=doaTF.getText();
            dateL1.setText(doa.substring(0,2)+"-"+doa.substring(2, 4)+"-"+doa.substring(4,8));

        }
        catch(Exception E){
        }        // TODO add your handling code here:
    }//GEN-LAST:event_doaTFCaretUpdate

    private void doaTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_doaTFFocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_doaTFFocusLost

    private void admInClassCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_admInClassCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_admInClassCBFocusLost

    private void admInClassCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admInClassCBActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_admInClassCBActionPerformed

    private void classCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_classCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_classCBFocusLost

    private void classCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_classCBActionPerformed

    private void sessionCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sessionCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_sessionCBFocusLost

    private void sessionCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessionCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sessionCBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
getDet();

  studentInfo obj = new studentInfo();
        obj.getSrDet(srTF.getText());

        if (obj.nameTF.getText().trim().equals("") == false || obj.dobTF.getText().trim().equals("") == false || obj.doaTF.getText().trim().equals("") == false) {
            System.out.println("BLOCK 1");
            obj.setVisible(true);
            JOptionPane.showMessageDialog(null, "S/R already allocated !", "ISSUE ERROR", 0);
        } else {
            System.out.println("BLOCK 2");
           
            
            if (checkNull() == 1) {
                JOptionPane.showMessageDialog(null, "Enter Important details at least !", "Partial Information Error !", 0);
                
            } else {
                System.out.println("BLOCK 3");
                convertToQuote(pI);
                insQuery = createInsertQuery(pI);
                setVal();

            }

        }


      



        


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void uidTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_uidTFCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_uidTFCaretUpdate

    private void uidTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_uidTFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_uidTFFocusLost

    private void uidTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidTFActionPerformed

    private void srFTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_srFTFCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_srFTFCaretUpdate

    private void srFTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_srFTFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_srFTFFocusLost

    private void srFTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srFTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_srFTFActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        getSrDet(srFTF.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
getDet();

  
         
           
            
            if (checkNull() == 1) {
                JOptionPane.showMessageDialog(null, "Enter Important details at least !", "Partial Information Error !", 0);
                
            } else {
                System.out.println("BLOCK 3");
                convertToQuote(pI);
                override=1;//signal to replace the existing sr
                insQuery = createInsertQuery(pI);
                setVal();                
            }

        

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        try{jButton7.doClick();}
        catch(Exception e){}
studentInfo obj=new studentInfo();

obj.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
try{jButton10.doClick();}
catch(Exception e){

}
        
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
try {
                Class.forName("java.sql.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
                Statement stmt = con.createStatement();
                String query1 = "USE rsl;";
                stmt.execute(query1);
                String query2="DELETE FROM studentinfo where sr='"+srTF.getText()+"';";
                stmt.execute(query2);
                
                if(bulkDelete==0){
                JOptionPane.showMessageDialog(null, "Deleted the sr="+srTF.getText());
                jButton6.doClick();
                }
                
                
                
            } catch (Exception E) {
                //JOptionPane.showMessageDialog(null, E);
                //System.out.println(E);
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
String skolar=srTF.getText();
        
        try{
    for(int i=0;i<skolar.length();i++){
    int flag=0;
        if(skolar.substring(i,i+1).equals("/") && flag==0){
    flag=1;
    skolar=skolar.substring(0,i)+skolar.substring((i+1));
    }
    }
    skolar=skolar.trim();
    }
catch(Exception e){}





   //     String a=nameTF.getText().trim()+classCB.getSelectedItem();
   String a=skolar;
        try {
            File file=new File(String.format("C:/Tech-Res Image Bank/"+a+".jpg"));
            ImageIO.write(wc.getImage(), "JPG", file);
            JOptionPane.showMessageDialog(this, "File Saved to :\n"+file.getAbsolutePath(), "camCap", 1);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vinita is : \n"+e ,"camCap",0);
           // JOptionPane.showMessageDialog(null, a);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
destroy(); 

System.out.println(wc.getLock());
// TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(studentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new studentInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea addTF;
    public javax.swing.JComboBox<String> admInClassCB;
    public javax.swing.JComboBox<String> castCB;
    public javax.swing.JComboBox<String> classCB;
    private javax.swing.JLabel dateL;
    private javax.swing.JLabel dateL1;
    private javax.swing.JLabel dateL2;
    public javax.swing.JTextField doaTF;
    public javax.swing.JTextField dobTF;
    public javax.swing.JTextField fCTF;
    public javax.swing.JTextField fNameTF;
    public javax.swing.JTextField gCTF;
    public javax.swing.JTextField gNameTF;
    public javax.swing.JTextField guardRelTF;
    public javax.swing.JComboBox<String> guardianCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    public javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextField mCTF;
    public javax.swing.JTextField mNameTF;
    public javax.swing.JComboBox<String> medCB;
    public javax.swing.JTextField nameTF;
    public javax.swing.JComboBox<String> religionCB;
    public javax.swing.JTextArea rmkTF;
    public javax.swing.JComboBox<String> sessionCB;
    public javax.swing.JComboBox<String> sexCB;
    public javax.swing.JTextField srFTF;
    public javax.swing.JTextField srTF;
    public javax.swing.JComboBox<String> statusCB;
    public javax.swing.JTextField uidTF;
    // End of variables declaration//GEN-END:variables
}

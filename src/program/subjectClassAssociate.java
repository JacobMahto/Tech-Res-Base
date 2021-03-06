package program;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class subjectClassAssociate extends javax.swing.JFrame {

    int[] subjectId;
    
    /**
     * Creates new form subjectClassAssociate
     */
    public subjectClassAssociate() {
        initComponents();
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
        countTF = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listLB = new javax.swing.JList<>();
        sessionCB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        showTA = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        classCB = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Assign");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        countTF.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        countTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Adobe Hebrew", 0, 24)); // NOI18N
        jLabel2.setText("Subjects Assignment");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addComponent(countTF, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(countTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel1.setText("Enter Class :");

        listLB.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listLB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listLBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listLB);

        sessionCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        sessionCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
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

        jScrollPane2.setBackground(new java.awt.Color(51, 255, 153));

        showTA.setEditable(false);
        showTA.setBackground(new java.awt.Color(255, 204, 204));
        showTA.setColumns(20);
        showTA.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        showTA.setRows(5);
        showTA.setText("MATH ENGINE\nBY\nJACOB MAHTO\n9782197199\n9636509907\n\nLORD IS MY SHEPHERD,\nI SHALL NOT WANT.");
        jScrollPane2.setViewportView(showTA);

        jButton1.setFont(new java.awt.Font("Adobe Hebrew", 1, 18)); // NOI18N
        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Adobe Hebrew", 1, 18)); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        classCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        classCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PG", "NUR", "LKG", "UKG", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI Arts-A", "XI Arts-B", "XI Arts-C", "XI Commerce-A", "XI Commerce-B", "XI Commerce-C", "XII Arts-A", "XII Arts-B", "XII Arts-C", "XII Commerce-A", "XII Commerce-B", "XII Commerce-C" }));
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

        jLabel3.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        jLabel3.setText("Enter Year :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sessionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jButton2)
                        .addComponent(classCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sessionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listLBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listLBMouseClicked




        // TODO add your handling code here:
    }//GEN-LAST:event_listLBMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //int[] a = listLB.getSelectedIndices();
        int a=listLB.getSelectedIndex();
        int max=0;

        String std = (String) classCB.getSelectedItem();
        int classIndex = classCB.getSelectedIndex();
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/rsl", "root", "lion");
            Statement stmt = con.createStatement();
                     

            
            // String queryClear="Delete from subjectsclass WHERE std='"+std+"';";
            // stmt.execute(queryClear);

            //for counting the number of selected items
           /* int count = 0;
            for (int i = 0; i <= 500; i++) {

                try {
                    int d = a[i];
                    count++;
                } catch (Exception e) {
                }

            }

            for (int j = 0; j < count; j++) {
                System.out.println("ITERATION -"+j+" AND VALUE"+subjectId[a[j]]);
                //subjectId has been filled by action listener of the combobox classCB
                String query00 = "INSERT INTO subjectsClass VALUES(" + subjectId[a[j]] + ",'" + std + "','"+sessionCB.getSelectedItem()+"',"+(j+1)+");";
                stmt.execute(query00);
            } */
           String queryToStopSubDuplicate="Select count(*) from subjectsclass where id="+subjectId[a]+" AND std='"+std+"' AND session='"+sessionCB.getSelectedItem()+"';";
           
            ResultSet rs=stmt.executeQuery(queryToStopSubDuplicate);
            rs.next();
            int num=rs.getInt(1);
          
            rs.close();
            if(num>=1){
            JOptionPane.showMessageDialog(null, "Duplicate Entry !");
            }
            else{
            String query="Select max(preced)+1 from subjectsclass;";
           rs=stmt.executeQuery(query);
           rs.next();
           max=rs.getInt(1);
           String query00 = "INSERT INTO subjectsClass VALUES(" + subjectId[a] + ",'" + std + "','"+sessionCB.getSelectedItem()+"',"+max+");";
                stmt.execute(query00);
                
           
            try{
                
                
            //Updating the list of 
            
            
                
                
                
                
            }
            catch(Exception e){}
            

            JOptionPane.showMessageDialog(null, "SUCCESS .");
            int sessionIndex=sessionCB.getSelectedIndex();
            //to refresh the current frame
            dispose();
            subjectClassAssociate obj = new subjectClassAssociate();
            obj.setVisible(true);
            obj.sessionCB.setSelectedIndex(sessionIndex);
            obj.classCB.setSelectedIndex(classIndex);

            //initializing the subject value for each student
            }
           
           
        } catch (Exception E) {
            System.out.println(E);
            JOptionPane.showMessageDialog(null, E);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
int[] a = listLB.getSelectedIndices();
        
        String std=(String)classCB.getSelectedItem();
        int classIndex=classCB.getSelectedIndex();

        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            


            stmt.execute(query1);
            stmt.execute(query2);
            
            
            System.out.println("JACOB");
            String queryClear="Delete from subjectsclass WHERE std='"+std+"';";
           // stmt.execute(queryClear);
            
            //for counting the number of selected items
            int count=0;
            for(int i=0;i<=500;i++){
            
                try{
                int d=a[i];
                count++;
                }
                catch(Exception e){}
                
            }
            
            
            
            for(int j=0;j<count;j++){
            String query4="DELETE FROM subjectsClass WHERE id="+subjectId[a[j]]+" AND std='"+std+"';";
            stmt.execute(query4);
            }
            
            JOptionPane.showMessageDialog(null, "SUCCESS .");
            
            //to refresh the current frame
            dispose();
            subjectClassAssociate obj=new subjectClassAssociate();
            obj.setVisible(true);
            obj.classCB.setSelectedIndex(classIndex);
            
            

            

            
        } catch (Exception E) {
            System.out.println(E);
            JOptionPane.showMessageDialog(null, E);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void classCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_classCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_classCBFocusLost

    private void classCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classCBActionPerformed
String std=(String)classCB.getSelectedItem();
showTA.setText("");

        try{
            Class.forName("java.sql.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost","root","lion");
                Statement stmt=con.createStatement();
                String query1="CREATE DATABASE IF NOT EXISTS rsl;";
                String query2="USE rsl;";
                
                stmt.execute(query1);
                stmt.execute(query2);
                
                    
                
                String querys="SELECT * FROM subjectsOriginal ;";
                ResultSet rs=stmt.executeQuery(querys);
                rs.last();
                rs.getRow();
                countTF.setText(rs.getRow()+"");
               
                
                String[] subjectArray=new String[rs.getRow()]; 
                subjectId=new int[rs.getRow()];
                rs.beforeFirst();
                
                int i=0;
                while(rs.next()){
                    System.out.println(rs.getString("subject"));
                subjectArray[i]=rs.getString("subject");
                i++;
                
                }
                
                rs.beforeFirst();
                
                
                
                i=0;
                while(rs.next()){
                subjectId[i]=rs.getInt("id");
                i++;
                }
                
                listLB.setListData(subjectArray);
                
                
                String avail="SELECT subject FROM subjectsClass,subjectsoriginal WHERE std='"+std+"' AND subjectsclass.id=subjectsoriginal.id AND session='"+sessionCB.getSelectedItem()+"' ORDER BY preced;";
                rs=stmt.executeQuery(avail);
                rs.beforeFirst();
                
                while(rs.next()){
                showTA.append(rs.getString("subject"));
                showTA.append("\n");
                }
                
                
                
                
}
catch(Exception E){
System.out.println(E);
JOptionPane.showMessageDialog(null,E);
}         // TODO add your handling code here:
    }//GEN-LAST:event_classCBActionPerformed

    private void sessionCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sessionCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_sessionCBFocusLost

    private void sessionCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessionCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sessionCBActionPerformed

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
            java.util.logging.Logger.getLogger(subjectClassAssociate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(subjectClassAssociate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(subjectClassAssociate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(subjectClassAssociate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new subjectClassAssociate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> classCB;
    private javax.swing.JLabel countTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listLB;
    private javax.swing.JComboBox<String> sessionCB;
    private javax.swing.JTextArea showTA;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printFinal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import program.enterMarks;
import program.securityBreach;

/**
 *
 * @author Jacob Mahto
 */
public class reportCardE extends javax.swing.JFrame {

    static DefaultTableModel tab;
    static int[] colWiseSum, colWiseSumPartial;
    static int[] rowWiseSum, rowWiseSumPartial;
    static int tempRowAssign = 0;
    static int tempRowAssignForOpSub = 0;
    static int tempColAssign = 0;
    static int noOfOpSub = 0;
    static String[] compSub;
    static String[] opSub;
    static int actualSub, optSub;
    static int[] srL, num;
    static int curSr = 0;
    int[] opSubTotal;
    DefaultTableModel tabl;
    int saveForOpSubTotal;
    static int variableToTellCompOP = 0;
    static int swi = 0;
    static int sr = 0;//state on
    static int dob = 0;//state on
    static int board = 1;//state off
    static int gt = 200, gtP = 100;
    static int minGT = 72, minGTP = 33;
    static int headerAlter = 1;
    static double xlength = 0.73, yheight = 0.83, aorigin = 36, borigin = 36;
    double thirdL = 35, secondL = 44, firstL = 59, disL = 75;
    int jacobVictory = 0;

    public static String stdToText(String a) {
        String stdWord = "";
        if (a.equals("I")) {
            stdWord = "First";
        }
        if (a.equals("II")) {
            stdWord = "Second";
        }
        if (a.equals("III")) {
            stdWord = "Third";
        }
        if (a.equals("IV")) {
            stdWord = "Fourth";
        }
        if (a.equals("V")) {
            stdWord = "Fifth";
        }
        if (a.equals("VI")) {
            stdWord = "Sixth";
        }
        if (a.equals("VII")) {
            stdWord = "Seventh";
        }
        if (a.equals("VIII")) {
            stdWord = "Eight";
        }
        if (a.equals("IX")) {
            stdWord = "Ninth";
        }
        if (a.equals("X")) {
            stdWord = "Tenth";
        }

        return (stdWord);
    }

    public static void reIntitialize() {
        tempRowAssign = 0;
        tempRowAssignForOpSub = 0;
        tempColAssign = 0;
        variableToTellCompOP = 0;
    }

    public static String cgpa(double value) {
        String val = "ERROR";
        if (value > 9 && value <= 10) {
            val = "A1";
        }
        if (value > 8 && value <= 9) {
            val = "A2";
        }
        if (value > 7 && value <= 8) {
            val = "B1";
        }
        if (value > 6 && value <= 7) {
            val = "B2";
        }
        if (value > 5 && value <= 6) {
            val = "C1";
        }
        if (value > 4 && value <= 5) {
            val = "C2";
        }
        if (value > 3 && value <= 4) {
            val = "D";
        }
        if (value > 2 && value <= 3) {
            val = "E1";
        }
        if (value <= 2) {
            val = "E2";
        }
        return (val);
    }

    public static void studentShuffler(int i) {
        reIntitialize();
        System.out.println("studentShuffler EXECUTED !!!" + i);

        printSimpRepE.setFormat(i);

        enterMarks obj = new enterMarks();
        tab = (DefaultTableModel) obj.table.getModel();
        obj.sessionCB.setSelectedItem(sessionCB.getSelectedItem());//added later
        obj.stdCB.setSelectedIndex(0);
        obj.stdCB.setSelectedItem(stdCB.getSelectedItem());

        System.out.println("selected sr=" + srL[i]);
        obj.nameListCB.setSelectedIndex(srL[i]);
        curSr = srL[i];
        //obj.setVisible(true);
        if (jCheckBox1.isSelected() == false && i == 0) {
            noOfOpSub = 0;
            System.out.println("NO OPTIONAL !!!");
        } else {

            num = listLB2.getSelectedIndices();
            int lim = num.length;
            noOfOpSub = lim;

            System.out.println("ACTUAL SUB-OP SUB" + (actualSub - noOfOpSub) + "--" + noOfOpSub);
            opSub = new String[lim];
            for (int il = 0; il < lim; il++) {
                opSub[il] = listLB2.getSelectedValue();
                for (int j = 2; j < tab.getColumnCount(); j++) {
                    tab.setValueAt("-100", num[il], j);
                }
            }
        }
        obj.table.setModel(tab);
//obj.setVisible(true);
        compSub = new String[tab.getRowCount() - noOfOpSub];
        grandT(tab);
        i = 0;
        System.out.println("SHUFFLER FINISHED !!!");
    }

    public static void grandT(DefaultTableModel t) {
        System.out.println("grandT EXECUTED !!!");
        int examLimit = 5;//upto annual
        int countRow = t.getRowCount();

        colWiseSum = new int[examLimit];//since there are 5 exams
        colWiseSumPartial = new int[examLimit];
        rowWiseSum = new int[countRow - noOfOpSub];
        rowWiseSumPartial = new int[countRow - noOfOpSub];

        int flagForRowWiseSum = 0, markerForRowWiseSum = 0;

        reIntitialize();
        //calculating grand total for each subject that is row wise
        for (int i = 0; i < countRow; i++) {
            System.out.println("I JACOB IS" + i + "---count row=" + countRow);
            int sumInRow = 0, sumInRowPartial = 0;
            for (int j = 2; j <= (examLimit + 1); j++) {
//if(j==4){continue;}        //since third term is not wanted        
                String val = t.getValueAt(i, j).toString();
                int value = Integer.parseInt(val);
                if (value == (-100)) {
                    opSub[tempRowAssignForOpSub] = (t.getValueAt(i, 1)).toString();//adding name of opSub to array
                    variableToTellCompOP = 1;
                }
                if (value != (-100)) {
                    compSub[tempRowAssign] = (t.getValueAt(i, 1)).toString();//adding name of compSub to array

                    if (value != (-1) && value != (-2)) {
                        sumInRow += value;
                        sumInRowPartial += value;
                        if (j == 6) {
                            sumInRowPartial -= value;
                        }
                        System.out.println("i---j----examlimit+1-------value" + i + "------" + j + "------" + (examLimit + 1) + "-----" + value);
                    }

                }
            }
            // System.out.println("I NOW IS "+i+"---count row -"+compSub.length+"-------tempRowAssign="+tempRowAssign);
            // System.out.println("COMP SUB ASSIGN : "+compSub[tempRowAssign]);

            try {
                rowWiseSum[tempRowAssign] = sumInRow;//row wise that is subject wise sum , it is enclosed in try block because if tempRowAssign go out of bounds there will be now error
                rowWiseSumPartial[tempRowAssign] = sumInRowPartial;
            } catch (Exception e) {
            }

            //System.out.println("sum in row : "+rowWiseSum[tempRowAssign]);
            //following code is to increment the value of current row for optional subject or the comp. subject so that next time the new subject is entered into another place
            if (variableToTellCompOP == 0) {
                tempRowAssign++;
            } else {
                tempRowAssignForOpSub++;
                variableToTellCompOP = 0;
            }
        }

        //just for checking purpose whether the above code is enterng values as intended by me into respective arrays
        //System.out.println("OP SUBS ARE "+opSub[0]+"----"+opSub[1]);
        //System.out.println("COMP SUBS ARE "+compSub[0]+"----"+compSub[5]+"----"+compSub[6]);
        //System.out.println("SUBJECT WISE SUM "+rowWiseSum[0]+"----"+rowWiseSum[examLimit]);
        //calculating grand total for each term
        variableToTellCompOP = 0;//just to reset it , no use
        for (int i = 2; i <= (examLimit + 1); i++) {
            int sumInCol = 0, sumInColPartial = 0;
            for (int j = 0; j < countRow; j++) {
                String val = t.getValueAt(j, i).toString();
                int value = Integer.parseInt(val);

                if (value >= 0) {
                    sumInCol += value;
                }
            }
            colWiseSum[i - 2] = sumInCol;

        }

        System.out.println("grandT FINISHED !!!" + colWiseSum[3]);

    }

    /**
     * Creates new form reportCard
     */
    public reportCardE() {
        initComponents();
    }

    public class priPan extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            if (swi == 0) {
                int cellwidth = 25;
                int RemebmerOpHorizontalPos = -50;

                super.paintComponent(g);
                System.out.println("JACOB TO DRAW");

                Graphics2D gd = (Graphics2D) g;

                //length & breadth of drawing area is 1048x337       
                gd.drawRect(2, 2, 1048/*width of parent rec cons*/, 337/*width of parent rec vari*/);
                gd.drawLine(2, 52/*initial ypos*/, 1048, 52);
                gd.setColor(Color.lightGray);
                gd.fillRect(2, 2, 1048, 50);
                gd.setColor(Color.black);

                //horizontal line
                int i = 0;
                gd.drawLine(230, 23, 1048, 23); //drawing horizontal sub-bar   
                int sub = actualSub - noOfOpSub;//no. of actual sub
                int ypos = 52;//52 here is the ypos of the above line
                ypos += cellwidth;

                while (i <= sub) {
                    gd.drawLine(2, ypos, 1048, ypos);
                    ypos += cellwidth;
                    i++;
                }

                //marking vert lines-----------we have almost 1048 x pixels in which i have set first colum(subject) of breadth 230 & next 3 columns of breadth200 & the last one the remaining is of 218 pixels
                i = 1;
                int xpos = 230;

                if (noOfOpSub == 0) {
                    ypos -= cellwidth;
                    while (i <= 4) {
                        gd.drawLine(xpos, 2, xpos, ypos);
                        xpos += 100;
                        i++;
                    }
                } else {
                    gd.drawLine(xpos, 2, xpos, ypos);

                    while (i <= 4) {
                        gd.drawLine(xpos, 52, xpos, ypos - cellwidth);
                        xpos += 100;
                        i++;
                    }
                    gd.drawLine(xpos, 2, xpos, ypos - cellwidth);
                    xpos += 100;
                    gd.drawLine(xpos + 2, 2, xpos + 2, ypos - cellwidth);

                }

                ypos -= (2 * cellwidth); //since y was incremented at the end of loop
                gd.setPaint(new Color(153, 255, 204, 100));

                //gd.setColor(Color.yellow);
                if (noOfOpSub == 0) {

                    gd.fillRect(2, ypos + 26, 1048, cellwidth);
                } else {
                    gd.fillRect(2, ypos + 2, 1048, cellwidth);
                    gd.setPaint(Color.DARK_GRAY);
                    gd.fillRect(880, 2, 5, ypos + cellwidth);//To draw dark marks between final & grand total
                }

                gd.setColor(Color.RED);
                ypos += cellwidth;
                gd.drawRect(2, ypos += 2, 1048, 3);
                gd.fillRect(2, ypos, 1048, 3);

                //section for optional subjects
                gd.setColor(Color.black);
                i = 0;
                sub = noOfOpSub;//no. of optional sub
                ypos += 3;
                int markForVertLine = ypos;
                while (i <= sub) {
                    gd.drawLine(2, ypos, 1048, ypos);
                    ypos += cellwidth;
                    i++;
                }

                //marking vert lines
                ypos -= cellwidth;//since y was incremented at the end of loop
                i = 1;
                xpos = 230;
                while (i < 6) {
                    gd.drawLine(xpos, markForVertLine, xpos, ypos);
                    xpos += 100;
                    i++;
                }
                gd.drawLine(xpos + 2, markForVertLine, xpos + 2, ypos);
                gd.drawLine(882, markForVertLine, 882, ypos);

                //entering heading
                gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                Font f1 = new Font("Adobe Hebrew", Font.BOLD, 18);
                gd.setFont(f1);
                int initialRowPos = 60;
                int verAlign = 20;
                gd.drawString("SUBJECTS", initialRowPos, verAlign);
                initialRowPos += 220;
                gd.drawString("UPTO HALF YEARLY EXAMINATION", initialRowPos, verAlign);
                initialRowPos += 270;
                gd.drawString("ANNUAL EXAM", initialRowPos += 186, verAlign);

                gd.drawString("GRAND TOTAL", initialRowPos += 162, verAlign);
                initialRowPos = 60;
                verAlign += 18;
                gd.drawString("I Term", initialRowPos += 195, verAlign);
                gd.drawString("II Term", initialRowPos += 100, verAlign);
                gd.drawString("Int. Ass.", initialRowPos += 90, verAlign);
                gd.drawString("Half Yearly", initialRowPos += 90, verAlign);
                gd.drawString("Total", initialRowPos += 120, verAlign);

                Font f2 = new Font("Adobe Hebrew", Font.PLAIN, 12);
                gd.setFont(f2);
                verAlign += 11;
                initialRowPos = 232;
                gd.drawString("Min.=04 Max.=10", initialRowPos, verAlign);
                gd.drawString("Min.=04 Max.=10", initialRowPos += 100, verAlign);
                gd.drawString("Min.=04 Max.=10", initialRowPos += 100, verAlign);
                gd.drawString("Min.=24 Max.=70", initialRowPos += 100, verAlign);
                gd.drawString("Min.=33 Max.=100", initialRowPos += 100, verAlign);
                gd.drawString("Min.=33              Max.=100", initialRowPos += 105, verAlign);
                gd.drawString("Min.=72               Max.=200", initialRowPos += 155, verAlign);

                Font f3 = new Font("Adobe Hebrew", Font.BOLD, 18);
                gd.setFont(f3);

                //entering Comp. Subjects & Optional Subjects name from compSub & opSub arrays
                verAlign -= 3;
                int initialverAlign = verAlign;
                try {
                    gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    i = 0;
                    while (i < compSub.length) {
                        gd.drawString(compSub[i], 10, verAlign += cellwidth);
                        i++;
                    }
                    Font f0 = new Font("Adobe Hebrew", Font.ITALIC, 18);
                    gd.setFont(f0);
                    gd.drawString("GRAND TOTAL", 10, verAlign += cellwidth);
                    gd.setFont(f3);

                    verAlign += 5;
                    RemebmerOpHorizontalPos = verAlign;
                    i = 0;
                    while (i < opSub.length) {
                        gd.drawString(opSub[i], 10, verAlign += cellwidth);
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println("ERROR LAOCATION-5----" + e);
                }

                //BLOCK1 displaying subject wise grand total in the grand total column       BLOCK2 calculating percentage
                try {
                    int countSupplem = 0;
                    String status = "";//it will affix [f] to subject wise grand total if total comes to be less than 30 or 33(board)
                    gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    Font f4 = new Font("Adobe Hebrew", Font.BOLD, 20);
                    gd.setFont(f4);
                    verAlign = initialverAlign;
                    i = 0;
                    int sum = 0;
                    passL.setText("PASS");//default is pass

                    nextClassStat.setText("PROMOTED TO CLASS : " + stdCB.getItemAt(stdCB.getSelectedIndex() + 1) + " (" + stdToText(stdCB.getItemAt(stdCB.getSelectedIndex() + 1)) + ")");
                    String temp = stdToText(stdCB.getItemAt(stdCB.getSelectedIndex() + 1));//class names which are not numbers need not to be written in words again
                    if (temp.equals("")) {
                        nextClassStat.setText("PROMOTED TO CLASS : " + stdCB.getItemAt(stdCB.getSelectedIndex() + 1));
                    }

                    while (i < rowWiseSum.length) {

                        if (rowWiseSum[i] < minGT) {
                            status = "[F]";
                            countSupplem++;

                        }
                        sum += rowWiseSum[i];
                        gd.drawString(rowWiseSum[i] + status, 940, verAlign += cellwidth);
                        status = "";
                        i++;
                    }
                    gd.drawString(sum + "", 940, verAlign += cellwidth);//entering total sum of vertical grand total column
                    if (countSupplem > 0 && countSupplem <= 2) {
                        passL.setText("SUPP.");
                        nextClassStat.setText("SUPPLEMENTARY IN " + countSupplem + " SUBJECT(S).");
                    }
                    if (countSupplem > 2) {
                        passL.setText("FAIL");
                        nextClassStat.setText("---FAIL---");
                    }

                    try {
                        int sumPartial = 0;
                        int jacob = 0;
                        verAlign = initialverAlign;
                        System.out.println(rowWiseSumPartial.length);
                        while (jacob < rowWiseSumPartial.length) {
                            System.out.println(rowWiseSumPartial.length);
                            if (rowWiseSumPartial[jacob] < minGTP) {
                                status = "[F]";
                            }
                            sumPartial += rowWiseSumPartial[jacob];
                            gd.drawString(rowWiseSumPartial[jacob] + status, 670, verAlign += cellwidth);
                            status = "";
                            jacob++;
                        }
                        gd.drawString(sumPartial + "", 670, verAlign += cellwidth);

                    } catch (Exception e) {
                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + e);
                    }

                    //getting percentage
                    double per = (double) (sum * 100) / (double) (rowWiseSum.length * 200);
                    String percentage = per + "";
                    try {
                        percentage = percentage.substring(0, 5);
                    } catch (Exception e) { //it means that percentage is not so long , so let it be printed without any change      
                    }
                    percentL.setText(percentage + " %");
                    if (passL.getText().equals("FAIL") == false) {
                        divL.setText("PASS");
                        rmkL.setText("Improvement needed! Work hard.");
                        if (per > thirdL) {
                            divL.setText("THIRD");
                            rmkL.setText("Needs to work hard !");
                        }
                        if (per > secondL) {
                            divL.setText("SECOND");
                            rmkL.setText("Fair Performance ! Efforts Needed.");
                        }
                        if (per > firstL) {
                            divL.setText("FIRST");
                            rmkL.setText("Nice Efforts! Can do much better.");
                        }
                        if (per >= disL) {
                            divL.setText("FIRST");
                            rmkL.setText("Outstanding ! Keep it up.");
                        }

                    } else {
                        divL.setText("");
                        rmkL.setText("");
                    }
                } catch (Exception e) {
                    System.out.println("ERROR LOCATION---10" + "----" + e);
                }

                //entering compulsory marks
                try {
                    int rowNo = tab.getRowCount();
                    verAlign = initialverAlign;
                    xpos = 275;
                    String val = "";
                    for (i = 2; i <= 6; i++) {

                        int j = 0;
                        for (j = 0; j < rowNo; j++) {
                            val = tab.getValueAt(j, i).toString();
                            if (val.equals("-100")) {
                                continue;
                            }
                            if (val.equals("-1")) {
                                val = "[Ab]";
                            }
                            if (val.equals("-2")) {
                                val = "[M]";
                            }
                            try {
                                double value = Double.parseDouble(val);
                                if (value < 4 && i < 5) {
                                    val += " [F]";
                                }
                                if (value < 24 && i == 5) {
                                    val += " [F]";

                                }
                                if (value < 33 && i == 6) {
                                    val += " [F]";
                                }
                            } catch (Exception e) {
                            }

                            gd.drawString(val, xpos, verAlign += cellwidth);
                        }

                        //entering col wise sum
                        try {
                            gd.drawString(colWiseSum[i - 2] + "", xpos, verAlign += cellwidth);

                        } catch (Exception e) {
                        }
                        verAlign = initialverAlign;

                        //for vertical alignment of marks
                        switch (i) {
                            case 2:
                                xpos += 98;
                                break;
                            case 3:
                                xpos += 100;
                                break;
                            case 4:
                                xpos += 100;
                                break;
                            case 5:
                                xpos += 220;
                                break;
                        }

                    }
                } catch (Exception e) {
                    System.out.println("sector 15" + e);
                }

                //entering grades of opt. sub
                try {
                    i = 0;
                    enterMarks ob = new enterMarks();
                    ob.sessionCB.setSelectedItem(sessionCB.getSelectedItem());
                    ob.stdCB.setSelectedItem(stdCB.getSelectedItem());
                    //srL = listLB1.getSelectedIndices();
                    ob.srCB.setSelectedIndex(curSr);
                    tabl = (DefaultTableModel) ob.table.getModel();

                    opSubTotal = new int[opSub.length];

                    xpos = 275;
                    RemebmerOpHorizontalPos += cellwidth;
                    saveForOpSubTotal = RemebmerOpHorizontalPos;

                    System.out.println("?????????????????????op length = " + opSub.length);
                    for (i = 0; i < opSub.length; i++) {

                        String val = "";
                        for (int j = 2; j <= 6; j++) {
                            val = tabl.getValueAt(num[i], j).toString();

                            if (val.equals("-1") || val.equals("-2")) {
                                if (val.equals("-1")) {
                                    val = "[Ab]";
                                }
                                if (val.equals("-2")) {
                                    val = "[M]";
                                }
                                gd.drawString(val, xpos, RemebmerOpHorizontalPos);
                                //for vertical alignment of marks
                                switch (j) {
                                    case 2:
                                        xpos += 98;
                                        break;
                                    case 3:
                                        xpos += 100;
                                        break;
                                    case 4:
                                        xpos += 100;
                                        break;
                                    case 5:
                                        xpos += 220;
                                        break;
                                }
                                System.out.println("FOUND TAGS!!!");
                            } else {
                                try {

                                    double value = Double.parseDouble(val);
                                    System.out.println("FOUND VALUES !!!");
                                    if (j == 5) {
                                        System.out.println("Enter Half Yearly Assign");
                                        //grade for HALF YEARLY out of 70

                                        value /= 7;//converting to cgpa system     
                                        val = cgpa(value);

                                    }
                                    if (j == 6) {
                                        value /= 10;
                                        val = cgpa(value);
                                    }

                                    if (j < 5) {
                                        System.out.println("Enter Terms Assign");
                                        val = cgpa(value);
                                    }
                                    gd.drawString(val, xpos, RemebmerOpHorizontalPos);
                                    switch (j) {
                                        case 2:
                                            xpos += 98;
                                            break;
                                        case 3:
                                            xpos += 100;
                                            break;
                                        case 4:
                                            xpos += 100;
                                            break;
                                        case 5:
                                            xpos += 220;
                                            break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("++++++++++++" + e);
                                }

                            }
                        }

                        RemebmerOpHorizontalPos += cellwidth;
                        xpos = 275;
                    }

                    System.out.println("^^^^^^^^^^^^JAACOB^^^^^^^^^^^^^^^^^^^^^^^");
                } catch (Exception e) {
                    System.out.println("^^^^^^^^^^^^^^not not not^^^^^^^^^^^^^^^^^^^^^" + e);
                }

                //entering grades for  grand total 
                try {
                    saveForOpSubTotal -= cellwidth;

                    double value = 0;

                    for (i = 0; i < opSub.length; i++) {
                        System.out.println("Here it is");
                        double sumPartial = 0, sum = 0;
                        String val;
                        for (int j = 2; j <= 6; j++) {
                            val = tabl.getValueAt(num[i], j).toString();
                            System.out.println("***************" + val);
                            if (val.equals("-1") || val.equals("-2") || val.equals("-100")) {//do nothing
                            } else {
                                try {
                                    value = Double.parseDouble(val);
                                    if (j != 6) {
                                        sumPartial += value;
                                    }
                                    sum += value;

                                } catch (Exception e) {
                                }
                            }
                        }

                        val = "ERROR";
                        sumPartial /= 10;//converting to cgpa system  

                        val = cgpa(sumPartial);

                        gd.drawString(val + "", 670, saveForOpSubTotal += cellwidth);

                        sum /= 20;
                        val = cgpa(sum);
                        gd.drawString(val + "", 930, saveForOpSubTotal);
                    }

                } catch (Exception e) {
                }

            } else {
                swi = 0;
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField7 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        sessTF = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        paintPanel = new priPan();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        fNameTF = new javax.swing.JTextField();
        dobTF = new javax.swing.JTextField();
        stdTF = new javax.swing.JTextField();
        srTF = new javax.swing.JTextField();
        medTF = new javax.swing.JTextField();
        sNameTF = new javax.swing.JTextField();
        mNameTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        passL = new javax.swing.JLabel();
        percentL = new javax.swing.JLabel();
        divL = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        rmkL = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        stIm = new javax.swing.JLabel();
        nextClassStat = new javax.swing.JLabel();
        stdCB = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listLB = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        listLB1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        showTA = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listLB2 = new javax.swing.JList<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        sessionCB = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jCheckBox6 = new javax.swing.JCheckBox();

        jTextField7.setFont(new java.awt.Font("Californian FB", 0, 14)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("Contact : 0141 - 2341265");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tech-Res Marksheet Print");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        mainPanel.setBackground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setFont(new java.awt.Font("Trajan", 1, 28)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("m . k . public sr. sec. school");
        jTextField1.setBorder(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mone.jpg"))); // NOI18N

        jTextField2.setFont(new java.awt.Font("Californian FB", 1, 17)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("237-239 , 21-South Colony , Niwaru Road , Jhotwara , Jaipur (Rajasthan)");
        jTextField2.setBorder(null);

        jTextField3.setFont(new java.awt.Font("Californian FB", 1, 14)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("Contact : 0141 - 2341265");
        jTextField3.setBorder(null);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        sessTF.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        sessTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sessTF.setText("Annual Examination : 2016-17");
        sessTF.setBorder(null);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        paintPanel.setBackground(new java.awt.Color(255, 255, 255));
        paintPanel.setPreferredSize(new java.awt.Dimension(1042, 341));

        javax.swing.GroupLayout paintPanelLayout = new javax.swing.GroupLayout(paintPanel);
        paintPanel.setLayout(paintPanelLayout);
        paintPanelLayout.setHorizontalGroup(
            paintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1035, Short.MAX_VALUE)
        );
        paintPanelLayout.setVerticalGroup(
            paintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel2.setText("Standard :");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel6.setText("Father's Name :");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel7.setText("Mother's Name :");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel3.setText("Reg. No. :");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel4.setText("Student's Name :");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel5.setText("Date of Birth :");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Roll no. :");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Medium :");

        fNameTF.setBackground(new java.awt.Color(204, 204, 204));
        fNameTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        fNameTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fNameTF.setBorder(null);

        dobTF.setBackground(new java.awt.Color(204, 204, 204));
        dobTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        dobTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        dobTF.setText("14 - August - 1995");
        dobTF.setBorder(null);
        dobTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobTFActionPerformed(evt);
            }
        });

        stdTF.setBackground(new java.awt.Color(204, 204, 204));
        stdTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        stdTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        stdTF.setText("XII COMMERCE -A ");
        stdTF.setBorder(null);

        srTF.setBackground(new java.awt.Color(204, 204, 204));
        srTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        srTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        srTF.setText("407/16");
        srTF.setBorder(null);

        medTF.setBackground(new java.awt.Color(204, 204, 204));
        medTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        medTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        medTF.setText("English");
        medTF.setBorder(null);
        medTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medTFActionPerformed(evt);
            }
        });

        sNameTF.setBackground(new java.awt.Color(204, 204, 204));
        sNameTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        sNameTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        sNameTF.setText("David Jacob Mahto");
        sNameTF.setBorder(null);
        sNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sNameTFActionPerformed(evt);
            }
        });

        mNameTF.setBackground(new java.awt.Color(204, 204, 204));
        mNameTF.setFont(new java.awt.Font("Trebuchet MS", 2, 15)); // NOI18N
        mNameTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        mNameTF.setBorder(null);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(srTF, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mNameTF))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(medTF, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(fNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stdTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(sNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel15)
                    .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Powered by The Math Engine- JacobMahto148@gmail.com");

        jPanel8.setBackground(new java.awt.Color(153, 204, 255));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLabel13.setText("<html><p align=center>[Ab] - Absent , [M] - Medical , [F] : Fail<br>All of the given tags affect marks whereas CGPA system (opt. subjects) doesn't affect overall result.");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("RESULT : ");

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("DIVISON :");

        jLabel14.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("ATTENDENCE :");

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("REMARKS :");

        jLabel17.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("OVERALL PERCENTAGE :");

        passL.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        passL.setForeground(new java.awt.Color(51, 51, 51));
        passL.setText("PASS");

        percentL.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        percentL.setForeground(new java.awt.Color(51, 51, 51));
        percentL.setText("99.99 %");

        divL.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        divL.setForeground(new java.awt.Color(51, 51, 51));
        divL.setText("FIRST");

        jLabel21.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));

        jLabel22.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));

        rmkL.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        rmkL.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(percentL, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(divL, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rmkL, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rmkL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel22)))
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel17)
                            .addComponent(passL)
                            .addComponent(percentL)
                            .addComponent(divL))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel24.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("PRINCIPAL");

        jLabel25.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("CLASS TEACHER");

        jLabel26.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("PARENT");

        jLabel27.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/prsign.jpg"))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        stIm.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nextClassStat.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        nextClassStat.setForeground(new java.awt.Color(51, 51, 51));
        nextClassStat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nextClassStat.setText("PROMOTED TO CLASS : ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(88, 88, 88)
                                        .addComponent(nextClassStat, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sessTF, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(188, 188, 188))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField1)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stIm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stIm, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sessTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nextClassStat, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        stdCB.setFont(new java.awt.Font("Adobe Hebrew", 0, 18)); // NOI18N
        stdCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PG", "NUR", "LKG", "UKG", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI Arts-A", "XI Arts-B", "XI Arts-C", "XI Commerce-A", "XI Commerce-B", "XI Commerce-C", "XII Arts-A", "XII Arts-B", "XII Arts-C", "XII Commerce-A", "XII Commerce-B", "XII Commerce-C" }));
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

        showTA.setBackground(new java.awt.Color(255, 204, 204));
        showTA.setColumns(20);
        showTA.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        showTA.setLineWrap(true);
        showTA.setRows(5);
        jScrollPane2.setViewportView(showTA);

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listLB2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listLB2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listLB2);

        jCheckBox1.setText("Grade Subjects");

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("PREVIEW");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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

        jButton4.setText("HIDE INFO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Hide SR");
        jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox2StateChanged(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("Hide DOB");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox5.setText("Alter Session Header");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jButton5.setText("Hide Div");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jCheckBox6.setText("Hide \"Signature\"");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(58, 58, 58))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stdCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(5, 5, 5)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(sessionCB, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCheckBox6))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jCheckBox1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stdCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sessionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jCheckBox3))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stdCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdCBActionPerformed
        String std = (String) stdCB.getSelectedItem();

        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS rsl;";
            String query2 = "USE rsl;";
            String query3 = "SELECT sr,name FROM studentInfo WHERE class='" + std + "' ;";

            stmt.execute(query1);
            stmt.execute(query2);

            ResultSet rs = stmt.executeQuery(query3);
            rs.last();
            String[] nm = new String[rs.getRow()];
            String[] sr = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                nm[i] = rs.getString("Name");
                sr[i] = rs.getString("sr");
                i++;
            }

            listLB.setListData(nm);
            listLB1.setListData(sr);
            con.close();

        } catch (Exception E) {

        }

        enterMarks obj = new enterMarks();
        obj.stdCB.setSelectedIndex(stdCB.getSelectedIndex());
        tab = (DefaultTableModel) obj.table.getModel();

        int row = tab.getRowCount();
        String[] subList = new String[row];
        actualSub = subList.length;//getting number of subjects for graphics line drawing
        for (int i = 0; i < row; i++) {

            subList[i] = tab.getValueAt(i, 1).toString();
        }
        listLB2.setListData(subList);
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List<String> selectedValuesList = listLB.getSelectedValuesList();
        System.out.println(selectedValuesList);
        showTA.setText("");
        showTA.append(selectedValuesList + "");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listLB2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listLB2MouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_listLB2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();

            String query0 = "CREATE DATABASE IF NOT EXISTS rsl;";
            stmt.execute(query0);
            String query1 = "USE rsl;";
            stmt.execute(query1);
            String query2 = "Select * from scale where code='finalrep';";
            ResultSet rs = stmt.executeQuery(query2);
            rs.next();
            xlength = rs.getDouble("x");
            yheight = rs.getDouble("y");
            aorigin = rs.getDouble("a");
            borigin = rs.getDouble("b");
            rs.close();
        } catch (Exception E) {
            //JOptionPane.showMessageDialog(null,"Problem Sychronising scale data !");                
        }
        try {
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "lion");
            Statement stmt = con.createStatement();

            String query0 = "CREATE DATABASE IF NOT EXISTS rsl;";
            stmt.execute(query0);
            String query1 = "USE rsl;";
            stmt.execute(query1);
            String query2 = "Select * from scale where code='divscale';";
            ResultSet rs = stmt.executeQuery(query2);
            rs.next();
            thirdL = rs.getDouble("x");
            secondL = rs.getDouble("y");
            firstL = rs.getDouble("a");
            disL = rs.getDouble("b");

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Problem Sychronising scale data !");
        }

        srL = listLB1.getSelectedIndices();
        //  int countSelectedStud= srL.length;  
        // countSelectedStud=1;

        MultPHandlerE.handler(0, srL.length, 0);

        //JOptionPane.showMessageDialog(null, actualSub+"-"+optSub);
        // JOptionPane.showMessageDialog(null, countSelectedStud);
        System.out.println("JACOB IS NOW");

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jacobVictory = 1;
        srL = listLB1.getSelectedIndices();
        System.out.println("PREVIEW");

        printSimpRepE.jac();

        reportCardE.studentShuffler(0);
        paintPanel.repaint();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dobTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dobTFActionPerformed

    private void sNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sNameTFActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void sessionCBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sessionCBFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_sessionCBFocusLost

    private void sessionCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessionCBActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_sessionCBActionPerformed

    private void medTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medTFActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jLabel1.setIcon(null);
        jLabel27.setIcon(null);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (jCheckBox2.isSelected()) {
            sr = 1;
        } else {
            sr = 0;

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox2StateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2StateChanged

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        if (jCheckBox3.isSelected()) {
            dob = 1;
        } else {
            dob = 0;

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        if (jCheckBox5.isSelected()) {
            headerAlter = 0;//on
        } else {
            headerAlter = 1;//off
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        divL.setForeground(jPanel4.getBackground());      // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        jLabel27.setIcon(null);
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

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
            java.util.logging.Logger.getLogger(reportCardE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportCardE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportCardE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportCardE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reportCardE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel divL;
    public static javax.swing.JTextField dobTF;
    public static javax.swing.JTextField fNameTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    public static javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField7;
    public static javax.swing.JList<String> listLB;
    public static javax.swing.JList<String> listLB1;
    public static javax.swing.JList<String> listLB2;
    public static javax.swing.JTextField mNameTF;
    public static javax.swing.JPanel mainPanel;
    public static javax.swing.JTextField medTF;
    private javax.swing.JLabel nextClassStat;
    public static javax.swing.JPanel paintPanel;
    public static javax.swing.JLabel passL;
    public static javax.swing.JLabel percentL;
    private javax.swing.JLabel rmkL;
    public static javax.swing.JTextField sNameTF;
    public static javax.swing.JTextField sessTF;
    public static javax.swing.JComboBox<String> sessionCB;
    private javax.swing.JTextArea showTA;
    public static javax.swing.JTextField srTF;
    public static javax.swing.JLabel stIm;
    public static javax.swing.JComboBox<String> stdCB;
    public static javax.swing.JTextField stdTF;
    // End of variables declaration//GEN-END:variables
}

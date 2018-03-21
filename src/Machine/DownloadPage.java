package Machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class DownloadPage {
static String timeArray[]=new String[500];
static String srArray[]=new String[500];
static String timeDate[][]=new String[500][7];//-----time date like %August 15, 2017 at 11:59PM%-----clock time like %11:59PM%-------%equivalent international time like %2359%------hour late---minute late----status (late or not late)-----sr
static int entries=0;
static double hh=07,mm=30;
static String hhmm="0730";
public static void separate(String timeVal,int pos){
   
String clock=timeVal.substring(timeVal.indexOf(":")-2, timeVal.indexOf(":")+5);
String day=timeVal.substring(0, timeVal.indexOf(":")-6);
timeDate[pos][0]=day;
timeDate[pos][1]=clock;
timeDate[pos][2]=timeMath(clock);
timeCompare(hh,mm,hhmm, timeDate[pos][2], pos);
    
}

public static String timeMath(String time){
if(time.indexOf("AM")>=0){
time=time.substring(0,2)+time.substring(3,5);
}
else {
time=(Integer.parseInt(time.substring(0,2))+12)+time.substring(3,5);
}
return time;
}

public static void timeCompare(double hh,double mm,String hhmm,String timeMath,int pos){
    System.out.println(timeMath+"^^^^"+hhmm);
    if(Integer.parseInt(timeMath)>Integer.parseInt(hhmm)){
      
    Double late=(Double.parseDouble(timeMath.substring(0,2))+Double.parseDouble(timeMath.substring(2,4))/60)-(hh+mm/60);    
    int hoursLate=late.intValue();    
    Double minuteLateD=((late-late.intValue())*60);
    int minuteLate=minuteLateD.intValue();
    timeDate[pos][3]=hoursLate+"";
    timeDate[pos][4]=minuteLate+"";
    timeDate[pos][5]="Late";    
    }
    else {
    
    Double late=(Double.parseDouble(timeMath.substring(0,2))+Double.parseDouble(timeMath.substring(2,4))/60)-(hh+mm/60);    
    int hoursLate=-1*late.intValue();    
    Double minuteLateD=((late-late.intValue())*-60);
    int minuteLate=minuteLateD.intValue();
    timeDate[pos][3]=hoursLate+"";
    timeDate[pos][4]=minuteLate+"";
    timeDate[pos][5]="Before";  
    }
    

}

public static void showReport(){
for(int i=0;i<=entries;i++){
System.out.println("-------------");
for(int j=0;j<7;j++){
System.out.print(timeDate[i][j]+"\t");
}
       
}
}



public static void internetRead(URL url){
    try{
    URLConnection con = url.openConnection();
    InputStream is =con.getInputStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
int i=0;
        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            String proto=line;
            int timeEnd=proto.indexOf("\",");
            try{timeArray[i]=proto.substring(1,timeEnd);
            proto=proto.substring(timeEnd+2);
            System.out.println("timeend"+timeEnd+"PROTONO : "+proto);
            int srEnd=proto.indexOf(",");
            System.out.println("srend"+srEnd);
                separate(timeArray[i], i);//call to function to separate date and clock time
                System.out.println("HERE"+srEnd);
                timeDate[i][6]=proto.substring(0,srEnd);//get sr
                entries=i;
                i++;
            }
            catch(Exception e){
            System.out.println("Exep----"+line);
            System.out.println(e);
            } 
                    }
        
        showReport();
    
    }
    catch(Exception e){
    System.out.println(e);
    }

    
    
}




    public static void main(String[] args) throws IOException {

        // Make a URL to the web page
        //URL url = new URL("https://docs.google.com/spreadsheets/d/1xcXkhaJp1AmuieDsMkpzogXoowxsAvR_xF5scpDegKg/pub?output=csv");
        URL url = new URL("https://docs.google.com/spreadsheets/d/e/2PACX-1vQPHbYzSvaVBBAyZKuVA81rNvJrEKIKcIPTySfXFeEvdDWe_ZbuyUNAG4eagVO0TlE_MA1X3Zo1sH2c/pub?output=csv");
        internetRead(url);
        // Get the input streams through URL Connection
        

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.


        
    }
}
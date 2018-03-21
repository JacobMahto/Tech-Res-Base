package sms;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 *
 * @author Jacob Mahto
 * HTTP CLIENT
 * http://enterprise.sancharsms.in/sendsms.jsp?user=mkpublic&password=123456&mobiles=9782692448&sms=checking java integration&senderid=sanchr
 */
public class testXML {
    public static String strResponseBody="";
   public static String URL="http://enterprise.sancharsms.in/sendsms.jsp?";
   public static void main(String[] args) throws Exception{
   present("Jacob","late","5 pm","Tuesday","9636509907","no");
   
  
   }
   
   public static void present(String name,String status,String time,String day,String contact,String statusby){
   String message="Your ward , "+name+" entered school ("+status+" by "+statusby+" ) at "+time+" on "+day+".\nM.K. PUBLIC SR. SEC. SCHOOL.";
   //String message="hello";
   testXML s=new testXML();
   String xmlRequest="<smslist><sms><user>mkpublic</user><password>tangent</password><message>"+message+"</message><mobiles>"+contact+"</mobiles><senderid>MORALS</senderid><clientsmsid>100</clientsmsid></sms></smslist>";
   //String xmlResponse=s.sendRequest(xmlRequest);
   s.sendRequest(xmlRequest);
   }
   
    public static void fullDetail(String name,String status,String ar,String dep,String day,String contact,String statusby){
   String message="Your ward , "+name+" arrived at "+ar+"("+status+" by "+statusby+") and departed at "+dep+".\nM.K. PUBLIC SR. SEC. SCHOOL.";
   //String message="hello";
   testXML s=new testXML();
   String xmlRequest="<smslist><sms><user>mkpublic</user><password>tangent</password><message>"+message+"</message><mobiles>"+contact+"</mobiles><senderid>MORALS</senderid><clientsmsid>100</clientsmsid></sms></smslist>";
   //String xmlResponse=s.sendRequest(xmlRequest);
   s.sendRequest(xmlRequest);
   }
   
   public static void absent(String name,String status,String day,String contact){
   String message="Your ward , "+name+" is absent in the school today ( "+day+" ).\nM.K. PUBLIC SR. SEC. SCHOOL.";
   //String message="hello";
   testXML s=new testXML();
   String xmlRequest="<smslist><sms><user>mkpublic</user><password>tangent</password><message>"+message+"</message><mobiles>"+contact+"</mobiles><senderid>MORALS</senderid><clientsmsid>100</clientsmsid></sms></smslist>";
   //String xmlResponse=s.sendRequest(xmlRequest);
   s.sendRequest(xmlRequest);
   }
   
   public static void generalInfo(String name,String contact,String message,int nameInc){
      
           if(nameInc==1){
           message="Regarding,"+name.trim()+","+message.trim()+"\nM.K. PUBLIC SR. SEC. SCHOOL.";
           }
   else{
 message=message.trim()+"\nM.K. PUBLIC SR. SEC. SCHOOL.";
}
   
   //String message="hello";
   testXML s=new testXML();
   String xmlRequest="<smslist><sms><user>mkpublic</user><password>tangent</password><message>"+message+"</message><mobiles>"+contact+"</mobiles><senderid>MORALS</senderid><clientsmsid>100</clientsmsid></sms></smslist>";
   //String xmlResponse=s.sendRequest(xmlRequest);
   s.sendRequest(xmlRequest);
   }
   public String sendRequest(String strXMLRequest){
       
   strResponseBody="";
   try{System.out.println("Hello");
   PostMethod post=new PostMethod(URL);
   
   System.out.println("Hello");
   post.setRequestBody(parseStringToIS(strXMLRequest));
   if(strXMLRequest.length()<Integer.MAX_VALUE){
   post.setRequestContentLength((int)strXMLRequest.length());
   }
   else{
   post.setRequestContentLength(EntityEnclosingMethod.CONTENT_LENGTH_CHUNKED);
   }
   post.setRequestHeader("Content-type","application/xml");
   HttpClient httpclient=new HttpClient();
   int result=httpclient.executeMethod(post);
   strResponseBody = post.getResponseBodyAsString();
  System.out.println(strResponseBody);
   post.releaseConnection();
   }
   catch(Exception e){
   System.out.println(e);
   }
   return strResponseBody;
   
   }
   
   public static java.io.InputStream parseStringToIS(String xml){
   if(xml==null){
   return null;
   }
   xml=xml.trim();
   java.io.InputStream in=null;
   try{
   in=new java.io.ByteArrayInputStream(xml.getBytes("UTF-8"));
   }
   catch(Exception ex){
   System.out.println(ex);
   }
   return in;
   }
    
    
}

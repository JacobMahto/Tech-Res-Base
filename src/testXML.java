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
   public static String URL="http://enterprise.sancharsms.in/sendsms.jsp?";
   public static void main(String[] args) throws Exception{
   testXML s=new testXML();
   String xmlRequest="<smslist><sms><user>mkpublic</user><password>123456</password><message>Checking Java</message><mobiles>9636509907</mobiles><senderid>morals</senderid><clientsmsid>1</clientsmsid></sms></smslist>";
   //String xmlResponse=s.sendRequest(xmlRequest);
   s.sendRequest(xmlRequest);
  
   }
   
   public String sendRequest(String strXMLRequest){
       
   String strResponseBody="";
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

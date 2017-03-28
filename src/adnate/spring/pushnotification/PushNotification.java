package adnate.spring.pushnotification;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

public class PushNotification {
	
	
	public final static String AUTH_KEY_FCM = "AIzaSyD3wOR7wXrIMSMLmCbhD2zP-f-UQUScHEA";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	// userDeviceIdKey is the device id you will query from your database

	public static void pushFCMNotification(String token,int orderId) throws Exception{
       System.out.println("call push"+token);
	   String authKey = AUTH_KEY_FCM; // You FCM AUTH key
	   String FMCurl = API_URL_FCM; 

	   URL url = new URL(FMCurl);
	   HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	   conn.setUseCaches(false);
	   conn.setDoInput(true);
	   conn.setDoOutput(true);

	   conn.setRequestMethod("POST");
	   conn.setRequestProperty("Authorization","key="+authKey);
	   conn.setRequestProperty("Content-Type","application/json");

	 
	  
	   JSONObject json = new JSONObject();
	   
	 
	   json.put("to",token);
	   JSONObject info = new JSONObject();
	   info.put("title", "Notification from Server"); // Notification title
	   info.put("body","Generated OrderId-"+String.valueOf(orderId)); // Notification body
	   json.put("notification", info);
	   
	   JSONObject info2=new JSONObject();
	   info2.put("title","Tejas");
	   info2.put("body","Generated Order"+String.valueOf(orderId));
	   json.put("data", info2);

	   OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	   wr.write(json.toString());
	   wr.flush();
	   conn.getInputStream();
     }
}

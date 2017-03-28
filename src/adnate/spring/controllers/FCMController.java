package adnate.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import adnate.spring.pojos.Fcm;

import adnate.spring.services.FcmServices;

@Controller
@RequestMapping(value="/fcm")
public class FCMController {

	
	@Autowired
	FcmServices service;
	
	@RequestMapping(value="/addToken", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean addToken(@RequestBody Fcm fcm) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class); 
			//service.insertToken(fcm);
           Fcm fcmNew= service.getTokenByIMEI(fcm.getImei());
           System.out.println(fcmNew);
         if(fcmNew!=null)
         {
           if(fcmNew.getImei().equals(fcm.getImei()))
           {
        	   System.out.println("update call");
        	   fcmNew.setImei(fcm.getImei());
        	   fcmNew.setToken_id(fcm.getToken_id());
        	   fcmNew.setSerial_id(fcmNew.getSerial_id());
        	   service.updateToken(fcmNew);
           }
         }
           else{
        	   System.out.println("insert call");
			service.insertToken(fcm);
           }
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	
	@RequestMapping(value= "/getAlltokens", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Fcm>> getAlltokens() {
		List<Fcm> fcmList = service.getAlltokens();
		System.out.println("getContractors() called");
		if(fcmList.isEmpty()){
			System.out.println("contractor List Empty");
			return new ResponseEntity<List<Fcm>>(HttpStatus.NO_CONTENT);
		}
		for (Fcm fcm : fcmList) 
			{
				System.out.println(fcm.toString());
			}
		
		/*try {
			System.out.println("before push");
			PushNotification.pushFCMNotification("dYqb9BHKj0k:APA91bEIETIQcK_bLQCgkiW7HUKcK1iBdA0OaJSB7X4faEBXhPIzvc94unKp_67PaNPSb2fofy30ss3XOEFnVTUNGBPFpRndBTCRxQ7_NY1MxtOTRWWhcEEyDfWdC-LrHtKXtNaqeu54");
		    System.out.println("after push");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return new ResponseEntity<List<Fcm>>(fcmList, HttpStatus.OK);
	}
	
	
	
}

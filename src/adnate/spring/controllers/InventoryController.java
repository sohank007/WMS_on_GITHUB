package adnate.spring.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/*import java.util.Calendar;*/
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import adnate.spring.pojos.Contractor;
import adnate.spring.pojos.InventoryPOJO;
import adnate.spring.services.IInventoryServices;

@Controller
@RequestMapping(value="/inventory")
public class InventoryController {
	
	@Autowired
	private IInventoryServices service;
	
	@RequestMapping("/inventoryDetails")
    public ModelAndView inventoryDetails(){ 
		System.out.println("in inventoryDetails()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("inventoryDetails","comd",new InventoryPOJO()); 
	}
	
	@RequestMapping(value= "/inventoryDetailsJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InventoryPOJO>> getInventory() {
		List<InventoryPOJO> inventoryList = service.getInventory();
		System.out.println("getInventory() called");
		if(inventoryList.isEmpty()){
			System.out.println("InventoryPOJO List Empty");
			return new ResponseEntity<List<InventoryPOJO>>(HttpStatus.NO_CONTENT);
		}
		for (InventoryPOJO inventory : inventoryList) 
			{
				System.out.println(inventory.toString());
			}
		return new ResponseEntity<List<InventoryPOJO>>(inventoryList, HttpStatus.OK);
	}	
	
	// +++++++++++++++++++++++++++++++ TOP MATERIAL SHORTAGE CONTROLLER METHOD +++++++++++++++++++++++++++++  //
	
	@RequestMapping("/materialShortage")
    public ModelAndView materialShortage(){ 
		System.out.println("in materialShortage()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("materialShortage","comd",new InventoryPOJO()); 
	}
	
	@RequestMapping(value= "/materialShortageJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getMaterialShortage()
	{
		List<Object> materialShortageList = service.getMaterialShortage();
		System.out.println("getMaterialShortage() called");
		
		
		
		if(materialShortageList.isEmpty()){
			System.out.println("materialShortageList List Empty");
			return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
		}
		for (Object mtrlShortageObj : materialShortageList) 
			{
				System.out.println(mtrlShortageObj.toString());
			}
		return new ResponseEntity<List<Object>>(materialShortageList, HttpStatus.OK);
		
	}

	
	// +++++++++++++++++++++++++++++++ MATERIAL AVAILABILITY CONTROLLER METHOD +++++++++++++++++++++++++++++  //
	
		@RequestMapping("/materialAvailability")
	    public ModelAndView materialAvailability(){ 
			System.out.println("in materialAvailability()");
	         //command is a reserved request attribute name, now use <form> tag to show object data  
	        return new ModelAndView("materialAvailability","comd",new InventoryPOJO()); 
		}
		
		@RequestMapping(value= "/materialAvailabilityJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Object>> getmaterialAvailability()
		{
			List<Object> materialAvailabilityList = service.getMaterialAvailability();
			System.out.println("getMaterialAvailability() called");
			
			
			
			if(materialAvailabilityList.isEmpty()){
				System.out.println("materialAvailabilityList List Empty");
				return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
			}
			for (Object materialAvailabilityObj : materialAvailabilityList) 
				{
					System.out.println(materialAvailabilityObj.toString());
				}
			return new ResponseEntity<List<Object>>(materialAvailabilityList, HttpStatus.OK);
			
		}
		
		
		@RequestMapping(value= "/todaysOrdersJson", method = RequestMethod.POST, consumes="Application/Json")
		public ResponseEntity<List<Object>> getTodaysOrders(@RequestBody String json) throws ParseException, JSONException 
		{
			JSONObject jsonObject = new JSONObject(json);

			JSONObject myDateObj = jsonObject.getJSONObject("mydate");
			String inDate=myDateObj.getString("myNewDate");
			
			System.out.println(inDate);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		/*	Calendar c = Calendar.getInstance();
			c.setTime(df.parse(inDate));
			c.add(Calendar.DATE, 1);
			inDate = df.format(c.getTime()); */
			Date parsedDate = df.parse(inDate);
			System.out.println(parsedDate);
			java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); 
			List<Object> todaysOrderList =service.getTodaysOrders(sqlDate);
			System.out.println("getTodaysOrders() called");
					
			if(todaysOrderList.isEmpty()){
				System.out.println("todaysOrderList List Empty");
				return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
			}
			for (Object todaysOrderObj : todaysOrderList) 
				{
					System.out.println(todaysOrderObj.toString());
				}
			return new ResponseEntity<List<Object>>(todaysOrderList, HttpStatus.OK);
		}			
			
			

/*			List<Object> todaysOrderList = service.getTodaysOrders(new Date());
			System.out.println("getTodaysOrders() called");
			
			
			
			if(todaysOrderList.isEmpty()){
				System.out.println("todaysOrderList List Empty");
				return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
			}
			for (Object todaysOrderObj : todaysOrderList) 
				{
					System.out.println(todaysOrderObj.toString());
				}
			return new ResponseEntity<List<Object>>(todaysOrderList, HttpStatus.OK);
			*/

		
		
		@RequestMapping(value= "/todaysInwardsJson", method = RequestMethod.POST,consumes="Application/Json")
		public ResponseEntity<List<Object>> getTodaysInwards(@RequestBody String json) throws ParseException, JSONException 
		{
			JSONObject jsonObject = new JSONObject(json);

			JSONObject myDateObj = jsonObject.getJSONObject("mydate");
			String inDate=myDateObj.getString("myNewDate");
			
			System.out.println(inDate);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(inDate));
			c.add(Calendar.DATE, 1);
			inDate = df.format(c.getTime());*/ 
			Date parsedDate = df.parse(inDate);
			System.out.println(parsedDate);
			java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); 
			
			List<Object> todaysInwardList =service.getTodaysInwards(sqlDate);
			System.out.println("getTodaysInwards() called");
			
			
			
			if(todaysInwardList.isEmpty()){
				System.out.println("todaysInwardList List Empty");
				return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
			}
			for (Object todaysInwardObj : todaysInwardList) 
				{
					System.out.println(todaysInwardObj.toString());
				}
			return new ResponseEntity<List<Object>>(todaysInwardList, HttpStatus.OK);
			
		}
/*			List<Object> todaysInwardList = service.getTodaysInwards();
			System.out.println("getTodaysInward() called");
			
			
			
			if(todaysInwardList.isEmpty()){
				System.out.println("todaysInwardsList List Empty");
				return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
			}
			for (Object todaysInwardObj : todaysInwardList) 
				{
					System.out.println(todaysInwardObj.toString());
				}
			return new ResponseEntity<List<Object>>(todaysInwardList, HttpStatus.OK);*/
		
		
		@RequestMapping(value="/getGivenDateOrders", method=RequestMethod.POST, consumes="Application/Json")
		public ResponseEntity<List<Object>> getGivenDateOrders(@RequestBody String json) throws ParseException, JSONException 
		{
			
				JSONObject jsonObject = new JSONObject(json);

				JSONObject myDateObj = jsonObject.getJSONObject("mydate");
				String inDate=myDateObj.getString("myNewDate");
				
				System.out.println(inDate);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*				Calendar c = Calendar.getInstance();
				c.setTime(df.parse(inDate));
				c.add(Calendar.DATE, 1);
				inDate = df.format(c.getTime()); */
				Date parsedDate = df.parse(inDate);
				System.out.println(parsedDate);
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); 
				List<Object> todaysOrderList =service.getTodaysOrders(sqlDate);
				System.out.println("getTodaysOrders() called");
				
				
				
				if(todaysOrderList.isEmpty()){
					System.out.println("todaysOrderList List Empty");
					return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
				}
				for (Object todaysOrderObj : todaysOrderList) 
					{
						System.out.println(todaysOrderObj.toString());
					}
				return new ResponseEntity<List<Object>>(todaysOrderList, HttpStatus.OK);
		
		}
		
		
		@RequestMapping(value="/getGivenDateInwards", method=RequestMethod.POST, consumes="Application/Json")
		public ResponseEntity<List<Object>> getGivenDateInwards(@RequestBody String json) throws ParseException, JSONException 
		{
			
				JSONObject jsonObject = new JSONObject(json);

				JSONObject myDateObj = jsonObject.getJSONObject("mydate");
				String inDate=myDateObj.getString("myNewDate");
				
				System.out.println(inDate);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*				Calendar c = Calendar.getInstance();
				c.setTime(df.parse(inDate));
				c.add(Calendar.DATE, 1);
				inDate = df.format(c.getTime()); */
				Date parsedDate = df.parse(inDate);
				System.out.println(parsedDate);
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); 
				
				List<Object> todaysInwardList =service.getTodaysInwards(sqlDate);
				System.out.println("getTodaysInwards() called");
				
				
				
				if(todaysInwardList.isEmpty()){
					System.out.println("todaysInwardList List Empty");
					return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
				}
				for (Object todaysInwardObj : todaysInwardList) 
					{
						System.out.println(todaysInwardObj.toString());
					}
				return new ResponseEntity<List<Object>>(todaysInwardList, HttpStatus.OK);
		
		}
		
		
		
		
		
	
}

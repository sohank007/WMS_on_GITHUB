package adnate.spring.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import adnate.spring.pojos.Inward;
import adnate.spring.pojos.InwardDetails;
import adnate.spring.pojos.Material;
import adnate.spring.pojos.OrderDetails;
import adnate.spring.pojos.Warehouse;
import adnate.spring.services.*;



@Controller
@RequestMapping(value="/inward")
public class InwardController {
	@Autowired 
	private IInwardServices service;
	@Autowired
	private IInventoryServices service1;
	@Autowired
	private IWarehouseServices service2;
	@Autowired
	private IMaterialServices service3;

	
//  ++++++++++++++++++++++++++++++++++++++ ADD INWARD  ++++++++++++++++++++++++++++++++	
	@RequestMapping("/add_inward")
	public String addInward() {
		return "/pages/addInward";
	}
	
	@RequestMapping(value="/add_inward", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody int addInward(@RequestBody String json ) throws ParseException, JSONException {
		System.out.println("b4 Insert");
		System.out.println(" Inward JSON :->  " +json);
			JSONObject jsonObject = new JSONObject(json);

			JSONObject inward = jsonObject.getJSONObject("inward");
			    String inDate=inward.getString("inDate");
			    int wId=inward.getInt("warehouseId");
			    System.out.println("wId:" +wId);
			    String orgId= inward.getString("organisationId");
			   // String wName=inward.getString("warehouseName");
			    System.out.println("inwardObj:"+inward);
			    
			    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				/*String inDate=(String)jsonObject.get("inDate");*/
				Date parsedDate = df.parse(inDate);
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); 
			    System.out.println("parsed Date: " + parsedDate);
			    System.out.println("sqlDate: " + sqlDate);
			    
			    Inward inwardPOJO = new Inward();
			    inwardPOJO.setInDate(sqlDate);
			    Warehouse w = service2.findWarehouse(wId);
			    System.out.println("wrhobj:" +w);
			    inwardPOJO.setWarehouse(w);
			    inwardPOJO.setOrganisationId(orgId);
			   // inwardPOJO.setwName(wName);
			    
			    int inwardId = service.insertInward(inwardPOJO);
			    
			    System.out.println(inwardId);
			    
			    
			   // Add inward Details to inward
			   
			    JSONObject inwardDetails = jsonObject.getJSONObject("inwardDetails");
			    JSONArray materialList = inwardDetails.getJSONArray("materialList");

			        for (int i = 0; i < materialList.length(); i++) {

			            JSONObject materialDet = materialList.getJSONObject(i);

			            int mtrlId = materialDet.getInt("materialID");
			            int mtrlQty = materialDet.getInt("inQty");
			            InwardDetails inwardDetailsPOJO = new InwardDetails();
			            inwardDetailsPOJO.setInward(inwardPOJO);
			            System.out.println("inwardDetails inside inwardPOJO" + inwardPOJO);
			            int inwardPOJO_inId = inwardPOJO.getInId();
			            System.out.println("inwardDetails inside inwardPOJO" +inwardPOJO_inId);
			            Material material= service3.findMaterial(mtrlId);
			            inwardDetailsPOJO.setMaterial(material);
			            inwardDetailsPOJO.setInQty(mtrlQty);
			            
			            int inwardDetails_inId = service.insertInwardDetails(inwardDetailsPOJO);
			            
			            if(inwardDetails_inId!=0)
			            {
			            	boolean success = service1.updateInventoryOnInward(inwardDetailsPOJO);
			            	System.out.println(success);
			            }
			            
			            System.out.println("controller: inwardDetails_inId" +inwardDetails_inId);
			            
			            System.out.println("inwardDetails-materialList-id:" +mtrlId);
			            System.out.println("inwardDetails-materialList-qty:" +mtrlQty);
			            System.out.println("materialDet:"+materialDet);
			             }
					return inwardId;
				
	}
	
//  ++++++++++++  INWARD  CONTROLLER  ++++++++++++++  //
	
	@RequestMapping("/viewInward")
    public ModelAndView viewInward(){ 
		System.out.println("in viewInward()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("viewInward","comd",new Inward()); 
	}
	
	
	@RequestMapping(value="/inwardByOrgId/{orgId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Inward>> getInwardByOrgId(@PathVariable("orgId") String OrganisationId) {
		List<Inward> InwardList = service.getInwardByOrgId(OrganisationId);
		System.out.println("getMaterialByOrgId() called");
		if(InwardList.isEmpty()){
			System.out.println("MaterialListByOrgId is Empty");
			
			return new ResponseEntity<List<Inward>>(InwardList, HttpStatus.OK);
		}
		for (Inward inwrd : InwardList) 
		{
			System.out.println(inwrd.toString());
		
		}
	return new ResponseEntity<List<Inward>>(InwardList, HttpStatus.OK);			
	}
	
	
	
	@RequestMapping(value= "/viewInwardJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Inward>> getInwardObject() {
		List<Inward> inwardObjList = service.getInwardObject();
		System.out.println("getInwardObject() called");
		if(inwardObjList.isEmpty()){
			System.out.println("InwardObject List Empty");
			return new ResponseEntity<List<Inward>>(HttpStatus.NO_CONTENT);
		}
		for (Inward inwardObj : inwardObjList) 
			{
				System.out.println(inwardObj.toString());
			}
		return new ResponseEntity<List<Inward>>(inwardObjList, HttpStatus.OK);
	}
	
	
//  ++++++++++++  INWARD  DETAILS  CONTROLLER  ++++++++++++++  //
	
	@RequestMapping("/inwardDetails")
    public ModelAndView inwardDetails(){ 
		System.out.println("in inwardDetails()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("inwardDetails","comd",new InwardDetails()); 
	}

	@RequestMapping(value= "/inwardDetailsJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InwardDetails>> getInwardDetails() {
		List<InwardDetails> inwardDetList = service.getInwardDetails();
		System.out.println("getInwardDetails() called");
		if(inwardDetList.isEmpty()){
			System.out.println("InwardDetails List Empty");
			return new ResponseEntity<List<InwardDetails>>(HttpStatus.NO_CONTENT);
		}
		for (InwardDetails inwardDet : inwardDetList) 
			{
				System.out.println(inwardDet.toString());
			}
		return new ResponseEntity<List<InwardDetails>>(inwardDetList, HttpStatus.OK);
	}
	
	
	// +++++++++++++++++++++++++++++++ INWARDS ON TODAY METHOD +++++++++++++++++++++++++++++  //		

				@RequestMapping("/todayInward")
			    public ModelAndView todayInward(){ 
					System.out.println("in todayInward()");
			         //command is a reserved request attribute name, now use <form> tag to show object data  
			        return new ModelAndView("todayInward","comd",new OrderDetails()); 
				}
				
				@RequestMapping(value= "/todayInwardJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<Object>> getTodayInward()
				{
					List<Object> todayInwardList = service.getTodayInward();
					System.out.println("getTodayInward() called");
					
					
					
					if(todayInwardList.isEmpty()){
						System.out.println("todayInwardList List Empty");
						return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
					}
					for (Object todayInwardObj : todayInwardList) 
						{
							System.out.println(todayInwardObj.toString());
						}
					return new ResponseEntity<List<Object>>(todayInwardList, HttpStatus.OK);
					
				}	
	
				
				// +++++++++++++++++++++++++++++++ AMT FOR INWARDS ON TODAY METHOD +++++++++++++++++++++++++++++  //		

				@RequestMapping("/todayInwardAmount")
			    public ModelAndView todayInwardAmount(){ 
					System.out.println("in todayInwardAmount()");
			         //command is a reserved request attribute name, now use <form> tag to show object data  
			        return new ModelAndView("todayInwardAmount","comd",new OrderDetails()); 
				}
				
				@RequestMapping(value= "/todayInwardAmountJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<Object>> gettodayInwardAmount()
				{
					List<Object> todayInwardAmtList = service.getTodayInwardAmount();
					System.out.println("gettodayInwardAmount() called");
					
					
					
					if(todayInwardAmtList.isEmpty()){
						System.out.println("todayInwardAmtList List Empty");
						return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
					}
					for (Object todayInwardAmtObj : todayInwardAmtList) 
						{
							System.out.println(todayInwardAmtObj.toString());
						}
					return new ResponseEntity<List<Object>>(todayInwardAmtList, HttpStatus.OK);
					
				}			
	
	
	
}

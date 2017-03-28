package adnate.spring.controllers;

import java.util.List;

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

import adnate.spring.pojos.Contractor;
import adnate.spring.services.*;

@Controller
@RequestMapping(value="/contractor")
public class ContractorController {
	
	@Autowired
	private IContractorServices service;


	@RequestMapping(value="/contractorByOrgId/{orgId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contractor>> getContractorByOrgId(@PathVariable("orgId") String OrganisationId) {
		List<Contractor> ctrList = service.getContractorByOrgId(OrganisationId);
		System.out.println("getContractorByOrgId() called");
		if(ctrList.isEmpty()){
			System.out.println("MaterialListByOrgId is Empty");
			
			return new ResponseEntity<List<Contractor>>(ctrList, HttpStatus.OK);
		}
		for (Contractor ctr : ctrList) 
		{
			System.out.println(ctr.toString());
		
		}
	return new ResponseEntity<List<Contractor>>(ctrList, HttpStatus.OK);			
	}
	
	
	
	@RequestMapping("/contractorDetails")
   	public ModelAndView contractorDetails(){ 
		System.out.println("in contractorDetails()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("contractorDetails","comd",new Contractor()); 
	}
	
	@RequestMapping(value= "/contractorDetailsJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contractor>> getContractors() {
		List<Contractor> ctrList = service.getContractors();
		System.out.println("getContractors() called");
		if(ctrList.isEmpty()){
			System.out.println("contractor List Empty");
			return new ResponseEntity<List<Contractor>>(HttpStatus.NO_CONTENT);
		}
		for (Contractor ctr : ctrList) 
			{
				System.out.println(ctr.toString());
			}
		return new ResponseEntity<List<Contractor>>(ctrList, HttpStatus.OK);
	}
	
	
	@RequestMapping("/addCtr")
	public String addCtr() {
		return "pages/addContractor";
	}
	
	@RequestMapping(value="/addCtr", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean addContractor(@RequestBody Contractor ctr) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.insertContractor(ctr);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	@RequestMapping(value="/contractorId/{mailId}", method=RequestMethod.GET, consumes="Application/Json")
	public @ResponseBody boolean updateStatus(@PathVariable("mailId") String mailId) {
		System.out.println("mailId"+mailId);
		return true;
	}
	
	@RequestMapping("/editCtr")
	public String editCtr() {
		return "pages/editContractor";
	}
	
	@RequestMapping(value="/editCtr", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean editContractor(@RequestBody Contractor ctr) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.updateContractor(ctr);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	@RequestMapping("/delete/ctr")
	public String deleteCtr() {
		return "pages/failed";
	}
	
	@RequestMapping(value="/delete/ctr", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean deleteContractor(@RequestBody Contractor ctr) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.deleteContractor(ctr);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	
}

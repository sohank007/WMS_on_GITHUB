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

import adnate.spring.pojos.Warehouse;
import adnate.spring.services.IWarehouseServices;

@Controller
@RequestMapping(value="/warehouse")
public class WarehouseController {
	
	@Autowired
	private IWarehouseServices service;
	
	
	
	@RequestMapping(value="/warehouseByOrgId/{orgId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Warehouse>> getWarehouseByOrgId(@PathVariable("orgId") String OrganisationId) {
		List<Warehouse> wrhouseList = service.getWarehouseByOrgId(OrganisationId);
		System.out.println("getWarehouseByOrgId() called");
		if(wrhouseList.isEmpty()){
			System.out.println("MaterialListByOrgId is Empty");
			
			return new ResponseEntity<List<Warehouse>>(wrhouseList, HttpStatus.OK);
		}

		return new ResponseEntity<List<Warehouse>>(wrhouseList, HttpStatus.OK);			
	}
	
	
	
	
	@RequestMapping("/warehouseDetails")
    public ModelAndView warehouseDetails(){ 
		System.out.println("in warehouseDetails()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("warehouseDetails","comd",new Warehouse()); 
	}
	
	@RequestMapping(value= "/warehouseDetailsJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Warehouse>> getWarehouses() {
		List<Warehouse> wList = service.getWarehouses();
		System.out.println("getWarehouses() called");
		if(wList.isEmpty()){
			System.out.println("warehouse List Empty");
			return new ResponseEntity<List<Warehouse>>(HttpStatus.NO_CONTENT);
		}
		for (Warehouse warehouse : wList) 
			{
				System.out.println(warehouse.toString());
			}
		return new ResponseEntity<List<Warehouse>>(wList, HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "/findRegionJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findRegion() {
		List<String> wList = service.findRegion();
		System.out.println("getWarehouses() called");
		if(wList.isEmpty()){
			System.out.println("warehouse List Empty");
			return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}
		for (String warehouse : wList) 
			{
				System.out.println(warehouse.toString());
			}
		return new ResponseEntity<List<String>>(wList, HttpStatus.OK);
	}
	
	
	
	@RequestMapping("/add_wrh")
	public String addwrh() {
		return "/pages/addWarehouse";
	}
	
	@RequestMapping(value="/add_wrh", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean addWarehouse(@RequestBody Warehouse w) {
		try{
			System.out.println("b4 Insert");
			service.insertWarehouse(w);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}	
	
	@RequestMapping("/editWre")
	public String editWre() {
		return "pages/editWarehouse";
	}
	
	@RequestMapping(value="/editWre", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean editWarehouse(@RequestBody Warehouse wre) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.updateWarehouse(wre);;
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	@RequestMapping("/delete/wre")
	public String deleteWre() {
		return "pages/failed";
	}
	
	@RequestMapping(value="/delete/wre", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean deleteWarehouse(@RequestBody Warehouse wre) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.deleteWarehouse(wre);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
}

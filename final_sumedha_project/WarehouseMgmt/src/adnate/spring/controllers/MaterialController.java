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

import adnate.spring.pojos.Material;
//import adnate.spring.pojos.OrderDetails;
import adnate.spring.services.IMaterialServices;

@Controller
@RequestMapping(value="/material")
public class MaterialController {
	 
		@Autowired
		private IMaterialServices service;
		
		@RequestMapping("/materialDetails")
	    public ModelAndView materialDetails(){ 
			System.out.println("in materialDetails()");
	         //command is a reserved request attribute name, now use <form> tag to show object data  
	        return new ModelAndView("materialDetails","comd",new Material()); 
		}
		
		@RequestMapping(value="/materialByOrgId/{orgId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Material>> getMaterialByOrgId(@PathVariable("orgId") String OrganisationId) {
			List<Material> mtrlList = service.getMaterialByOrgId(OrganisationId);
			System.out.println("getMaterialByOrgId() called");
			if(mtrlList.isEmpty()){
				System.out.println("MaterialListByOrgId is Empty");
				
				return new ResponseEntity<List<Material>>(mtrlList, HttpStatus.OK);
			}
		return new ResponseEntity<List<Material>>(mtrlList, HttpStatus.OK);			
		}
		
		@RequestMapping(value="/materialByCategoryId/{catId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Material>> getMaterialByCategoryName(@PathVariable("catId") int catId) {
			List<Material> mtrlList = service.getMaterialByCategoryName(catId);
			System.out.println("getMaterialByCategoryName() called");
			if(mtrlList.isEmpty()){
				System.out.println("materialListByCategoryName is Empty");
				
				return new ResponseEntity<List<Material>>(mtrlList, HttpStatus.OK);
			}
		return new ResponseEntity<List<Material>>(mtrlList, HttpStatus.OK);			
		}
		
		@RequestMapping(value= "/materialDetail/{mtrlId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Material findMaterial(@PathVariable("mtrlId") int id) {
			Material material = service.findMaterial(id);
			System.out.println("getInvoiceDetails() called");
			
			return material;
		}
		 
		
		@RequestMapping(value= "/materialDetailsJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Material>> getMaterials() {
			
			List<Material> mtrlList = service.getMaterials();
			System.out.println("getMaterials() called");
			if(mtrlList.isEmpty()){
				System.out.println("material List Empty");
				return new ResponseEntity<List<Material>>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<List<Material>>(mtrlList, HttpStatus.OK);
		}	
		 	
		@RequestMapping("/add_mtr")
		public String addmtrl() {
			return "pages/addMaterial";
		}
		
		
		@RequestMapping(value="/add_mtr", method=RequestMethod.POST, consumes="Application/Json")
		public @ResponseBody int addMaterial(@RequestBody Material mtrl) {
			
				System.out.println("b4 Insert");
				//Material obj = new Gson().fromJson(params, MyClass.class);  
				int mtrId=service.insertMaterial(mtrl);
				System.out.println("mtrId: "+mtrId);
				return mtrId;
		
		}
		
		@RequestMapping("/editMtr")
		public String editMtr() {
			return "pages/editMaterial";
		}
		
		@RequestMapping(value="/editMtr", method=RequestMethod.POST, consumes="Application/Json")
		public @ResponseBody int editWarehouse(@RequestBody Material mtrl) {
			
				System.out.println("b4 Insert");
				//Material obj = new Gson().fromJson(params, MyClass.class);  
				int mtrId=service.updateMaterial(mtrl);
				System.out.println("returning true");
				return mtrId;
			
		}
		
		@RequestMapping("/delete/mtr")
		public String deleteMtr() {
			return "pages/failed";
		}
		
		@RequestMapping(value="/delete/mtr", method=RequestMethod.POST, consumes="Application/Json")
		public @ResponseBody int deleteMaterial(@RequestBody Material mtrl) {
			
				System.out.println("b4 Insert");
				//Material obj = new Gson().fromJson(params, MyClass.class);  
				int mtrId=service.deleteMaterial(mtrl);
				System.out.println("returning true");
				return mtrId;
		}
		
		
		
		
		
}

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

import adnate.spring.pojos.Category;

import adnate.spring.services.ICategoryServices;

@Controller
@RequestMapping(value="/category")
public class CategoryController {

	@Autowired
	private ICategoryServices categoryService;
	
	@RequestMapping("/categoryDetails")
    public ModelAndView categoryDetails(){ 
		System.out.println("in categoryDetails()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("categoryDetails","comd",new Category()); 
	}
	
	@RequestMapping(value="/categoryByOrgId/{orgId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> getCategoryByOrgId(@PathVariable("orgId") String OrganisationId) {
		List<Category> categoryList = categoryService.getCategoryByOrgId(OrganisationId);
		System.out.println("getCategoryByOrgId() called");
		if(categoryList.isEmpty()){
			System.out.println("MaterialListByOrgId is Empty");
			
			return new ResponseEntity<List<Category>>(categoryList, HttpStatus.NO_CONTENT);
		}
	return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);			
	}
	
	@RequestMapping(value= "/categoryDetailsJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> getCategories() {
		
		List<Category> categoryList = categoryService.getCategories();
		System.out.println("getCategories() called");
		if(categoryList.isEmpty()){
			System.out.println("getCategories List Empty");
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
	}	
	
	@RequestMapping("/add_ctgry")
	public String addctgry() {
		return "pages/addCategory";
	}
	
	
	@RequestMapping(value="/add_ctgry", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean addCategory(@RequestBody Category ctgry) {
		try{
			System.out.println("b4 Insert");
			System.out.println("category form UI " + ctgry);
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			categoryService.insertCategory(ctgry);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	
	@RequestMapping("/editCtgry")
	public String editMtr() {
		return "pages/editCategory";
	}
	
	@RequestMapping(value="/editCtgry", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean editCategory(@RequestBody Category ctgry) {
		try{
			System.out.println("b4 Insert");
			System.out.println("categoru obj: " + ctgry);
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			categoryService.updateCategory(ctgry);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	
	@RequestMapping("/delete/ctgry")
	public String deleteCtgry() {
		return "pages/failed";
	}
	
	@RequestMapping(value="/delete/ctgry", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean deleteCategory(@RequestBody Category ctgry) {
		try{
			System.out.println("b4 Insert");
			System.out.println("obj to be delted : " + ctgry );
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			categoryService.deleteCategory(ctgry);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	
	@RequestMapping(value= "/categoryDetail/{ctgryId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Category findCategory(@PathVariable("ctgryId") int id) {
		Category category = categoryService.findCategory(id);
		System.out.println("findCategory() called");
		
		return category;
	}
	
	
}

package adnate.spring.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import adnate.spring.pojos.MenuInfo;
import adnate.spring.services.*;

@Controller
@RequestMapping(value="/menuMaster")
public class MenuController {
	
	@Autowired
	private IMenuMasterService menuMasterService;

//	@RequestMapping("/menuMaster")
//    public ModelAndView menuMaster(){ 
//		System.out.println("in menuMaster()");
//         //command is a reserved request attribute name, now use <form> tag to show object data  
//        return new ModelAndView("menuMaster","comd",new Menu()); 
//	}
	
	@RequestMapping(value="/menuByOrgId/{orgId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MenuInfo>> getMenuByOrgId(@PathVariable("orgId") String OrganisationId) {
		List<MenuInfo> MenuListByOrgId = menuMasterService.getMenuByOrgId(OrganisationId);
		System.out.println("getMenuByOrgId() called");
		if(MenuListByOrgId.isEmpty()){
			System.out.println("MenuListByOrgId is Empty");
			
			return new ResponseEntity<List<MenuInfo>>(MenuListByOrgId, HttpStatus.OK);
		}
	return new ResponseEntity<List<MenuInfo>>(MenuListByOrgId, HttpStatus.OK);			
	}
	
	
	
	/*@RequestMapping(value= "/menumasterJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MenuInfo>> getMenu() {
		List<MenuInfo> menuList = menuMasterService.getMenu();
		System.out.println("getMenu() called");
		if(menuList.isEmpty()){
			System.out.println("menu List Empty");
			return new ResponseEntity<List<MenuInfo>>(HttpStatus.NO_CONTENT);
		}
		for (MenuInfo menu : menuList) 
			{
				System.out.println(menu.toString());
			}
		return new ResponseEntity<List<MenuInfo>>(menuList, HttpStatus.OK);
	}
	
	
	@RequestMapping("/addMenu")
	public String addMenu() {
		return "pages/addMenu";
	}
	
	@RequestMapping(value="/addMenu", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean addMenu(@RequestBody MenuInfo menuInfo) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			menuMasterService.insertMenuInfo(menuInfo);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}*/
	
	/*@RequestMapping("/editMenu")
	public String editMenu() {
		return "pages/editMenu";
	}
	
	@RequestMapping(value="/editMenu", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean editMenu(@RequestBody Menu menu) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.updateMenu(menu);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	@RequestMapping("/delete/Menu")
	public String deleteMenu() {
		return "pages/failed";
	}
	
	@RequestMapping(value="/delete/Menu", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean deleteMenu(@RequestBody Menu menu) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.deleteMenu(menu);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}*/
	
	
}












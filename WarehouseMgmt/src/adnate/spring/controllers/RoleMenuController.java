package adnate.spring.controllers;

//import java.util.ArrayList;
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

import adnate.spring.pojos.MenuInfo;
import adnate.spring.pojos.RoleInfo;
import adnate.spring.pojos.RoleMenu;
import adnate.spring.services.*;

@Controller
@RequestMapping(value="/roleMenu")
public class RoleMenuController {
	
	@Autowired
	private IRoleMenuService roleMenuService;

	@Autowired
	private IRoleMasterService roleMasterService;
	
	
	@RequestMapping("/roleMenu")
    public ModelAndView roleMenu(){ 
		System.out.println("in roleMenu()");
         //command is a reserved request attribute name, now use <form> tag to show object data  
        return new ModelAndView("roleMenu","comd",new RoleMenu()); 
	}
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++ ROLE MENU MAPPING FOR ORGANISATION +++++++++++++++++++++++++
	
	
	/*@RequestMapping(value="/menuByOrgId/{orgId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MenuInfo>> getMenuByOrgId(@PathVariable("orgId") String OrganisationId) {
		List<MenuInfo> MenuListByOrgId = menuMasterService.getMenuByOrgId(OrganisationId);
		System.out.println("getMenuByOrgId() called");
		if(MenuListByOrgId.isEmpty()){
			System.out.println("MenuListByOrgId is Empty");
			
			return new ResponseEntity<List<MenuInfo>>(MenuListByOrgId, HttpStatus.OK);
		}
	return new ResponseEntity<List<MenuInfo>>(MenuListByOrgId, HttpStatus.OK);			
	}*/
	
	
	
	@RequestMapping(value= "/roleMenuByOrgIdRoleCode/{roleCode}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoleMenu>> getRoleMenuMapping(@PathVariable("roleCode") String roleCode) {
		//roleMasterService.getRoleByOrgIdRoleCode(organisationId, roleCode);
		List<RoleMenu> roleMenuList = roleMenuService.getRoleMenuMapping(roleCode);
		System.out.println("getRoleMenuMapping() called");
		if(roleMenuList.isEmpty()){
			System.out.println("menu List Empty");
			return new ResponseEntity<List<RoleMenu>>(HttpStatus.NO_CONTENT);
		}
		/*for (RoleMenu menu : roleMenuList) 
			{
				System.out.println(menu.toString());
			}*/
		return new ResponseEntity<List<RoleMenu>>
				(roleMenuList, HttpStatus.OK);
	}
	
	
	/*@RequestMapping("/addRoleMenu")
	public String addRoleMenu() {
		return "pages/addRoleMenu";
	}
	
	@RequestMapping(value="/addRoleMenu", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean addRoleMenu(@RequestBody RoleMenu roleMenu) {
		try{
			System.out.println("b4 Insert");
			System.out.println("RoleMenu obj: " + roleMenu);
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.insertRole(roleMenu);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}*/
	
	/*@RequestMapping("/editRole")
	public String editRole() {
		return "pages/editRole";
	}
	
	@RequestMapping(value="/editRole", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean editMenu(@RequestBody RoleMenu role) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.editRole(role);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}
	
	@RequestMapping("/delete/Role")
	public String deleteRole() {
		return "pages/failed";
	}
	
	@RequestMapping(value="/delete/Role", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean deleteRole(@RequestBody RoleMenu role) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			service.deleteRole(role);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}*/
	
	
}
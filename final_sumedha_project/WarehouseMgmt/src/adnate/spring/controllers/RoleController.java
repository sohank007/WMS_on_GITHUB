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



import adnate.spring.pojos.RoleInfo;

import adnate.spring.services.IRoleMasterService;

	
@Controller
@RequestMapping(value="/roleMaster")
public class RoleController {
	
	@Autowired
	private IRoleMasterService roleMasterService;
	
	/*@RequestMapping(value= "/roles/{role_code}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoleMenu>>getRoleMenu(@PathVariable("role_code") String role_code) {
		List<RoleMenu> menuList = service.getMenuByRole(role_code);
		System.out.println("getInvoiceDetails() called");
		if(menuList.isEmpty()){
			System.out.println("role List Empty");
			return new ResponseEntity<List<RoleMenu>>(HttpStatus.NO_CONTENT);
		}
		for (RoleMenu menu : menuList) 
			{
				System.out.println(menu.toString());
			}
		return new ResponseEntity<List<RoleMenu>>(menuList, HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/roleByOrgIdRoleCode/{orgId}/{roleCode}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoleInfo>> getRoleByOrgIdRoleCode(@PathVariable("orgId") String OrganisationId, @PathVariable("roleCode") String roleCode) {
		List<RoleInfo> roleListByOrgId = roleMasterService.getRoleByOrgIdRoleCode(OrganisationId, roleCode);
		System.out.println("getRoleByOrgIdRoleCode() called");
		if(roleListByOrgId.isEmpty()){
			System.out.println("roleListByOrgId is Empty");
			
			return new ResponseEntity<List<RoleInfo>>(roleListByOrgId, HttpStatus.OK);
		}
	return new ResponseEntity<List<RoleInfo>>(roleListByOrgId, HttpStatus.OK);			
	}
	
	
	/*@RequestMapping(value= "/rolemasterJson", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoleInfo>> getRole() {
		List<RoleInfo> roleList = roleMasterService.getRole();
		System.out.println("getRole() called");
		if(roleList.isEmpty()){
			System.out.println("menu List Empty");
			return new ResponseEntity<List<RoleInfo>>(HttpStatus.NO_CONTENT);
		}
		;
		for (RoleInfo menu : roleList) 
			{
				System.out.println(menu.toString());
			}
		return new ResponseEntity<List<RoleInfo>>(roleList, HttpStatus.OK);
	}
	
	
	@RequestMapping("/addRole")
	public String addRole() {
		return "pages/addRole";
	}
	
	@RequestMapping(value="/addRole", method=RequestMethod.POST, consumes="Application/Json")
	public @ResponseBody boolean addRole(@RequestBody RoleInfo role) {
		try{
			System.out.println("b4 Insert");
			//Material obj = new Gson().fromJson(params, MyClass.class);  
			roleMasterService.insertRole(role);
			System.out.println("returning true");
			return true;
		} catch(Exception ex) {
			
			System.out.println("returning flase");
			return false; 
		}
	}*/
	
}

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

import adnate.spring.pojos.RoleMenu;
import adnate.spring.services.IRoleMenuServices;

	
@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Autowired
	IRoleMenuServices service;
	
	@RequestMapping(value= "/roles/{role_code}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
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
	}
}

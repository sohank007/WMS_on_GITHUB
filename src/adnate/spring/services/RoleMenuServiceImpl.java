package adnate.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import adnate.spring.daos.RoleMenuDao;
import adnate.spring.pojos.RoleMenu;

@Service
public class RoleMenuServiceImpl implements IRoleMenuServices{

	@Autowired
	private RoleMenuDao roleMenuDao; 
	
	@Override
	public List<RoleMenu> getMenuByRole(String role_code) {
		return roleMenuDao.getMenuByRole(role_code);
	}
}

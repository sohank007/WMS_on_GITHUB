package adnate.spring.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import adnate.spring.daos.RoleMenuDao;
import adnate.spring.pojos.RoleMenu;



@Service
public class RoleMenuServiceImpl implements IRoleMenuService{


	@Autowired
	RoleMenuDao roleMenuDao;

		public List<RoleMenu> getRoleMenuMapping(String roleCode) {
			return roleMenuDao.getRoleMenuMapping(roleCode);
		}

	

		/*@Override
		public boolean insertRole(RoleMenu role) 
		{			try {
				roleMenuDao.insertRole(role);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			return false;
		}


		@Override
		public void deleteRole(RoleMenu role) {
			roleMenuDao.deleteRole(role);
			
		}


		@Override
		public void editRole(RoleMenu role) {
			roleMenuDao.editRole(role);
			
		}


		@Override
		public List<RoleMenu> getMenuByRole(String role_code) {
			// TODO Auto-generated method stub
			return null;
		}
*/


		
		
	
}


	


	
	



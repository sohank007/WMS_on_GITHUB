package adnate.spring.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.RoleMasterDao;

import adnate.spring.pojos.RoleInfo;



@Service
public class RoleMasterServiceImpl implements IRoleMasterService{


	@Autowired
	RoleMasterDao roleMasterDao;

		public List<RoleInfo> getRole() {
			return roleMasterDao.getAllRole();
		}

		@Override
		public List<RoleInfo> getRoleByOrgIdRoleCode(String organisationId, String roleCode) {
			return roleMasterDao.getRoleByOrgIdRoleCode(organisationId, roleCode);
		}

		@Override
		public boolean insertRole(RoleInfo role) {
			try {
				roleMasterDao.insertRole(role);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			return false;
		}

		/*@Override
		public RoleInfo getRoleMenuByRoleCode(String roleCode) {
			return roleMasterDao.getRoleMenuByRoleCode(roleCode);
		}*/


		



		
		
	
}


	


	
	



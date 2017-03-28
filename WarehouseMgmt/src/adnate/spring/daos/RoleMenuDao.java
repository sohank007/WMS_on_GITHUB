package adnate.spring.daos;

import java.util.List;


import adnate.spring.pojos.RoleMenu;


public interface RoleMenuDao {
	
	public   List<RoleMenu> getRoleMenuMapping(String roleCode);

	
	/*public void insertRole(RoleMenu role);
	
	public void deleteRole(RoleMenu role);
	
	public void editRole(RoleMenu role);*/


}
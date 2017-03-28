package adnate.spring.services;

import java.util.List;
import adnate.spring.pojos.RoleMenu;

public interface IRoleMenuService {
	public List<RoleMenu> getRoleMenuMapping(String roleCode);

	
	
	/*public boolean insertRole(RoleMenu role);

	public void deleteRole(RoleMenu role);

	public void editRole(RoleMenu role);*/

	
}
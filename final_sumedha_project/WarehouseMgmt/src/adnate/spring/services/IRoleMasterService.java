package adnate.spring.services;

import java.util.List;
import adnate.spring.pojos.RoleInfo;

public interface IRoleMasterService {
	public List<RoleInfo> getRole();

	public List<RoleInfo> getRoleByOrgIdRoleCode(String organisationId, String roleCode);

	public boolean insertRole(RoleInfo role);

	//public RoleInfo getRoleMenuByRoleCode(String roleCode);
	
	/*public boolean insertRole(RoleInfo role);

	public void deleteRole(RoleInfo role);

	public void editRole(RoleInfo role);*/

	
}
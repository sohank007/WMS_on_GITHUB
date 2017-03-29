package adnate.spring.daos;

import java.util.List;

import adnate.spring.pojos.RoleInfo;


public interface RoleMasterDao {
public   List<RoleInfo> getAllRole();

public List<RoleInfo> getRoleByOrgIdRoleCode(String organisationId, String roleCode);

public void insertRole(RoleInfo role);

//public RoleInfo getRoleMenuByRoleCode(String roleCode);

/*public void insertRole(RoleInfo role);

public void deleteRole(RoleInfo role);

public void editRole(RoleInfo role);*/
}
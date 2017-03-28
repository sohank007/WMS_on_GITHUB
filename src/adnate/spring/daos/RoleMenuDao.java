package adnate.spring.daos;

import java.util.List;

import adnate.spring.pojos.RoleMenu;

public interface RoleMenuDao {

	List<RoleMenu> getMenuByRole(String role_code);
}

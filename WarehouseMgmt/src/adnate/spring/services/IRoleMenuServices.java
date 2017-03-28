package adnate.spring.services;

import java.util.List;

import adnate.spring.pojos.RoleMenu;

public interface IRoleMenuServices {
	public	List<RoleMenu> getMenuByRole(String role_code);
}

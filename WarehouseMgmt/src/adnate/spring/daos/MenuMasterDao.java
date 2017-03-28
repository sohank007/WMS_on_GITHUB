package adnate.spring.daos;

import java.util.List;

import adnate.spring.pojos.MenuInfo;

public interface MenuMasterDao {

	public   List<MenuInfo> getAllMenu();

	public void insertMenuInfo(MenuInfo menuInfo);

	public List<MenuInfo> getMenuByOrgId(String organisationId);


}

//public void updateMenu(Menu menu);
//public static void deleteMenu(Menu menu) {
//	// TODO Auto-generated method stub
//	
//}
//public static void editMenu(Menu menu);

//}

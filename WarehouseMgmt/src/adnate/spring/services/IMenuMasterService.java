package adnate.spring.services;

import java.util.List;

import javax.management.relation.RoleInfo;

import adnate.spring.pojos.MenuInfo;

public interface IMenuMasterService {

public  List<MenuInfo> getMenu();

public boolean insertMenuInfo(MenuInfo menuInfo);

public List<MenuInfo> getMenuByOrgId(String organisationId);

/*

public void deleteMenu(Menu menu);

public void editMenu(Menu menu);

public void updateMenu(Menu menu);*/







}



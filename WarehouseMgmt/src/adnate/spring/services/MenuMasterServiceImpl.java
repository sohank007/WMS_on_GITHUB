package adnate.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.ContractorDao;
import adnate.spring.daos.InventoryDao;
import adnate.spring.daos.MenuMasterDao;
import adnate.spring.daos.TaxDao;
import adnate.spring.pojos.Contractor;
import adnate.spring.pojos.Material;
import adnate.spring.pojos.MenuInfo;
import adnate.spring.pojos.TaxInfo;


@Service
public class MenuMasterServiceImpl implements IMenuMasterService{


	@Autowired
	MenuMasterDao menuMasterDao;

		@Override
		public List<MenuInfo> getMenu() {
			return menuMasterDao.getAllMenu();
		}

		@Override
		public boolean insertMenuInfo(MenuInfo menuInfo) {
			try {
				menuMasterDao.insertMenuInfo(menuInfo);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			return false;
		}

		@Override
		public List<MenuInfo> getMenuByOrgId(String organisationId) {
			return menuMasterDao.getMenuByOrgId(organisationId);
		}


		/*@Override
		public boolean insertMenu(Menu menu) {
			try {
				menuMasterDao.insertMenu(menu);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			return false;
		}


		@Override
		public void deleteMenu(Menu menu) {
			menuMasterDao.deleteMenu(menu);
			
		}


		@Override
		public void editMenu(Menu menu) {
			menuMasterDao.editMenu(menu);
			
		}


		@Override
		public void updateMenu(Menu menu) {
			// TODO Auto-generated method stub
			
		}*/
		
	
}


	


	
	



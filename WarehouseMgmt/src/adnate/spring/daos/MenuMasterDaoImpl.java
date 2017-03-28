package adnate.spring.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adnate.spring.pojos.Material;
import adnate.spring.pojos.MenuInfo;
import adnate.spring.pojos.RoleInfo;

@Transactional
@Repository
public class MenuMasterDaoImpl implements MenuMasterDao {

	@Autowired
	SessionFactory sFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuInfo> getAllMenu() {
		System.out.println("inside getAllMenu()");
		Session hbSession = sFactory.getCurrentSession();
		Criteria cr = hbSession.createCriteria(MenuInfo.class);
		System.out.println("hbSession created!");
		System.out.println(cr.list());
		return cr.list();
	}

	@Override
	public void insertMenuInfo(MenuInfo menuInfo) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(menuInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuInfo> getMenuByOrgId(String organisationId) {
		System.out.println("inside getMaterialByOrgId() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("select * from menumaster where organisation_id=:organisationId")
				.addEntity(MenuInfo.class);
		query.setString("organisationId", organisationId);
		return query.list();
	}

//	@Override
//	public void insertMenu(Menu menu) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateMenu(Menu menu) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteMenu(Menu menu) {
//		// TODO Auto-generated method stub
//		
//	}
	

}

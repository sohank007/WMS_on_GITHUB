
package adnate.spring.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adnate.spring.pojos.MenuInfo;
import adnate.spring.pojos.RoleMenu;

@Transactional
@Repository
public class RoleMenuDaoImpl implements RoleMenuDao {

	@Autowired
	SessionFactory sFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleMenu> getRoleMenuMapping(String roleCode) {
		System.out.println("inside getRoleMenuMapping()");
		Session hbSession = sFactory.getCurrentSession();
		/*Criteria cr = hbSession.createCriteria(RoleMenu.class);*/
		System.out.println("hbSession created!");
		
		Query query = hbSession.createSQLQuery("SELECT MM.* FROM rolemenu RM  JOIN menumaster MM ON"
				+ " RM.menu_id=MM.menu_id  JOIN rolemaster RM1 ON RM1.role_id=RM.role_id "
				+ "WHERE  RM1.role_code=:roleCode ORDER BY MM.menu_id")
				.addEntity(MenuInfo.class);
		query.setString("roleCode", roleCode);
		System.out.println("queryList: " + query.list());
		return query.list();
		
	}

	
	

	/*@Override
	public void insertRole(RoleMenu role) {
		System.out.println("inside getAllRole()");
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(role);		
	}

	@Override
	public void deleteRole(RoleMenu role) {
		System.out.println("inside getAllRole()");
		Session hbSession = sFactory.getCurrentSession();
		hbSession.delete(role);	
		
	}

	@Override
	public void editRole(RoleMenu role) {
		System.out.println("inside getAllRole()");
		Session hbSession = sFactory.getCurrentSession();
		hbSession.update(role);	
		
	}*/
	
	
}
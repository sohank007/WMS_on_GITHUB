//package adnate.spring.daos;
//
//import java.util.*;
//
//import javax.management.relation.RoleInfo;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import adnate.spring.pojos.Material;
//import adnate.spring.pojos.Menu;
//import adnate.spring.pojos.MenuInfo;
//import adnate.spring.pojos.RoleMenu;
//
//@Transactional
//@Repository
//
//public class RoleMasterDaoImpl implements RoleMenuDao {
//
//	@Autowired
//	SessionFactory sFactory;
//	
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<RoleInfo> getAllRole() {
//		System.out.println("inside getAllRole()");
//		Session hbSession = sFactory.getCurrentSession();
//		Criteria cr = hbSession.createCriteria(RoleInfo.class);
//		System.out.println("hbSession created!");
//		System.out.println(cr.list());
//		return cr.list();
//	}
//
//	@Override
//	public List<RoleMenu> getMenuByRole(String role_code) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//}


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
import adnate.spring.pojos.RoleInfo;
import adnate.spring.pojos.RoleMenu;

@Transactional
@Repository
public class RoleMasterDaoImpl implements RoleMasterDao {

	@Autowired
	SessionFactory sFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleInfo> getAllRole() {
		System.out.println("inside getAllRole()");
		Session hbSession = sFactory.getCurrentSession();
		Criteria cr = hbSession.createCriteria(RoleInfo.class);
		System.out.println("hbSession created!");
		System.out.println(cr.list());
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleInfo> getRoleByOrgIdRoleCode(String organisationId, String roleCode) {
		System.out.println("inside getRoleByOrgIdRoleCode() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("select * from rolemaster where organisation_id=:organisationId "
				+ " AND role_code=:roleCode")
				.addEntity(RoleInfo.class);
		query.setString("organisationId", organisationId);
		query.setString("roleCode", roleCode);
		return query.list();
	}

	@Override
	public void insertRole(RoleInfo role) {
		System.out.println("inserting new Role in DaoImpl: " + role);
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(role);
	}

	/*@Override
	public RoleInfo getRoleMenuByRoleCode(String roleCode) {
			System.out.println("inside getRoleMenuMapping()");
			Session hbSession = sFactory.getCurrentSession();
			System.out.println("hbSession created!");
			
			Query query = hbSession.createSQLQuery("SELECT * FROM rolemaster WHERE role_code=:roleCode")
					.addEntity(RoleInfo.class);
			query.setString("roleCode", roleCode);
			return query.list();

	}*/

	

	/*@Override
	public void insertRole(RoleInfo role) {
		System.out.println("inside getAllRole()");
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(role);		
	}

	@Override
	public void deleteRole(RoleInfo role) {
		System.out.println("inside getAllRole()");
		Session hbSession = sFactory.getCurrentSession();
		hbSession.delete(role);	
		
	}

	@Override
	public void editRole(RoleInfo role) {
		System.out.println("inside getAllRole()");
		Session hbSession = sFactory.getCurrentSession();
		hbSession.update(role);	
		
	}*/
}

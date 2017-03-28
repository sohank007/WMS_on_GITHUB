package adnate.spring.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import adnate.spring.pojos.RoleMenu;

@Transactional
@Repository
public class RoleMenuDaoImpl implements RoleMenuDao{

	
	@Autowired
	SessionFactory sFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleMenu> getMenuByRole(String role_code) {
		Session hbSession=sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("SELECT * from rolemenu where role_code=:role_code")
				.addEntity(RoleMenu.class);
		query.setString("role_code", role_code);
		
		List<RoleMenu> menuList=(List<RoleMenu>)query.list();
		
		return menuList;
	}

}

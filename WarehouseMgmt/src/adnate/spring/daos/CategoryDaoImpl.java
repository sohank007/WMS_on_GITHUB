package adnate.spring.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adnate.spring.pojos.Category;
import adnate.spring.pojos.Material;

@Transactional
@Repository
public class CategoryDaoImpl implements ICategoryDao{

	@Autowired
	SessionFactory sFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoryByOrgId(String organisationId) {
		System.out.println("inside getMaterialByOrgId() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("select * from categorymaster where organisation_id=:organisationId")
				.addEntity(Category.class);
		query.setString("organisationId", organisationId);
		return query.list();	
	}

	@Override
	public Category findCategory(int id) {
		Session hbSession = sFactory.getCurrentSession();
		return (Category) hbSession.get(Category.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() {
		System.out.println("in getCategories() in DaoImpl");
		Session hbSession = sFactory.getCurrentSession();
		Criteria cr = hbSession.createCriteria(Category.class);
		System.out.println("hbSession created!");
		System.out.println(cr.list());
		return cr.list();
	}

	@Override
	public void insertCategory(Category ctgry) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(ctgry);
	}

	@Override
	public void updateCategory(Category ctgry) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.update(ctgry);
	}

	@Override
	public void deleteCategory(Category ctgry) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.delete(ctgry);
	}
	
	

}

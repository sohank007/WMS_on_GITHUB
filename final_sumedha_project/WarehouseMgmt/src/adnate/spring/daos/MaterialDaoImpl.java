package adnate.spring.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adnate.spring.pojos.Material;
//import adnate.spring.pojos.OrderDetails;

@Transactional
@Repository
public class MaterialDaoImpl implements MaterialDao{

	@Autowired(required=true)
	private SessionFactory sFactory;
	
	@Override
	public int insertMaterial(Material mtrl) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(mtrl);
		return mtrl.getMaterialID();
		
	}

	@Override
	public int updateMaterial(Material mtrl) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.update(mtrl);
		return mtrl.getMaterialID();
	}

	@Override
	public int deleteMaterial(Material mtrl) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.delete(mtrl);
		return mtrl.getMaterialID();
	}

	@Override
	public Material findMaterial(int id) {
		Session hbSession = sFactory.getCurrentSession();
		return (Material) hbSession.get(Material.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> getAllMaterials() {
		Session hbSession = sFactory.getCurrentSession();
		Criteria cr = hbSession.createCriteria(Material.class);
		System.out.println("hbSession created!");
		System.out.println(cr.list());
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> getMaterialByOrgId(String organisationId) {
		System.out.println("inside getMaterialByOrgId() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("select * from materialmaster where organisation_id=:organisationId")
				.addEntity(Material.class);
		query.setString("organisationId", organisationId);
		return query.list();		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Material> getMaterialByCategoryName(int catId) {
		System.out.println("inside getMaterialByOrgId() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("SELECT * FROM materialmaster m "
				+ " JOIN categorymaster c ON c.category_id=m.category_id WHERE c.category_id=:catId AND "
				+ " c.organisation_id IN (SELECT c.organisation_id FROM categorymaster WHERE c.category_id=:catId)")
				.addEntity(Material.class);
		query.setInteger("catId", catId);
		List<Material> result = query.list();
		System.out.println("Resultlist" + result);
		return result;
	}
	

}

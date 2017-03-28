package adnate.spring.daos;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import adnate.spring.pojos.Warehouse;

@Transactional
@Repository
public class WarehouseDaoImpl implements WarehouseDao{
	@Autowired
	private SessionFactory sFactory;
	
	@Override
	public int insertWarehouse(Warehouse w) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(w);
		return w.getWarehouseId();
	}

	@Override
	public int updateWarehouse(Warehouse w) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.update(w);
		return w.getWarehouseId();
	}

	@Override
	public int deleteWarehouse(Warehouse w) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.delete(w);
		return w.getWarehouseId();
	}

	@Override
	public Warehouse findWarehouse(int id) {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("inside findwarehousedaoimpl");
		return (Warehouse) hbSession.get(Warehouse.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> getAllWarehouses() {
		Session hbSession = sFactory.getCurrentSession();
		Criteria cr = hbSession.createCriteria(Warehouse.class);
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findRegion() {
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("SELECT DISTINCT(w.warehouse_region) FROM warehousemaster w");	
		return (List<String>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> getWarehouseByOrgId(String organisationId) {
		System.out.println("inside getWarehouseByOrgId() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("select * from warehousemaster where organisation_id=:organisationId")
				.addEntity(Warehouse.class);
		query.setString("organisationId", organisationId);
		return query.list();
	}
}

/*package adnate.spring.daos;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adnate.spring.pojos.Warehouse;

@Transactional
@Repository
public class WarehouseDaoImpl implements WarehouseDao{
	@Autowired
	private SessionFactory sFactory;
	
	@Override
	public void insertWarehouse(Warehouse w) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(w);
	}

	@Override
	public void updateWarehouse(Warehouse w) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.update(w);
	}

	@Override
	public void deleteWarehouse(Warehouse w) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.delete(w);
	}

	@Override
	public Warehouse findWarehouse(int id) {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("inside findwarehousedaoimpl");
		return (Warehouse) hbSession.get(Warehouse.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> getAllWarehouses() {
		Session hbSession = sFactory.getCurrentSession();
		Criteria cr = hbSession.createCriteria(Warehouse.class);
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findRegion() {
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("SELECT DISTINCT(w.warehouse_region) FROM warehousemaster w");	
		return (List<String>) query.list();
	}
}
*/
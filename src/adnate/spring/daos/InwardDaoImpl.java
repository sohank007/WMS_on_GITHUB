package adnate.spring.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Query;

import adnate.spring.pojos.Inward;
import adnate.spring.pojos.InwardDetails;

@Transactional
@Repository
public class InwardDaoImpl implements InwardDao{
	
	@Autowired
	private SessionFactory sFactory;
	
	@Override
	public int insertInward(Inward inwardPOJO) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(inwardPOJO);
		int inwardId = inwardPOJO.getInId();
		return inwardId;
		
	}
	
	@Override
	public int insertInwardDetails(InwardDetails inwardDetailsPOJO) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(inwardDetailsPOJO);
		int inwardDetails_inId = inwardDetailsPOJO.getInward().getInId();
		return inwardDetails_inId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InwardDetails> getInwardDetails() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("InWardDetails hbSession Created");
		Criteria cr = hbSession.createCriteria(InwardDetails.class);
		System.out.println("returning InwardDetlist");
		return cr.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Inward> getInwardObject() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("InWardObject hbSession Created");
		Criteria cr = hbSession.createCriteria(Inward.class);
		System.out.println("returning InwardObj list");
		System.out.println(cr.list().toString());
		return cr.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTodayInward() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getTodayInward hbSession Created");
		Query query = hbSession.createSQLQuery("SELECT in_id,in_date FROM inwardmaster WHERE in_date >= DATE_SUB(CURDATE(), INTERVAL 5 DAY) AND in_date <= CURDATE() ORDER BY in_date DESC");
		
		List<Object> result = query.list();
		System.out.println("list" +result);
		return result;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTodayInwardAmount() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getTodayInwardAmount hbSession Created");
		Query query = hbSession.createSQLQuery("SELECT i.`in_id`, i.`in_date`, m.material_name, id.in_qty, id.in_qty*m.unit_price AS amt FROM inward_details id, inwardmaster i, materialmaster m WHERE i.in_date >= DATE_SUB(CURDATE(), INTERVAL 5 DAY) AND i.in_date <= CURDATE()AND i.`in_id`=id.`in_id` AND id.material_id = m.material_id ORDER BY i.in_date DESC");
		
		List<Object> result = query.list();
		System.out.println("list" +result);
		return result;
	}
}

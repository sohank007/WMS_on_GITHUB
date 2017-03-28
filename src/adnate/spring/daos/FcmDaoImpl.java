package adnate.spring.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adnate.spring.pojos.Fcm;
import adnate.spring.pojos.Sequence;


@Transactional
@Repository
public class FcmDaoImpl implements FcmDao{

	@Autowired
    SessionFactory sFactory;

	@Override
	public void insertToken(Fcm fcm) {
		Session hbSession=sFactory.getCurrentSession();
		hbSession.save(fcm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fcm> getAlltokens() {
		Session hbSession =sFactory.getCurrentSession();
		Criteria criteria=hbSession.createCriteria(Fcm.class);
		return criteria.list();
	}

	
	@Override
	public Fcm getTokenByIMEI(String imei) {
	  Session hbSession=sFactory.getCurrentSession();
	  Query query=hbSession.createSQLQuery("Select * FROM fcm WHERE imei=:imei")
			  .addEntity(Fcm.class);
	  query.setString("imei",imei);
	  Fcm fcm=(Fcm) query.uniqueResult();
	  return fcm;
	   
	}

	@Override
	public void updateToken(Fcm fcm) {
		Session hbSession=sFactory.getCurrentSession();
		hbSession.update(fcm);
	}

	

}

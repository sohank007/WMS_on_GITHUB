package adnate.spring.daos;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import adnate.spring.pojos.Contractor;
@Transactional
@Repository
public class ContractorDaoImpl implements ContractorDao{

	@Autowired
	private SessionFactory sFactory;
	@Override
	public int insertContractor(Contractor ctr) {
		System.out.println("inside hbSession InsertContractor()");
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("hbSession created.");
		System.out.println("Inserting Contractor");
		hbSession.save(ctr);
		return ctr.getCtrId();
	}

	@Override
	public int updateContractor(Contractor ctr) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.update(ctr);
		return ctr.getCtrId();
	}

	@Override
	public int deleteContractor(Contractor ctr) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.delete(ctr);
		return ctr.getCtrId();
	}

	@Override
	public Contractor findContractor(int id) {
		Session hbSession = sFactory.getCurrentSession();
		return (Contractor) hbSession.get(Contractor.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contractor> getAllContractors() {
		Session hbSession = sFactory.getCurrentSession();
		Criteria cr = hbSession.createCriteria(Contractor.class);
		System.out.println("in getAllContractors()");
		System.out.println("hbSession created!");
		System.out.println(cr.list());
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contractor> getContractorByOrgId(String organisationId) {
		System.out.println("inside getContractorByOrgId() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("select * from contractormaster where organisation_id=:organisationId")
				.addEntity(Contractor.class);
		query.setString("organisationId", organisationId);
		return query.list();
	}

	@Override
	public boolean updateContractorByMailId(String mailId) {
		System.out.println("inside updateContractorByMailId() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("UPDATE contractormaster SET ctrStatus = 'active' "
				+ " WHERE contractor_email =:mailId")
				.addEntity(Contractor.class);
		int updateVariable = query.executeUpdate();
		if(updateVariable!=0){
			return true;					
		}
		return false;
	}

	@Override
	public Contractor findCtrByMailId(String mailId) {
		System.out.println("inside updateContractorByMailId() method");
		Session hbSession = sFactory.getCurrentSession();
		Contractor ctrObj =  (Contractor) hbSession.get(Contractor.class, mailId);
		System.out.println("ctrObj : " + ctrObj);
		return ctrObj;
	}
}

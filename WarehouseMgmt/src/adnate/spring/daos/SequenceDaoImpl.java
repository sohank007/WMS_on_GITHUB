package adnate.spring.daos;

import adnate.spring.pojos.Sequence;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class SequenceDaoImpl implements SequenceDao{

	
	@Autowired
	SessionFactory sFactory;
	
	@Override
	public Sequence findSequence(int id) {
		Session hbSession=sFactory.getCurrentSession();
		return (Sequence) hbSession.get(Sequence.class,id);
	}

	@Override
	public Sequence getSequenceByTableName(String tableName) {
		Session hbSession=sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("SELECT * FROM sequence WHERE tablename=:tableName")
				.addEntity(Sequence.class);
		
		query.setString("tableName", tableName);
		
		Sequence sequence=(Sequence) query.uniqueResult();
		return sequence;
	}

	@Override
	public void updateSequence(Sequence sequence) {
		Session hbSession=sFactory.getCurrentSession();
		hbSession.update(sequence);
		
	}

}

package adnate.spring.services;

import adnate.spring.pojos.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.SequenceDao;

@Service
public class ISequenceServiceImpl implements SequenceService{

	   
		@Autowired
	     private SequenceDao sequenceDao;
	@Override
	public Sequence findSequence(int id) {
		return sequenceDao.findSequence(id);
	}

	@Override
	public Sequence getSequenceByTableName(String tableName) {
		return sequenceDao.getSequenceByTableName(tableName);
	}

	@Override
	public void updateSequence(Sequence sequence) {
		sequenceDao.updateSequence(sequence);
		
	}

}

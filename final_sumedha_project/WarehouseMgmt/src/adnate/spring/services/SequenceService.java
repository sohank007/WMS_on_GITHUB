package adnate.spring.services;

import adnate.spring.pojos.Sequence;

public interface SequenceService {
	public Sequence findSequence(int id);
	public Sequence getSequenceByTableName(String tableName);
	public void updateSequence(Sequence sequence);
}

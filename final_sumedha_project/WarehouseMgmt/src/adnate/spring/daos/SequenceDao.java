package adnate.spring.daos;

import adnate.spring.pojos.Sequence;

public interface SequenceDao {
   public Sequence findSequence(int id);
   public Sequence getSequenceByTableName(String tableName);
   public void updateSequence(Sequence sequence);
}

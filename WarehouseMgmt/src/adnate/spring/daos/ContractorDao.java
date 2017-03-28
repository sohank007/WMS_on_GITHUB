package adnate.spring.daos;

import adnate.spring.pojos.Contractor;
import java.util.List;

public interface ContractorDao {
	public int insertContractor(Contractor ctr);
	public int updateContractor(Contractor ctr);
	public int deleteContractor(Contractor ctr);
	public Contractor findContractor(int id);
	
	public List<Contractor> getAllContractors();
	
	public List<Contractor> getContractorByOrgId(String organisationId);
	public boolean updateContractorByMailId(String mailId);
}

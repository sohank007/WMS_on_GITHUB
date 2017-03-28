package adnate.spring.services;

import java.util.List;

import adnate.spring.pojos.Contractor;

public interface IContractorServices {
	public Contractor findContractor(int id);
	public int insertContractor(Contractor ctr);
	public int updateContractor(Contractor ctr);
	public int deleteContractor(Contractor ctr);	
	public List<Contractor> getContractors();
	
	public List<Contractor> getContractorByOrgId(String organisationId);
	
	public boolean updateContractorByMailId(String mailId);
	
	public Contractor findCtrByMailId(String mailId);
}

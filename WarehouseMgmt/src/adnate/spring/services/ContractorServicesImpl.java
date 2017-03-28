package adnate.spring.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.ContractorDao;
import adnate.spring.pojos.Contractor;

@Service
public class ContractorServicesImpl implements IContractorServices{

	@Autowired
	private ContractorDao contractordao;
	
	@Override
	public Contractor findContractor(int id) {
		return contractordao.findContractor(id);
	}

	@Override
	public int insertContractor(Contractor ctr) {
			System.out.println("inside ServiceMethod InsertCtr()");
			
			return contractordao.insertContractor(ctr);
		
	}

	@Override
	public int updateContractor(Contractor ctr) {
		return contractordao.updateContractor(ctr);
	}

	@Override
	public int deleteContractor(Contractor ctr) {
		return contractordao.deleteContractor(ctr);
	}

	@Override
	public List<Contractor> getContractors() {
		return contractordao.getAllContractors();
	}

	@Override
	public List<Contractor> getContractorByOrgId(String organisationId) {
		return contractordao.getContractorByOrgId(organisationId);
	}

	@Override
	public boolean updateContractorByMailId(String mailId) {
		return contractordao.updateContractorByMailId(mailId);
	}
}

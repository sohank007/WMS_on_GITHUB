package adnate.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import adnate.spring.daos.InwardDao;
import adnate.spring.pojos.Inward;
import adnate.spring.pojos.InwardDetails;

@Service
public class InwardServicesImpl implements IInwardServices{
	
	@Autowired
	private InwardDao inwarddao;
	
	@Override
	public List<InwardDetails> getInwardDetails() {
		System.out.println("in Servicemethod for InwardDetails");
		return inwarddao.getInwardDetails();
	}
	
	@Override
	public int insertInward(Inward inwardPOJO) {
		try {
			int inwardId = inwarddao.insertInward(inwardPOJO);
			return inwardId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int insertInwardDetails(InwardDetails inwardDetailsPOJO) {
		try {
			int inwardDetails_inId = inwarddao.insertInwardDetails(inwardDetailsPOJO);
			return inwardDetails_inId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<Inward> getInwardObject() {
		System.out.println("in Servicemethod for InwardObject");
		return inwarddao.getInwardObject();
	}
	
	@Override
	public List<Object> getTodayInward() {
		return inwarddao.getTodayInward();
	}

	@Override
	public List<Object> getTodayInwardAmount() {
		return inwarddao.getTodayInwardAmount();
	}

}

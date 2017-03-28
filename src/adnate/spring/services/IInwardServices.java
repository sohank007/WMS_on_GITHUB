package adnate.spring.services;

import java.util.List;

import adnate.spring.pojos.Inward;
import adnate.spring.pojos.InwardDetails;

public interface IInwardServices {
	public List<InwardDetails> getInwardDetails();
	public int insertInward(Inward inward);
	public int insertInwardDetails(InwardDetails inwardDetailsPOJO);
	public List<Inward> getInwardObject();
	public List<Object> getTodayInward();
	public List<Object> getTodayInwardAmount();
}

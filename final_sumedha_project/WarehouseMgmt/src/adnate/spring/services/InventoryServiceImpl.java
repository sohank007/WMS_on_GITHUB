package adnate.spring.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.InventoryDao;
import adnate.spring.pojos.InventoryPOJO;
import adnate.spring.pojos.InwardDetails;
import adnate.spring.pojos.OrderDetails;

@Service
public class InventoryServiceImpl implements IInventoryServices{
	@Autowired
	private InventoryDao inventorydao;
	

	@Override
	public boolean updateInventoryOnInward(InwardDetails inwardDetailsPOJO) {
		try {
			inventorydao.updateInventoryOnInward(inwardDetailsPOJO);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<InventoryPOJO> getInventory() {
		return inventorydao.getInventory();
	}

	
	@Override
	public boolean updateInventoryOnOutward(OrderDetails orderDetailsPOJO) {
		try {
			inventorydao.updateInventoryOnOutward(orderDetailsPOJO);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}



/*	@Override
	public List<Object> getTopSeller() {
		return inventorydao.getTopSeller();
	}*/
	
	@Override
	public List<Object> getMaterialShortage() {
		return inventorydao.getMaterialShortage();
	}

	@Override
	public List<Object> getMaterialAvailability() {
		return inventorydao.getMaterialAvailability();
	}

	@Override
	public List<Object> getTodaysOrders(Date date) {
		return inventorydao.getTodaysOrders(date);
	}

	@Override
	public List<Object> getTodaysInwards(Date date) {
		return inventorydao.getTodaysInwards(date);
	}

	@Override
	public List<InventoryPOJO> getInventoryByOrgId(String organisationId) {
		return inventorydao.getInventoryByOrgId(organisationId);
	}
}

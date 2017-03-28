package adnate.spring.services;

import java.sql.Date;
import java.util.List;

import adnate.spring.pojos.InventoryPOJO;
import adnate.spring.pojos.InwardDetails;
import adnate.spring.pojos.OrderDetails;

public interface IInventoryServices {
	public List<InventoryPOJO> getInventory();
	
	public boolean updateInventoryOnInward(InwardDetails inwardDetailsPOJO);
	public boolean updateInventoryOnOutward(OrderDetails orderDetailsPOJO);

	//public List<Object> getTopSeller();

	public List<Object> getMaterialShortage();
	public List<Object> getMaterialAvailability();
	public List<Object> getTodaysOrders(Date date);
	public List<Object> getTodaysInwards(Date date);

	public List<InventoryPOJO> getInventoryByOrgId(String organisationId);
}

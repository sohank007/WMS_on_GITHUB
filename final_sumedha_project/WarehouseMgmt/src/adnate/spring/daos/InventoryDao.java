package adnate.spring.daos;

import java.sql.Date;
import java.util.List;

import adnate.spring.pojos.InventoryPOJO;
import adnate.spring.pojos.InwardDetails;
import adnate.spring.pojos.OrderDetails;

public interface InventoryDao {
	public void updateInventoryOnInward(InwardDetails inwardDetailsPOJO);
	public void updateInventoryOnOutward(OrderDetails orderDetailsPOJO);	
	public List<InventoryPOJO> getInventory();	
	public InventoryPOJO findInventory(int mtrlId, int wId);
//	public List<Object> getTopBuyer();
/*	public List<Object> getTopSeller();*/
	public List<Object> getMaterialShortage();
	public List<Object> getMaterialAvailability();
	
	public List<Object> getTodaysOrders(Date date);
	public List<Object> getTodaysInwards(Date date);
	
	public List<InventoryPOJO> getInventoryByOrgId(String organisationId);
	
}

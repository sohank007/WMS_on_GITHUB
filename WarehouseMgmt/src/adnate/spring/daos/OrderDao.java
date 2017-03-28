package adnate.spring.daos;

import java.text.ParseException;
import java.util.Date;
//import java.util.ArrayList;
import java.util.List;

/*import adnate.spring.pojos.InventoryPOJO;
import adnate.spring.pojos.Material;*/
import adnate.spring.pojos.Order;
import adnate.spring.pojos.OrderDetails;

public interface OrderDao {
	public List<OrderDetails> getOrderDetails();
	public List<Order> getOrderObject();
	public int insertOrder(Order orderPOJO);
	public int insertOrderDetails(OrderDetails orderDetailsPOJO);
	public List<Object> findOrderDetailsById(int id);
	public Order findOrder(int id);
	public List<Object[]> findAllInvoiceDetails(List<Integer> ids);
	
/*	public List<OrderDetails> getTopOrderQty();
	public ArrayList<Object> getTopMaterial();
	public List<Object> getTopSeller();
	//public List<InventoryPOJO> getTopBuyer();
	List<Object> getTopBuyer();*/
	public List<Object> getTopMaterial();
	public List<Object> getTopSeller();
	public List<Object> getTopBuyer();
/*	public List<Object> getTodayOrder();
	public List<Object> getTodayOrderAmount();*/
	
	
	public List<OrderDetails> findOrderDetailsById(List<Integer> intList);
	
	public List<OrderDetails> getOrderDetailsInvoiceStatus(List<Integer> mtrlIdList, List<Integer> intList);
	public void updateInvoiceStatusOrderDetails(String status, List<Integer> mtrlIdList, List<Integer> intList);
	public List<Object> completeInvoiceOrderDetails(String status, List<Integer> mtrlIdList, List<Integer> intList);
	
	public List<Order> getOrderByOrgId(String organisationId);
	
	public List<Object> getOrdersBetweenDates(long fromDate, long toDate) throws ParseException;
}

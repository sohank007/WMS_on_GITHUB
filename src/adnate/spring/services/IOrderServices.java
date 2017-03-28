package adnate.spring.services;


import java.util.List;

import adnate.spring.pojos.Order;
import adnate.spring.pojos.OrderDetails;

public interface IOrderServices {
	public List<OrderDetails> getOrderDetails();
	public List<Order> getOrderObject();
	public int insertOrder(Order orderPOJO);
	public int insertOrderDetails(OrderDetails orderDetailsPOJO);
	public List<Object> findOrderDetailsById(int id);
	public Order findOrder(int id);
	public List<Object[]>	findAllInvoiceDetails(List<Integer> ids);
	
	public List<Object> getTopMaterial();
	public List<Object> getTopSeller();
	public List<Object> getTopBuyer();

/*	public List<Object> getTodayOrder();
	public List<Object> getTodayOrderAmount();*/
	//public List<InventoryPOJO> getTopBuyer();
	
}

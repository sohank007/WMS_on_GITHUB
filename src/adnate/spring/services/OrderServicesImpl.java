package adnate.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.OrderDao;
/*import adnate.spring.pojos.InventoryPOJO;
import adnate.spring.pojos.Material;*/
import adnate.spring.pojos.Order;
import adnate.spring.pojos.OrderDetails;

@Service
public class OrderServicesImpl implements IOrderServices{
	
	@Autowired
	private OrderDao orderdao;
	
	@Override
	public List<OrderDetails> getOrderDetails() {
		System.out.println("in Servicemethod for OrderDetails");
		return orderdao.getOrderDetails();
	}

	@Override
	public List<Order> getOrderObject() {
		System.out.println("in Servicemethod for OrderObject");
		return orderdao.getOrderObject();
	}

	@Override
	public int insertOrder(Order orderPOJO) {
		try {
			int orderId = orderdao.insertOrder(orderPOJO);
			return orderId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertOrderDetails(OrderDetails orderDetailsPOJO) {
		try {
			int orderDetails_orderId = orderdao.insertOrderDetails(orderDetailsPOJO);
			return orderDetails_orderId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public List<Object> getTopMaterial() {
		return orderdao.getTopMaterial();
	}

	@Override
	public List<Object> getTopSeller() {
		return orderdao.getTopSeller();
	}
	
	@Override
	public List<Object> getTopBuyer() {
		return orderdao.getTopBuyer();
	}

	@Override
	public List<Object> findOrderDetailsById(int id) {
		return orderdao.findOrderDetailsById(id);
	}

	@Override
	public List<Object[]> findAllInvoiceDetails(List<Integer>ids) {
		return orderdao.findAllInvoiceDetails(ids);
	}

	@Override
	public Order findOrder(int id) {
		return orderdao.findOrder(id);
	}

/*	@Override
	public List<Object> getTodayOrder() {
		return orderdao.getTodayOrder();
	}

	@Override
	public List<Object> getTodayOrderAmount() {
		return orderdao.getTodayOrderAmount();
	}
*/
}

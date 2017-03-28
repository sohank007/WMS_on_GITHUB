package adnate.spring.daos;

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
}

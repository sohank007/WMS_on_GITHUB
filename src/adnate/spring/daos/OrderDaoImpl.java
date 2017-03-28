package adnate.spring.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import adnate.spring.pojos.Order;
import adnate.spring.pojos.OrderDetails;


@Transactional
@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetails> getOrderDetails() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("OrderDetails hbSession Created");
		Criteria cr = hbSession.createCriteria(OrderDetails.class);
		System.out.println("returning orderDetlist");
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderObject() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("OrderObject hbSession Created");
		Criteria cr = hbSession.createCriteria(Order.class);
		System.out.println("returning OrderObject list");
		System.out.println(cr.list().toString());
		return cr.list();
	}

	@Override
	public int insertOrder(Order orderPOJO) {
		Session hbSession = sFactory.getCurrentSession();
		/*
		 * Query query = hbSession.getNamedQuery("callGenOrderStoreProcedure")
		 * .setParameter("stockCode", "7277");
		 */
		hbSession.save(orderPOJO);
		int orderId = orderPOJO.getOrderId();
		return orderId;
	}

	@Override
	public int insertOrderDetails(OrderDetails orderDetailsPOJO) {
		Session hbSession = sFactory.getCurrentSession();
		hbSession.save(orderDetailsPOJO);
		int orderDetails_orderId = orderDetailsPOJO.getOrder().getOrderId();
		return orderDetails_orderId;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTopMaterial() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getTopMaterial hbSession Created");
		Query query = hbSession
				.createSQLQuery("SELECT SUM(order_qty) TotalQuantity,OD.material_id,MM.material_name"
						+ " FROM order_details OD JOIN materialmaster MM ON OD.material_id=MM.material_id JOIN ordermaster "
						+ "OM ON OD.order_id=OM.order_id GROUP BY material_id  ORDER BY TotalQuantity DESC LIMIT 5;");
		List<Object> result = query.list();
		System.out.println("list" + result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTopSeller() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getTopSeller hbSession Created");
		Query query = hbSession
				.createSQLQuery("SELECT SUM(order_qty*mm.unit_price) TotalQuantity,WD.warehouse_name FROM warehousemaster "
						+ "WD JOIN ordermaster OM ON WD.warehouse_id=OM.warehouse_id JOIN order_details OD "
						+ "ON OM.order_id=OD.order_id JOIN materialmaster mm  ON mm.material_id=od.material_id  GROUP "
						+ "BY warehouse_name ORDER BY TotalQuantity DESC LIMIT 5;");
		List<Object> result = query.list();
		System.out.println("list" + result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTopBuyer() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getTopBuyer hbSession Created");
		Query query = hbSession
				.createSQLQuery("SELECT SUM(order_qty * MM.unit_price) TotalQuantity,OM.contractor_id,CM.contractor_name "
						+ "FROM ordermaster OM JOIN contractormaster CM ON OM.contractor_id=CM.contractor_id "
						+ "JOIN order_details OD ON OD.order_id=OM.order_id JOIN materialmaster MM "
						+ " ON MM.material_id=OD.material_id GROUP BY contractor_id  ORDER BY TotalQuantity DESC LIMIT 5;");
		// .addEntity(OrderDetails.class);

		List<Object> result = query.list();
		System.out.println("list" + result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findOrderDetailsById(int id) {
		Session hbSession=sFactory.getCurrentSession();
		System.out.println("findOrderDetailsById hbsession Created");
/*		List<OrderDetails> o=(List<OrderDetails>) hbSession.get(OrderDetails.class, id);*/
		Query query = hbSession.createSQLQuery("SELECT OD.order_id,MM.material_name,OD.order_qty,"
				+ "OD.return_qty,OD.issued_date,OD.return_date FROM order_details OD JOIN "
				+ "materialmaster MM ON MM.material_id=OD.material_id WHERE OD.order_id=:id");
		query.setInteger("id", id);
		List<Object> result = (List<Object>)query.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAllInvoiceDetails(List<Integer> ids) {
		Session hbSession=sFactory.getCurrentSession();
		System.out.println("findAllInvoiceDetails hbsession Created");
/*		List<OrderDetails> o=(List<OrderDetails>) hbSession.get(OrderDetails.class, id);*/
/*		Query query = hbSession.createSQLQuery("SELECT OD.order_id,OD.material_id,MM.unit_price,OD.order_qty,"
				+ " SUM(OD.order_qty*MM.unit_price) FROM ordermaster OM JOIN order_details OD"
				+ " ON OD.order_id=OM.order_id JOIN materialmaster MM ON "
				+ " OD.material_id=MM.material_id WHERE OM.order_id in (:ids) group by OD.material_id");*/
		Query query=hbSession.createSQLQuery("SELECT OD.order_id,CM.contractor_id,CM.contractor_name,"
				+ "OD.material_id,MM.material_name, MM.unit_price,OD.order_qty,OD.order_qty*MM.unit_price "
				+ "AS amt,WM.warehouse_region FROM ordermaster OM JOIN order_details OD ON OD.order_id=OM.order_id "
				+ "JOIN materialmaster MM ON OD.material_id=MM.material_id JOIN contractormaster CM "
				+ "ON OM.contractor_id=CM.contractor_id JOIN warehousemaster WM ON "
				+ "WM.warehouse_id=OM.warehouse_id WHERE OM.order_id IN (:ids) ");
		query.setParameterList("ids", ids);
		/*query.setInteger("id", id);*/
		List<Object[]> result = (List<Object[]>)query.list();
		return result;
	}

	@Override
	public Order findOrder(int id) {
		Session hbSession=sFactory.getCurrentSession();
		return (Order)hbSession.get(Order.class,id);
	}
	
	
}
/*	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTodayOrder() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getTodayOrder() hbSession Created");
		Query query = hbSession
				.createSQLQuery("SELECT order_id,order_date FROM ordermaster WHERE order_date >= DATE_SUB(CURDATE(), "
						+ "INTERVAL 5 DAY) AND order_date <= CURDATE() ORDER BY order_date DESC");

		List<Object> result = query.list();
		System.out.println("list" + result);
		return result;

	}*/

/*	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTodayOrderAmount() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getTodayOrderAmount() hbSession Created");
		Query query = hbSession
				.createSQLQuery("SELECT o.order_id, o.order_date, m.material_name,"
						+ " od.order_qty, od.order_qty*m.unit_price AS amt FROM order_details od, "
						+ "ordermaster o, materialmaster m WHERE o.order_date >= DATE_SUB(CURDATE(), "
						+ "INTERVAL 5 DAY) AND o.order_date <= CURDATE()AND o.order_id=od.order_id AND "
						+ "od.material_id = m.material_id ORDER BY o.order_date DESC");

		List<Object> result = query.list();
		System.out.println("list" + result);
		return result;

	}*/
	/*
	 * @SuppressWarnings({ "unchecked" })
	 * 
	 * @Override public List<OrderDetails> getTopOrderQty() { Session hbSession
	 * = sFactory.getCurrentSession();
	 * System.out.println("getTopOrderQty hbSession Created"); Query query =
	 * hbSession.createSQLQuery(
	 * "SELECT * FROM order_details ORDER BY order_qty DESC LIMIT 0,5")
	 * .addEntity(OrderDetails.class); List<OrderDetails> result = query.list();
	 * System.out.println("list" +result); return result; }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public ArrayList<Object> getTopMaterial() { Session hbSession =
	 * sFactory.getCurrentSession();
	 * System.out.println("getTopMaterial hbSession Created"); Query query =
	 * hbSession.createSQLQuery(
	 * "SELECT DISTINCT(m.material_name) FROM materialmaster m, order_details od WHERE m.material_id = od.material_id ORDER BY order_qty DESC LIMIT 0,5"
	 * ); ArrayList<Object> result = (ArrayList<Object>) query.list();
	 * System.out.println("list" +result); return result; }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Object> getTopSeller() { Session hbSession =
	 * sFactory.getCurrentSession();
	 * System.out.println("getTopSeller hbSession Created"); Query query =
	 * hbSession.createSQLQuery(
	 * "SELECT w.`warehouse_name`, total_qty*unit_price as amt FROM warehousemaster w, inventory_details invd WHERE w.warehouse_id = invd.warehouse_id ORDER BY total_qty*unit_price DESC LIMIT 0,5"
	 * ); List<Object> result = query.list(); System.out.println("list"
	 * +result); return result; }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Object> getTopBuyer() { Session hbSession =
	 * sFactory.getCurrentSession();
	 * System.out.println("getTopBuyer hbSession Created"); Query query =
	 * hbSession.createSQLQuery(
	 * "SELECT ordr.*, invd.* FROM ordermaster ordr, materialmaster m, inventory_details invd  WHERE m.material_id = invd.material_id ORDER BY invd.total_qty*m.unit_price DESC LIMIT 0,5"
	 * ) .addEntity(InventoryPOJO.class) .addEntity(Order.class);
	 * 
	 * List<Object> result = query.list(); System.out.println("list" +result);
	 * return result; }
	 */
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<InventoryPOJO> getTopBuyer() { Session hbSession =
	 * sFactory.getCurrentSession();
	 * System.out.println("getTopBuyer hbSession Created"); Query query =
	 * hbSession.createSQLQuery(
	 * "SELECT ctr.contractor_id, ctr.`contractor_name`, invd.`total_qty`, invd.`unit_price`, total_qty*invd.`unit_price` FROM contractormaster ctr, inventory_details invd, materialmaster m WHERE m.material_id = invd.material_id ORDER BY total_qty*unit_price DESC LIMIT 0,5"
	 * ) .addEntity(InventoryPOJO.class); List<InventoryPOJO> result =
	 * query.list(); System.out.println("list" +result); return result; }
	 */




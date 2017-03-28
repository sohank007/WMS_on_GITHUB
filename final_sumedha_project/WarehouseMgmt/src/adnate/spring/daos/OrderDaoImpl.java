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
		//System.out.println(cr.list().toString());
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
				+ "AS amt,WM.warehouse_region,MM.unit_of_measure FROM ordermaster OM JOIN order_details OD ON "
				+ "OD.order_id=OM.order_id "
				+ "JOIN materialmaster MM ON OD.material_id=MM.material_id JOIN contractormaster CM "
				+ "ON OM.contractor_id=CM.contractor_id JOIN warehousemaster WM ON "
				+ "WM.warehouse_id=OM.warehouse_id WHERE OM.order_id IN (:ids) AND OD.inv_status='Delivered' ");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetails> findOrderDetailsById(List<Integer> intList) {
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("SELECT * FROM order_details OD JOIN "
				+ "materialmaster MM ON MM.material_id=OD.material_id WHERE OD.order_id IN (:intList)")
				.addEntity(OrderDetails.class);
		query.setParameterList("intList", intList);
		List<OrderDetails> result = query.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetails> getOrderDetailsInvoiceStatus(List<Integer> mtrlIdList, List<Integer> intList) {
		System.out.println("mtrlIdList : -> " + mtrlIdList);
		System.out.println("intList: ->" + intList);
		Session hbSession = sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("SELECT * FROM order_details WHERE order_id IN (:intList) AND material_id IN (:mtrlIdList)")
				.addEntity(OrderDetails.class);
		//query.setString("status", status);
		query.setParameterList("intList", intList);
		query.setParameterList("mtrlIdList", mtrlIdList);
		return query.list();
	}

	@Override
	public void updateInvoiceStatusOrderDetails(String status, List<Integer> mtrlIdList, List<Integer> intList) {
		System.out.println("updateInvoiceStatusOrderDetails called");
		System.out.println("status: "+status);
		System.out.println(" intList : " + intList);
		System.out.println("MaterialIdList : "+ mtrlIdList);
		Session hbSession = sFactory.getCurrentSession();
		Query query=hbSession.createSQLQuery("UPDATE order_details SET inv_status=:status WHERE material_id IN (:mtrlIdList) AND order_id IN (:intList)");
		query.setString("status", status);
		query.setParameterList("mtrlIdList", mtrlIdList);
		query.setParameterList("intList", intList);
		query.executeUpdate();
		System.out.println("InvoiceStatus in orderDetails updated for materialIdList" + mtrlIdList +"against orderId intList" + intList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> completeInvoiceOrderDetails(String status, List<Integer> mtrlIdList, List<Integer> intList) {
		System.out.println("completeInvoiceOrderDetails() called");
		System.out.println("status: "+status);
		System.out.println(" intList : " + intList);
		System.out.println("MaterialIdList : "+ mtrlIdList);
		Session hbSession = sFactory.getCurrentSession();
		//Query query = hbSession.createSQLQuery("CALL stored_proc1(inv_status varchar, order_id int)");
		Query query=hbSession.createSQLQuery("SELECT COUNT(material_id) FROM order_details WHERE "
				+ " order_id IN (:intList) AND material_id IN (:mtrlIdList) AND inv_status='Delivered'");
		query.setParameterList("intList", intList);
		query.setParameterList("mtrlIdList", mtrlIdList);
		List<Object> result = (List<Object>)query.list();
		System.out.println("result Set for incompleteInvoice : -> " + result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderByOrgId(String organisationId) {
		System.out.println("inside getOrderByOrgId() method");
		Session hbSession = sFactory.getCurrentSession();
		Query query = hbSession.createSQLQuery("select * from ordermaster where organisation_id=:organisationId")
				.addEntity(Order.class);
		query.setString("organisationId", organisationId);
		return query.list();
	}
	
	
}





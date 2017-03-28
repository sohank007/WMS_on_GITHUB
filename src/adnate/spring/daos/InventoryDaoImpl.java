package adnate.spring.daos;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.hibernate.transform.Transformers;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import adnate.spring.pojos.Contractor;
import adnate.spring.pojos.InventoryPOJO;
import adnate.spring.pojos.InwardDetails;
import adnate.spring.pojos.Material;
import adnate.spring.pojos.OrderDetails;
import adnate.spring.pojos.Warehouse;

@Transactional
@Repository
public class InventoryDaoImpl implements InventoryDao {
	@Autowired
	private SessionFactory sFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<InventoryPOJO> getInventory() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("InventoryPOJO hbSession Created");
		Criteria cr = hbSession.createCriteria(InventoryPOJO.class);
		System.out.println("returning InventoryPOJO list");
		System.out.println(cr.list().toString());
		return cr.list();
	}

	@Override
	public InventoryPOJO findInventory(int mtrlId, int wId) {
		List<InventoryPOJO> list = getInventory();
		System.out.println("INVENTORY POJO LIST :" + list);
		for (InventoryPOJO inventoryObj : list) {
			Material material = inventoryObj.getMaterial();
			int inventory_materialID = material.getMaterialID();
			Warehouse warehouse = inventoryObj.getWarehouse();
			int inventory_wId = warehouse.getWarehouseId();
			if (inventory_materialID == mtrlId && inventory_wId == wId) {
				return inventoryObj;
			}

		}
		System.out.println("returing null as obj not matched");
		return null;
	}

	@Override
	public void updateInventoryOnInward(InwardDetails inwardDetailsPOJO) {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("updateInventoryOnInward() called ");
		InventoryPOJO inventoryPOJO = new InventoryPOJO();
		// get mtrlID and mtrlQty from inwardDetailsPOJO
		Material material = inwardDetailsPOJO.getMaterial();
		int mtrlId = material.getMaterialID();
		double mtrlQty = inwardDetailsPOJO.getInQty();
		Warehouse warehouse = inwardDetailsPOJO.getInward().getWarehouse();
		int wId = warehouse.getWarehouseId();
		String orgId=inwardDetailsPOJO.getInward().getOrganisationId();
		System.out.println("on get from inwardDetailsPOJO");
		System.out.println("mtrlID" + mtrlId + "mtrlQty" + mtrlQty + "wId"
				+ wId);
        inventoryPOJO.setOrganisationId(orgId);
		inventoryPOJO = findInventory(mtrlId, wId);
		System.out.println("got inventory obj from findBy() " + inventoryPOJO);

		if (inventoryPOJO != null) {
			double inventory_availQty = inventoryPOJO.getAvailableQty();
			double inventory_totalQty = inventoryPOJO.getTotalQty();
			inventory_availQty += mtrlQty;
			inventory_totalQty += mtrlQty;
			inventoryPOJO.setAvailableQty(inventory_availQty);
			inventoryPOJO.setTotalQty(inventory_totalQty);
			inventoryPOJO.setOrganisationId(orgId);
			hbSession.update(inventoryPOJO);
			System.out.println("updated inventorypPOJO :" + inventoryPOJO);
		}

		else {
			InventoryPOJO inventoryPOJOObj = new InventoryPOJO();
			System.out.println("in else : saving new inventoryPOJOObj-->");

			Material material2 = (Material) hbSession.get(Material.class,
					mtrlId);
			inventoryPOJOObj.setMaterial(material2);
			Warehouse warehouse2 = (Warehouse) hbSession.get(Warehouse.class,
					wId);
			inventoryPOJOObj.setWarehouse(warehouse2);
			inventoryPOJOObj.setTotalQty(mtrlQty);
			inventoryPOJOObj.setAvailableQty(mtrlQty);
			inventoryPOJOObj.setOrganisationId(orgId);
			System.out.println("on Set to inventoryPOJOObj");
			System.out.println("mtrlID" + mtrlId + "mtrlQty" + mtrlQty + "wId"
					+ wId);
			hbSession.save(inventoryPOJOObj);
		}

	}

	@Override
	public void updateInventoryOnOutward(OrderDetails orderDetailsPOJO) {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("updateInventoryOnOutward() called ");
		InventoryPOJO inventoryPOJO = new InventoryPOJO();
		Material material = orderDetailsPOJO.getMaterial();
		int mtrlId = material.getMaterialID();
		double mtrlQty = orderDetailsPOJO.getOrderQty();

		Warehouse warehouse = orderDetailsPOJO.getOrder().getWarehouse();
		int wId = warehouse.getWarehouseId();
		String orgId=orderDetailsPOJO.getOrder().getOrganisationId();
		System.out.println("on get from orderDetailsPOJO");
		System.out.println("mtrlID" + mtrlId + "mtrlQty" + mtrlQty + "wId"
				+ wId);
		
		inventoryPOJO = findInventory(mtrlId, wId);
		System.out.println("got inventory obj from findBy() " + inventoryPOJO);

		if (inventoryPOJO != null) {
			double inventory_availQty = inventoryPOJO.getAvailableQty();
/*			double inventory_totalQty = inventoryPOJO.getTotalQty();*/
			if (inventory_availQty >= mtrlQty) {
				inventory_availQty -= mtrlQty;
				/*inventory_totalQty -= mtrlQty;*/
				inventoryPOJO.setAvailableQty(inventory_availQty);
				inventoryPOJO.setOrganisationId(orgId);
			/*	inventoryPOJO.setTotalQty(inventory_totalQty);*/
				hbSession.update(inventoryPOJO);
				System.out.println("updated inventorypPOJO :" + inventoryPOJO);
			} else {
				System.out.println("can not outward !");
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getMaterialShortage() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getMaterialShortage() called ");
		Query query = hbSession
				.createSQLQuery("SELECT w.warehouse_name, m.material_name, invd.available_qty "
						+ "FROM inventory_details invd, materialmaster m, warehousemaster w "
						+ " WHERE invd.material_id=m.material_id AND invd.warehouse_id=w.warehouse_id AND "
						+ "invd.available_qty < 100");
		// .addEntity(OrderDetails.class);

		List<Object> result = query.list();
		System.out.println("list" + result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getMaterialAvailability() {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getmaterialAvailability() called ");
		Query query = hbSession
				.createSQLQuery("SELECT m.material_name, w.warehouse_name,"
						+ " invd.available_qty FROM inventory_details invd, materialmaster m,"
						+ " warehousemaster w WHERE m.material_id = invd.material_id AND "
						+ "w.warehouse_id = invd.warehouse_id ORDER BY warehouse_name");
		List<Object> result = query.list();
		System.out.println("list" + result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTodaysOrders(Date date) {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getTodaysOrders() called ");
		Query query = hbSession
				.createSQLQuery("SELECT wm.warehouse_name,mm.material_name,SUM(od.order_qty)"
						+ " FROM order_details od JOIN ordermaster om ON od.order_id=om.order_id "
						+ "JOIN warehousemaster wm ON wm.warehouse_id=om.warehouse_id JOIN contractormaster"
						+ " cm ON cm.contractor_id=om.contractor_id JOIN materialmaster mm ON"
						+ " mm.material_id=od.material_id WHERE om.order_date=:tDate GROUP BY od.material_id "
						+ "ORDER BY wm.warehouse_name;");
		query.setDate("tDate", date);
		List<Object> result = query.list();
		System.out.println("list" + result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTodaysInwards(Date date) {
		Session hbSession = sFactory.getCurrentSession();
		System.out.println("getTodaysInward() called ");
		Query query = hbSession
				.createSQLQuery(" SELECT wm.warehouse_name,mm.material_name,SUM(id.in_qty) FROM inwardmaster im "
						+ "JOIN inward_details id ON im.in_id=id.in_id JOIN warehousemaster wm ON "
						+ "wm.warehouse_id=im.warehouse_id JOIN materialmaster mm ON mm.material_id=id.material_id "
						+ "WHERE im.in_date=:tDate GROUP BY id.material_id ORDER BY wm.warehouse_name;");
		query.setDate("tDate", date);
		List<Object> result = query.list();
		System.out.println("list" + result);
		return result;
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Object> getTopSeller() { Session hbSession =
	 * sFactory.getCurrentSession();
	 * System.out.println("getTopSeller hbSession Created"); Query query =
	 * hbSession.createSQLQuery(
	 * "SELECT w.*, invd.* FROM warehousemaster w, materialmaster m, inventory_details invd WHERE w.warehouse_id = invd.warehouse_id ORDER BY invd.total_qty*m.unit_price DESC LIMIT 0,5"
	 * ) .addEntity("inventory",InventoryPOJO.class) .addEntity("wrh",
	 * Warehouse.class); //.addScalar("amt");
	 * //.setResultTransformer(Transformers.aliasToBean(Warehouse.class));
	 * List<Object> result = query.list(); System.out.println("list" +result);
	 * return result; }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Object> getMaterialShortage() { Session hbSession =
	 * sFactory.getCurrentSession();
	 * System.out.println("getMaterialShortage() called "); Query query =
	 * hbSession.createSQLQuery(
	 * "SELECT w.warehouse_name, m.material_name, invd.`available_qty` FROM inventory_details invd, materialmaster m, warehousemaster w  WHERE invd.`material_id`=m.`material_id` AND invd.`warehouse_id`=w.`warehouse_id` AND invd.`available_qty` < 1000"
	 * ); //.addEntity(OrderDetails.class);
	 * 
	 * List<Object> result = query.list(); System.out.println("list" +result);
	 * return result; }
	 */

}

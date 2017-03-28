package adnate.spring.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import adnate.spring.pojos.Contractor;

import adnate.spring.pojos.Material;
import adnate.spring.pojos.Order;
import adnate.spring.pojos.OrderDetails;
import adnate.spring.pojos.Sequence;
import adnate.spring.pojos.Warehouse;
import adnate.spring.services.IContractorServices;
import adnate.spring.services.IInventoryServices;
import adnate.spring.services.IInvoiceServices;
import adnate.spring.services.IMaterialServices;
import adnate.spring.services.IOrderServices;
import adnate.spring.services.IWarehouseServices;
import adnate.spring.services.SequenceService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	private IOrderServices service;
	
	@Autowired
	private IInventoryServices inventoryService;
	
	@Autowired
	private IMaterialServices mtrlService;
	
	@Autowired
	private IWarehouseServices wrhService;
	
	@Autowired
	private IContractorServices ctrService;
	
	@Autowired
	private IInvoiceServices invService;
	
	@Autowired
	private SequenceService seqService;

	// +++++++++++++++++++++++++++ADD ORDER ++++++++++++++++++++++++++++++++//

	@RequestMapping("/add_order")
	public String addOrder() {
		System.out.println("add_order called");
		return "/pages/addOrder1";
	}

	@RequestMapping(value = "/add_order", method = RequestMethod.POST, consumes = "Application/Json")
	public @ResponseBody int addOrder(@RequestBody String json)
			throws ParseException, JSONException {
		System.out.println("b4 Insert");
		System.out.println("JSON :" + json);
		
			JSONObject jsonObject = new JSONObject(json);

			JSONObject order = jsonObject.getJSONObject("order");
			System.out.println("JSON OBJ:" + order);
			String orderDate = order.getString("orderDate");
			int wId = order.getInt("warehouseId");
			System.out.println("warehouse ID: " + wId);
			int ctrId = order.getInt("contractorID");
			System.out.println("orderObj:" + order);
			String orgId = order.getString("organisationId");

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			/* String inDate=(String)jsonObject.get("inDate"); */
			Date parsedDate = df.parse(orderDate);
			java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
			System.out.println("parsed Date: " + parsedDate);
			System.out.println("sqlDate: " + sqlDate);

			Order orderPOJO = new Order();

			orderPOJO.setOrderDate(sqlDate);
			Warehouse warehouse = wrhService.findWarehouse(wId);
			orderPOJO.setWarehouse(warehouse);
			Contractor contractor = ctrService.findContractor(ctrId);
			orderPOJO.setContractor(contractor);
			
			Sequence sequence=seqService.getSequenceByTableName("ordermaster");
			String prefix=sequence.getPrefix();
			int seqId=sequence.getSequence()+1;
			System.out.println("updated seq id: adding + 1 -->" + seqId);
			String sequenceId=prefix + String.valueOf(seqId);
			System.out.println("String sequenceId: prefix + seqId" + sequenceId);
			sequence.setSequence(seqId);
			System.out.println(sequence);
			seqService.updateSequence(sequence);
			
			orderPOJO.setSequenceId(sequenceId);;
			
			orderPOJO.setOrderMasterInvoiceStatus("Delivered");
			
			orderPOJO.setOrganisationId(orgId);

			int orderId = service.insertOrder(orderPOJO);
			

			// Add order Details to order

			JSONObject orderDetails = jsonObject.getJSONObject("orderDetails");
			System.out.println("orderDetails JsonObj:-> from UI" + orderDetails);
			JSONArray orderList = orderDetails.getJSONArray("orderList");
			System.out.println("orderList JsonArray: -> " + orderList);

			for (int i = 0; i < orderList.length(); i++) {

				JSONObject orderDet = orderList.getJSONObject(i);

				int mtrlId = orderDet.getInt("materialId");
				int orderQty = orderDet.getInt("orderQty");
				OrderDetails orderDetailsPOJO = new OrderDetails();
				orderDetailsPOJO.setOrder(orderPOJO);
				System.out.println("orderDetails inside orderPOJO" + orderPOJO);
				int orderPOJO_orderId = orderPOJO.getOrderId();
				System.out.println("orderDetails inside orderPOJO"
						+ orderPOJO_orderId);
				Material material = mtrlService.findMaterial(mtrlId);
				orderDetailsPOJO.setMaterial(material);
				orderDetailsPOJO.setOrderQty(orderQty);
				orderDetailsPOJO.setIssuedDate(sqlDate);
				
				orderDetailsPOJO.setInvoiceStatus("Delivered");

				int orderDetails_orderId = service
						.insertOrderDetails(orderDetailsPOJO);

				if (orderDetails_orderId != 0) {
					boolean success = inventoryService
							.updateInventoryOnOutward(orderDetailsPOJO);
					System.out
							.println("Success Var on updateInventoryOnInward(): "
									+ success);
				}

				System.out.println("inwardDetails-materialList-id:" + mtrlId);
				System.out
						.println("inwardDetails-materialList-qty:" + orderQty);
				System.out.println("materialDet:" + orderDet);
			}
			System.out.println(orderId);
			return orderId;
		
	}

	// ++++++++++++ ORDER CONTROLLER ++++++++++++++ //

	@RequestMapping("/orderDetails")
	public ModelAndView orderDetails() {
		System.out.println("in orderDetails()");
		// command is a reserved request attribute name, now use <form> tag to
		// show object data
		return new ModelAndView("orderDetails", "comd", new OrderDetails());
	}

	@RequestMapping(value = "/orderDetailsJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDetails>> getOrderDetails() {
		List<OrderDetails> orderDetList = service.getOrderDetails();
		System.out.println("getOrderDetails() called");
		if (orderDetList.isEmpty()) {
			System.out.println("OrderDetails List Empty");
			return new ResponseEntity<List<OrderDetails>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<OrderDetails>>(orderDetList,
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/orderDetailsByIdJson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getOrderDetailsById(@RequestBody String json)throws ParseException, JSONException 
	{
		int id=Integer.parseInt(json.toString());
		System.out.println(id);
		List<Object> orderDetList = service.findOrderDetailsById(id);
		System.out.println("getOrderDetails() called");
		if (orderDetList.isEmpty()) {
			System.out.println("OrderDetails List Empty");
			return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Object>>(orderDetList,
				HttpStatus.OK);
	}
	

/*	JSONArray array=new JSONArray();
	System.out.println(array.getJSONArray(json.length));*/
/*		int id=Integer.parseInt(json.toString());
		System.out.println(id);
		List<Object> orderDetList = service.findAllInvoiceDetails(id);
		System.out.println("findAllInvoiceDetails() called");
		if (orderDetList.isEmpty()) {
			System.out.println("findAllInvoiceDetails List Empty");
			return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
		}
		for (Object ordrDet : orderDetList) {
			System.out.println(ordrDet.toString());
		}
		return new ResponseEntity<List<Object>>(orderDetList,
				HttpStatus.OK);*/

	
	@RequestMapping(value="/orderByOrgId/{orgId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getOrderByOrgId(@PathVariable("orgId") String OrganisationId) {
		List<Order> orderList = service.getOrderByOrgId(OrganisationId);
		System.out.println("getOrderByOrgId() called");
		if(orderList.isEmpty()){
			System.out.println("MaterialListByOrgId is Empty");
			
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
		}
		for (Order order : orderList) 
		{
			System.out.println(order.toString());
		
		}
	return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);			
	}
	
	
	@RequestMapping(value="/getOrderBetweenDates/{fromDate}/{toDate}", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getOrdersBetweenDates(@RequestParam(value="fromDate") @DateTimeFormat(iso=ISO.DATE) Date fromDate,
			@RequestParam(value="toDate")@DateTimeFormat(iso=ISO.DATE) Date toDate){
		List<Order> orderList = service.getOrdersBetweenDates(fromDate, toDate);
		System.out.println("getOrderByOrgId() called");
		if(orderList.isEmpty()){
			System.out.println("MaterialListByOrgId is Empty");
			
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
		}
		for (Order order : orderList) 
		{
			//System.out.println(order.toString());
		
		}
	return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
	
	}
	

	@RequestMapping("/viewOrder")
	public ModelAndView viewOrder() {
		System.out.println("in viewOrder()");
		// command is a reserved request attribute name, now use <form> tag to
		// show object data
		return new ModelAndView("viewOrder", "comd", new Order());
	}

	@RequestMapping(value = "/viewOrderJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getOrderObject() {
		List<Order> orderObjList = service.getOrderObject();
		System.out.println("getOrderObject() called");
		if (orderObjList.isEmpty()) {
			System.out.println("orderObjList List Empty");
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Order>>(orderObjList, HttpStatus.OK);
	}

	// +++++++++++++++++++++++ TOP FIVE MATERIAL_QTY CONTROLLER METHOD
	// ++++++++++++++++++++++ //

	@RequestMapping("/topMaterial")
	public ModelAndView topMaterial() {
		System.out.println("in topMaterial()");
		// command is a reserved request attribute name, now use <form> tag to
		// show object data
		return new ModelAndView("topMaterial", "comd", new OrderDetails());
	}

	@RequestMapping(value = "/topMaterialJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getTopMaterial() {
		List<Object> topMaterialList = service.getTopMaterial();
		System.out.println("getTopMaterial() called");

		if (topMaterialList.isEmpty()) {
			System.out.println("topMaterial List Empty");
			return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
		}
		for (Object MaterialObj : topMaterialList) {
			System.out.println(MaterialObj.toString());
		}
		return new ResponseEntity<List<Object>>(topMaterialList, HttpStatus.OK);

	}

	// +++++++++++++++++++++++++++++++ TOP FIVE BUYERS CONTROLLER METHOD
	// ++++++++++++++++++++++ //

	@RequestMapping("/topBuyer")
	public ModelAndView topBuyer() {
		System.out.println("in topBuyer()");
		// command is a reserved request attribute name, now use <form> tag to
		// show object data
		return new ModelAndView("topBuyer", "comd", new Order());
	}

	@RequestMapping(value = "/topBuyerJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getTopBuyer() {
		List<Object> topBuyerList = service.getTopBuyer();
		System.out.println("getTopBuyer() called");

		if (topBuyerList.isEmpty()) {
			System.out.println("topBuyer List Empty");
			return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
		}
		for (Object WarehouseObj : topBuyerList) {
			System.out.println(WarehouseObj.toString());
		}
		return new ResponseEntity<List<Object>>(topBuyerList, HttpStatus.OK);

	}

	// +++++++++++++++++++++++++++++++ TOP FIVE SELLERS CONTROLLER METHOD
	// +++++++++++++++++++++++++++++ //

	@RequestMapping("/topSeller")
	public ModelAndView topSeller() {
		System.out.println("in topSeller()");
		// command is a reserved request attribute name, now use <form> tag to
		// show object data
		return new ModelAndView("topSeller", "comd", new OrderDetails());
	}

	@RequestMapping(value = "/topSellerJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getTopSeller() {
		List<Object> topSellerList = service.getTopSeller();
		
		System.out.println("getTopSeller() called");

		if (topSellerList.isEmpty()) {
			System.out.println("topSeller List Empty");
			return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
		}
		for (Object topSellerObj : topSellerList) {
			System.out.println(topSellerObj.toString());
		}
		return new ResponseEntity<List<Object>>(topSellerList, HttpStatus.OK);

	}
}

// +++++++++++++++++++++++++++++++ TOP FIVE WAREHOUSES GETTING MOST ORDERS
// CONTROLLER METHOD +++++++++++++++++++++++++++++ //

/*
 * @RequestMapping("/topWarehousesOnOrders") public ModelAndView
 * topWarehousesOnOrders(){ System.out.println("in topWarehousesOnOrders()");
 * //command is a reserved request attribute name, now use <form> tag to show
 * object data return new ModelAndView("topWarehousesOnOrders","comd",new
 * OrderDetails()); }
 * 
 * @RequestMapping(value= "/topWarehousesOnOrdersJson", method =
 * RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE) public
 * ResponseEntity<List<Object>> getTopWarehousesOnOrders() { List<Object>
 * topWarehousesOnOrdersList = service.getTopOrderQty();
 * System.out.println("getTopWarehousesOnOrders() called");
 * 
 * 
 * 
 * if(topOrderQtyList.isEmpty()){
 * System.out.println("topWarehousesOnOrdersList List Empty"); return new
 * ResponseEntity<List<OrderDetails>>(HttpStatus.NO_CONTENT); } for (Object
 * topWarehouseObj : topWarehousesOnOrders) {
 * System.out.println(topWarehouseObj.toString()); } return new
 * ResponseEntity<List<Object>>(topWarehousesOnOrdersList, HttpStatus.OK);
 * 
 * }
 */

/*
 * // +++++++++++++++++++++++++++++++ ORDERS PLACED ON TODAY CONTROLLER METHOD
 * +++++++++++++++++++++++++++++ //
 * 
 * @RequestMapping("/todayOrder") public ModelAndView todayOrder(){
 * System.out.println("in todayOrder()"); //command is a reserved request
 * attribute name, now use <form> tag to show object data return new
 * ModelAndView("todayOrder","comd",new OrderDetails()); }
 * 
 * @RequestMapping(value= "/todayOrderJson", method = RequestMethod.GET,
 * produces=MediaType.APPLICATION_JSON_VALUE) public
 * ResponseEntity<List<Object>> getTodayOrder() { List<Object> todayOrderList =
 * service.getTodayOrder(); System.out.println("getTodayOrder() called");
 * 
 * 
 * 
 * if(todayOrderList.isEmpty()){
 * System.out.println("todayOrderList List Empty"); return new
 * ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT); } for (Object
 * todayOrderObj : todayOrderList) {
 * System.out.println(todayOrderObj.toString()); } return new
 * ResponseEntity<List<Object>>(todayOrderList, HttpStatus.OK);
 * 
 * }
 */

/*
 * // +++++++++++++++++++++++++++++++ AMT FOR ORDERS ON TODAY METHOD
 * +++++++++++++++++++++++++++++ //
 * 
 * @RequestMapping("/todayOrderAmount") public ModelAndView todayOrderAmount(){
 * System.out.println("in todayOrderAmount()"); //command is a reserved request
 * attribute name, now use <form> tag to show object data return new
 * ModelAndView("todayOrderAmount","comd",new OrderDetails()); }
 * 
 * @RequestMapping(value= "/todayOrderAmountJson", method = RequestMethod.GET,
 * produces=MediaType.APPLICATION_JSON_VALUE) public
 * ResponseEntity<List<Object>> getTodayOrderAmount() { List<Object>
 * todayOrderAmtList = service.getTodayOrderAmount();
 * System.out.println("gettodayInwardAmount() called");
 * 
 * 
 * 
 * if(todayOrderAmtList.isEmpty()){
 * System.out.println("todayInwardAmtList List Empty"); return new
 * ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT); } for (Object
 * todayOrderAmtObj : todayOrderAmtList) {
 * System.out.println(todayOrderAmtObj.toString()); } return new
 * ResponseEntity<List<Object>>(todayOrderAmtList, HttpStatus.OK);
 * 
 * }
 */

// +++++++++++++++++++++++++++++++ TOP FIVE ORDER_QTY CONTROLLER METHOD
// +++++++++++++++++++++++++++++ //

/*
 * @RequestMapping("/topOrderQty") public ModelAndView topOrderQty(){
 * System.out.println("in topOrderQty()"); //command is a reserved request
 * attribute name, now use <form> tag to show object data return new
 * ModelAndView("topOrderQty","comd",new OrderDetails()); }
 * 
 * @RequestMapping(value= "/topOrderQtyJson", method = RequestMethod.GET,
 * produces=MediaType.APPLICATION_JSON_VALUE) public
 * ResponseEntity<List<OrderDetails>> getTopOrderQty() { List<OrderDetails>
 * topOrderQtyList = service.getTopOrderQty();
 * System.out.println("getTopOrderQty() called");
 * 
 * 
 * 
 * if(topOrderQtyList.isEmpty()){
 * System.out.println("topOrderQtyList List Empty"); return new
 * ResponseEntity<List<OrderDetails>>(HttpStatus.NO_CONTENT); } for
 * (OrderDetails orderObj : topOrderQtyList) {
 * System.out.println(orderObj.toString()); } return new
 * ResponseEntity<List<OrderDetails>>(topOrderQtyList, HttpStatus.OK);
 * 
 * }
 */


package adnate.spring.pojos;

import java.util.*;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/*import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;*/
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;

/*@NamedNativeQueries({
	@NamedNativeQuery(
	name = "callGenOrderStoreProcedure",
	query = "",
	//CALL GenOrder(:warehouse_id)
	resultClass = Order.class
	)
})*/


@Component
@Entity
@Table(name="ordermaster")	
public class Order {
	@Id
	@GenericGenerator(name="gen", strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="sequence_id")
	private String sequenceId;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="inv_status")
	private String orderMasterInvoiceStatus;
	
	@Column(name="organisation_id")
	private String organisationId;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="warehouse_id")
	private Warehouse warehouse;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="contractor_id")
	private Contractor contractor;

	
	public Order(){
		
	}


	public Order(int orderId, String sequenceId, Date orderDate, String orderMasterInvoiceStatus, String organisationId,
			Warehouse warehouse, Contractor contractor) {
		super();
		this.orderId = orderId;
		this.sequenceId = sequenceId;
		this.orderDate = orderDate;
		this.orderMasterInvoiceStatus = orderMasterInvoiceStatus;
		this.organisationId = organisationId;
		this.warehouse = warehouse;
		this.contractor = contractor;
	}


	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	

	public String getSequenceId() {
		return sequenceId;
	}



	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}



	public String getOrderMasterInvoiceStatus() {
		return orderMasterInvoiceStatus;
	}



	public void setOrderMasterInvoiceStatus(String orderMasterInvoiceStatus) {
		this.orderMasterInvoiceStatus = orderMasterInvoiceStatus;
	}
	
	public String getOrganisationId() {
		return organisationId;
	}


	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}


	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}


	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", sequenceId=" + sequenceId + ", orderDate=" + orderDate
				+ ", orderMasterInvoiceStatus=" + orderMasterInvoiceStatus + ", organisationId=" + organisationId
				+ ", warehouse=" + warehouse + ", contractor=" + contractor + "]";
	}

}

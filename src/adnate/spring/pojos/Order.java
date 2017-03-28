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
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="sequence_id")
	private String sequenceId;
	
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

	
	

	public Order(int orderId, Date orderDate, String sequenceId,
			String organisationId, Warehouse warehouse, Contractor contractor) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.sequenceId = sequenceId;
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

	public String getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}




	public String getSequenceId() {
		return sequenceId;
	}




	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}




	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate
				+ ", sequenceId=" + sequenceId + ", organisationId="
				+ organisationId + ", warehouse=" + warehouse + ", contractor="
				+ contractor + "]";
	}

	

	




}

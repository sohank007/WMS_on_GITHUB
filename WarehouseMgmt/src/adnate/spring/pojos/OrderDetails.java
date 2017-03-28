package adnate.spring.pojos;

import java.util.Date;
//import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="order_details")	
public class OrderDetails {
	
	@Id
	@GenericGenerator(name="gen", strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="serial_id")
	private int serialId;
	
	@Column(name="order_qty")
	private double orderQty;
	
	@Column(name="return_qty")
	private double returnQty;

	@Column(name="issued_date")
	private Date issuedDate;
	
	@Column(name="return_date")
	private Date returnDate;
	
	@Column(name="inv_status")
	private String invoiceStatus;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="material_id")
	private Material material;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="order_id")
	private Order order; 
	
////////////// CONSTRUCTOR  ///////////////////////////	
	
	public OrderDetails(){
		
	}

public OrderDetails(int serialId, double orderQty, double returnQty, Date issuedDate, Date returnDate,
		String invoiceStatus, Material material, Order order) {
	super();
	this.serialId = serialId;
	this.orderQty = orderQty;
	this.returnQty = returnQty;
	this.issuedDate = issuedDate;
	this.returnDate = returnDate;
	this.invoiceStatus = invoiceStatus;
	this.material = material;
	this.order = order;
}


//////////////GETTERS SETTERS  ///////////////////////////	
	

	public int getSerialId() {
		return serialId;
	}

	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}

	public double getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(double orderQty) {
		this.orderQty = orderQty;
	}


	public double getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(double returnQty) {
		this.returnQty = returnQty;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	


	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Material getMaterial() {
		return material;
	}



	public void setMaterial(Material material) {
		this.material = material;
	}



	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetails [serialId=" + serialId + ", orderQty=" + orderQty + ", returnQty=" + returnQty
				+ ", issuedDate=" + issuedDate + ", returnDate=" + returnDate + ", invoiceStatus=" + invoiceStatus
				+ ", material=" + material + ", order=" + order + "]";
	}



	
	


}

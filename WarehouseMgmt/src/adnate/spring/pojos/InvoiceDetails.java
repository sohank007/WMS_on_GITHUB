package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="invoice_details")
public class InvoiceDetails {

	@Id
	@Column(name="serialId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serialId;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="invoice_id")
	private Invoice invoice; 
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="material_id")
	private Material material;
	
	@Column(name="quantity")
	private double quantity;
	
	@Column(name="total_amt")
	private double totalAmount;
	
	public InvoiceDetails(){
		
	}

	public InvoiceDetails(int serialId, Invoice invoice, Material material,
			double quantity, double totalAmount) {
		super();
		this.serialId = serialId;
		this.invoice = invoice;
		this.material = material;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}



	public int getSerialId() {
		return serialId;
	}


	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}


	public Invoice getInvoice() {
		return invoice;
	}


	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}


	public Material getMaterial() {
		return material;
	}


	public void setMaterial(Material material) {
		this.material = material;
	}


	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "InvoiceDetails [serialId=" + serialId + ", invoice=" + invoice
				+ ", material=" + material + ", quantity=" + quantity
				+ ", totalAmount=" + totalAmount + "]";
	}
	
}

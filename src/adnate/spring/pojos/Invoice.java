package adnate.spring.pojos;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="invoicemaster")
public class Invoice {

	@Id
	@GenericGenerator(name="gen", strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="invoice_id")
	private int invoiceId;
	
	@Column(name="invoice_date")
	private Date invoiceDate;
	
	@Column(name="total_invoice_amt")
	private double invoiceAmtTotal;
	
	@Column(name="valid_till")
	private Date validDate;
	
	@Column(name="invoice_status")
	private String invoiceStatus;
	
	@Column(name="discount")
	private double discount;
	
	@Column(name="discountPt")
	private double discountPercentage;
	
	@Column(name="purchase_order")
	private String purchaseOrder;
	
	@Column(name="sequence_id")
	private String sequenceId;
		
	@Column(name="pdf_path")
	private String pdfPath;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="contractor_id")
	private Contractor  contractor;


	public Invoice() {
		super();
	}



	public Invoice(int invoiceId, Date invoiceDate, double invoiceAmtTotal,
			Date validDate, String invoiceStatus, double discount,
			double discountPercentage, String purchaseOrder, String sequenceId,
			String pdfPath, Contractor contractor) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.invoiceAmtTotal = invoiceAmtTotal;
		this.validDate = validDate;
		this.invoiceStatus = invoiceStatus;
		this.discount = discount;
		this.discountPercentage = discountPercentage;
		this.purchaseOrder = purchaseOrder;
		this.sequenceId = sequenceId;
		this.pdfPath = pdfPath;
		this.contractor = contractor;
	}



	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public double getInvoiceAmtTotal() {
		return invoiceAmtTotal;
	}

	public void setInvoiceAmtTotal(double invoiceAmtTotal) {
		this.invoiceAmtTotal = invoiceAmtTotal;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}


	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}


	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate="
				+ invoiceDate + ", invoiceAmtTotal=" + invoiceAmtTotal
				+ ", validDate=" + validDate + ", invoiceStatus="
				+ invoiceStatus + ", discount=" + discount
				+ ", discountPercentage=" + discountPercentage
				+ ", purchaseOrder=" + purchaseOrder + ", sequenceId="
				+ sequenceId + ", pdfPath=" + pdfPath + ", contractor="
				+ contractor + "]";
	}

}

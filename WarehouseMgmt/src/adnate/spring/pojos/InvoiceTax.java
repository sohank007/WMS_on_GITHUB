package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="invoice_tax")
public class InvoiceTax {
	@Id
	@Column(name="invoice_tax_id")
	private int invoiceTaxId;
		
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="tax_id")
	private TaxInfo tax;
		
	@Column(name="invoice_id")
	private int invoiceId;
	
	@Column(name="tax_applied_amt")
	private double taxableAmt;
	
	
	public InvoiceTax(){
		
	}

	

	public InvoiceTax(int invoiceTaxId, TaxInfo tax, int invoiceId,
			double taxableAmt) {
		super();
		this.invoiceTaxId = invoiceTaxId;
		this.tax = tax;
		this.invoiceId = invoiceId;
		this.taxableAmt = taxableAmt;

	}



	public int getInvoiceTaxId() {
		return invoiceTaxId;
	}

	public void setInvoiceTaxId(int invoiceTaxId) {
		this.invoiceTaxId = invoiceTaxId;
	}

	public TaxInfo getTax() {
		return tax;
	}

	public void setTax(TaxInfo tax) {
		this.tax = tax;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public double getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(double taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	
	@Override
	public String toString() {
		return "InvoiceTax [invoiceTaxId=" + invoiceTaxId + ", tax=" + tax
				+ ", invoiceId=" + invoiceId + ", taxableAmt=" + taxableAmt
				 + "]";
	}


	
}

package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="tax_details")
public class TaxInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tax_id")
	private int taxId;
	
	@Column(name="tax_name")
	private String taxName;
	
	@Column(name="tax_region")
	private String taxRegion;
	
	@Column(name="tax_value")
	private double taxValue;
	
	public TaxInfo(){
		
	}

	public TaxInfo(String taxName, String taxRegion, double taxValue) {
		super();
		this.taxName = taxName;
		this.taxRegion = taxRegion;
		this.taxValue = taxValue;
	}
	
	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public String getTaxRegion() {
		return taxRegion;
	}

	public void setTaxRegion(String taxRegion) {
		this.taxRegion = taxRegion;
	}

	public double getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(double taxValue) {
		this.taxValue = taxValue;
	}

	@Override
	public String toString() {
		return "TaxInfo [taxId=" + taxId + ", taxName=" + taxName
				+ ", taxRegion=" + taxRegion + ", taxValue=" + taxValue + "]";
	}
	
}

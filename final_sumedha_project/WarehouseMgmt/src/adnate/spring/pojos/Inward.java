package adnate.spring.pojos;

import java.util.Date;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/*import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;*/
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="inwardmaster")
public class Inward {
	@Id
	@GenericGenerator(name="gen", strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="in_id")
	private int inId;
	
	@Column(name="in_date")
    private Date inDate;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="warehouse_id")
	private Warehouse warehouse;
	
	
	@Column(name="organisation_id")
	private String organisationId;
	
	/*@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="material_id")
	private Set<Material> mtrlList;*/
	
	/*@OneToMany(fetch=FetchType.EAGER,mappedBy="inward", cascade=CascadeType.ALL)
	private Set<InwardDetails> inwardDetList;*/

	public Inward(){
		
	}

	public Inward(Date inDate, Warehouse warehouse) {
		super();
		this.inDate = inDate;
		this.warehouse = warehouse;
	}


	

	public Inward(int inId, Date inDate, Warehouse warehouse, String organisationId) {
		super();
		this.inId = inId;
		this.inDate = inDate;
		this.warehouse = warehouse;
		this.organisationId = organisationId;
	}

	public int getInId() {
		return inId;
	}

	public void setInId(int inId) {
		this.inId = inId;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}

	@Override
	public String toString() {
		return "Inward [inId=" + inId + ", inDate=" + inDate + ", warehouse=" + warehouse + ", organisationId="
				+ organisationId + "]";
	}


	


}

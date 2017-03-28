package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="inward_details")
public class InwardDetails {
	@Id
	@Column(name="serial_id")
	private int serialId;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="material_id")
	private Material material;
	
	@Column(name="in_qty")
	private double inQty;
    
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="in_id")
	private Inward inward; 
	
	public InwardDetails() {
		
	}
	
	
	
	public InwardDetails(int serialId, Material material, double inQty,
			Inward inward) {
		super();
		this.serialId = serialId;
		this.material = material;
		this.inQty = inQty;
		this.inward = inward;
	}


	public int getSerialId() {
		return serialId;
	}

	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
     
	
	public Inward getInward() {
		return inward;
	}

	public void setInward(Inward inward) {
		this.inward = inward;
	}



	public Material getMaterial() {
		return material;
	}



	public void setMaterial(Material material) {
		this.material = material;
	}



	public double getInQty() {
		return inQty;
	}

	public void setInQty(double inQty) {
		this.inQty = inQty;
	}



	@Override
	public String toString() {
		return "InwardDetails [serialId=" + serialId + ", material=" + material
				+ ", inQty=" + inQty + ", inward=" + inward + "]";
	}





}

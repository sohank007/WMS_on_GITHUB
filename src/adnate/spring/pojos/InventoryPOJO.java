package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



//import org.hibernate.annotations.NaturalId;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="inventory_details")
public class InventoryPOJO {
	@Id
	@Column(name="serial_id")
	private int serialId;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="material_id")	
	private Material material;
	
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)	
	@JoinColumn(name="warehouse_id")	
	private Warehouse warehouse;
	
	@Column(name="total_qty")
	private double totalQty;
	
	@Column(name="available_qty")
	private double availableQty;
	
	@Column(name="organisation_id")
	private String organisationId;
	
	public InventoryPOJO(){
		
	}

	public InventoryPOJO(int serialId, Material material, Warehouse warehouse,
			double totalQty, double availableQty, String organisationId) {
		super();
		this.serialId = serialId;
		this.material = material;
		this.warehouse = warehouse;
		this.totalQty = totalQty;
		this.availableQty = availableQty;
		this.organisationId = organisationId;
	}

	


	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public double getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(double totalQty) {
		this.totalQty = totalQty;
	}

	public double getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(double availableQty) {
		this.availableQty = availableQty;
	}


	public int getSerialId() {
		return serialId;
	}

	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}

	

	public String getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}
	
	
	
	@Override
	public String toString() {
		return "InventoryPOJO [serialId=" + serialId + ", material=" + material
				+ ", warehouse=" + warehouse + ", totalQty=" + totalQty
				+ ", availableQty=" + availableQty + ", organisationId="
				+ organisationId + "]";
	}


	




}

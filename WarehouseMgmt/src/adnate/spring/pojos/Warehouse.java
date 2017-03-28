package adnate.spring.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;


@Component
@Entity
@Table(name="warehousemaster")
public class Warehouse {
	
	@Id
	@GenericGenerator(name="gen", strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="warehouse_id")
	private int warehouseId;
	@Column(name="warehouse_name")
	private String warehouseName;
	@Column(name="warehouse_location")
	private String warehouseLoc;
	@Column(name="warehouse_region")
	private String wRegion;
	
	@Column(name="organisation_id")
	private String organisationId;
	
	public Warehouse(){
		
	}
	
	

	public Warehouse(int warehouseId, String warehouseName, String warehouseLoc, String wRegion,
			String organisationId) {
		super();
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.warehouseLoc = warehouseLoc;
		this.wRegion = wRegion;
		this.organisationId = organisationId;
	}



	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseLoc() {
		return warehouseLoc;
	}

	public void setWarehouseLoc(String warehouseLoc) {
		this.warehouseLoc = warehouseLoc;
	}

	public String getwRegion() {
		return wRegion;
	}

	public void setwRegion(String wRegion) {
		this.wRegion = wRegion;
	}
	
	

	public String getOrganisationId() {
		return organisationId;
	}



	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}



	@Override
	public String toString() {
		return "Warehouse [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", warehouseLoc="
				+ warehouseLoc + ", wRegion=" + wRegion + ", organisationId=" + organisationId + "]";
	}



	
	
	
	

}

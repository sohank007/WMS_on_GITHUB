package adnate.spring.pojos;

import org.springframework.stereotype.Component;

@Component
public class FetchDataFromQuery {

	private Material material;
	private Contractor contractor;
	private Warehouse warehouse;
	private double quantity;
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Contractor getContractor() {
		return contractor;
	}
	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public FetchDataFromQuery(Material material, Contractor contractor,
			Warehouse warehouse, double quantity) {
		super();
		this.material = material;
		this.contractor = contractor;
		this.warehouse = warehouse;
		this.quantity = quantity;
	}
	public FetchDataFromQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "FetchDataFromQuery [material=" + material + ", contractor="
				+ contractor + ", warehouse=" + warehouse + ", quantity="
				+ quantity + "]";
	} 
	
}

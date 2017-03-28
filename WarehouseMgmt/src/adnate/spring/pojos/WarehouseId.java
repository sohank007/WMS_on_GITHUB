package adnate.spring.pojos;

import org.springframework.stereotype.Component;

@Component
public class WarehouseId {

	private int wHouseId;

	public WarehouseId(){
		
	}
	
	public WarehouseId(int wHouseId) {
		super();
		this.wHouseId = wHouseId;
	}

	public int getwHouseId() {
		return wHouseId;
	}

	public void setwHouseId(int wHouseId) {
		this.wHouseId = wHouseId;
	}

	@Override
	public String toString() {
		return "WarehouseId [wHouseId=" + wHouseId + "]";
	}
	
}

package adnate.spring.services;

import java.util.List;

import adnate.spring.pojos.Warehouse;

public interface IWarehouseServices {
	public Warehouse findWarehouse(int id);
	public int insertWarehouse(Warehouse w);
	public int updateWarehouse(Warehouse w);
	public int deleteWarehouse(Warehouse w);	
	public List<Warehouse> getWarehouses();
	public List<String> findRegion();
	
	public List<Warehouse> getWarehouseByOrgId(String organisationId);
}


/*package adnate.spring.services;

import java.util.List;

import adnate.spring.pojos.Warehouse;

public interface IWarehouseServices {
	public Warehouse findWarehouse(int id);
	public boolean insertWarehouse(Warehouse w);
	public void updateWarehouse(Warehouse w);
	public void deleteWarehouse(Warehouse w);	
	public List<Warehouse> getWarehouses();
	public List<String> findRegion();
}
*/

package adnate.spring.daos;

import java.util.List;

import adnate.spring.pojos.Warehouse;

public interface WarehouseDao {
	public int insertWarehouse(Warehouse w);
	public int updateWarehouse(Warehouse w);
	public int deleteWarehouse(Warehouse w);
	public Warehouse findWarehouse(int id);
	public List<Warehouse> getAllWarehouses();
	public List<String> findRegion();
	
	public List<Warehouse> getWarehouseByOrgId(String organisationId);
}

/*package adnate.spring.daos;

import java.util.List;

import adnate.spring.pojos.Warehouse;

public interface WarehouseDao {
	public void insertWarehouse(Warehouse w);
	public void updateWarehouse(Warehouse w);
	public void deleteWarehouse(Warehouse w);
	public Warehouse findWarehouse(int id);
	public List<Warehouse> getAllWarehouses();
	public List<String> findRegion();
}
*/
package adnate.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.WarehouseDao;
import adnate.spring.pojos.Warehouse;

@Service
public class WarehouseServicesImpl implements IWarehouseServices{
	@Autowired
	private WarehouseDao warehousedao;

	@Override
	public Warehouse findWarehouse(int id) {
		return warehousedao.findWarehouse(id);
	}

	@Override
	public int insertWarehouse(Warehouse w) {
		
			return warehousedao.insertWarehouse(w);
		
	}

	@Override
	public int updateWarehouse(Warehouse w) {
		return warehousedao.updateWarehouse(w);
	}

	@Override
	public int deleteWarehouse(Warehouse w) {
		return warehousedao.deleteWarehouse(w);
	}

	@Override
	public List<Warehouse> getWarehouses() {
		return warehousedao.getAllWarehouses();
	}

	@Override
	public List<String> findRegion() {
		return warehousedao.findRegion();
	}

	@Override
	public List<Warehouse> getWarehouseByOrgId(String organisationId) {
		return warehousedao.getWarehouseByOrgId(organisationId);
	}

	
}

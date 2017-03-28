package adnate.spring.daos;

import java.util.List;
import adnate.spring.pojos.Material;

public interface MaterialDao {
	public int insertMaterial(Material mtrl);
	public int updateMaterial(Material mtrl);
	public int deleteMaterial(Material mtrl);
	public Material findMaterial(int id);
	public List<Material> getAllMaterials();
	
	public List<Material> getMaterialByOrgId(String organisationId);
	
}

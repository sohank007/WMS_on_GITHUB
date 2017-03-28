package adnate.spring.services;

import java.util.List;

import adnate.spring.pojos.Material;

public interface IMaterialServices {
	public Material findMaterial(int id);
	public int insertMaterial(Material mtrl);
	public int updateMaterial(Material prod);
	public int deleteMaterial(Material mtrl);	
	public List<Material> getMaterials();
	
	public List<Material> getMaterialByOrgId(String organisationId);
	
}

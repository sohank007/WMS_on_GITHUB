package adnate.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import adnate.spring.daos.MaterialDao;
import adnate.spring.pojos.Material;


@Service
public class MaterialServicesImpl implements IMaterialServices{
	
	@Autowired
	private MaterialDao materialdao;
	
	@Override
	public Material findMaterial(int id) {
		return materialdao.findMaterial(id);
	}

	@Override
	public int insertMaterial(Material mtrl) {
			
			return materialdao.insertMaterial(mtrl);
	
	}

	@Override
	public int updateMaterial(Material mtrl) {
		return materialdao.updateMaterial(mtrl);
	}

	@Override
	public int deleteMaterial(Material mtrl) {
		return materialdao.deleteMaterial(mtrl);
	}

	@Override
	public List<Material> getMaterials() {
		return materialdao.getAllMaterials();
	}

	@Override
	public List<Material> getMaterialByOrgId(String organisationId) {
		return materialdao.getMaterialByOrgId(organisationId);
	}

	
}

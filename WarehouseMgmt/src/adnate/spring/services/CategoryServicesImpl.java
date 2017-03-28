package adnate.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.ICategoryDao;
import adnate.spring.pojos.Category;


@Service
public class CategoryServicesImpl implements ICategoryServices{

	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	public List<Category> getCategoryByOrgId(String organisationId) {
		return categoryDao.getCategoryByOrgId(organisationId);
	}

	@Override
	public Category findCategory(int id) {
		return categoryDao.findCategory(id);
	}

	@Override
	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

	@Override
	public boolean insertCategory(Category ctgry) {
		try {
			categoryDao.insertCategory(ctgry);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateCategory(Category ctgry) {
		categoryDao.updateCategory(ctgry);
	}

	@Override
	public void deleteCategory(Category ctgry) {
		categoryDao.deleteCategory(ctgry);
	}
	
	

}

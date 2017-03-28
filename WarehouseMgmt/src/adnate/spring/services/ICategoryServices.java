package adnate.spring.services;

import java.util.List;

import adnate.spring.pojos.Category;


public interface ICategoryServices {
	
	public List<Category> getCategoryByOrgId(String organisationId);

	public Category findCategory(int id);

	public List<Category> getCategories();

	public boolean insertCategory(Category ctgry);

	public void updateCategory(Category ctgry);

	public void deleteCategory(Category ctgry);

}

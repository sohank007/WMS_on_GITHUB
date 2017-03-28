package adnate.spring.daos;

import java.util.List;

import adnate.spring.pojos.Category;

public interface ICategoryDao {
	
	public List<Category> getCategoryByOrgId(String organisationId);

	public Category findCategory(int id);

	public List<Category> getCategories();

	public void insertCategory(Category ctgry);

	public void updateCategory(Category ctgry);

	public void deleteCategory(Category ctgry);

}

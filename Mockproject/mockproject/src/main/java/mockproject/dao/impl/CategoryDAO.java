package mockproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mockproject.dao.ICategoryDAO;
import mockproject.mapper.CategoriesMapper;
import mockproject.model.CategoryModel;
@Repository
public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> listAllCategoryModels() {
		String sql = "select * from categories";
		return query(sql, new CategoriesMapper());
	}

	@Override
	public List<CategoryModel> listAllCategoryByFilmid(int film_id) {
		String sql = "select * from categories c inner join  category_film cf on c.id = cf.category_id where cf.film_id = ?";
		
		return query(sql, new CategoriesMapper(), film_id);
		
	}
	
	

	@Override
	public List<String> listALlCategoryName() {
		List<CategoryModel> listCategoryModels = this.listAllCategoryModels();
		List<String> listCategoryName = new ArrayList<>();
		for(int i = 0; i < listCategoryModels.size(); i++) {
			listCategoryName.add(listCategoryModels.get(i).getName());
		}
		return listCategoryName;
	}

	

	@Override
	public int findCategoryIdByName(String name) {
		Integer  id = null;
		List<CategoryModel> listCategoryModels = this.listAllCategoryModels();
		for(int i = 0; i < listCategoryModels.size(); i++) {
			if(listCategoryModels.get(i).getName().equalsIgnoreCase(name)) {
				id = listCategoryModels.get(i).getId();
				break;
			}
		}
		return id;
		
		
	}
	
	
	public static void main(String...strings) {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryModel> newList = categoryDAO.listAllCategoryByFilmid(1);
		for (CategoryModel categoryModel : newList) {
			System.out.println(categoryModel.getName());
		}
		
		System.out.println(categoryDAO.findCategoryIdByName("Hành động"));
	}

	@Override
	public void deleteCategoryById(int id) {
		String sql = "delete from categories where id = ?";
		delete(sql, id);
	}

	@Override
	public int insertOneCategoy(CategoryModel categoryModel) {
		
		
		String name = categoryModel.getName();
		String sql = "insert into categories(name) values(?); ";
		Integer result = insert(sql, name);
		return result;
	}

	@Override
	public CategoryModel findCategoryById(int id) {
		
		String sql = "select * from categories where id = ?";
		List<CategoryModel> listCategoryModels = query(sql, new CategoriesMapper(), id);
		if(listCategoryModels.size() > 0) {
			return listCategoryModels.get(0);
		}
		return null;
	}

	@Override
	public void updateCategory(int id, String name) {
		String sql = "update categories "
				+ "set name = ?"
				+ "where id = ?";
		update(sql, name, id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}

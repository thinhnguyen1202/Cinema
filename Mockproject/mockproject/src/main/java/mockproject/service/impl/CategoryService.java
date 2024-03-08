package mockproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mockproject.dao.ICategoryDAO;
import mockproject.model.CategoryModel;
import mockproject.service.ICategoryService;
@Service
public class CategoryService implements ICategoryService {

	@Autowired
	ICategoryDAO categoryDao;
	@Override
	public List<CategoryModel> listAllCategoryModels() {
		return categoryDao.listAllCategoryModels();
	}
	@Override
	public List<CategoryModel> listAllCategoryByFilmid(int film_id) {
		return categoryDao.listAllCategoryByFilmid(film_id);
	}
	@Override
	public List<String> listALlCategoryName() {
		return categoryDao.listALlCategoryName();
	}
	@Override
	public int findCategoryIdByName(String name) {
		// Trả về +1 do index bắt đầu từ 0
		return categoryDao.findCategoryIdByName(name);
	}
	@Override
	public void deleteCategoryById(int id) {
		categoryDao.deleteCategoryById(id);
	}
	@Override
	public int insertOneCategoy(CategoryModel categoryModel) {
		return categoryDao.insertOneCategoy(categoryModel);
	}
	@Override
	public CategoryModel findCategoryById(int id) {
		
		if(categoryDao.findCategoryById(id) != null) {
			return categoryDao.findCategoryById(id);
		}
		return null;
	}
	@Override
	public void updateCategory(int id, String name) {
		categoryDao.updateCategory(id, name);
		
	}
	
	
	
	
	
	
	

}

package mockproject.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mockproject.dao.ICategoryFilmDAO;
import mockproject.mapper.CategoryFilmMapper;
import mockproject.model.CategoryFilmModel;
@Repository
public class CategoryFilmDAO extends AbstractDAO<CategoryFilmModel> implements ICategoryFilmDAO {

	@Override
	public List<CategoryFilmModel> listAllByID(int film_id) {
		String sql = "select * from category_film where film_id = ?";
		return query(sql,new CategoryFilmMapper(), film_id);
	}

	@Override
	public int insertOneRow(int film_id, int category_id) {
		String sql = "insert into category_film(category_id,film_id) values(?,?);";
		return insert(sql, category_id,film_id);
	}

	@Override
	public void deleteByFilmID(int film_id) {
		String sql = "delete from category_film where film_id = ?";
		delete(sql, film_id);
	}

	@Override
	public void deleteByCategoryId(int category_id) {
		String sql = "delete from category_film where category_id = ?;";
		delete(sql, category_id);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}

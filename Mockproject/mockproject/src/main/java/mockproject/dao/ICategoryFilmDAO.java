package mockproject.dao;

import java.util.List;

import mockproject.model.CategoryFilmModel;

public interface ICategoryFilmDAO  extends GenericDAO<CategoryFilmModel>{
	
	List<CategoryFilmModel> listAllByID(int film_id);
	
	int insertOneRow(int film_id, int category_id);
	
	void deleteByFilmID(int film_id);
	
	void deleteByCategoryId(int category_id);
	
	
}

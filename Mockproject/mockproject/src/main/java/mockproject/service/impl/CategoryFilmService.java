package mockproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mockproject.dao.ICategoryFilmDAO;
import mockproject.service.ICategoryFilmService;

@Service
public class CategoryFilmService implements ICategoryFilmService {

	@Autowired
	ICategoryFilmDAO categoryFilmDAO;
	@Override
	public int insertOneRow(int film_id, int category_id) {
		return categoryFilmDAO.insertOneRow(film_id, category_id);
	}
	@Override
	public void deleteByFilmID(int film_id) {
		categoryFilmDAO.deleteByFilmID(film_id);
	}
	@Override
	public void deleteByCategoryId(int category_id) {
		categoryFilmDAO.deleteByCategoryId(category_id);
		
	}
	
	
	
	
	
}

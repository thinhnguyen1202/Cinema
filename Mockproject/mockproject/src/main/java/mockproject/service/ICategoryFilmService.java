package mockproject.service;

public interface ICategoryFilmService {
	int insertOneRow(int film_id, int category_id);
	
	void deleteByFilmID(int film_id);
	
	void deleteByCategoryId(int category_id);
}

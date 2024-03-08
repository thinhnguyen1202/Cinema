package mockproject.dao;

import java.util.List;

import mockproject.model.FilmModel;

public interface IFilmDAO extends GenericDAO<FilmModel> {
	List<FilmModel> listAllFilm();
	
	int saveFilm(FilmModel filmModel);
	
	void deleteFilm(int id);
	
	void deleteFilmOnCategoryFilmTable(int id);
	
	int getTheLastID();
	
	FilmModel getFilmById(int id);
	
	FilmModel getIdFilmByName(String name);
	
	void updateImagePath(int id,String path);
	
	void updateFilm(FilmModel filmModel);
	
	List<String> listAllFilmName();
	
	
	List<FilmModel> getAllFilmPlaying();
	List<FilmModel> getAllFilmComing();
	List<FilmModel> getNextFilmPlaying(int number);
	List<FilmModel> getNextFilmComing(int number);
	List<FilmModel> searchFilm(String input);
}

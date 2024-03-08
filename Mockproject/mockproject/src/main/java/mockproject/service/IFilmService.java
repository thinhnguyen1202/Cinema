package mockproject.service;

import java.util.List;

import mockproject.model.FilmModel;

public interface IFilmService {

	List<FilmModel> listAllFilm();

	Integer saveFilm(FilmModel filmModel);

	void deleteFilm(int id);

	void deleteFilmOnCategoryFilmTable(int id);
	
	int getTheLastID();
	
	 FilmModel getFilmById(int id);
	 
	 void updateImagePath(int id, String path);
	 
	 void updateFilm(FilmModel filmModel);
	 
	 List<String> listAllFilmName();
	 FilmModel getIdFilmByName(String name);
	 
	 
	 List<FilmModel> getAllFilmPlaying();
	 List<FilmModel> getAllFilmComing();
	 List<FilmModel> getNextFilmPlaying(int number);
	 List<FilmModel> getNextFilmComing(int number);
	 List<FilmModel> searchFilm(String input);
}

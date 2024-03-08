package mockproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import mockproject.dao.IFilmDAO;
import mockproject.mapper.FilmMapper;
import mockproject.model.FilmModel;

@Repository
public class FilmDAO extends AbstractDAO<FilmModel> implements IFilmDAO {
	@Override
	public List<FilmModel> listAllFilm() {

		String sql = "select * from films";
		return query(sql, new FilmMapper());
	}

	@Override
	public int saveFilm(FilmModel filmModel) {
		String sql = "insert into films(name, actors, producer, duration, description, imagepath, created_at, updated_at)"
				+ "values" + "(?,?,?,?,?,?,?,?);";

		return insert(sql, filmModel.getName(), filmModel.getActors(), filmModel.getProducer(), filmModel.getDuration(),
				filmModel.getDescription(), filmModel.getImagepath(), filmModel.getCreated_at().toString(),
				filmModel.getUpdate_at().toString());
	}

	@Override
	public void deleteFilm(int id) {
		String sql = "delete from films where id = ?";
		delete(sql, id);
	}

	@Override
	public void deleteFilmOnCategoryFilmTable(int id) {
		String sql = "delete from category_film where film_id = ?";
		delete(sql, id);
	}

	@Override
	public int getTheLastID() {
		List<FilmModel> listFilm = listAllFilm();
		// take the last index of film
		FilmModel lastFilm = listFilm.get(listFilm.size() - 1);
		int maxIndex = lastFilm.getId();
		return maxIndex + 1;

	}

	@Override
	public FilmModel getFilmById(int id) {
		String sql = "select * from films where id = ?";
		List<FilmModel> listFilmQuery = query(sql, new FilmMapper(), id);
		FilmModel returnFilmModelInstance = listFilmQuery.get(0);

		return returnFilmModelInstance;
	}

	@Override
	public void updateImagePath(int id, String path) {
		String sql = "update films " + "set imagepath = ?;";

		update(sql, path);

	}

	@Override
	public void updateFilm(FilmModel filmModel) {
		String sql = "update films "
				+ "set name = ?, actors = ?, producer = ? , duration = ?, description = ?,"
				+ "imagepath = ?, created_at = ? , updated_at = ?"
				+ "where id = ?;";
		update(sql, filmModel.getName(), filmModel.getActors(), filmModel.getProducer(), filmModel.getDuration(),
				filmModel.getDescription(), filmModel.getImagepath(), filmModel.getCreated_at().toString(),
				filmModel.getUpdate_at().toString(), filmModel.getId());
	}

	@Override
	public List<String> listAllFilmName() {
		List<FilmModel> allFilm = listAllFilm();
		List<String> filmName = new ArrayList<>();
		for(int i = 0; i < allFilm.size(); i++) {
			filmName.add(allFilm.get(i).getName());
		}
		return filmName;
	}

	@Override
	public FilmModel getIdFilmByName(String name) {
		String sql = "select * from films where name = ?;";
		List<FilmModel> listFilm = query(sql, new FilmMapper(), name);
		if(listFilm != null && listFilm.size() > 0) {
			return listFilm.get(0);
		}
		return null;
	}
	
	@Override
	public List<FilmModel> getAllFilmPlaying() {
		String sql = "SELECT * FROM films WHERE created_at >= CURDATE() - INTERVAL 70 DAY and created_at <= (CURDATE() + INTERVAL 1 DAY) ORDER BY created_at DESC LIMIT 8";
		return query(sql, new FilmMapper());
	}
	
	@Override
	public List<FilmModel> getAllFilmComing() {
		String sql = "SELECT * FROM films WHERE created_at <= CURDATE() + INTERVAL 90 DAY and created_at > (CURDATE() + INTERVAL 1 DAY) LIMIT 8";
		return query(sql, new FilmMapper());
	}
	
	@Override
	public List<FilmModel> getNextFilmPlaying(int number) {
		String sql = "SELECT * FROM films WHERE created_at >= CURDATE() - INTERVAL 70 DAY and created_at <= (CURDATE() + INTERVAL 1 DAY) ORDER BY created_at DESC limit ? ,4;";
		return query(sql, new FilmMapper(), number);
	}
	
	@Override
	public List<FilmModel> getNextFilmComing(int number) {
		String sql = "SELECT * FROM films WHERE created_at <= CURDATE() + INTERVAL 90 DAY and created_at > (CURDATE() + INTERVAL 1 DAY) limit ? ,4;";
		return query(sql, new FilmMapper(), number);
	}
	@Override
	public List<FilmModel> searchFilm(String input) {
		String sql = "SELECT * FROM films WHERE name LIKE '%"+input+"%' or actors LIKE '%"+input+"%'";
		return query(sql, new FilmMapper());
	}
	
	
	
	
	
	
	

}

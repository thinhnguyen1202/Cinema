package mockproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mockproject.model.FilmModel;

public class FilmMapper implements RowMapper<FilmModel> {

	@Override
	public FilmModel mapRow(ResultSet resultSet) {
		
		try {
			FilmModel film = new FilmModel();
			film.setId(resultSet.getInt("id"));
			film.setName(resultSet.getString("name"));
			film.setActors(resultSet.getString("actors"));
			film.setProducer(resultSet.getString("producer"));
			film.setDuration(resultSet.getInt("duration"));
			film.setDescription(resultSet.getNString("description"));
			film.setImagepath(resultSet.getString("imagepath"));
			film.setCreated_at(resultSet.getDate("created_at"));
			film.setUpdate_at(resultSet.getTimestamp("updated_at"));
			return film;
		}catch (SQLException e) {
			return null;
		}
	}
	
	
	
}

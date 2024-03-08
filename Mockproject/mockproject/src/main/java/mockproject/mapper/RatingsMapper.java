package mockproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mockproject.model.RatingsModel;

public class RatingsMapper implements RowMapper<RatingsModel>{

	@Override
	public RatingsModel mapRow(ResultSet resultSet) {
		try {
			RatingsModel rating = new RatingsModel();
			rating.setId(resultSet.getInt("id"));
			rating.setFilm_id(resultSet.getInt("film_id"));
			rating.setRate(resultSet.getInt("rate"));
			return rating;
		}catch (SQLException e) {
			return null;
		}
	}

}

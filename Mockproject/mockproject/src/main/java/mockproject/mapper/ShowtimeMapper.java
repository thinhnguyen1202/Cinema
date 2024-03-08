package mockproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mockproject.model.ShowtimeModel;

public class ShowtimeMapper implements RowMapper<ShowtimeModel> {

	@Override
	public ShowtimeModel mapRow(ResultSet resultSet) {
		
		try {
			ShowtimeModel showtimeModel = new ShowtimeModel();
			showtimeModel.setId(resultSet.getInt("id"));
			showtimeModel.setFilm_id(resultSet.getInt("film_id"));
			showtimeModel.setRoom_id(resultSet.getInt("room_id"));
			showtimeModel.setStart_time(resultSet.getTimestamp("start_time"));
			showtimeModel.setEnd_time(resultSet.getTimestamp("end_time"));
			showtimeModel.setShowdate(resultSet.getDate("showdate"));
			showtimeModel.setCreated_date(resultSet.getTimestamp("created_date"));
			showtimeModel.setUpdated_date(resultSet.getTimestamp("updated_date"));
			showtimeModel.setShowtime_price(resultSet.getInt("showtime_price"));
			return showtimeModel;
		}catch(SQLException e) {
			return null;
		}
	}
	
	

}

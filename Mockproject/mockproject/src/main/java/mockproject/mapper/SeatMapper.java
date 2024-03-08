package mockproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mockproject.model.SeatModel;

public class SeatMapper implements RowMapper<SeatModel>{

	@Override
	public SeatModel mapRow(ResultSet resultSet) {
		try {
			 SeatModel model = new SeatModel();
			 model.setId(resultSet.getInt("id"));
			 model.setRoom_id(resultSet.getInt("room_id"));
			 model.setShowtime_id(resultSet.getInt("showtime_id"));
			 model.setName(resultSet.getString("name"));
			 int status = resultSet.getInt("status");
			 if(status == 1) {
				 model.setStatus(true);
			 }
			 return model;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		
	}
	
	
	
}

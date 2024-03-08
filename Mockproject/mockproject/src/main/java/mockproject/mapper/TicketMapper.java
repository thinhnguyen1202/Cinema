package mockproject.mapper;

import java.sql.ResultSet;	
import java.sql.SQLException;
import java.sql.Timestamp;
import mockproject.utils.DateTimeFormat;

import mockproject.model.TicketModel;

public class TicketMapper implements RowMapper<TicketModel> {

	@Override
	public TicketModel mapRow(ResultSet resultSet) {
		try {
			TicketModel model = new TicketModel();
			int ticket_id = resultSet.getInt("ticket_id");
			int showtime_id = resultSet.getInt("showtime_id");
			String film_name = resultSet.getString("film_name");
			int price = resultSet.getInt("price");
			String room_name = resultSet.getString("room_name");
			int seat_id = resultSet.getInt("seat_id");
			String seat_name = resultSet.getString("seat_name");
			String username = resultSet.getString("username");
			String user_fullname = resultSet.getString("user_fullname");
			Timestamp created_at = resultSet.getTimestamp("created_at");
			Timestamp start_time = resultSet.getTimestamp("start_time");
			
			model.setTicket_id(ticket_id);
			model.setShowtime_id(showtime_id);
			model.setFilm_name(film_name);
			model.setPrice(price);
			model.setRoom_name(room_name);
			model.setSeat_id(seat_id);
			model.setSeat_name(seat_name);
			model.setUsername(username);
			model.setUser_fullname(user_fullname);
			model.setCreated_at(created_at);
			model.setStart_time(start_time);
			
			model.setStart_time_string(start_time.toString());
			model.setCreated_at_string(created_at.toString());
			
			Timestamp now=DateTimeFormat.getNow();
			Timestamp hoursForCancle = DateTimeFormat.plusTheTimeStampValueByMinutes(created_at, 60);
			
			if(now.before(hoursForCancle)) {
				model.setCanCancel(1);
			}else {
				model.setCanCancel(0);
			}
			return model;
			
		}catch(SQLException e) {
			return null;
		}
	}
	
	

}

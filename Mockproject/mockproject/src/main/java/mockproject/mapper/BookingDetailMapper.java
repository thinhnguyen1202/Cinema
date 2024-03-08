package mockproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mockproject.model.BookingDetailModel;

public class BookingDetailMapper implements RowMapper<BookingDetailModel> {

	@Override
	public BookingDetailModel mapRow(ResultSet resultSet) {
		
		try {
			BookingDetailModel model = new BookingDetailModel();
			int id = resultSet.getInt("id");
			int booking_id = resultSet.getInt("booking_id");
			int tickets_id = resultSet.getInt("tickets_id");
			int seats_id = resultSet.getInt("seats_id");
			
			model.setId(id);
			model.setBooking_id(booking_id);
			model.setTickets_id(tickets_id);
			model.setSeats_id(seats_id);
			
			
			return model;
			
		}catch(SQLException e) {
			return null;
		}
	}
	
	
	

}

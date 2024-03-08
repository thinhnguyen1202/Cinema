package mockproject.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mockproject.dao.IBookingDetailDAO;
import mockproject.mapper.BookingDetailMapper;
import mockproject.model.BookingDetailModel;


@Repository
public class BookingDetailDAO extends AbstractDAO<BookingDetailModel> implements IBookingDetailDAO{

	@Override
	public BookingDetailModel getBookingDetailByTicketsID(int tick_id) {
		
		String sql = "select * from booking_details where tickets_id = ? limit 1" ;
		
		List<BookingDetailModel> list = query(sql, new BookingDetailMapper(), tick_id);
		
		if(list != null && list.size() != 0) {
			return list.get(0);
		}
		
		return null;
	}
	
	
	


	
	
	@Override
	public void deleteOneRowOnBookingDetails(int booking_details_id) {
		String sql = "delete from booking_details where id = ?";
		delete(sql, booking_details_id);
	}


	@Override
	public void deleteOneRowOnBooking(int booking_id) {
		String sql = "delete from bookings bookings where id = ?";
		delete(sql, booking_id);
	}
	
	
	
	
	

}

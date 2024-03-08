package mockproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mockproject.dao.IBookingDetailDAO;
import mockproject.model.BookingDetailModel;
import mockproject.service.IBookingDetailService;

@Service
public class BookingDetailService  implements IBookingDetailService{
	
	@Autowired
	IBookingDetailDAO bookingDetailDAO;
	@Override
	public BookingDetailModel getBookingDetailByTicketsID(int tick_id) {
		return bookingDetailDAO.getBookingDetailByTicketsID(tick_id);
	}
	
	
	@Override
	public void deleteOneRowOnBookingDetails(int booking_details_id) {
		bookingDetailDAO.deleteOneRowOnBookingDetails(booking_details_id);
	}
	@Override
	public void deleteOneRowOnBooking(int booking_id) {
		bookingDetailDAO.deleteOneRowOnBooking(booking_id);
	}
	
	

	
	
}

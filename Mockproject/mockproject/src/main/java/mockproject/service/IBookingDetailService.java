package mockproject.service;

import mockproject.model.BookingDetailModel;

public interface IBookingDetailService {
	
	BookingDetailModel getBookingDetailByTicketsID(int tick_id);
	
	void deleteOneRowOnBookingDetails(int booking_details_id);
	void deleteOneRowOnBooking(int booking_id);

}

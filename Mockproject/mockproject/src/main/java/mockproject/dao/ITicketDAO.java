package mockproject.dao;

import java.util.List;

import mockproject.model.TicketModel;

public interface ITicketDAO extends GenericDAO<TicketModel> {
	
	List<TicketModel> listALlTicket();
	
	int getCountOfBookings();
	
	
	// ham loi viet them
	List<TicketModel> listAllTickByUserId(int user_id);
	
	void deleteOneRow(int id);
	
	
	
	int insertTickets(int show_time_id);
	int insertBookings(int user_id);
	int insertBookingsDetail(int booking_id, int ticket_id, int seat_id);
	
	
}

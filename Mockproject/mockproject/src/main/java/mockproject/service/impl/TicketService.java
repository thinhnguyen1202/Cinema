package mockproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mockproject.dao.ITicketDAO;
import mockproject.model.TicketModel;
import mockproject.service.ITicketService;

@Service
public class TicketService implements ITicketService {

	@Autowired
	ITicketDAO ticketDAO;
	@Override
	public List<TicketModel> listALlTicket() {
		// TODO Auto-generated method stub
		return ticketDAO.listALlTicket();
	}
	@Override
	public int getCountOfBookings() {
		
		return ticketDAO.getCountOfBookings();
	}
	
	
	// ham loi viet them
	@Override
	public List<TicketModel> listAllTickByUserId(int user_id) {
		// TODO Auto-generated method stub
		return ticketDAO.listAllTickByUserId(user_id);
	}
	@Override
	public void deleteOneRow(int id) {
		ticketDAO.deleteOneRow(id);
	}
	@Override
	public int insertTickets(int show_time_id) {
		// TODO Auto-generated method stub
		
		return ticketDAO.insertTickets(show_time_id);
	}
	@Override
	public int insertBookings(int user_id) {
		// TODO Auto-generated method stub
		return ticketDAO.insertBookings(user_id);
	}
	@Override
	public int insertBookingsDetail(int booking_id, int ticket_id, int seat_id) {
		
		return  ticketDAO.insertBookingsDetail(booking_id, ticket_id, seat_id);
	}
	
	
	
	
	
	
	
	
	
	

	
}

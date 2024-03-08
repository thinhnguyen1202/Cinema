package mockproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import mockproject.dao.ITicketDAO;
import mockproject.mapper.TicketMapper;
import mockproject.model.TicketModel;
import mockproject.utils.DateTimeFormat;

@Repository
public class TicketDAO extends AbstractDAO<TicketModel> implements ITicketDAO {

	@Override
	public List<TicketModel> listALlTicket() {
		String sql = "select tk.id as ticket_id, st.id as showtime_id, st.start_time as start_time ,film.name as film_name, st.showtime_price as price, r.name as room_name,s.id as seat_id,s.name as seat_name,user.username as username,\r\n"
				+ "user.full_name as user_fullname, bk.created_at as created_at\r\n"
				+ "from tickets tk \r\n"
				+ "inner join showtimes st on tk.showtime_id = st.id\r\n"
				+ "inner join films film on st.film_id = film.id\r\n"
				+ "inner join rooms r on st.room_id = r.id\r\n"
				+ "inner join booking_details bkdt on tk.id = bkdt.tickets_id\r\n"
				+ "inner join seats s on bkdt.seats_id = s.id\r\n"
				+ "inner join bookings bk on bkdt.booking_id = bk.id\r\n"
				+ "inner join users user on bk.user_id = user.id\r\n"
				+ "where s.status = 1";
		List<TicketModel> listTicket = query(sql, new TicketMapper());
		return listTicket;
	}
	

	@Override
	public int getCountOfBookings() {
		String sql = "select count(*) as soluong from booking_details";	
		int result = 0;
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				result = rs.getInt("soluong");
				return result;
			}
		}catch (SQLException e) {
			return 0;
		}finally {
			closeConnection();
		}
		return 0;
	}

	@Override
	public List<TicketModel> listAllTickByUserId(int user_id) {
			String sql = "select tk.id as ticket_id, st.id as showtime_id,st.start_time as start_time, film.name as film_name, st.showtime_price as price, r.name as room_name,s.id as seat_id,s.name as seat_name,user.username as username,  \r\n"
					+ "					 user.full_name as user_fullname, bk.created_at as created_at  \r\n"
					+ "					 from tickets tk   \r\n"
					+ "					 inner join showtimes st on tk.showtime_id = st.id  \r\n"
					+ "					 inner join films film on st.film_id = film.id  \r\n"
					+ "					 inner join rooms r on st.room_id = r.id  \r\n"
					+ "					 inner join booking_details bkdt on tk.id = bkdt.tickets_id  \r\n"
					+ "					 inner join seats s on bkdt.seats_id = s.id  \r\n"
					+ "					 inner join bookings bk on bkdt.booking_id = bk.id  \r\n"
					+ "					 inner join users user on bk.user_id = user.id  \r\n"
					+ "					 where bk.user_id = ?";
			
			List<TicketModel> listTicketModelWithUser = query(sql, new TicketMapper(), user_id);
			return listTicketModelWithUser;
	}
	
	
	
	
	@Override
	public void deleteOneRow(int id) {
		String sql = "delete from tickets where id = ?";
		delete(sql, id);
	}


	public static void main(String[] args) {
		TicketDAO td = new TicketDAO();
		
		List<TicketModel> list = td.listALlTicket();
		
		TicketModel list0 = list.get(0);
		
		
		System.out.println(list.size());
	}


	@Override
	public int insertTickets(int show_time_id) {
		String sql = "insert into tickets(showtime_id) values (?)";
		
		return insert(sql, show_time_id); 
		
	}


	@Override
	public int insertBookings(int user_id) {
		String sql = "insert into bookings(user_id, created_at, updated_at) values (?,?,?)";
		Timestamp now = DateTimeFormat.getNow();
		
		return insert(sql, user_id, now, now);
	}


	@Override
	public int insertBookingsDetail(int booking_id, int ticket_id, int seat_id) {
		String sql = "insert into booking_details(booking_id, tickets_id, seats_id) values (?,?,?);";
		
		
		return insert(sql, booking_id, ticket_id, seat_id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}	

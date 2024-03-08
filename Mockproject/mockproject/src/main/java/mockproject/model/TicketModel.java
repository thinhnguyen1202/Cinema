package mockproject.model;

import java.sql.Timestamp;

public class TicketModel {

	private int ticket_id;
	private int showtime_id;
	private String film_name;
	private int price;
	private String room_name;
	private int seat_id;
	private String seat_name;
	private String username;
	private String user_fullname;
	private Timestamp created_at;
	private int canCancel;
	

	private Timestamp start_time;
	
	String created_at_string;
	
	String start_time_string;
	
	public TicketModel() {
		super();
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public int getShowtime_id() {
		return showtime_id;
	}

	public String getFilm_name() {
		return film_name;
	}

	public int getPrice() {
		return price;
	}

	public String getRoom_name() {
		return room_name;
	}

	public int getSeat_id() {
		return seat_id;
	}

	public String getSeat_name() {
		return seat_name;
	}

	public String getUsername() {
		return username;
	}

	public String getUser_fullname() {
		return user_fullname;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public void setShowtime_id(int showtime_id) {
		this.showtime_id = showtime_id;
	}

	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}

	public void setSeat_name(String seat_name) {
		this.seat_name = seat_name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public String getStart_time_string() {
		return start_time_string;
	}

	public void setStart_time_string(String start_time_string) {
		this.start_time_string = start_time_string;
	}

	public String getCreated_at_string() {
		return created_at_string;
	}

	public void setCreated_at_string(String created_at_string) {
		this.created_at_string = created_at_string;
	}

	public int getCanCancel() {
		return canCancel;
	}

	public void setCanCancel(int canCancel) {
		this.canCancel = canCancel;
	}
	
	
	
	
	
	
	
	
	
	
	

}

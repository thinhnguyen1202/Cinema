package mockproject.model;

public class BookingDetailModel {
	
	private int id; 
	private int booking_id; 
	private int tickets_id; 
	private int seats_id;
	
	
	public BookingDetailModel() {
		super();
	}


	public int getId() {
		return id;
	}


	public int getBooking_id() {
		return booking_id;
	}


	public int getTickets_id() {
		return tickets_id;
	}


	public int getSeats_id() {
		return seats_id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}


	public void setTickets_id(int tickets_id) {
		this.tickets_id = tickets_id;
	}


	public void setSeats_id(int seats_id) {
		this.seats_id = seats_id;
	}
	
	
	
	
	
	

}

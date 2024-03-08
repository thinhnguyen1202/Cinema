package mockproject.model;

public class SeatModel {
	private int id; 
	private int room_id; 
	private int showtime_id; 
	private String name; 
	private boolean status;
	
	
	public SeatModel() {
		super();
	}


	public int getId() {
		return id;
	}


	public int getRoom_id() {
		return room_id;
	}


	public int getShowtime_id() {
		return showtime_id;
	}


	public String getName() {
		return name;
	}


	public boolean isStatus() {
		return status;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}


	public void setShowtime_id(int showtime_id) {
		this.showtime_id = showtime_id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	
}

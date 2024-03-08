package mockproject.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ShowtimeModel {
	int id; 
	int film_id; 
	int room_id; 
	Timestamp start_time; 
	Timestamp end_time; 
	Date showdate; 
	Timestamp created_date; 
	Timestamp updated_date;
	int film_duration;
	String film_name;
	String room_name;
	String film_imagepath;
	String showdateString;
	String showDateString;
	String start_time_string;
	int showtime_price;
	Time start_time_time_type;
	
	
	
	public ShowtimeModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public int getFilm_id() {
		return film_id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public Date getShowdate() {
		return showdate;
	}

	public Timestamp getCreated_date() {
		return created_date;
	}

	public Timestamp getUpdated_date() {
		return updated_date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public void setShowdate(Date showdate) {
		this.showdate = showdate;
	}

	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

	public void setUpdated_date(Timestamp updated_date) {
		this.updated_date = updated_date;
	}

	public int getFilm_duration() {
		return film_duration;
	}

	public void setFilm_duration(int film_duration) {
		this.film_duration = film_duration;
	}

	public String getFilm_name() {
		return film_name;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getFilm_imagepath() {
		return film_imagepath;
	}

	public void setFilm_imagepath(String film_imagepath) {
		this.film_imagepath = film_imagepath;
	}

	public String getShowdateString() {
		return showdateString;
	}

	public void setShowdateString(String showdateString) {
		this.showdateString = showdateString;
	}

	public String getShowDateString() {
		return showDateString;
	}

	public String getStart_time_string() {
		return start_time_string;
	}

	public void setShowDateString(String showDateString) {
		this.showDateString = showDateString;
	}

	public void setStart_time_string(String start_time_string) {
		this.start_time_string = start_time_string;
	}

	public int getShowtime_price() {
		return showtime_price;
	}

	public void setShowtime_price(int showtime_price) {
		this.showtime_price = showtime_price;
	}

	public Time getStart_time_time_type() {
		return start_time_time_type;
	}

	public void setStart_time_time_type(Time start_time_time_type) {
		this.start_time_time_type = start_time_time_type;
	}
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

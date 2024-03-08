package mockproject.dao.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mockproject.dao.IShowtimeDAO;
import mockproject.mapper.ShowtimeMapper;
import mockproject.model.BookingDetailModel;
import mockproject.model.ShowtimeModel;
import mockproject.utils.DateFormater;

@Repository
public class ShowtimeDAO extends AbstractDAO<ShowtimeModel> implements IShowtimeDAO {

	@Override
	public List<ShowtimeModel> listAllShowTime() {
		String sql = "select s.id, f.id as film_id , r.id as room_id, s.start_time, s.end_time, s.showdate, s.created_date, s.updated_date, s.showtime_price\r\n"
				+ "from showtimes s\r\n" + "inner join films f on s.film_id = f.id\r\n"
				+ "inner join rooms r on s.room_id = r.id\r\n" + "order by s.showdate ";

		return query(sql, new ShowtimeMapper());
	}
	@Override
	public List<ShowtimeModel> listAllShowTimeNow() {
		String sql = "select s.id, f.id as film_id , r.id as room_id,r.name as room_name, s.start_time, s.end_time, s.showdate, s.created_date, s.updated_date, s.showtime_price\r\n"
				+ "from showtimes s\r\n" + "inner join films f on s.film_id = f.id\r\n"
				+ "inner join rooms r on s.room_id = r.id\r\n" + "where s.showdate=Date(now()) ";

		return query(sql, new ShowtimeMapper());
	}

	@Override
	public void deleteOneRow(int showtimeId) {
		String sql = "delete from showtimes where id = ?";
		delete(sql, showtimeId);
	}
	@Override
	public void deleteshowtimebyfilmid(int id) {
		String sql = "delete from showtimes where film_id = ?";
		delete(sql,id);
	}

	@Override
	public List<ShowtimeModel> listAllShowTimeWithDateAndRoomId(Date date, int room_id) {
		String sql = "select * from showtimes where showdate = '" + date.toString() + "' and room_id = ?";
		List<ShowtimeModel> listShowTime = query(sql, new ShowtimeMapper(), room_id);
		if (listShowTime != null) {
			return listShowTime;
		}
		return null;
	}

	@Override
	public List<ShowtimeModel> listAllShowTimeWithDateAndRoomIdButNotEquasToid(Date date, int room_id,
			int showtime_id) {
		String sql = "select * from showtimes where showdate = '" + date.toString() + "' and room_id = ? and id != ?;";
		List<ShowtimeModel> listShowTime = query(sql, new ShowtimeMapper(), room_id, showtime_id);
		if ((listShowTime != null && listShowTime.size() > 0) || (listShowTime != null && listShowTime.size() == 0)) {
			return listShowTime;
		}
		return null;
	}

	@Override
	public Integer insertoneRow(ShowtimeModel showtimeModel) {
		String sql = "insert into showtimes( film_id, room_id, start_time, end_time, showdate, created_date, updated_date, showtime_price)"
				+ "values (?,?,?,?,?,?,?,?);";

		return insert(sql, showtimeModel.getFilm_id(), showtimeModel.getRoom_id(), showtimeModel.getStart_time(),
				showtimeModel.getEnd_time(), showtimeModel.getShowdate(), showtimeModel.getCreated_date(),
				showtimeModel.getUpdated_date(), showtimeModel.getShowtime_price());

	}

	@Override
	public void updateWithId(ShowtimeModel model) {
		String sql = "update showtimes " + "set film_id = ?," + "room_id = ?," + "start_time = ?," + "end_time = ?,"
				+ "showdate =?," + "showtime_price = ?," + "updated_date = ?" + "where id = ?;";
		update(sql, model.getFilm_id(), model.getRoom_id(), model.getStart_time(), model.getEnd_time(),
				model.getShowdate(), model.getShowtime_price(), model.getUpdated_date(), model.getId());

	}

	@Override
	public ShowtimeModel getShowTimeWithId(int id) {
		String sql = "select * from showtimes where id = ? limit 1";
		List<ShowtimeModel> listShowTime = query(sql, new ShowtimeMapper(), id);
		if ((listShowTime != null && listShowTime.size() > 0)) {
			return listShowTime.get(0);
		}
		return null;
	}

	public static void main(String[] args) {
		ShowtimeDAO st = new ShowtimeDAO();
		Date date = DateFormater.toSqlDate("2023-05-05");
		List<ShowtimeModel> hehe = st.listAllShowtimeWithDate(date);
		System.out.println(hehe.size());

	}

	@Override
	//public List<ShowtimeModel> listAllShowtimeWithDate(Date date) {
		//String sql = "select * from showtimes where showdate = ?";
		//List<ShowtimeModel> listShowtime = query(sql, new ShowtimeMapper(), date);
		//return listShowtime;
	//}
	public List<ShowtimeModel> listAllShowtimeWithDate(Date date) {
	String sql = "select s.id , f.id as film_id , r.id as room_id, s.start_time, s.end_time, s.showdate, s.created_date, s.updated_date, s.showtime_price\r\n"
			+ "from showtimes s \r\n" + "inner join films f on s.film_id = f.id\r\n"
			+ "inner join rooms r on s.room_id = r.id\r\n" + "where s.showdate=? ";

	List<ShowtimeModel> listShowtime = query(sql, new ShowtimeMapper(), date);
	return listShowtime;
}
	@Override
	public List<ShowtimeModel> listAllShowtimeWithFilmIdandDate(Date date,int id) {
		String sql = "select s.id, f.id as film_id , r.id as room_id, s.start_time, s.end_time, s.showdate, s.created_date, s.updated_date, s.showtime_price\r\n"
				+ "from showtimes s\r\n" + "inner join films f on s.film_id = f.id\r\n"
				+ "inner join rooms r on s.room_id = r.id\r\n" + "where s.showdate=? and film_id=?";

		List<ShowtimeModel> listShowtime = query(sql, new ShowtimeMapper(), date,id);
		return listShowtime;
	}

	@Override
	public List<ShowtimeModel> listAllShowTimeForWeek(Date date1, Date date2) {
		String sql = "select * from showtimes where (showdate between ? and ?);";
		List<ShowtimeModel> listShowtime = query(sql, new ShowtimeMapper(), date1, date2);
		return listShowtime;
	}


	@Override
	public List<ShowtimeModel> listShowtimesEnableBookingByFimId(int id) {
		String sql = "select * from showtimes join films on films.id = showtimes.film_id join rooms on rooms.id = showtimes.room_id where start_time > CURDATE() and start_time <= (CURDATE() + INTERVAL 5 DAY)  and showtimes.film_id = ? order by start_time";
		List<ShowtimeModel> listShowtime = query(sql, new ShowtimeMapper(), id);
		System.out.println("size" + listShowtime.size());
		return listShowtime;
	}

}

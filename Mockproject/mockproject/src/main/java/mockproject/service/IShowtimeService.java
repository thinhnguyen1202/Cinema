package mockproject.service;

import java.sql.Date;
import java.util.List;

import mockproject.model.ShowtimeModel;

public interface IShowtimeService {

	List<ShowtimeModel> listAllShowTime();
	
	List<ShowtimeModel> listAllShowTimeNow();

	void deleteOneRow(int showtimeId);
	//mới
	void deleteshowtimebyfilmid(int id);

	List<ShowtimeModel> listAllShowTimeWithDateAndRoomId(Date date, int room_id);

	Integer insertoneRow(ShowtimeModel showtimeModel);

	void updateWithId(ShowtimeModel model);

	List<ShowtimeModel> listAllShowTimeWithDateAndRoomIdButNotEquasToid(Date date, int room_id, int showtime_id);

	ShowtimeModel getShowTimeWithId(int id);

	List<ShowtimeModel> listAllShowtimeWithDate(Date date);
	
	List<ShowtimeModel> listAllShowtimeWithFilmIdandDate(Date date,int id);

	List<ShowtimeModel> listAllShowTimeForWeek(Date date1, Date date2);

	List<ShowtimeModel> listShowtimesEnableBookingByFimId(int id);

}

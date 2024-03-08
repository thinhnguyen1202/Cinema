package mockproject.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mockproject.dao.IShowtimeDAO;
import mockproject.model.ShowtimeModel;
import mockproject.service.IShowtimeService;

@Service
public class ShowtimeService implements IShowtimeService {
	
	@Autowired
	IShowtimeDAO showTimeDAO;

	@Override
	public List<ShowtimeModel> listAllShowTime() {
		return showTimeDAO.listAllShowTime();
	}
	
	@Override
	public List<ShowtimeModel> listAllShowTimeNow() {
		return showTimeDAO.listAllShowTimeNow();
	}

	@Override
	public void deleteOneRow(int showtimeId) {
		showTimeDAO.deleteOneRow(showtimeId);;
	}
//mới
	@Override
	public void deleteshowtimebyfilmid(int id) {
		showTimeDAO.deleteshowtimebyfilmid(id);;
	}
	@Override
	public List<ShowtimeModel> listAllShowTimeWithDateAndRoomId(Date date, int room_id) {
		return showTimeDAO.listAllShowTimeWithDateAndRoomId(date, room_id);
	}

	@Override
	public Integer insertoneRow(ShowtimeModel showtimeModel) {
		return showTimeDAO.insertoneRow(showtimeModel);
	}

	@Override
	public void updateWithId(ShowtimeModel model) {
		showTimeDAO.updateWithId(model);
	}

	@Override
	public List<ShowtimeModel> listAllShowTimeWithDateAndRoomIdButNotEquasToid(Date date, int room_id,
			int showtime_id) {
		return showTimeDAO.listAllShowTimeWithDateAndRoomIdButNotEquasToid(date, room_id, showtime_id);
	}

	@Override
	public ShowtimeModel getShowTimeWithId(int id) {
		// TODO Auto-generated method stub
		return showTimeDAO.getShowTimeWithId(id);
	}

	@Override
	public List<ShowtimeModel> listAllShowtimeWithDate(Date date) {
		// TODO Auto-generated method stub
		return showTimeDAO.listAllShowtimeWithDate(date);
	}
	
	@Override
	public List<ShowtimeModel> listAllShowtimeWithFilmIdandDate(Date date,int id){
		// TODO Auto-generated method stub
		return showTimeDAO.listAllShowtimeWithFilmIdandDate(date, id);
	}
	

	@Override
	public List<ShowtimeModel> listAllShowTimeForWeek(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return showTimeDAO.listAllShowTimeForWeek(date1, date2);
	}
	
	@Override
	public
	List<ShowtimeModel> listShowtimesEnableBookingByFimId(int id) {
		// TODO Auto-generated method stub
		return showTimeDAO.listShowtimesEnableBookingByFimId(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

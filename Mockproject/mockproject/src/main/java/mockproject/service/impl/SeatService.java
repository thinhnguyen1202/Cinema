package mockproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mockproject.dao.ISeatDAO;
import mockproject.model.SeatModel;
import mockproject.service.ISeatService;

@Service
public class SeatService implements ISeatService {

	@Autowired
	ISeatDAO seatDAO;

	@Override
	public void deleteByShowtimeid(int showtime_id) {
		seatDAO.deleteByShowtimeid(showtime_id);
	}

	@Override
	public void insertSeatsWithRoomAndShowTime(int showtime_id, int room_id, int row, int col) {
		seatDAO.insertSeatsWithRoomAndShowTime(showtime_id, room_id, row, col);
	}

	@Override
	public int countingBookedSeatWithThatShowTimeId(int showtime_id) {
		return seatDAO.countingBookedSeatWithThatShowTimeId(showtime_id);
	}

	@Override
	public void updateSeatStatus(int seat_id) {
		seatDAO.updateSeatStatus(seat_id);
	}
	
	

	// toai

	@Override
	public void updateForBookedSeat(int seat_id) {
		seatDAO.updateForBookedSeat(seat_id);
	}

	@Override
	public List<SeatModel> listAllSeatByShowtimeid(int showtime_id) {

		return seatDAO.listAllSeatByShowtimeid(showtime_id);
	}

	@Override
	public SeatModel getSeatById(int id) {

		return seatDAO.getSeatById(id);
	}

}

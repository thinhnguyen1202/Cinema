package mockproject.dao;

import java.util.List;

import mockproject.model.SeatModel;

public interface ISeatDAO extends GenericDAO<SeatModel> {

	List<SeatModel> listAllSeatByShowtimeid(int showtime_id);

	void deleteByShowtimeid(int showtime_id);

	void insertSeatsWithRoomAndShowTime(int showtime_id, int room_id, int row, int col);

	int countingBookedSeatWithThatShowTimeId(int showtime_id);

	void updateSeatStatus(int seat_id);
	
	void updateForBookedSeat(int seat_id);

	SeatModel getSeatById(int id);

}

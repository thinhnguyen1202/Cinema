package mockproject.service;

import java.util.List;

import mockproject.model.SeatModel;

public interface ISeatService {

	void deleteByShowtimeid(int showtime_id);

	void insertSeatsWithRoomAndShowTime(int showtime_id, int room_id, int row, int col);

	int countingBookedSeatWithThatShowTimeId(int showtime_id);

	// ham loi viet them

	void updateSeatStatus(int seat_id);
	
	void updateForBookedSeat(int seat_id);

	
	//TOai
	List<SeatModel> listAllSeatByShowtimeid(int showtime_id);

	SeatModel getSeatById(int id);

}

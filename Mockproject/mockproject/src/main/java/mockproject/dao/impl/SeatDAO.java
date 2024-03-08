package mockproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import mockproject.dao.ISeatDAO;
import mockproject.mapper.SeatMapper;
import mockproject.model.SeatModel;

@Repository
public class SeatDAO extends AbstractDAO<SeatModel> implements ISeatDAO {

	@Override
	public List<SeatModel> listAllSeatByShowtimeid(int showtime_id) {
		// TODO Auto-generated method stub
		String sql = "select * from seats where showtime_id = ?";
		return query(sql, new SeatMapper(), showtime_id);
	}

	@Override
	public void deleteByShowtimeid(int showtime_id) {
		// TODO Auto-generated method stub
		String sql = "delete from seats where showtime_id = ?";
		delete(sql, showtime_id);

	}

	@Override
	public void insertSeatsWithRoomAndShowTime(int showtime_id, int room_id, int row, int col) {
		insertSeats(showtime_id, room_id, row, col);
	}

	public void insertSeats(int showtime_id, int room_id, int row, int col) {

		String sql = "insert into seats(room_id,showtime_id,name) values (?,?,?);";
		char rowName = 'A';

		for (int i = 0; i < col; i++) {

			for (int j = 0; j < row; j++) {
				StringBuilder name = new StringBuilder(Character.toString(rowName));
				name.append(j + 1);
				insert(sql, room_id, showtime_id, name.toString());
			}
			rowName++;
		}
	}

	@Override
	public int countingBookedSeatWithThatShowTimeId(int showtime_id) {
		String sql = "select count(*) as soluong from seats where showtime_id = ? and status = 1";
		int result = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement statment = conn.prepareStatement(sql);
			setParameters(statment, showtime_id);
			ResultSet rs = statment.executeQuery();
			if (rs.next()) {
				result = rs.getInt("soluong");

			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			closeConnection();
		}
	}
	
	@Override
	public void updateSeatStatus(int seat_id) {
		
		String sql = "update seats set seats.status = 0 where id = ?";
		update(sql, seat_id);
	}
	

	@Override
	public void updateForBookedSeat(int seat_id) {
		String sql = "update seats set seats.status = 1 where id = ?";
		update(sql, seat_id);
	}

	public static void main(String... strings) {
		
		SeatDAO st = new SeatDAO();
		System.out.println(st.countingBookedSeatWithThatShowTimeId(18));
	}
	
	
	@Override
	public SeatModel getSeatById(int id) {
		String sql = "SELECT * from seats where id = ?";
		List<SeatModel> list = query(sql, new SeatMapper(), id);
		return list.get(0);
	}

}
